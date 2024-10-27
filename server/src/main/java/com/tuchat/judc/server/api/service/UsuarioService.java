package com.tuchat.judc.server.api.service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;
import com.tuchat.judc.server.api.dto.request.ConfirmarCorreoDTO;
import com.tuchat.judc.server.api.exception.UsuarioExistenteException;
import com.tuchat.judc.server.mapper.UsuarioMapper;
import com.tuchat.judc.server.model.CodigoVerificacion;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.CodigoVerificacionRepository;
import com.tuchat.judc.server.repository.UsuarioRepository;
import com.tuchat.judc.server.util.AutenticacionUtil;

@Service
public class UsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class); 

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;

    @Autowired
    private UsuarioValidatorService usuarioValidatorService;

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByCorreo(usuarioDTO.getCorreo())) {
            throw new UsuarioExistenteException("El correo ya está registrado.");
        }

        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        crearNuevoCodigoVerificacion(usuarioDTO.getCorreo());

        return UsuarioMapper.toDTO(usuario);
    }

    public boolean confirmarCorreo(ConfirmarCorreoDTO confirmarCorreoDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(confirmarCorreoDTO.getCorreo());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado.");
        }

        Optional<CodigoVerificacion> codigoVerificacionOpt = codigoVerificacionRepository
            .findCodigoVerificacionReciente(usuario.getId(), PageRequest.of(0, 1))
            .stream().findFirst();

        if (codigoVerificacionOpt.isEmpty() || codigoVerificacionOpt.get().getUsado() 
                || !codigoVerificacionOpt.get().getCodigo().equals(confirmarCorreoDTO.getCodigo())) {
            usuarioValidatorService.guardarIntentoFallido(usuario, false, confirmarCorreoDTO.getCodigo(), "Código inválido o ya usado", codigoVerificacionOpt.orElse(null));
            return false;
        }

        CodigoVerificacion codigoVerificacion = codigoVerificacionOpt.get();
        usuarioValidatorService.procesarCodigosNoUsados(usuario.getId());

        codigoVerificacion.setUsado(true);
        codigoVerificacion.setComprobado(true);
        codigoVerificacionRepository.save(codigoVerificacion);

        usuario.setCorreoConfirmado(true);
        usuarioRepository.save(usuario);

        usuarioValidatorService.guardarIntentoExitoso(usuario, false, confirmarCorreoDTO.getCodigo(), "", codigoVerificacion);

        return true;
    }

    public void crearNuevoCodigoVerificacion(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            logger.error("Usuario no encontrado para el correo: {}", correo);
            return;
        }

        long usadosNoComprobadosUltimas24Horas = codigoVerificacionRepository
            .countUsadosNoComprobadosUltimas24Horas(usuario.getId(), 
                Timestamp.valueOf(LocalDateTime.now().minusHours(24)));

        if (usadosNoComprobadosUltimas24Horas >= 7) {
            logger.warn("Demasiados códigos usados no comprobados para el usuario: {}", correo);
            return;
        }

        String nuevoCodigo = AutenticacionUtil.generarCodigoValidacion(10);
        CodigoVerificacion codigoVerificacion = CodigoVerificacion.builder()
            .usuario(usuario)
            .codigo(nuevoCodigo)
            .usado(false)
            .comprobado(false)
            .build();

        codigoVerificacionRepository.save(codigoVerificacion);
        logger.info("Nuevo código de verificación generado para el usuario: {}", correo);
    }
}

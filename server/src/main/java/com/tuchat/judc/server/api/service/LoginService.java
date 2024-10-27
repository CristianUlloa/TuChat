package com.tuchat.judc.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;
import com.tuchat.judc.server.api.dto.request.LoginDTO;
import com.tuchat.judc.server.mapper.UsuarioMapper;
import com.tuchat.judc.server.model.CodigoVerificacion;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.CodigoVerificacionRepository;
import com.tuchat.judc.server.repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;

    @Autowired
    private UsuarioValidatorService usuarioValidatorService;

    public UsuarioDTO verificarCredenciales(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreo(loginDTO.getCorreo());
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado.");
        }

        if (loginDTO.isUsarContrasenia()) {
            verificarCredencialesConContrasenia(usuario, loginDTO);
        } else {
            verificarCredencialesConCodigo(usuario, loginDTO);
        }

        return UsuarioMapper.toDTO(usuario);
    }

    private void verificarCredencialesConContrasenia(Usuario usuario, LoginDTO loginDTO) {
        usuarioValidatorService.validarCorreo(usuario);
        usuarioValidatorService.validarOpcionContrasenia(usuario);
        usuarioValidatorService.validarIntentosFallidos(usuario);

        if (!usuario.getContrasenia().equals(loginDTO.getContrasenia())) {
            usuarioValidatorService.guardarIntentoFallido(usuario, true, loginDTO.getContrasenia(), "Contraseña incorrecta.", null);
            throw new RuntimeException("Contraseña incorrecta.");
        }

        usuarioValidatorService.guardarIntentoExitoso(usuario, true, loginDTO.getContrasenia(), "", null);
    }

    private void verificarCredencialesConCodigo(Usuario usuario, LoginDTO loginDTO) {
        usuarioValidatorService.validarCorreo(usuario);
        usuarioValidatorService.validarOpcionSinContrasenia(usuario);
        usuarioValidatorService.validarIntentosFallidos(usuario);

        CodigoVerificacion codigoVerificacion = codigoVerificacionRepository
            .findCodigoVerificacionPorUsuario(usuario.getId(), loginDTO.getCodigo());

        if (codigoVerificacion == null || codigoVerificacion.getUsado()) {
            usuarioValidatorService.guardarIntentoFallido(usuario, false, loginDTO.getCodigo(), "Código de verificación inválido o ya usado", codigoVerificacion);
            throw new RuntimeException("Código de verificación inválido o ya usado");
        }

        codigoVerificacion.setUsado(true);
        codigoVerificacion.setComprobado(true);

        codigoVerificacionRepository.save(codigoVerificacion);
        usuarioValidatorService.guardarIntentoExitoso(usuario, false, loginDTO.getCodigo(), "", codigoVerificacion);
    }

    public UsuarioDTO getUsuarioByCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        return UsuarioMapper.toDTO(usuario);
    }
}

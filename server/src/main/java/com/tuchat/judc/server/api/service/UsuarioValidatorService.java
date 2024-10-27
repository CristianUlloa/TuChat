package com.tuchat.judc.server.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.model.CodigoVerificacion;
import com.tuchat.judc.server.model.RegistroInicioSesion;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.CodigoVerificacionRepository;
import com.tuchat.judc.server.repository.RegistroInicioSesionRepository;

@Service
public class UsuarioValidatorService {

    @Autowired
    private RegistroInicioSesionRepository registroInicioSesionRepository;

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;

    public void validarCorreo(Usuario usuario) {
        if (!usuario.getCorreoConfirmado()) {
            throw new RuntimeException("El correo no está confirmado.");
        }
    }

    public void validarOpcionSinContrasenia(Usuario usuario) {
        if (usuario.getUsarContrasenia()) {
            throw new RuntimeException("La opción de usar contraseña está activa, se debe usar contraseña.");
        }
    }

    public void validarIntentosFallidos(Usuario usuario) {
        long fallidosUltimas24Horas = registroInicioSesionRepository.countFallidosUltimas24Horas(
            usuario.getId(), Timestamp.valueOf(LocalDateTime.now().minusHours(24))
        );

        if (fallidosUltimas24Horas >= 7) {
            throw new RuntimeException("Demasiados intentos fallidos en las últimas 24 horas.");
        }
    }

    public void guardarIntentoFallido(Usuario usuario, boolean isPass, String valor, String des, CodigoVerificacion cv) {
        RegistroInicioSesion registro = crearRegistro(usuario, false, isPass, valor, des, cv);
        registroInicioSesionRepository.save(registro);
    }

    public void guardarIntentoExitoso(Usuario usuario, boolean isPass, String valor, String des, CodigoVerificacion cv) {
        RegistroInicioSesion registro = crearRegistro(usuario, true, isPass, valor, des, cv);
        registroInicioSesionRepository.save(registro);
    }

    private RegistroInicioSesion crearRegistro(Usuario usuario, boolean exitoso, boolean isPass, String valor, String des, CodigoVerificacion cv) {
        return RegistroInicioSesion.builder()
            .usuario(usuario)
            .fechaRegistro(Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
            .exitoso(exitoso)
            .valor(valor)
            .usaContrasenia(isPass)
            .descripcion(des)
            .codigoVerificacion(cv)
            .build();
    }

    public void validarOpcionContrasenia(Usuario usuario) {
        if (!usuario.getUsarContrasenia()) {
            throw new RuntimeException("La opción de usar contraseña está desactivada.");
        }
    }

    public void procesarCodigosNoUsados(Integer usuarioId) {
        List<CodigoVerificacion> codigos = codigoVerificacionRepository.findCodigosNoUsadosPorUsuario(usuarioId);

        if (!codigos.isEmpty()) {
            codigos.forEach(codigo -> {
                codigo.setUsado(true);
                codigoVerificacionRepository.save(codigo); // Guardar cada código modificado
            });
        } else {
            System.out.println("No hay códigos no usados y no comprobados.");
        }
    }
}

package com.tuchat.judc.server.api.controller;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;
import com.tuchat.judc.server.api.dto.request.ConfirmarCorreoDTO;
import com.tuchat.judc.server.api.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/confirmar-correo")
    public ResponseEntity<String> confirmarCorreo(@RequestBody ConfirmarCorreoDTO confirmarCorreoDTO,
                                                  HttpServletRequest request) {
        boolean confirmado = usuarioService.confirmarCorreo(confirmarCorreoDTO);
        if (confirmado) {
            HttpSession session = request.getSession();
            session.setAttribute(LoginController.USER_CORREO, confirmarCorreoDTO.getCorreo());
            return ResponseEntity.ok("Correo confirmado con éxito.");
        } else {
            return ResponseEntity.badRequest().body("Código de verificación inválido o ya usado.");
        }
    }

    @PostMapping("/nuevo-codigo")
    public ResponseEntity<String> crearCodigoVerificacion(@RequestParam String correo) {
        try {
            usuarioService.crearNuevoCodigoVerificacion(correo);
            return ResponseEntity.ok("Código de verificación creado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

package com.tuchat.judc.server.api.controller;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;
import com.tuchat.judc.server.api.dto.request.LoginDTO;
import com.tuchat.judc.server.api.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    public static final String USER_CORREO = "user_correo";

    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        try {
            UsuarioDTO usuarioDTO = loginService.verificarCredenciales(loginDTO);

            // Guardar el usuario en la sesión
            session.setAttribute(LoginController.USER_CORREO, usuarioDTO.getCorreo());
            return ResponseEntity.ok("Login exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout exitoso");
    }

    @GetMapping("/status")
    public ResponseEntity<UsuarioDTO> getSessionStatus(HttpSession session) {
        String userCorreo = (String) session.getAttribute(USER_CORREO);

        if (userCorreo != null) {
            UsuarioDTO usuarioDTO = loginService.getUsuarioByCorreo(userCorreo);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

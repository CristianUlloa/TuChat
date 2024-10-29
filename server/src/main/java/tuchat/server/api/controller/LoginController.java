package tuchat.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.AuthCodigoDTO;
import tuchat.server.api.dto.request.AuthPasswDTO;
import tuchat.server.api.dto.request.CrearCuentaDTO;
import tuchat.server.api.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/passw")
    public ResponseEntity<?> loginWithPassword(@RequestBody AuthPasswDTO auth, HttpSession session) {
        boolean isAuthenticated = loginService.login(auth, session);
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Inicio de sesión exitoso con contraseña.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
    @PostMapping("/codigo")
    public ResponseEntity<?> loginWithCode(@RequestBody AuthCodigoDTO auth, HttpSession session) {
        boolean isAuthenticated = loginService.login(auth, session);
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Inicio de sesión exitoso con código.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
    
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        
    	loginService.logout(session);
        
        return ResponseEntity.ok("Cierre de sesión exitoso.");

    }
    
    @PostMapping("/crearCuenta")
    public ResponseEntity<?> crearCuenta(@RequestBody CrearCuentaDTO nuevaCuenta, HttpSession session) {
        boolean creado = loginService.crearCuenta(nuevaCuenta, session);
        
        if (creado) {
            return ResponseEntity.ok("Su cuenta fue creada con exito, hemos enviado el codigo de verificacion a su correo.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se a podido crear la cuenta, correo ya esta en uso.");
        }
    }
}

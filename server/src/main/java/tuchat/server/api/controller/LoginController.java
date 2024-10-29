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
import tuchat.server.api.dto.request.PedirNuevoCodigoDTO;
import tuchat.server.api.dto.response.ObtenerLoginStatus;
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
			return ResponseEntity.ok("Inicio de sesión exitoso. ¡Bienvenido de nuevo!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Error: Las credenciales ingresadas no son válidas. Por favor, verifica tu contraseña.");
		}
	}

	@PostMapping("/codigo")
	public ResponseEntity<?> loginWithCode(@RequestBody AuthCodigoDTO auth, HttpSession session) {
		boolean isAuthenticated = loginService.login(auth, session);

		if (isAuthenticated) {
			return ResponseEntity.ok("Inicio de sesión exitoso utilizando código. ¡Bienvenido de nuevo!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Error: El código ingresado no es válido. Por favor, intenta nuevamente.");
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {

		loginService.logout(session);

		return ResponseEntity.ok("Has cerrado sesión exitosamente. ¡Hasta luego!");
	}

	@GetMapping("/status")
	public ResponseEntity<?> status(HttpSession session) {

		ObtenerLoginStatus status = loginService.status(session);

		if (status != null) {
			return ResponseEntity.ok(status); // Responde con el estado de login del usuario
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No has iniciado sesión"); // Usuario no
																									// autenticado
		}
	}

	@PostMapping("/crearCuenta")
	public ResponseEntity<?> crearCuenta(@RequestBody CrearCuentaDTO nuevaCuenta, HttpSession session) {
		boolean creado = loginService.crearCuenta(nuevaCuenta, session);

		if (creado) {
			return ResponseEntity
					.ok("Cuenta creada con éxito. Hemos enviado un código de verificación a tu correo electrónico.");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("No se pudo crear la cuenta. El correo electrónico ya está en uso.");
		}
	}

	@PostMapping("/confirmarCorreo")
	public ResponseEntity<?> confirmarCorreo(@RequestBody AuthCodigoDTO auth, HttpSession session) {
		boolean confirm = loginService.confirmarCorreo(auth, session);

		if (confirm) {
			return ResponseEntity.ok("Correo confirmado exitosamente. Ahora puedes iniciar sesión.");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
					"Error: El código de verificación ingresado es incorrecto. Por favor, revisa tu correo electrónico e intenta nuevamente.");
		}
	}

	@PostMapping("/pedirNuevoCodigo")
	public ResponseEntity<?> pedirNuevoCodigo(@RequestBody PedirNuevoCodigoDTO nuevoCodigo, HttpSession session) {
		boolean confirm = loginService.pedirNuevoCodigo(nuevoCodigo);

		if (confirm) {
			return ResponseEntity.ok("Un nuevo código de verificación ha sido enviado a tu correo electrónico.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No se pudo enviar el nuevo código. Por favor, verifica la información proporcionada.");
		}
	}
}

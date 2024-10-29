package tuchat.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.InfoContactos;
import tuchat.server.api.dto.request.InfoNoContactos;
import tuchat.server.api.dto.response.ObtenerContactosDTO;
import tuchat.server.api.service.InfoService;

@RestController
@RequestMapping("/info")
@CrossOrigin(origins = "*") // Permitir todos los orígenes
public class InfoController {

	@Autowired
	private InfoService infoService;

	@PostMapping("/infoContactos")
	public ResponseEntity<?> infoContactos(@RequestBody InfoContactos contactos, HttpSession session) {

		ObtenerContactosDTO contactosDTO = infoService.infoContactos(session);

		if (contactosDTO != null) {
			return ResponseEntity.ok(contactosDTO); // Responde con el estado de login del usuario
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No has iniciado sesión"); // Usuario no
																									// autenticado
		}
	}
	
	@PostMapping("/infoNoContactos")
	public ResponseEntity<?> infoNoContactos(@RequestBody InfoNoContactos contactos, HttpSession session) {

		ObtenerContactosDTO contactosDTO = infoService.infoNoContactos(session);

		if (contactosDTO != null) {
			return ResponseEntity.ok(contactosDTO); // Responde con el estado de login del usuario
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No has iniciado sesión"); // Usuario no
																									// autenticado
		}
	}
}

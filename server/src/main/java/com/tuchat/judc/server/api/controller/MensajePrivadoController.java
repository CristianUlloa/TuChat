package com.tuchat.judc.server.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuchat.judc.server.api.dto.request.data.MensajeDataDTO;
import com.tuchat.judc.server.api.dto.response.MensajeDTO;
import com.tuchat.judc.server.api.service.MensajePrivadoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/mensaje-privado")
public class MensajePrivadoController {

	@Autowired
	private MensajePrivadoService mensajePrivadoService;

	@PostMapping("/enviar")
	public ResponseEntity<String> enviarMensaje(@RequestBody MensajeDataDTO mensajeDataDTO, HttpSession session) {

		String userCorreo = (String) session.getAttribute(LoginController.USER_CORREO);

		if (userCorreo != null) {
			// Verificar si el emisor y el receptor son el mismo
			if (userCorreo.equals(mensajeDataDTO.getCorreoReceptor())) {
				return ResponseEntity.status(400).body("No puedes enviarte mensajes a ti mismo.");
			}

			try {
				mensajeDataDTO.setCorreoEmisor(userCorreo);
				mensajePrivadoService.enviarMensaje(mensajeDataDTO);
				return ResponseEntity.ok("Mensaje enviado exitosamente.");
			} catch (Exception e) {
				return ResponseEntity.status(400).body(e.getMessage());
			}
		} else {
			return ResponseEntity.status(401).build(); // Retorna un error 401 si no hay usuario en sesión
		}
	}

	@GetMapping("/obtener")
	public ResponseEntity<List<MensajeDTO>> obtenerMensajes(@RequestParam String correoReceptor, HttpSession session) {

		String userCorreo = (String) session.getAttribute(LoginController.USER_CORREO);

		if (userCorreo != null) {
			List<MensajeDTO> mensajes = mensajePrivadoService.obtenerMensajesEntreUsuarios(userCorreo, correoReceptor);
			return ResponseEntity.ok(mensajes);
		} else {
			return ResponseEntity.status(401).build(); // Retorna un error 401 si no hay usuario en sesión
		}
	}
}

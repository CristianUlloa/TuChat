package com.tuchat.judc.server.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuchat.judc.server.api.dto.request.CrearGrupoDTO;
import com.tuchat.judc.server.api.dto.request.EnviarMensajeGrupoDTO;
import com.tuchat.judc.server.api.dto.request.data.MensajeDataDTO;
import com.tuchat.judc.server.api.dto.response.GrupoDTO;
import com.tuchat.judc.server.api.service.GrupoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@GetMapping("/owner")
	public ResponseEntity<List<GrupoDTO>> obtenerGruposComoOwner(HttpSession session) {
		String userCorreo = validarLogiado(session);
		List<GrupoDTO> grupos = grupoService.obtenerGruposComoOwner(userCorreo);
		return ResponseEntity.ok(grupos);
	}

	@GetMapping("/admin")
	public ResponseEntity<List<GrupoDTO>> obtenerGruposComoAdmin(HttpSession session) {
		String userCorreo = validarLogiado(session);
		List<GrupoDTO> grupos = grupoService.obtenerGruposComoAdmin(userCorreo);
		return ResponseEntity.ok(grupos);
	}

	@GetMapping("/miembro")
	public ResponseEntity<List<GrupoDTO>> obtenerGruposComoMiembro(HttpSession session) {
		String userCorreo = validarLogiado(session);
		List<GrupoDTO> grupos = grupoService.obtenerGruposComoMiembro(userCorreo);
		return ResponseEntity.ok(grupos);
	}

	@PostMapping("/crear")
	public ResponseEntity<String> crearGrupo(@RequestBody CrearGrupoDTO crearGrupoDTO, HttpSession session) {
		String userCorreo = validarLogiado(session);
		grupoService.crearGrupo(crearGrupoDTO, userCorreo);
		return ResponseEntity.ok("Grupo creado exitosamente.");
	}

	@PostMapping("/agregar-miembro")
	public ResponseEntity<String> agregarMiembro(@RequestParam String correoUsuario, @RequestParam int grupoId,
			HttpSession session) {
		String userCorreo = validarLogiado(session);
		grupoService.agregarMiembro(correoUsuario, grupoId, userCorreo);
		return ResponseEntity.ok("Miembro agregado exitosamente.");
	}

	@PostMapping("/otorgar-admin")
	public ResponseEntity<String> otorgarAdmin(@RequestParam String correoUsuario, @RequestParam int grupoId,
			HttpSession session) {
		String userCorreo = validarLogiado(session);
		grupoService.otorgarAdmin(correoUsuario, grupoId, userCorreo);
		return ResponseEntity.ok("Admin otorgado exitosamente.");
	}

	@PostMapping("/remover-miembro")
	public ResponseEntity<String> removerMiembro(@RequestParam String correoUsuario, @RequestParam int grupoId,
			HttpSession session) {
		String userCorreo = validarLogiado(session);
		if (grupoService.esOwner(correoUsuario, grupoId)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede eliminar al propietario del grupo.");
		}
		grupoService.removerMiembro(correoUsuario, grupoId, userCorreo);
		return ResponseEntity.ok("Miembro eliminado exitosamente.");
	}

	@PostMapping("/revocar-admin")
	public ResponseEntity<String> revocarAdmin(@RequestParam String correoUsuario, @RequestParam int grupoId,
			HttpSession session) {
		String userCorreo = validarLogiado(session);
		grupoService.revocarAdmin(correoUsuario, grupoId, userCorreo);
		return ResponseEntity.ok("Admin revocado exitosamente.");
	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<String> eliminarGrupo(@RequestParam int grupoId, HttpSession session) {
		String userCorreo = validarLogiado(session);
		grupoService.eliminarGrupo(grupoId, userCorreo);
		return ResponseEntity.ok("Grupo eliminado exitosamente.");
	}

	@PostMapping("/enviar-mensaje")
	public ResponseEntity<String> enviarMensaje(@RequestBody EnviarMensajeGrupoDTO enviarMensajeGrupoDTO,
			HttpSession session) {
		String userCorreo = validarLogiado(session);

		// Extraer el EnviarMensajeDTO y el grupoId
		MensajeDataDTO mensajeDataDTO = enviarMensajeGrupoDTO.getEnviarMensajeDTO();
		int grupoId = enviarMensajeGrupoDTO.getGrupoId();

		grupoService.enviarMensajeGrupo(mensajeDataDTO, grupoId, userCorreo);
		return ResponseEntity.ok("Mensaje enviado exitosamente.");
	}

	private String validarLogiado(HttpSession session) {
		String userCorreo = (String) session.getAttribute(LoginController.USER_CORREO);
		if (userCorreo == null) {
			throw new RuntimeException("Usuario no logueado.");
		}
		return userCorreo;
	}
}

package com.tuchat.judc.server.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.api.dto.request.data.MensajeDataDTO;
import com.tuchat.judc.server.api.dto.response.MensajeDTO;
import com.tuchat.judc.server.model.EstadoMensaje;
import com.tuchat.judc.server.model.Mensaje;
import com.tuchat.judc.server.model.MensajeArchivo;
import com.tuchat.judc.server.model.MensajePrivadoData;
import com.tuchat.judc.server.model.TipoMensaje;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.MensajeArchivoRepository;
import com.tuchat.judc.server.repository.MensajePrivadoDataRepository;
import com.tuchat.judc.server.repository.MensajeRepository;
import com.tuchat.judc.server.repository.UsuarioRepository;

@Service
public class MensajePrivadoService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private MensajePrivadoDataRepository mensajePrivadoDataRepository;

	@Autowired
	private MensajeArchivoRepository mensajeArchivoRepository;

	public void enviarMensaje(MensajeDataDTO mensajeDataDTO) {
		// Validar que el emisor exista
		Usuario emisor = usuarioRepository.findByCorreo(mensajeDataDTO.getCorreoEmisor());
		if (emisor == null) {
			throw new RuntimeException("Usuario emisor no encontrado.");
		}

		// Validar que el receptor exista
		Usuario receptor = usuarioRepository.findByCorreo(mensajeDataDTO.getCorreoReceptor());
		if (receptor == null) {
			throw new RuntimeException("Usuario receptor no encontrado.");
		}

		// Crear y guardar el mensaje
		Mensaje mensaje = Mensaje.builder().userEmisor(emisor).text(mensajeDataDTO.getText())
				.tipo(mensajeDataDTO.isEsArchivo() ? TipoMensaje.ARCHIVO : TipoMensaje.TEXTO)
				.fechaEnvio(Timestamp.valueOf(LocalDateTime.now())).build();

		if (mensajeDataDTO.isEsArchivo()) {
			MensajeArchivo ma = MensajeArchivo.builder().mensaje(mensaje)
					.extensionArchivo(mensajeDataDTO.getExtension())
					.archivoPath(guardarArchivo(mensajeDataDTO.getArchivoBase64(), mensajeDataDTO.getExtension()))
					.build();

			mensajeArchivoRepository.save(ma);

		}

		mensajeRepository.save(mensaje);

		// Registrar en MensajePrivadoData
		MensajePrivadoData mensajePrivadoData = MensajePrivadoData.builder().mensaje(mensaje).usuarioReceptor(receptor)
				.status(EstadoMensaje.NO_RECIBIDO).build();

		mensajePrivadoDataRepository.save(mensajePrivadoData);
	}

	public List<MensajeDTO> obtenerMensajesEntreUsuarios(String correoEmisor, String correoReceptor) {
	    List<MensajeDTO> mensajes = mensajePrivadoDataRepository.findMensajesByUsuarios(correoEmisor, correoReceptor);

	   
	    // Usar el builder para construir la lista de MensajeDTO
	    return mensajes.stream()
	            .map(m -> MensajeDTO.builder()
	                    .id(m.getId())
	                    .correoEmisor(m.getCorreoEmisor())
	                    .text(m.getText())
	                    .tipo(m.getTipo() != null ? m.getTipo() : null) // Obtener el nombre del enum
	                    .fechaEnvio(m.getFechaEnvio())
	                    .archivoPath(m.getArchivoPath())
	                    .build())
	            .collect(Collectors.toList());
	}



	private String guardarArchivo(String archivo, String extension) {
		// TODO Auto-generated method stub
		return "/path/test";
	}
}

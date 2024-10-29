package tuchat.server.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.EnviarMensajePrivadoDTO;
import tuchat.server.api.dto.request.InfoMensajePrivadoDTO;
import tuchat.server.api.dto.response.ObtenerMensajesPrivadoDTO;
import tuchat.server.api.dto.response.data.MensajePrivadoDataDTO;
import tuchat.server.mapper.MensajeMapper;
import tuchat.server.model.tabla.Mensaje;
import tuchat.server.model.tabla.MensajeData;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.repository.tabla.EnumStatusMensajeRepository;
import tuchat.server.repository.tabla.MensajeDataRepository;
import tuchat.server.repository.tabla.MensajeRepository;
import tuchat.server.repository.tabla.UsuarioRepository;

@Service
public class MensajeService {

	@Autowired
	private LogiadoService logiadoService;

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private MensajeDataRepository mensajeDataRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnumStatusMensajeRepository enumStatusMensajeRepository;

	public boolean enviarMensajePrivado(EnviarMensajePrivadoDTO mensajePrivado, HttpSession session) {
		if (logiadoService.noLogiado(session))
			return false;

		Mensaje mensaje = MensajeMapper.crear(mensajePrivado.getMensajeData());

		mensaje.setUsuarioEmisor(logiadoService.getUsuario(session));

		mensajeRepository.saveAndFlush(mensaje);

		Usuario usuarioReceptor = usuarioRepository.findByCorreo(mensajePrivado.getCorreo());

		if (usuarioReceptor == null)
			return false;
		
		var status = enumStatusMensajeRepository.findById(1).orElse(null);

		MensajeData mensajeData = MensajeData.builder().mensaje(mensaje).usuarioReceptor(usuarioReceptor)
				.status(status).build();

		mensajeDataRepository.saveAndFlush(mensajeData);
		return true;
	}

	public ObtenerMensajesPrivadoDTO infoMensajePrivado(InfoMensajePrivadoDTO info, HttpSession session) {
		if (logiadoService.noLogiado(session))
			return null;

		Usuario usuario = logiadoService.getUsuario(session);
		Usuario usuario2 = usuarioRepository.findByCorreo(info.getCorreo());

		if (usuario2 == null)
			return null;

		List<Mensaje> mensajes = mensajeRepository.findMensajesEntreUsuariosSinGrupo(usuario.getId(), usuario2.getId());

		// Convertimos cada Mensaje a MensajePrivadoDataDTO
		List<MensajePrivadoDataDTO> mensajeDTOList = mensajes.stream().map(MensajeMapper::toDTO)
				.collect(Collectors.toList());

		// Construimos el ObtenerMensajesPrivadoDTO con la lista de DTOs
		return new ObtenerMensajesPrivadoDTO(mensajeDTOList);
	}

}

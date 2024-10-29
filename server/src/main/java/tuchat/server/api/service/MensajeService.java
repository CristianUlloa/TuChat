package tuchat.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.EnviarMensajePrivadoDTO;
import tuchat.server.api.dto.request.InfoMensajePrivadoDTO;
import tuchat.server.api.dto.response.ObtenerMensajesPrivadoDTO;

@Service
public class MensajeService {

	@Autowired
	private LogiadoService logiadoService;

	public boolean enviarMensajePrivado(EnviarMensajePrivadoDTO mensajePrivado, HttpSession session) {
		if (logiadoService.noLogiado(session))
			return false;
		
		return true;
	}

	public ObtenerMensajesPrivadoDTO infoMensajePrivado(InfoMensajePrivadoDTO info, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}

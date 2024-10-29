package tuchat.server.api.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.TuChat;
import tuchat.server.model.tabla.Usuario;

@Service
public class LogiadoService {
	
	public boolean logiado(HttpSession session) {
		Usuario usuario = getUsuario(session);
		if (usuario == null)
			return false;

		return true;
	}
	
	public boolean noLogiado(HttpSession session)
	{
		return !logiado(session);
	}

	public Usuario getUsuario(HttpSession session) {
		return (Usuario) session.getAttribute(TuChat.USUARIO);
	}
	
	public void setUsuario(Usuario usuario, HttpSession session) {
		session.setAttribute(TuChat.USUARIO, usuario);
	}
	
}

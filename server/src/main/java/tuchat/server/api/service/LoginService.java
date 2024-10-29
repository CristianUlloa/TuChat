package tuchat.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.TuChat;
import tuchat.server.api.dto.request.AuthCodigoDTO;
import tuchat.server.api.dto.request.AuthPasswDTO;
import tuchat.server.api.dto.request.CrearCuentaDTO;
import tuchat.server.model.tabla.AuthPassw;
import tuchat.server.model.tabla.Codigo;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.model.tabla.UsuarioAuthData;
import tuchat.server.repository.tabla.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean login(AuthPasswDTO auth, HttpSession session) {

		Usuario usuario = usuarioRepository.findByCorreo(auth.getCorreo());

		if (!_validarLogin(usuario, true, auth.getPassw()))
			return false;
		_login(usuario, session);

		return true;
	}

	public boolean login(AuthCodigoDTO auth, HttpSession session) {

		Usuario usuario = usuarioRepository.findByCorreo(auth.getCorreo());

		if (!_validarLogin(usuario, false, auth.getCodigo()))
			return false;
		_login(usuario, session);

		return true;
	}

	private boolean _validarLogin(Usuario usuario, boolean passw, String val) {
		UsuarioAuthData authData = usuario.getAuthData();

		if (!authData.isCorreoConfirmado())
			return false;

		if (!(passw == authData.isUsaPassw()))
			return false;

		if (passw) {
			AuthPassw currentAuth = authData.getCurrentPassw();

			return currentAuth.getPassw().equals(val);
		} else {
			 Codigo currentAuth = authData.getCurrentCodigo();

			return currentAuth.getCodigo().equals(val);
		}
	}

	private void _login(Usuario u, HttpSession ss) {
		ss.setAttribute(TuChat.USUARIO, u);
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public boolean crearCuenta(CrearCuentaDTO nuevaCuenta, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}
}

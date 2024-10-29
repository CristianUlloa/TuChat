package tuchat.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.AuthCodigoDTO;
import tuchat.server.api.dto.request.AuthPasswDTO;
import tuchat.server.api.dto.request.CrearCuentaDTO;
import tuchat.server.api.dto.request.PedirNuevoCodigoDTO;
import tuchat.server.api.dto.response.ObtenerLoginStatus;
import tuchat.server.mapper.UsuarioDataMapper;
import tuchat.server.mapper.UsuarioMapper;
import tuchat.server.model.tabla.AuthPassw;
import tuchat.server.model.tabla.Codigo;
import tuchat.server.model.tabla.RegistroInicioSesion;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.model.tabla.UsuarioAuth;
import tuchat.server.repository.tabla.CodigoRepository;
import tuchat.server.repository.tabla.RegistroInicioSesionRepository;
import tuchat.server.repository.tabla.UsuarioAuthRepository;
import tuchat.server.repository.tabla.UsuarioRepository;
import tuchat.server.util.AutenticacionUtil;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RegistroInicioSesionRepository registroInicioSesionRepository;

	@Autowired
	private CodigoRepository codigoRepository;

	@Autowired
	private UsuarioAuthRepository usuarioAuthRepository;

	@Autowired
	private LogiadoService logiadoService;

	public boolean login(AuthPasswDTO auth, HttpSession session) {
		Usuario usuario = usuarioRepository.findByCorreo(auth.getCorreo());

		if (!_validarLogin(usuario, true, auth.getPassw())) {
			registroInicioSession(usuario, auth.getPassw(), false, usuario.getAuth().getCurrentPassw());
			return false;
		}
		// Llama a _login con los parámetros adicionales
		_login(usuario, session, auth.getPassw(), usuario.getAuth().getCurrentPassw());
		return true;
	}

	public boolean login(AuthCodigoDTO auth, HttpSession session) {
		Usuario usuario = usuarioRepository.findByCorreo(auth.getCorreo());

		if (!_validarLogin(usuario, false, auth.getCodigo())) {
			registroInicioSession(usuario, auth.getCodigo(), false, usuario.getAuth().getCurrentCodigo());
			return false;
		}
		// Llama a _login con los parámetros adicionales
		_login(usuario, session, auth.getCodigo(), usuario.getAuth().getCurrentCodigo());
		return true;
	}

	public boolean confirmarCorreo(AuthCodigoDTO auth, HttpSession session) {
		Usuario usuario = usuarioRepository.findByCorreo(auth.getCorreo());

		if (!_validarConfirmarCorreo(usuario, auth)) {
			registroInicioSession(usuario, auth.getCodigo(), false, usuario.getAuth().getCurrentCodigo());
			return false;
		}
		// Llama a _login con los parámetros adicionales
		_login(usuario, session, auth.getCodigo(), usuario.getAuth().getCurrentCodigo());

		usuario.getAuth().setCorreoConfirmado(true);

		usuarioAuthRepository.saveAndFlush(usuario.getAuth());
		return true;
	}

	private boolean _validarLogin(Usuario usuario, boolean passw, String val) {
		UsuarioAuth authData = usuario.getAuth();

		if (!authData.isCorreoConfirmado())
			return false;

		if (passw && !authData.isUsaPassw())
			return false;

		if (passw) {
			AuthPassw currentAuth = authData.getCurrentPassw();

			return currentAuth.getPassw().equals(val);
		} else {
			Codigo currentAuth = authData.getCurrentCodigo();

			return currentAuth.getCodigo().equals(val);
		}
	}

	private void _login(Usuario usuario, HttpSession session, String valor, Object auth) {
		// Establece la sesión del usuario
		logiadoService.setUsuario(usuario, session);

		// Registra el intento exitoso
		registroInicioSession(usuario, valor, true, auth);

		if (auth instanceof Codigo) {
			Codigo cod = (Codigo) auth;

			cod.setUsado(true);
			cod.setComprobado(true);

			codigoRepository.saveAndFlush(cod);
		}
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public boolean crearCuenta(CrearCuentaDTO nuevaCuenta, HttpSession session) {
		Usuario usuario = UsuarioMapper.crear(nuevaCuenta);

		pedirNuevoCodigo(usuario);

		usuario = usuarioRepository.saveAndFlush(usuario);

		// no se agrega el usuario a la session
		// aun falta confirmar el correo
		return usuario != null;
	}

	public boolean pedirNuevoCodigo(PedirNuevoCodigoDTO nuevoCodigo) {
		Usuario usuario = usuarioRepository.findByCorreo(nuevoCodigo.getCorreo());
		Codigo lastCodigo = usuario.getAuth().getCurrentCodigo();
		pedirNuevoCodigo(usuario);

		Codigo codigo = usuario.getAuth().getCurrentCodigo();
		codigo = codigoRepository.saveAndFlush(codigo);

		if (lastCodigo != null) {
			lastCodigo.setUsado(true);
			codigoRepository.saveAndFlush(lastCodigo);
		}

		usuarioAuthRepository.saveAndFlush(usuario.getAuth());

		return codigo != null;
	}

	private void pedirNuevoCodigo(Usuario usuario) {
		Codigo codigo = generarCodigo();

		codigo.setUsuario(usuario);

		usuario.getAuth().setCurrentCodigo(codigo);

	}

	private Codigo generarCodigo() {
		return Codigo.builder().codigo(AutenticacionUtil.generarCodigoValidacion(20)).build();
	}

	private boolean _validarConfirmarCorreo(Usuario usuario, AuthCodigoDTO auth) {
		UsuarioAuth authData = usuario.getAuth();

		if (authData.isCorreoConfirmado())
			return false;

		Codigo currentAuth = authData.getCurrentCodigo();

		if (currentAuth.isUsado())
			return false;

		return currentAuth.getCodigo().equals(auth.getCodigo());
	}

	private void registroInicioSession(Usuario usuario, String valor, Boolean exitoso, Object auth) {
		RegistroInicioSesion registroInicioSesion = RegistroInicioSesion.builder().usuario(usuario).valor(valor)
				.exitoso(exitoso).build();

		if (auth != null) {
			if (auth instanceof AuthPassw) {

				AuthPassw authP = (AuthPassw) auth;

				registroInicioSesion.setAuthPassw(authP);

			} else if (auth instanceof Codigo) {
				Codigo cod = (Codigo) auth;

				registroInicioSesion.setCodigo(cod);
			}

		}

		registroInicioSesionRepository.saveAndFlush(registroInicioSesion);
	}

	public ObtenerLoginStatus status(HttpSession session) {
		if (logiadoService.noLogiado(session))
			return null;

		return new ObtenerLoginStatus(UsuarioDataMapper.toInfoDTO(logiadoService.getUsuario(session)));
	}

	@Override
	public String toString() {
		return "xd" + getClass().getSimpleName();
	}
}

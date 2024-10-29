package tuchat.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.BorrarContactoDTO;
import tuchat.server.api.dto.request.CrearContactoDTO;
import tuchat.server.api.dto.response.ObtenerInfoUsuarioDTO;
import tuchat.server.mapper.UsuarioDataMapper;
import tuchat.server.mapper.UsuarioMapper;
import tuchat.server.model.tabla.ContactoUsuario;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.repository.tabla.ContactoUsuarioRepository;
import tuchat.server.repository.tabla.UsuarioRepository;

@Service
public class ContactosService {
	@Autowired
	private LogiadoService logiadoService;

	@Autowired
	private ContactoUsuarioRepository contactoUsuarioRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean crearContacto(CrearContactoDTO nuevoContacto, HttpSession session) {
		System.out.println("crearContacto");
		if (logiadoService.noLogiado(session))
			return false;

		Usuario contacto = usuarioRepository.findByCorreo(nuevoContacto.getCorreo());

		ObtenerInfoUsuarioDTO info = UsuarioDataMapper.toInfoDTO(contacto);

		ContactoUsuario contactoUsuario = ContactoUsuario.builder().nombre(info.getNombre())
				.usuario(logiadoService.getUsuario(session)).usuarioRef(contacto).build();

		contactoUsuarioRepository.saveAndFlush(contactoUsuario);
		return true;
	}

	public boolean borrarContacto(BorrarContactoDTO contactoDTO, HttpSession session) {
		if (logiadoService.noLogiado(session)) {
			return false; // El usuario no está logueado
		}

		Usuario usuarioLogeado = logiadoService.getUsuario(session); // Obtener el usuario logueado
		Usuario contacto = usuarioRepository.findByCorreo(contactoDTO.getCorreo()); // Buscar el contacto por correo

		// Verificar si el contacto existe
		if (contacto == null) {
			return false; // El contacto no existe
		}

		// Buscar el contacto en la tabla ContactoUsuario
		ContactoUsuario contactoUsuario = contactoUsuarioRepository.findByUsuarioAndUsuarioRef(usuarioLogeado,
				contacto);

		// Verificar si el contacto existe y si pertenece al usuario logueado
		if (contactoUsuario != null) {
			contactoUsuarioRepository.delete(contactoUsuario); // Eliminar el contacto
			return true; // Contacto eliminado con éxito
		}

		return false; // No se pudo eliminar el contacto
	}

}

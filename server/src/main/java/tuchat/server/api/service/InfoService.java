package tuchat.server.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.response.ObtenerContactosDTO;
import tuchat.server.mapper.ContactoMapper;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.repository.tabla.MensajeRepository;
import tuchat.server.repository.tabla.UsuarioRepository;

@Service
public class InfoService {

	@Autowired
	private LogiadoService logiadoService;

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public ObtenerContactosDTO infoContactos(HttpSession session) {
		if (logiadoService.noLogiado(session))
			return null;

		Usuario usuario = logiadoService.getUsuario(session);

		 usuario = usuarioRepository.findById(logiadoService.getUsuario(session).getId())
                 .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


		logiadoService.setUsuario(usuario, session);

		List<String> correos = usuario.getContactos().stream().map(ContactoMapper::toString).toList();

		return new ObtenerContactosDTO(correos);
	}

	@Transactional
	public ObtenerContactosDTO infoNoContactos(HttpSession session) {
	    if (logiadoService.noLogiado(session))
	        return null;

	    Usuario usuario = logiadoService.getUsuario(session);
	    usuario = usuarioRepository.findById(logiadoService.getUsuario(session).getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    
	    
	    List<String> exclude = infoContactos(session).getCorreos();
	    String excludeMiCorreo = usuario.getCorreo();

	    // Obtener los correos de los usuarios con los que ha chateado
	    List<String> contactosChat = mensajeRepository.findContactEmailsByUserId(usuario.getId());

	    for(String str: contactosChat)
	    {
	    	System.out.println(str);
	    }
	    
	    // Excluir los correos que están en la lista "exclude"
	    contactosChat.removeAll(exclude);
	    contactosChat.remove(excludeMiCorreo);

	    return new ObtenerContactosDTO(contactosChat);
	}

}

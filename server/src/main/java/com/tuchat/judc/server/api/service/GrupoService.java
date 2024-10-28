package com.tuchat.judc.server.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.api.dto.request.CrearGrupoDTO;
import com.tuchat.judc.server.api.dto.request.data.MensajeDataDTO;
import com.tuchat.judc.server.api.dto.response.GrupoDTO;
import com.tuchat.judc.server.mapper.GrupoMapper;
import com.tuchat.judc.server.model.EstadoMensaje;
import com.tuchat.judc.server.model.Grupo;
import com.tuchat.judc.server.model.Mensaje;
import com.tuchat.judc.server.model.MensajeArchivo;
import com.tuchat.judc.server.model.MensajeGrupoData;
import com.tuchat.judc.server.model.MiembrosGrupo;
import com.tuchat.judc.server.model.RegistroGrupoEvento;
import com.tuchat.judc.server.model.TipoEvento;
import com.tuchat.judc.server.model.TipoMensaje;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.GrupoRepository;
import com.tuchat.judc.server.repository.MensajeArchivoRepository;
import com.tuchat.judc.server.repository.MensajeGrupoDataRepository;
import com.tuchat.judc.server.repository.MensajeRepository;
import com.tuchat.judc.server.repository.MiembrosGrupoRepository;
import com.tuchat.judc.server.repository.UsuarioRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RegistroGrupoEventoService registroGrupoEventoService;

	@Autowired
	private MiembrosGrupoRepository miembrosGrupoRepository;
	
	@Autowired
	private MensajeArchivoRepository mensajeArchivoRepository;
	
	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Autowired
	private MensajeGrupoDataRepository mensajeGrupoDataRepository;

	public List<GrupoDTO> obtenerGruposComoOwner(String userCorreo) {
		Usuario u = usuarioRepository.findByCorreo(userCorreo);
		return u.getGrupos().stream().map(GrupoMapper::toDTO).collect(Collectors.toList());
	}

	public List<GrupoDTO> obtenerGruposComoAdmin(String userCorreo) {
		Usuario u = usuarioRepository.findByCorreo(userCorreo);
		List<MiembrosGrupo> miembrosGrupos = miembrosGrupoRepository.findByUsuarioIdAndEsAdminTrue(u.getId());
		return miembrosGrupos.stream().map(miembro -> miembro.getGrupo()).map(GrupoMapper::toDTO)
				.collect(Collectors.toList());
	}

	public List<GrupoDTO> obtenerGruposComoMiembro(String userCorreo) {
		Usuario u = usuarioRepository.findByCorreo(userCorreo);
		List<MiembrosGrupo> miembrosGrupos = miembrosGrupoRepository.findByUsuarioIdAndEsAdminFalse(u.getId());
		return miembrosGrupos.stream().map(miembro -> miembro.getGrupo()).map(GrupoMapper::toDTO)
				.collect(Collectors.toList());
	}

	public void crearGrupo(CrearGrupoDTO crearGrupoDTO, String userCorreo) {
		Usuario owner = usuarioRepository.findByCorreo(userCorreo);
		if (owner == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}
		Grupo nuevoGrupo = GrupoMapper.toEntity(crearGrupoDTO, owner, guardarArchivo(crearGrupoDTO.getIconBase64(), crearGrupoDTO.getIconExtension()));
		nuevoGrupo.setOwner(owner);
		grupoRepository.save(nuevoGrupo);
		registroGrupoEventoService.registrarEvento(nuevoGrupo, owner, null,
				TipoEvento.CREACION_GRUPO);
		MiembrosGrupo miembro = new MiembrosGrupo();
		miembro.setGrupo(nuevoGrupo);
		miembro.setUsuario(owner);
		miembro.setEsAdmin(true);
		miembrosGrupoRepository.save(miembro);
	}

	public void agregarMiembro(String correoUsuario, int grupoId, String userCorreo) {
		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
		Usuario admin = usuarioRepository.findByCorreo(userCorreo);

		if (!esOwnerOAdmin(userCorreo, grupoId)) {
			throw new IllegalArgumentException("No tienes permisos para agregar miembros a este grupo.");
		}

		Usuario nuevoMiembro = usuarioRepository.findByCorreo(correoUsuario);
		if (nuevoMiembro == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}

		MiembrosGrupo miembro = new MiembrosGrupo();
		miembro.setGrupo(grupo);
		miembro.setUsuario(nuevoMiembro);
		miembro.setEsAdmin(false);
		miembrosGrupoRepository.save(miembro);
		registroGrupoEventoService.registrarEvento(grupo, admin, nuevoMiembro,
				TipoEvento.AGREGAR_USUARIO);
	}

	public void otorgarAdmin(String correoUsuario, int grupoId, String userCorreo) {
		if (!esOwner(userCorreo, grupoId)) {
			throw new IllegalArgumentException("No tienes permisos para otorgar admin.");
		}

		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
		Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
		MiembrosGrupo miembro = miembrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);

		if (miembro == null) {
			throw new IllegalArgumentException("El usuario no es miembro del grupo.");
		}

		miembro.setEsAdmin(true);
		miembrosGrupoRepository.save(miembro);
		registroGrupoEventoService.registrarEvento(grupo, usuario, usuario, TipoEvento.HACER_ADMIN);
	}

	public void removerMiembro(String correoUsuario, int grupoId, String userCorreo) {
		if (!esOwnerOAdmin(userCorreo, grupoId)) {
			throw new IllegalArgumentException("No tienes permisos para remover miembros.");
		}

		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
		Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
		MiembrosGrupo miembro = miembrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);

		if (miembro == null) {
			throw new IllegalArgumentException("El usuario no es miembro del grupo.");
		}

		miembrosGrupoRepository.delete(miembro);
		registroGrupoEventoService.registrarEvento(grupo, usuario, usuario,
				TipoEvento.QUITAR_USUARIO);
	}

	public boolean esOwnerOAdmin(String userCorreo, int grupoId) {
		Usuario usuario = usuarioRepository.findByCorreo(userCorreo);
		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));

		if (grupo.getOwner().getId().equals(usuario.getId())) {
			return true;
		}

		MiembrosGrupo miembro = miembrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);
		return miembro != null && miembro.getEsAdmin();
	}

	public boolean esOwner(String correoUsuario, int grupoId) {
		Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));

		return grupo.getOwner().getId().equals(usuario.getId());
	}

	public void revocarAdmin(String correoUsuario, int grupoId, String userCorreo) {
		if (!esOwner(userCorreo, grupoId)) {
			throw new IllegalArgumentException("No tienes permisos para revocar administradores.");
		}

		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
		Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
		MiembrosGrupo miembro = miembrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);

		if (miembro == null || !miembro.getEsAdmin()) {
			throw new IllegalArgumentException("El usuario no es administrador del grupo.");
		}

		miembro.setEsAdmin(false);
		miembrosGrupoRepository.save(miembro);
		registroGrupoEventoService.registrarEvento(grupo, usuario, usuario,
				TipoEvento.QUITAR_ADMIN);
	}

	public void eliminarGrupo(int grupoId, String userCorreo) {
		if (!esOwner(userCorreo, grupoId)) {
			throw new IllegalArgumentException("No tienes permisos para eliminar este grupo.");
		}

		Grupo grupo = grupoRepository.findById(grupoId)
				.orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));
		grupoRepository.delete(grupo);
		registroGrupoEventoService.registrarEvento(grupo, null, null, TipoEvento.ELIMINAR_GRUPO);
	}

	public void enviarMensajeGrupo(MensajeDataDTO mensajeDataDTO, int grupoId, String userCorreo) {
	    // Validar que el grupo exista
	    Grupo grupo = grupoRepository.findById(grupoId)
	            .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado"));

	    // Validar que el usuario exista
	    Usuario usuario = usuarioRepository.findByCorreo(userCorreo);
	    if (usuario == null) {
	        throw new RuntimeException("Usuario emisor no encontrado.");
	    }

	    // Verificar si el usuario es miembro del grupo
	    MiembrosGrupo miembro = miembrosGrupoRepository.findByGrupoAndUsuario(grupo, usuario);
	    if (miembro == null) {
	        throw new IllegalArgumentException("No eres miembro de este grupo.");
	    }

	    // Crear y guardar el mensaje
	    Mensaje mensaje = Mensaje.builder()
	            .userEmisor(usuario)
	            .text(mensajeDataDTO.getText())
	            .tipo(mensajeDataDTO.isEsArchivo() ? TipoMensaje.ARCHIVO : TipoMensaje.TEXTO)
	            .fechaEnvio(Timestamp.valueOf(LocalDateTime.now()))
	            .build();

	    // Si es un archivo, crear y guardar la referencia al archivo
	    if (mensajeDataDTO.isEsArchivo()) {
	        MensajeArchivo ma = MensajeArchivo.builder()
	                .mensaje(mensaje)
	                .extensionArchivo(mensajeDataDTO.getExtension())
	                .archivoPath(guardarArchivo(mensajeDataDTO.getArchivoBase64(), mensajeDataDTO.getExtension()))
	                .build();
	        mensajeArchivoRepository.save(ma);
	    }

	    // Guardar el mensaje en la base de datos
		mensajeRepository.save(mensaje);

	    // Registrar el mensaje en MensajeGrupoData para todos los miembros del grupo
	    List<MiembrosGrupo> miembros = miembrosGrupoRepository.findAllByGrupo(grupo);
	    for (MiembrosGrupo miembroGrupo : miembros) {
	        MensajeGrupoData mensajeGrupoData = MensajeGrupoData.builder()
	                .mensaje(mensaje)
	                .grupo(grupo)
	                .usuarioReceptor(miembroGrupo.getUsuario()) // El receptor es cada miembro del grupo
	                .status(EstadoMensaje.NO_ENVIADO)
	                .build();
	        mensajeGrupoDataRepository.save(mensajeGrupoData);
	    }

	    // Registrar el evento de envío de mensaje al grupo
	    registroGrupoEventoService.registrarEvento(grupo, usuario, null, TipoEvento.ENVIAR_MENSAJE);
	}

	private String guardarArchivo(String archivoBase64, String extension) {
		// TODO Auto-generated method stub
		return null;
	}


}

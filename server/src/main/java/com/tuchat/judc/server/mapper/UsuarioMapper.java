package com.tuchat.judc.server.mapper;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;
import com.tuchat.judc.server.model.Usuario;

public class UsuarioMapper {

	// Convertir de Usuario a UsuarioDTO
	public static UsuarioDTO toDTO(Usuario usuario) {
		return UsuarioDTO.builder().nombre(usuario.getNombre()).apellido(usuario.getApellido())
				.nombreCompleto(usuario.getNombreCompleto()).correo(usuario.getCorreo())
				.usarNombreCompleto(usuario.getUsaNombreCompleto()).iconPath(usuario.getIconPath()).build();
	}

	// Convertir de UsuarioDTO a Usuario utilizando el builder
	public static Usuario toEntity(UsuarioDTO usuarioDTO) {
		return Usuario.builder().nombre(usuarioDTO.getNombre()).apellido(usuarioDTO.getApellido())
				.nombreCompleto(usuarioDTO.getNombreCompleto()).correo(usuarioDTO.getCorreo())
				.usaNombreCompleto(usuarioDTO.isUsarNombreCompleto()).iconPath(usuarioDTO.getIconPath()).build();
	}

}

package com.tuchat.judc.server.mapper;

import java.util.stream.Collectors;

import com.tuchat.judc.server.api.dto.request.CrearGrupoDTO;
import com.tuchat.judc.server.api.dto.response.GrupoDTO;
import com.tuchat.judc.server.model.Grupo;
import com.tuchat.judc.server.model.Usuario;

public class GrupoMapper {

	// Convertir de Grupo a GrupoDTO
	public static GrupoDTO toDTO(Grupo grupo) {
	    return GrupoDTO.builder()
	            .id(grupo.getId())
	            .nombre(grupo.getNombre())
	            .descripcion(grupo.getDescripcion())
	            .owner_correo(grupo.getOwner() != null ? grupo.getOwner().getCorreo() : null) // Verifica si owner es null
	            .iconPath(grupo.getIconPath())
	            .miembros(grupo.getMiembros().stream()
	                .map(m -> UsuarioMapper.toDTO(m.getUsuario())) // Mapea los usuarios de MiembrosGrupo
	                .collect(Collectors.toList())) // Colecciona la lista de miembros
	            .build();
	}
	// Convertir de GrupoDTO a Grupo utilizando el builder
	public static Grupo toEntity(GrupoDTO grupoDTO) {
		return Grupo.builder().id(grupoDTO.getId()).nombre(grupoDTO.getNombre()).descripcion(grupoDTO.getDescripcion()).iconPath(grupoDTO.getIconPath())
				.build();
	}

	public static Grupo toEntity(CrearGrupoDTO crearGrupoDTO, Usuario userCorreo, String iconPath) {
		return Grupo.builder().nombre(crearGrupoDTO.getNombre()).descripcion(crearGrupoDTO.getDescripcion()).iconPath(iconPath).owner(userCorreo).build();
	}
}

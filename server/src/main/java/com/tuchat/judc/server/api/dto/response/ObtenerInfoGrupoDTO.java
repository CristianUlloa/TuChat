package com.tuchat.judc.server.api.dto.response;

import java.util.List;

import com.tuchat.judc.server.api.dto.response.data.GrupoUsuarioDataDTO;
import com.tuchat.judc.server.api.dto.response.data.ObtenerArchivoDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerInfoGrupoDTO {

	private int id;

	private String nombre;

	private String descripcion;

	private ObtenerArchivoDataDTO iconData;

	private List<GrupoUsuarioDataDTO> integrantes;

}

package com.tuchat.judc.server.api.dto.response;

import com.tuchat.judc.server.api.dto.response.data.ObtenerArchivoDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerInfoUsuarioDTO {
	private String nombre;
	private String correo;
	private String descripcion;
	private ObtenerArchivoDataDTO iconData;
}

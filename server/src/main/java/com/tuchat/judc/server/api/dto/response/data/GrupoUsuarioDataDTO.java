package com.tuchat.judc.server.api.dto.response.data;

import com.tuchat.judc.server.api.dto.response.ObtenerInfoUsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoUsuarioDataDTO {

	private String rol;
	
	private ObtenerInfoUsuarioDTO data;
}

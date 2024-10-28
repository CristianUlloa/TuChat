package com.tuchat.judc.server.api.dto.request;

import com.tuchat.judc.server.api.dto.request.data.GrupoDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarGrupoDTO {
	
	private int id;

	private GrupoDataDTO grupoData;

}

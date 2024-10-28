package com.tuchat.judc.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPerfil {
	
	private EnviarArchivoDTO icon;

	private EnviarUsuarioDataDTO data;
}

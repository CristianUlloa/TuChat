package com.tuchat.judc.server.api.dto.request;

import com.tuchat.judc.server.api.dto.request.data.ArchivoDataDTO;
import com.tuchat.judc.server.api.dto.request.data.UsuarioDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPerfilDTO {
	
	private ArchivoDataDTO icon;

	private UsuarioDataDTO usuarioData;
}

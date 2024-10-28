package com.tuchat.judc.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnviarUsuarioDataDTO {
	
	private String nombres;
	
	private String apellidos;
	
	private String nombreCompleto;
	
	private boolean usaNombreCompleto;

	private String descripcion;

}

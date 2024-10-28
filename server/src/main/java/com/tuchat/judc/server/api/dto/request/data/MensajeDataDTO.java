package com.tuchat.judc.server.api.dto.request.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDataDTO {

	private String texto;
	
	private ArchivoDataDTO archivo;
	
}

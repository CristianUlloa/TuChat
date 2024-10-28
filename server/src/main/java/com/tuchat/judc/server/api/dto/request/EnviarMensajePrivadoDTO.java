package com.tuchat.judc.server.api.dto.request;

import com.tuchat.judc.server.api.dto.request.data.MensajeDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnviarMensajePrivadoDTO {
	
	private MensajeDataDTO mensajeData;

	private String correo;


}

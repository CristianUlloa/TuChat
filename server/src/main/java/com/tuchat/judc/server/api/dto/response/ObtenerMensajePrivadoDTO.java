package com.tuchat.judc.server.api.dto.response;

import com.tuchat.judc.server.api.dto.response.data.MensajeStatusData;
import com.tuchat.judc.server.api.dto.response.data.ObtenerMensajeDataDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerMensajePrivadoDTO {
	
	private ObtenerMensajeDataDTO mensajeData;
	
	private MensajeStatusData statusData;

}

package com.tuchat.judc.server.api.dto.response;

import java.util.List;

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
public class ObtenerMensajeGrupoDTO {
	
	private ObtenerMensajeDataDTO mensajeData;
	
	private List<MensajeStatusData> statusData;
}

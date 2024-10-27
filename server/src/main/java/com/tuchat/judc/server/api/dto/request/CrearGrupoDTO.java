package com.tuchat.judc.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearGrupoDTO {

	private String iconBase64;
    private String iconExtension;

    private String nombre;
    private String descripcion;

}

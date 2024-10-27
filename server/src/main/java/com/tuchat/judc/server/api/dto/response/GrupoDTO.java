package com.tuchat.judc.server.api.dto.response;

import java.util.List;

import com.tuchat.judc.server.api.dto.common.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
	private int id;
    private String nombre;
    private String descripcion;
    private String owner_correo;
    private String iconPath;
    
    private List<UsuarioDTO> miembros;
}

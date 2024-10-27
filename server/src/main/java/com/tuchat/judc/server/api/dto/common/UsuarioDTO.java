package com.tuchat.judc.server.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String correo;
    private boolean usarNombreCompleto;
    private String iconPath;
}

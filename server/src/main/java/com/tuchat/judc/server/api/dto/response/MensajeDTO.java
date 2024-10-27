package com.tuchat.judc.server.api.dto.response;

import java.sql.Timestamp;

import com.tuchat.judc.server.model.TipoMensaje;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private int id;
    private String correoEmisor;
    private String text;
    private TipoMensaje tipo; // enum
    private Timestamp fechaEnvio;
    private String archivoPath;
}

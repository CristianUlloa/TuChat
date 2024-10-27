package com.tuchat.judc.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnviarMensajeDTO {
    private String correoEmisor;
    private String correoReceptor;
    private String text;
    private boolean esArchivo;
    private String archivoBase64;
    private String extension;
}

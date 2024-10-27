package com.tuchat.judc.server.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.tuchat.judc.server.api.dto.response.MensajeDTO;
import com.tuchat.judc.server.model.Mensaje;
import com.tuchat.judc.server.model.Usuario;

@Component
public class MensajeMapper {

    // Convierte un MensajeDTO a una entidad Mensaje
    public static Mensaje toEntity(MensajeDTO mensajeDTO, Usuario emisor) {
        return Mensaje.builder()
                .emisor(emisor)
                .text(mensajeDTO.getText())
                .tipo(mensajeDTO.getTipo())
                .fechaEnvio(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    // Convierte una entidad Mensaje a un MensajeDTO
    public static MensajeDTO toDTO(Mensaje mensaje) {
        return MensajeDTO.builder()
                .text(mensaje.getText())
                .tipo(mensaje.getTipo())
                .fechaEnvio(mensaje.getFechaEnvio())
                .build();
    }
}

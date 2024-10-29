package tuchat.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.EnviarMensajePrivadoDTO;
import tuchat.server.api.dto.request.InfoMensajePrivadoDTO;
import tuchat.server.api.dto.response.ObtenerMensajesPrivadoDTO;
import tuchat.server.api.service.MensajeService;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/enviarMensajePrivado")
    public ResponseEntity<String> enviarMensajePrivado(@RequestBody EnviarMensajePrivadoDTO mensajePrivado, HttpSession session) {
        boolean enviado = mensajeService.enviarMensajePrivado(mensajePrivado, session);

        if (enviado) {
            return ResponseEntity.ok("Mensaje enviado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error: No se pudo enviar el mensaje. Por favor, verifica los datos.");
        }
    }

    @GetMapping("/info")
    public ResponseEntity<ObtenerMensajesPrivadoDTO> infoMensajePrivado(@RequestBody InfoMensajePrivadoDTO info, HttpSession session) {
        ObtenerMensajesPrivadoDTO obtenerMensajes = mensajeService.infoMensajePrivado(info, session);
        
        if (obtenerMensajes != null) {
            return ResponseEntity.ok(obtenerMensajes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}

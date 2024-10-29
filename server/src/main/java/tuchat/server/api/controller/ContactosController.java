package tuchat.server.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tuchat.server.api.dto.request.BorrarContactoDTO;
import tuchat.server.api.dto.request.CrearContactoDTO;
import tuchat.server.api.service.ContactosService;

@RestController
@RequestMapping("/contactos")
public class ContactosController {

    @Autowired
    private ContactosService contactosService;

    @PostMapping("/crearContacto")
    public ResponseEntity<?> crearContacto(@RequestBody CrearContactoDTO nuevoContacto, HttpSession session) {
        boolean isCreated = contactosService.crearContacto(nuevoContacto, session);

        if (isCreated) {
            return ResponseEntity.ok("Contacto creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: No se pudo crear el contacto. Verifica la información proporcionada.");
        }
    }

    @PostMapping("/borrarContacto")
    public ResponseEntity<?> borrarContacto(@RequestBody BorrarContactoDTO contacto, HttpSession session) {
        boolean isDeleted = contactosService.borrarContacto(contacto, session);

        if (isDeleted) {
            return ResponseEntity.ok("Contacto borrado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo encontrar el contacto para borrar.");
        }
    }
}

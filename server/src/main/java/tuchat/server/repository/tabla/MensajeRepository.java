package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
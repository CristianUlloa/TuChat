package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.MensajeData;

@Repository
public interface MensajeDataRepository extends JpaRepository<MensajeData, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.EnumStatusMensaje;

@Repository
public interface EnumStatusMensajeRepository extends JpaRepository<EnumStatusMensaje, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.RegistroInicioSesion;

@Repository
public interface RegistroInicioSesionRepository extends JpaRepository<RegistroInicioSesion, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

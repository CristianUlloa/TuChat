package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.BloqueadoUsuario;

@Repository
public interface BloqueadoUsuarioRepository extends JpaRepository<BloqueadoUsuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

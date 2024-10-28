package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.UsuarioData;

@Repository
public interface UsuarioDataRepository extends JpaRepository<UsuarioData, Integer> {
}

package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.IntegranteGrupo;

@Repository
public interface IntegranteGrupoRepository extends JpaRepository<IntegranteGrupo, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

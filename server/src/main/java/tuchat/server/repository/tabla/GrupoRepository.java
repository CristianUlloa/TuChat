package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

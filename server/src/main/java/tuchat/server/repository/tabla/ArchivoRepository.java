package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;

import tuchat.server.model.tabla.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
}

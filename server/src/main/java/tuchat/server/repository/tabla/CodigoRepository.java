package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;

import tuchat.server.model.tabla.Codigo;

public interface CodigoRepository extends JpaRepository<Codigo, Integer> {
}

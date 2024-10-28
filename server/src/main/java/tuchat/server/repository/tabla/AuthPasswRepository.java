package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;

import tuchat.server.model.tabla.AuthPassw;

public interface AuthPasswRepository extends JpaRepository<AuthPassw, Integer> {
}

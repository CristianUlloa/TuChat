package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.UsuarioAuth;

@Repository
public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

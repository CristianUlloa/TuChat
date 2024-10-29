package tuchat.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.ContactoUsuario;
import tuchat.server.model.tabla.Usuario;

@Repository
public interface ContactoUsuarioRepository extends JpaRepository<ContactoUsuario, Integer> {

	ContactoUsuario findByUsuarioAndUsuarioRef(Usuario usuario, Usuario usuarioRef);

}

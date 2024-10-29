package tuchat.server.repository.tabla;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tuchat.server.model.tabla.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

	@Query("SELECT m FROM Mensaje m JOIN MensajeData md ON m.id = md.mensaje.id "
			+ "WHERE ((m.usuarioEmisor.id = :usuario1Id AND md.usuarioReceptor.id = :usuario2Id) "
			+ "OR (m.usuarioEmisor.id = :usuario2Id AND md.usuarioReceptor.id = :usuario1Id)) "
			+ "AND md.grupo IS NULL ORDER BY m.createTime DESC")
	List<Mensaje> findMensajesEntreUsuariosSinGrupo(@Param("usuario1Id") Integer usuario1Id,
			@Param("usuario2Id") Integer usuario2Id);

	// En el repositorio MensajeRepository

	@Query("SELECT DISTINCT u.correo " +
	       "FROM Mensaje m " +
	       "JOIN m.usuarioEmisor u " +
	       "JOIN MensajeData md ON md.mensaje = m " +
	       "WHERE (u.id = :userId AND md.usuarioReceptor.id != :userId) " +
	       "OR (md.usuarioReceptor.id = :userId AND u.id != :userId)")
	List<String> findContactEmailsByUserId(@Param("userId") Integer userId);

}

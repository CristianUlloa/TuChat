package tuchat.server.model.tabla;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UsuarioAuth")
public class UsuarioAuth {

	@Id
	private Integer id;

	@Column(name = "usa_passw", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	private boolean usaPassw;

	@Column(name = "correo_confirmado", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
	private boolean correoConfirmado;

	@Column(name = "update_time", nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updateTime;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "current_passw_id", unique = true)
	private AuthPassw currentPassw; // Asegúrate de que la clase AuthPassw esté definida

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "current_codigo_id", unique = true)
	private Codigo currentCodigo; // Asegúrate de que la clase Codigo esté definida

	@OneToOne
	@MapsId // Esto indica que la clave primaria de UsuarioData es la misma que la de
			// Usuario.
	@JoinColumn(name = "id")
	private Usuario usuario;

}

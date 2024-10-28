package tuchat.server.model.tabla;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RegistroGrupo")
public class RegistroGrupo implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -1496365995565728602L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "grupo_id", nullable = false)
    private Integer grupoId;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "usuario_ref_id")
    private Integer usuarioRefId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoAccion tipo;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public enum TipoAccion {
        CREAR_GRUPO,
        AGREGAR_INTEGRANTE,
        MODIFICAR_GRUPO,
        MODIFICAR_ROL,
        QUITAR_INTEGRANTE,
        BORRAR
    }
}

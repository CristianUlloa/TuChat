package tuchat.server.model.tabla;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Grupo")
public class Grupo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2410559830259865023L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_ownner_id", nullable = false)
    private Usuario usuarioOwner;

    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private Archivo icon;

    @Column(name = "borrado", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean borrado; // Se utiliza Boolean para mapear el tinyint

}

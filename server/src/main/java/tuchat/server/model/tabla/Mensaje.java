package tuchat.server.model.tabla;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Mensaje")
public class Mensaje implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7123057511134841563L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_emisor_id", nullable = false)
    private Usuario usuarioEmisor;

    @Lob // Para textos largos, se usa @Lob para indicar que es un campo de tipo texto
    @Column(name = "texto")
    private String texto;

    @Column(name = "create_time", nullable = false,insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @OneToMany(mappedBy = "mensaje")
    private List<MensajeData> mensajeData;

    @ManyToOne
    @JoinColumn(name = "archivo_id")
    private Archivo archivo; // Este campo puede ser nulo, así que no es obligatorio
}

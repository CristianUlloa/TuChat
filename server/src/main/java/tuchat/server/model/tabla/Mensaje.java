package tuchat.server.model.tabla;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "archivo_id")
    private Archivo archivo; // Este campo puede ser nulo, así que no es obligatorio
}

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
@Table(name = "Codigo")
public class Codigo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1202428685083864395L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", length = 20, nullable = false)
    private String codigo;

    @Column(name = "create_time", nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @Column(name = "usado", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean usado;

    @Column(name = "comprobado", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean comprobado;

    @Column(name = "update_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}

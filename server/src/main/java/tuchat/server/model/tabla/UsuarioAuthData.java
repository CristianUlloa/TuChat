package tuchat.server.model.tabla;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UsuarioAuthData")
public class UsuarioAuthData {

    @Id
    private Integer id;

    @Column(name = "usa_passw", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean usaPassw;

    @Column(name = "correo_confirmado", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean correoConfirmado;

    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    @OneToOne
    @JoinColumn(name = "current_passw_id", unique = true)
    private AuthPassw currentPassw; // Asegúrate de que la clase AuthPassw esté definida

    @OneToOne
    @JoinColumn(name = "current_codigo_id", unique = true)
    private Codigo currentCodigo; // Asegúrate de que la clase Codigo esté definida

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario; // Asegúrate de que la clase Usuario esté definida
}

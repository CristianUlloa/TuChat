package tuchat.server.model.tabla;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "UsuarioData")
public class UsuarioData {

    @Id
    private Integer id;

    @Column(name = "nombres", length = 50)
    private String nombres;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(name = "usa_nombre_completo", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean usaNombreCompleto;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @OneToOne
    @MapsId // Esto indica que la clave primaria de UsuarioData es la misma que la de Usuario.
    @JoinColumn(name = "id")
    private Usuario usuario;
}

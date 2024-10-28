package tuchat.server.model.tabla;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EnumRolGrupo")
public class EnumRolGrupo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8939736461812201556L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false, unique = true)
    private String valor;
}

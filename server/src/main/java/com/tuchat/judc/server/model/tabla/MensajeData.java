package com.tuchat.judc.server.model.tabla;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MensajeData")
public class MensajeData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3870726123334661927L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_receptor_id", nullable = false)
    private Usuario usuarioReceptor;

    @ManyToOne
    @JoinColumn(name = "mensaje_id", nullable = false)
    private Mensaje mensaje;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private EnumStatusMensaje status;

    @ManyToOne
    @JoinColumn(name = "grupo_id")  // campo adicional para el grupo
    private Grupo grupo;  // relación con la entidad Grupo
}

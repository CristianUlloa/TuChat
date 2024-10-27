package com.tuchat.judc.server.model.tabla;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RegistroInicioSesion")
public class RegistroInicioSesion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5193023576408854275L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "codigo_id")
    private Codigo codigo;

    @ManyToOne
    @JoinColumn(name = "auth_passw_id")
    private AuthPassw authPassw;

    @Column(name = "valor", length = 50, nullable = false)
    private String valor;

    @Column(name = "exitoso", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean exitoso;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}

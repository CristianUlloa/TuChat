package com.tuchat.judc.server.model.tabla;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EnumStatusMensaje")
public class EnumStatusMensaje implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4767830144207200919L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor", length = 20, nullable = false, unique = true)
    private String valor;
}

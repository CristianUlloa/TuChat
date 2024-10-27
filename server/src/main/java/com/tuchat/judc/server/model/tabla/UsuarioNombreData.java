package com.tuchat.judc.server.model.tabla;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "UsuarioNombreData")
public class UsuarioNombreData {

    @Id
    private Integer id;

    @Column(name = "nombres", length = 50)
    private String nombres;

    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(name = "usa_nombre_completo", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean usaNombreCompleto;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario; // Asegúrate de que la clase Usuario esté definida
}

package com.tuchat.judc.server.model.tabla;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ContactoUsuario")
public class ContactoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "usuario_ref_id", nullable = false)
    private Usuario usuarioRef;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

}

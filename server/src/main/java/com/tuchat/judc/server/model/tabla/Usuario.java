package com.tuchat.judc.server.model.tabla;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Usuario")
public class Usuario implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1160890616646076479L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "correo", length = 50, nullable = false, unique = true)
    private String correo;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private Archivo icon;

    //omitido
//    @OneToMany(mappedBy = "usuario")
//    private List<AuthPassw> authPassws;

    //omitido
//    @OneToMany(mappedBy = "usuario")
//    private List<Codigo> codigos;

    // Relación uno a muchos con Grupo
    @OneToMany(mappedBy = "usuarioOwner")
    private List<Grupo> grupos;

    // Relación uno a muchos con IntegranteGrupo
    @OneToMany(mappedBy = "usuario")
    private List<IntegranteGrupo> integrantesGrupo;

    // Relación uno a muchos con Mensaje (como emisor)
    @OneToMany(mappedBy = "usuarioEmisor")
    private List<Mensaje> mensajesEmitidos;

    // Relación uno a muchos con MensajeData (como receptor)
    @OneToMany(mappedBy = "usuarioReceptor")
    private List<MensajeData> mensajesRecibidos;

    // Relación uno a muchos con RegistroInicioSesion
    @OneToMany(mappedBy = "usuario")
    private List<RegistroInicioSesion> registrosInicioSesion;

    // Relación uno a uno con UsuarioAuthData
    @OneToOne(mappedBy = "usuario")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UsuarioAuthData authData;

    // Relación uno a uno con UsuarioNombreData
    @OneToOne(mappedBy = "usuario")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UsuarioNombreData nombreData;

    
 // Relación uno a muchos con BloqueadoUsuario
    @OneToMany(mappedBy = "usuario")
    private List<BloqueadoUsuario> bloqueados;

    // Relación uno a muchos con ContactoUsuario
    @OneToMany(mappedBy = "usuario")
    private List<ContactoUsuario> contactos;

}

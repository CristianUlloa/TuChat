package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    Usuario findByCorreo(String correo); // Método para buscar un usuario por correo
}

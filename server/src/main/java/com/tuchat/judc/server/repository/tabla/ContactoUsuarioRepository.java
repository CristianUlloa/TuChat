package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.ContactoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoUsuarioRepository extends JpaRepository<ContactoUsuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

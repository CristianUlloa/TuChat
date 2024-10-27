package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.UsuarioAuthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAuthDataRepository extends JpaRepository<UsuarioAuthData, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.UsuarioNombreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioNombreDataRepository extends JpaRepository<UsuarioNombreData, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

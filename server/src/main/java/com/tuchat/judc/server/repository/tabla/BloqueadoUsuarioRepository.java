package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.BloqueadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueadoUsuarioRepository extends JpaRepository<BloqueadoUsuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

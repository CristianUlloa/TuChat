package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.RegistroInicioSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroInicioSesionRepository extends JpaRepository<RegistroInicioSesion, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

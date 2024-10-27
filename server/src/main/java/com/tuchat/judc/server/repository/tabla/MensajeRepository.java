package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

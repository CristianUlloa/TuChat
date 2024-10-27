package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.MensajeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeDataRepository extends JpaRepository<MensajeData, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.EnumStatusMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumStatusMensajeRepository extends JpaRepository<EnumStatusMensaje, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.IntegranteGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegranteGrupoRepository extends JpaRepository<IntegranteGrupo, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

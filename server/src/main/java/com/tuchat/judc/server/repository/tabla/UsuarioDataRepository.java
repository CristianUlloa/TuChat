package com.tuchat.judc.server.repository.tabla;

import com.tuchat.judc.server.model.tabla.UsuarioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDataRepository extends JpaRepository<UsuarioData, Integer> {
}

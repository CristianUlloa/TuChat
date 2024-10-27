package com.tuchat.judc.server.repository.tabla;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuchat.judc.server.model.tabla.Archivo;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
}

package com.tuchat.judc.server.api.config;

import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionCustomizer;

public class TuChatTableNamingCustomizer implements SessionCustomizer {
    @Override
    public void customize(Session session) {
        // Iterar sobre todas las entidades y sus descriptores
        session.getProject().getAliasDescriptors().forEach((alias, descriptor) -> {
            // Obtener el nombre de la tabla para cada entidad
            String tableName = descriptor.getTableName();
            
            // Imprimir el nombre de la tabla
            System.out.println("Entidad: " + alias + ", Nombre de la tabla: " + tableName);
        });
    }
}

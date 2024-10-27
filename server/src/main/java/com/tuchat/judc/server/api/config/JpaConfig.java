package com.tuchat.judc.server.api.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class JpaConfig {

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.tuchat.judc.server.model"); // Cambia esto a tu paquete de entidades
		em.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());

		// Propiedades de JPA
		em.getJpaPropertyMap().put("jakarta.persistence.provider", "org.eclipse.persistence.jpa.PersistenceProvider");
		em.getJpaPropertyMap().put("eclipselink.logging.level", "FINE");
		em.getJpaPropertyMap().put("eclipselink.ddl-generation", "none");

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}

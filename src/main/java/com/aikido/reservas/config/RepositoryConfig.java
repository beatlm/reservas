package com.aikido.reservas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.aikido.reservas.entity.Usuario;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    	config.exposeIdsFor(Usuario.class);//para devolver los ids de las tablas
    }
}

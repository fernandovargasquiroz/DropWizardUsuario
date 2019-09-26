package com.bbdd.usuario.rest;

import com.bbdd.usuario.rest.controller.UsuarioRestController;
import com.bbdd.usuario.rest.dao.UsuarioConfiguration;
import com.bbdd.usuario.rest.dao.UsuarioDAO;
import com.bbdd.usuario.rest.entity.Usuario;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class App extends Application<UsuarioConfiguration> {
    private final HibernateBundle<UsuarioConfiguration> hibernate = new HibernateBundle<UsuarioConfiguration>(Usuario.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(UsuarioConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

	@Override
	public void initialize(Bootstrap<UsuarioConfiguration> b) {
            b.addBundle(hibernate);
	}

	@Override
	public void run(UsuarioConfiguration c, Environment e) throws Exception {	
        final UsuarioDAO dao = new UsuarioDAO(hibernate.getSessionFactory());
        e.jersey().register(new UsuarioRestController(dao));
    
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
package com.bbdd.usuario.rest;

import com.bbdd.usuario.rest.controller.UsuarioRestController;
import com.bbdd.usuario.rest.dao.TrivialAuthenticator;
import com.bbdd.usuario.rest.dao.UsuarioConfiguration;
import com.bbdd.usuario.rest.dao.UsuarioDAO;
import com.bbdd.usuario.rest.entity.Usuario;
import com.google.inject.Singleton;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.errors.EarlyEofExceptionMapper;
import io.dropwizard.jersey.errors.LoggingExceptionMapper;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;


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
        
        //        injector.getInstance(SessionFactory.class); //init DB
        installCorsFilter(e);
        //init all Singletons semi-eagerly
//        REFLECTIONS.getTypesAnnotatedWith(Singleton.class).forEach(injector::getInstance);
//        final Set<Class<?>> resources = REFLECTIONS.getTypesAnnotatedWith(Path.class);
//        register(e.jersey(), resources);


        e.jersey().register(new LoggingExceptionMapper<Throwable>() {
            @Override
            protected String formatErrorMessage(long id, Throwable exception) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                exception.printStackTrace(pw);
                return sw.toString();
            }
        });
        e.jersey().register(new JsonProcessingExceptionMapper(true));
        e.jersey().register(new EarlyEofExceptionMapper());


//        final TrivialAuthenticator instance = injector.getInstance(TrivialAuthenticator.class);
//        environment.jersey().register(new AuthDynamicFeature(
//                new BasicCredentialAuthFilter.Builder<Principal>()
//                        .setAuthenticator(instance)
//                        .setAuthorizer((principal, role) -> false)
//                        .buildAuthFilter()));
//        environment.jersey().register(RolesAllowedDynamicFeature.class);
    
	}
    private void installCorsFilter(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);

        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
    }
	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
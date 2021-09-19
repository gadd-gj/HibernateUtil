package com.uv.jsfhibernate.model;

import com.uv.jsfhibernate.entity.Departamento;
import com.uv.jsfhibernate.entity.Empleado;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

  //Retorna una única instancia de la sesión
    public static SessionFactory getSessionFactory() {

        if (null == sessionFactory) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties(); // Crea una instancia de Properties

                settings.put(Environment.DRIVER, "org.postgresql.Driver"); // Driver de conexión
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/db"); // URL de la bd
                settings.put(Environment.USER, "username"); // Usuario de la bd
                settings.put(Environment.PASS, "password"); // Contraseña del usuario
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true"); // Muestra las consultas SQL en la consola
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update"); // Actualiza la base de datos en caso de algun cambio

                configuration.setProperties(settings); // Agrega las opciones de las configuraciones

               // Mapeo de las clases que contienen anotaciones
                configuration.addAnnotatedClass(Empleado.class);
                configuration.addAnnotatedClass(Departamento.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}

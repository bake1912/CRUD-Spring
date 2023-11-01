package com.ua.config;

import java.util.Properties;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import com.ua.constant.DatabaseSettings;
import com.ua.entity.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseConfig {

	private static Optional<SessionFactory> sessionFactory = Optional.empty();

	public static SessionFactory getSessionFactory() {
		return sessionFactory.orElseGet(() -> buildSessionFactory());
	}

	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		Properties properties = new Properties();
		properties.put(Environment.DRIVER, DatabaseSettings.DRIVER.getValue());
		properties.put(Environment.URL, DatabaseSettings.URL.getValue());
		properties.put(Environment.USER, DatabaseSettings.USER.getValue());
		properties.put(Environment.PASS, DatabaseSettings.PASSWORD.getValue());
		properties.put(Environment.DIALECT, DatabaseSettings.DIALECT.getValue());
		properties.put(Environment.SHOW_SQL, "true");
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		properties.put(Environment.HBM2DDL_AUTO, "validate");
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Student.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		log.info("Hibernate Java Config serviceRegistry created");
		sessionFactory = Optional.ofNullable(configuration.buildSessionFactory(serviceRegistry));
		return sessionFactory.get();
	}
}

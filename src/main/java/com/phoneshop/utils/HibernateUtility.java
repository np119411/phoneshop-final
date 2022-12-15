package com.phoneshop.utils;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	private static EntityManagerFactory entityManagerFactory;
	private static javax.persistence.EntityManager entityManager;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				String hostname = "remotemysql.com";
				String port = "3306";
				String database = "7XqmTB5WTD";
				String user = "7XqmTB5WTD";
				String password = "53GSlMwjIy";
				
				String urlMySQL = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
				

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, urlMySQL);
				settings.put(Environment.USER, user);
				settings.put(Environment.PASS, password);
				// https://stackoverflow.com/questions/43716068/invalid-syntax-error-type-myisam-in-ddl-generated-by-hibernate
				// Error: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version
				// for the right syntax to use near 'type=MyISAM' at line 1
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(com.phoneshop.entities.ProductEntity.class);
				configuration.addAnnotatedClass(com.phoneshop.entities.DetailOrderEntity.class);
				configuration.addAnnotatedClass(com.phoneshop.entities.OrderEntity.class);
				configuration.addAnnotatedClass(com.phoneshop.entities.DetailBillEntity.class);
				configuration.addAnnotatedClass(com.phoneshop.entities.BillEntity.class);
				configuration.addAnnotatedClass(com.phoneshop.entities.UserEntity.class);
				System.out.println("Da khoi tao");
				// 000000configuration.addClass(annotations.ProductEntity.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static EntityManager getSessionFactoryXml() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("PictureStoreWebsite");
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
	public static void main(String[] args) {
		getSessionFactory();
		System.out.println(" Khoi tao ket noi database: " + getSessionFactory());
		System.out.println("Done!");
	}
}

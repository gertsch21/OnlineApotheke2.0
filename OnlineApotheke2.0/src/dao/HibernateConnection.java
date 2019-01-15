package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Produkt_mit_annotation;
import model.Produktgruppe;


public class HibernateConnection {
	private SessionFactory factory;
	private static HibernateConnection con;

	private HibernateConnection() {
		try {
			//this.factory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
			this.factory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("HibernateConnection: Fehler beim erzeugen der SessionFactory von Hibernate!\n" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static HibernateConnection getInstance() {
		if (con == null)
			con = new HibernateConnection();
		return con;
	}

	public SessionFactory getFactory() {
		return this.factory;
	}
}

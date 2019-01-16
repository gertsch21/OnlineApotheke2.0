/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.*;

/**
 * @author Gerhard
 *
 * 
 */

public class DBProduktDAO implements ProduktDAO {

	private static SessionFactory factory;

	/**
	 * Im Konstruktor wird die Verbindung zur Datenbank hergestellt Es werden auch
	 * die Statements definiert, mit welchen man die Daten aus der Datenbank bekommt
	 * Achtung, die User werden aus der Datenbank mit dem Usernamen herausgeholt,
	 * wohingegen es beim Kunden und Mitarbeiter die UserID ist
	 */
	public DBProduktDAO() {
		try {
			factory = new Configuration().configure().
			// addPackage("com.xyz") //add package if used.
					buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("DBProduktDAO:Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	@Override
	public void closeConnection() {
		factory.close();
	}
	
	
	@Override
	public boolean speichereProdukt(Produkt p) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long produktID = null;

		try {
			tx = session.beginTransaction();
			produktID = (Long) session.save(p);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		if (produktID != null)
			return true;
		return false;
	}

	@Override
	public List<Produkt> getProduktListe() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Produkt> liste = new ArrayList<Produkt>();
		try {
			tx = session.beginTransaction();
			List produktliste = session.createQuery("FROM Produkt").list();
			for (Iterator iterator = produktliste.iterator(); iterator.hasNext();) {
				liste.add((Produkt) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return liste;
	}

	@Override
	public List<Spezielle_salbe> getSpezielleSalbenListe() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Spezielle_salbe> liste = new ArrayList<Spezielle_salbe>();
		try {
			tx = session.beginTransaction();
			List spezielle_salben = session.createQuery("FROM Spezielle_salbe").list();
			for (Iterator iterator = spezielle_salben.iterator(); iterator.hasNext();) {
				liste.add((Spezielle_salbe) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return liste;
	}

	@Override
	public List<ZugekauftesProdukt> getZugekauftesProduktListe() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<ZugekauftesProdukt> liste = new ArrayList<ZugekauftesProdukt>();
		try {
			tx = session.beginTransaction();
			List zugekaufte_produkte = session.createQuery("FROM ZugekauftesProdukt").list();
			for (Iterator iterator = zugekaufte_produkte.iterator(); iterator.hasNext();) {
				liste.add((ZugekauftesProdukt) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {

		}
		return liste;
	}

	@Override
	public Produkt getProduktByProduktID(int produkt_id) {
		for(Produkt i : getProduktListe())
			if(i.getProdukt_id() == produkt_id)
				return i;
		return null;
	}

	@Override
	public void loescheProduktByProduktID(int produkt_id) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Produkt produkt = getProduktByProduktID(produkt_id);
			session.delete(produkt);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}

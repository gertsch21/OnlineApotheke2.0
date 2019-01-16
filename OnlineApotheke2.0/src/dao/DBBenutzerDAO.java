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
 *         Diese Klasse ist dafür zuständig, die Daten aus der Datenbank(Oracle)
 *         zu holen und diese Managementklassen zu übergeben
 */
public class DBBenutzerDAO implements BenutzerDAO {
	private static SessionFactory factory;

	/**
	 * Im Konstruktor wird die Verbindung zur Datenbank hergestellt Es werden auch
	 * die Statements definiert, mit welchen man die Daten aus der Datenbank bekommt
	 * Achtung, die User werden aus der Datenbank mit dem Usernamen herausgeholt,
	 * wohingegen es beim Kunden und Mitarbeiter die UserID ist
	 */
	public DBBenutzerDAO() {
		try {
			factory = new Configuration().configure().
			// addPackage("com.xyz") //add package if used.
					buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("DBBenutzerDAO: Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public void closeConnection() {
		factory.close();
	}
	
	
	@Override
	public boolean reklamationErstellen(Reklamation r) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer reklamation_id = null;

		try {
			tx = session.beginTransaction();
			reklamation_id = (Integer) session.save(r);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		if (reklamation_id != null)
			return true;
		return false;
	}
	
	
	@Override
	public boolean speichereKunde(Kunde k) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer kundeID = null;

		try {
			tx = session.beginTransaction();
			kundeID = (Integer) session.save(k);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		if (kundeID != null)
			return true;
		return false;
	}

	@Override
	public boolean speichereMitarbeiter(Mitarbeiter m) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer mitarbeiterID = null;

		try {
			tx = session.beginTransaction();
			mitarbeiterID = (Integer) session.save(m);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		if (mitarbeiterID != null)
			return true;
		return false;

	}

	@Override
	public List<Benutzer> getBenutzerList() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Benutzer> liste = new ArrayList<Benutzer>();
		try {
			tx = session.beginTransaction();
			List benutzerliste = session.createQuery("FROM Benutzer").list();
			for (Iterator iterator = benutzerliste.iterator(); iterator.hasNext();) {
				liste.add((Benutzer) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return liste;
	}

	@Override
	public List<Kunde> getKundenList() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Kunde> liste = new ArrayList<Kunde>();
		try {
			tx = session.beginTransaction();
			List kunden = session.createQuery("FROM Kunde").list();
			for (Iterator iterator = kunden.iterator(); iterator.hasNext();) {
				liste.add((Kunde) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();

		}
		return liste;
	}

	@Override
	public List<Mitarbeiter> getMitarbeiterList() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Mitarbeiter> liste = new ArrayList<Mitarbeiter>();
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Mitarbeiter").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				liste.add((Mitarbeiter) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return liste;
	}

	@Override
	public Benutzer getBenutzerByUName(String benutzername) {
		for (Benutzer x : getBenutzerList()) {
			if (x.getBenutzername() == benutzername)
				return x;
		}
		return null;
	}

	@Override
	public Kunde getKundeByUsername(String benutzername) {
		for (Kunde x : getKundenList()) {
			if (x.getBenutzername().equals(benutzername))
				return x;
		}
		return null;
	}

	@Override
	public Mitarbeiter getMitarbeiterByUsername(String benutzername) {
		for (Mitarbeiter x : getMitarbeiterList()) {
			if (x.getBenutzername().equals(benutzername))
				return x;
		}
		return null;
	}

	@Override
	public void loescheKundeByUname(String benutzername) {
		Kunde kunde = getKundeByUsername(benutzername);
		if(kunde == null) return;
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			session.delete(kunde);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void loescheMitarbeiterByUname(String benutzername) {
		Mitarbeiter employee = getMitarbeiterByUsername(benutzername);
		if(employee == null) return;
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.delete(employee);
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

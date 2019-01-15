import java.util.List;
import java.util.Random;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import model.Benutzer;
import model.Employee;
import model.Kunde;
import model.Mitarbeiter;
import model.Spezielle_salbe;
import model.ZugekauftesProdukt;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	private static SessionFactory factory;

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().
			// addPackage("com.xyz") //add package if used.
					buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		HibernateTest ME = new HibernateTest();

//		int neueKundenID = ME.addKunde("kunde" + (new Random().nextInt(5000)), "hibernate", "hibernate", "hibernate",
//				new Date(1993, 11, 21), "hibernate", "hibernate", "land", 2020, "hollabrunn", "Anton ehren", 2, "ich",
//				new Date(1999, 2, 5), "m");
//		int neueMitarbeiterID = ME.addMitarbeiter("mitarbeiter" + (new Random().nextInt(5000)), "hibernate",
//				"hibernate", "hibernate", new Date(1993, 11, 21), "hibernate", "hibernate", "land", 2020, "hollabrunn",
//				"Anton ehren", 2, "ich", "4032/211193", 2500, new Date(2000, 11, 11));
//		
		
		System.out.println("Kunden:");
		ME.printListe(ME.getListKunden());
		//ME.updateMitarbeiter(34, "neu", "neu", "neu", "neu", new Date(1999,11,11), "neu", "neu", "neu", 2222, "neu", "neu", 2, "neu", "neu", 222, new Date(1999,7,7));
		System.out.println("Mitarbeiter:");
		ME.printListe(ME.getListMitarbeiter());

		System.out.println("Salben: ");
		ME.printListe(ME.getListSalben());
		
		System.out.println("Zugekaufte Produkte: ");
		ME.printListe(ME.getListZugekaufteProdukte());
		
		factory.close();
	}

	private void printListe(List listKunden) {
		for(Object item : listKunden){
			System.out.println(item);
		}
	}
	
	
	/* Method to READ all the employees */
	public List<Spezielle_salbe> getListSalben() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Spezielle_salbe> liste = new ArrayList<Spezielle_salbe>();
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Spezielle_salbe").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				liste.add((Spezielle_salbe) iterator.next());
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
	
	/* Method to READ all the employees */
	public List<ZugekauftesProdukt> getListZugekaufteProdukte() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<ZugekauftesProdukt> liste = new ArrayList<ZugekauftesProdukt>();
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM ZugekauftesProdukt").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				liste.add((ZugekauftesProdukt) iterator.next());
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
	/* Method to READ all the employees */
	public List<Kunde> getListKunden() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Kunde> liste = new ArrayList<Kunde>();
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Kunde").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
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

	/* Method to CREATE an employee in the database */
	public Integer addKunde(String benutzername, String passwort, String vorname, String nachname, Date geburtsdatum,
			String email, String telnr, String land, int plz, String ort, String strasse, int hausnummer,
			String hausnummer_zus, Date anmeldedatum, String geschlecht) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer kundeID = null;

		try {
			tx = session.beginTransaction();
			Kunde k = new Kunde(benutzername, passwort, vorname, nachname, geburtsdatum, email, telnr, land, plz, ort,
					strasse, hausnummer, hausnummer_zus, anmeldedatum, geschlecht);
			kundeID = (Integer) session.save(k);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return kundeID;
	}

	public Integer addMitarbeiter(String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus, String svnr, double gehalt, Date eintrittsdatum) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer mitarbeiterID = null;

		try {
			tx = session.beginTransaction();
			Mitarbeiter m = new Mitarbeiter(benutzername, passwort, vorname, nachname, geburtsdatum, email, telnr, land,
					plz, ort, strasse, hausnummer, hausnummer_zus, svnr, gehalt, eintrittsdatum);
			mitarbeiterID = (Integer) session.save(m);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return mitarbeiterID;
	}

	public List<Mitarbeiter> getListMitarbeiter() {
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

	
	public void updateMitarbeiter(int benutzer_id, String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus, String svnr, double gehalt, Date eintrittsdatum)  {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Mitarbeiter mitarbeiter = (Mitarbeiter) session.get(Mitarbeiter.class, benutzer_id);
			mitarbeiter.setBenutzername(benutzername);
			mitarbeiter.setPasswort(passwort);
			mitarbeiter.setVorname(vorname);
			mitarbeiter.setNachname(nachname);
			mitarbeiter.setGeburtsdatum(geburtsdatum);
			mitarbeiter.setEmail(email);
			mitarbeiter.setTelnr(telnr);
			mitarbeiter.setLand(land);
			mitarbeiter.setPlz(plz);
			mitarbeiter.setOrt(ort);
			mitarbeiter.setStrasse(strasse);
			mitarbeiter.setHausnummer(hausnummer);
			mitarbeiter.setHausnummer_zus(hausnummer_zus);
			mitarbeiter.setSvnr(svnr);
			mitarbeiter.setGehalt(gehalt);
			mitarbeiter.setEintrittsdatum(eintrittsdatum);
			session.update(mitarbeiter);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateKunde(int benutzer_id, String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus,  Date anmeldedatum, String geschlecht)  {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Kunde kunde = (Kunde) session.get(Kunde.class, benutzer_id);
			kunde.setBenutzername(benutzername);
			kunde.setPasswort(passwort);
			kunde.setVorname(vorname);
			kunde.setNachname(nachname);
			kunde.setGeburtsdatum(geburtsdatum);
			kunde.setEmail(email);
			kunde.setTelnr(telnr);
			kunde.setLand(land);
			kunde.setPlz(plz);
			kunde.setOrt(ort);
			kunde.setStrasse(strasse);
			kunde.setHausnummer(hausnummer);
			kunde.setHausnummer_zus(hausnummer_zus);
			kunde.setAnmeldedatum(anmeldedatum);
			kunde.setGeschlecht(geschlecht);
			session.update(kunde);
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
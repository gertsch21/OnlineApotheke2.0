/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Benutzer;
import model.Einkaufswagen;
import model.Item;
import model.Produkt;

/**
 * @author Gerhard
 *
 */
public class DBBestellungDAO implements BestellungDAO {
	private static SessionFactory factory;

	public DBBestellungDAO() {
		try {
			factory = new Configuration().configure().
			// addPackage("com.xyz") //add package if used.
					buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("DBBestellungDAO:Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override 
	public void closeConnection() {
		factory.close();
	}
	
	@Override
	public boolean speichereItem(Item i) {
		Session session = factory.openSession();
		Transaction tx = null;
		int itemID = 1;

		try {
			tx = session.beginTransaction();
			System.out.println(session.save(i));
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (itemID != 0)
			return true;
		return false;
	}

	@Override
	public boolean speichereEinkaufswagen(Einkaufswagen e) {
		Session session = factory.openSession();
		Transaction tx = null;
		int einkaufswagenID = 0;

		try {
			tx = session.beginTransaction();
			einkaufswagenID = (int) session.save(e);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(einkaufswagenID);
		for(Item item:e.getItems()) {
			speichereItem(item);
		}
		if (einkaufswagenID != 0)
			return true;
		return false;
	}
	
	@Override
	public boolean aktualisiereEinkaufswagen(Einkaufswagen e) {
		Session session = factory.openSession();
		Transaction tx = null;
		Einkaufswagen einkaufswagen =null;
		try {
			tx = session.beginTransaction();
			einkaufswagen = (Einkaufswagen) session.merge(e);
			session.saveOrUpdate(einkaufswagen);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		if (einkaufswagen != null)
			return true;
		return false;
	}

	@Override
	public Set<Item> getItemListbyEinkaufswagen(int einkaufswagen_id) {
		Einkaufswagen einkaufswagen = getEinkaufswagenByID(einkaufswagen_id);
		if (einkaufswagen != null)
			return (einkaufswagen.getItems());
		return new HashSet<Item>();

	}

	@Override
	public List<Einkaufswagen> getEinkaufswagenList() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Einkaufswagen> liste = new ArrayList<Einkaufswagen>();
		try {
			tx = session.beginTransaction();
			List einkaufswagenliste = session.createQuery("FROM Einkaufswagen").list();
			for (Iterator iterator = einkaufswagenliste.iterator(); iterator.hasNext();) {
				liste.add((Einkaufswagen) iterator.next());
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
	public Item getItemByID(int item_id, int einkaufswagen_id) {
		Einkaufswagen e = getEinkaufswagenByID(einkaufswagen_id);
		if (e == null)
			return null;
		for (Item i : e.getItems())
			if (i.getItem_id() == item_id)
				return i;
		return null;
	}

	@Override
	public Einkaufswagen getEinkaufswagenByID(int einkaufswagen_id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Einkaufswagen einkaufswagen = null;
		try {
			tx = session.beginTransaction();

			einkaufswagen = (Einkaufswagen) session.get(Einkaufswagen.class, einkaufswagen_id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return einkaufswagen;
	}

	@Override
	public void loescheItem(int item_id, int einkaufswagen_id) {
		Item item = getItemByID(item_id, einkaufswagen_id);
		if(item ==null ) return;
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			
			System.out.println("drinnen: " + item);
			session.delete(item);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void loescheEinkaufswagen(int einkaufswagen_id) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Einkaufswagen ein = getEinkaufswagenByID(einkaufswagen_id);
			
			session.delete(ein);
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
	public Set<Item> getAllItems() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Item> liste = new ArrayList<Item>();
		try {
			tx = session.beginTransaction();
			List itemliste = session.createQuery("FROM Item").list();
			for (Iterator iterator = itemliste.iterator(); iterator.hasNext();) {
				liste.add((Item) iterator.next());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new HashSet<Item>(liste);
	}

	@Override
	public Einkaufswagen getEinkaufswagenByBenutzerID(int benutzer_id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Einkaufswagen einkaufswagen = null;
		try {
			tx = session.beginTransaction();
			einkaufswagen = (Einkaufswagen) session.createQuery("from Einkaufswagen ORDER BY bestelldatum DESC").setMaxResults(1).uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return einkaufswagen;
	}

}
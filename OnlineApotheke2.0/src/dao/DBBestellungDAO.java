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
		Long itemID = null;

		try {
			tx = session.beginTransaction();
			itemID = (Long) session.save(i);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (itemID != null)
			return true;
		return false;
	}

	@Override
	public boolean speichereEinkaufswagen(Einkaufswagen e) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long einkaufswagenID = null;

		try {
			tx = session.beginTransaction();
			einkaufswagenID = (Long) session.save(e);
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		if (einkaufswagenID != null)
			return true;
		return false;
	}

	@Override
	public List<Item> getItemListbyEinkaufswagen(int einkaufswagen_id) {
		Einkaufswagen einkaufswagen = getEinkaufswagenByID(einkaufswagen_id);
		if (einkaufswagen != null)
			return new ArrayList<Item>(einkaufswagen.getItems());
		return new ArrayList<Item>();

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

}
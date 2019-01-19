package management;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Spezielle_salbe;
import model.ZugekauftesProdukt;
import model.Inhaltsstoff;
import model.Produkt;
import dao.ProduktDAO;

import dao.DBProduktDAO;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public class Produktmanagement {
	private static Produktmanagement produktmanagementInstance = null;
	private ProduktDAO produkt_dao;

	private Produktmanagement() {
		produkt_dao = new DBProduktDAO();
	}

	public static Produktmanagement getInstance() {
		if (produktmanagementInstance == null)
			produktmanagementInstance = new Produktmanagement();
		return produktmanagementInstance;
	}

	public boolean SalbeAnlegen(String name, double preis, String anmerkung, double volumen, String zusatzinformationen,
			Date erstelldatum, Set<Inhaltsstoff> enthaltene_inhaltsstoffe) {
		try {
			produkt_dao.speichereProdukt(new Spezielle_salbe(name, preis, anmerkung, volumen, zusatzinformationen,
					erstelldatum, enthaltene_inhaltsstoffe));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean ZugekauftesProduktAnlegen(String name, double preis, String anmerkung, int menge_enthalten, String hersteller, String wirkstoff, String wirkungsweise,
			String anwendungsweise) {
		try {
			produkt_dao.speichereProdukt(new ZugekauftesProdukt(name, preis, anmerkung, menge_enthalten, hersteller, wirkstoff, wirkungsweise, anwendungsweise));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	
	public List<Produkt> getAlleProdukt() {
		return produkt_dao.getProduktListe();
	}

	
	public Produkt getProduktByProduktID(int produkt_id) {
		return produkt_dao.getProduktByProduktID(produkt_id);
	}


	public List<Produkt> getProduktByProduktName(String prodName) {
		List<Produkt> returnListe = new ArrayList<Produkt>();
		for(Produkt p : produkt_dao.getProduktListe())
			if(p.getName().toLowerCase().contains(prodName.toLowerCase()))
				returnListe.add(p);
		return returnListe;
	}

	public void loescheProduktByID(int produkt_id) {
		produkt_dao.loescheProduktByProduktID(produkt_id);
	}
	
	public List<Produkt> getProdukteByKriterium(String krit){
		String kriterium = krit.trim().toLowerCase();
		List<Produkt> alleProdukte = this.getAlleProdukt();
		List<Produkt> ausgewaehlte = new ArrayList<Produkt>();
		ZugekauftesProdukt zp;
		Spezielle_salbe ss;
		boolean zwischen = false;
		
		//durchgehen ob das kriterium in einem wort vorkommt der attribute
		for(Produkt x : alleProdukte) {
			zwischen = false;
			if(x instanceof ZugekauftesProdukt) {
				zp = (ZugekauftesProdukt) x;
				if(zp.getAnmerkung()!=null && zp.getAnmerkung().toLowerCase().contains(kriterium)) zwischen = true;
				if(zp.getAnwendungsweise() != null && zp.getAnwendungsweise().toLowerCase().contains(kriterium)) zwischen = true;
				if(zp.getHersteller() != null && zp.getHersteller().toLowerCase().contains(kriterium)) zwischen = true;
				if(zp.getName() != null && zp.getName().toLowerCase().contains(kriterium)) zwischen = true;
				if(zp.getWirkstoff() != null && zp.getWirkstoff().toLowerCase().contains(kriterium)) zwischen = true;
				if(zp.getWirkungsweise() != null && zp.getWirkungsweise().toLowerCase().contains(kriterium)) zwischen = true;
				if(zwischen) ausgewaehlte.add(x);
			}else {
				ss = (Spezielle_salbe) x;
				if(ss.getAnmerkung()!=null && ss.getAnmerkung().toLowerCase().contains(kriterium)) zwischen = true;
				if(ss.getName()!=null && ss.getName().toLowerCase().contains(kriterium)) zwischen = true;
				if(ss.getZusatzinformationen()!=null && ss.getZusatzinformationen().toLowerCase().contains(kriterium)) zwischen = true;
				for(Inhaltsstoff i : ss.getEnthaltene_inhaltsstoffe())
					if(i.getStoff_name()!=null && i.getStoff_name().toLowerCase().contains(kriterium)) zwischen = true;
				if(zwischen) ausgewaehlte.add(x);
			}
		}
		
		return ausgewaehlte;
	}


	}

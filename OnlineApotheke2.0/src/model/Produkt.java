package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
@Entity
@Table(name = "produkt")
public class Produkt {
	@Id
	@GeneratedValue
	@Column(name = "produkt_id")
	private int produkt_id;
	private String name;
	private double preis;
	private int menge_enthalten;
	private String hersteller;
	private String wirkstoff;
	private String wirkungsweise;
	private String anwendungsweise;
	private String anmerkung;
	private int ersteller_id;
	private int produktgruppe_id;

	public Produkt(int produkt_id, String name, double preis, int menge_enthalten, String hersteller, String wirkstoff,
			String wirkungsweise, String anwendungsweise, String anmerkung, int ersteller_id, int produktgruppe_id) {
		super();
		this.produkt_id = produkt_id;
		this.name = name;
		this.preis = preis;
		this.menge_enthalten = menge_enthalten;
		this.hersteller = hersteller;
		this.wirkstoff = wirkstoff;
		this.wirkungsweise = wirkungsweise;
		this.anwendungsweise = anwendungsweise;
		this.anmerkung = anmerkung;
		this.ersteller_id = ersteller_id;
		this.produktgruppe_id = produktgruppe_id;
	}

	public Produkt() {
		super();
	}

	@Override
	public String toString() {
		return "Produkt [produkt_id=" + produkt_id + ", name=" + name + ", preis=" + preis + ", menge_enthalten="
				+ menge_enthalten + ", hersteller=" + hersteller + ", wirkstoff=" + wirkstoff + ", wirkungsweise="
				+ wirkungsweise + ", anwendungsweise=" + anwendungsweise + ", anmerkung=" + anmerkung
				+ ", ersteller_id=" + ersteller_id + ", produktgruppe_id=" + produktgruppe_id + "]";
	}

	public int getProdukt_id() {
		return produkt_id;
	}

	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getMenge_enthalten() {
		return menge_enthalten;
	}

	public void setMenge_enthalten(int menge_enthalten) {
		this.menge_enthalten = menge_enthalten;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getWirkstoff() {
		return wirkstoff;
	}

	public void setWirkstoff(String wirkstoff) {
		this.wirkstoff = wirkstoff;
	}

	public String getWirkungsweise() {
		return wirkungsweise;
	}

	public void setWirkungsweise(String wirkungsweise) {
		this.wirkungsweise = wirkungsweise;
	}

	public String getAnwendungsweise() {
		return anwendungsweise;
	}

	public void setAnwendungsweise(String anwendungsweise) {
		this.anwendungsweise = anwendungsweise;
	}

	public String getAnmerkung() {
		return anmerkung;
	}

	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}

	public int getErsteller_id() {
		return ersteller_id;
	}

	public void setErsteller_id(int ersteller_id) {
		this.ersteller_id = ersteller_id;
	}

	public int getProduktgruppe_id() {
		return produktgruppe_id;
	}

	public void setProduktgruppe_id(int produktgruppe_id) {
		this.produktgruppe_id = produktgruppe_id;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		Produkt obj2 = (Produkt) obj;
		if ((this.produkt_id == obj2.getProdukt_id()) && (this.name.equals(obj2.getName()))) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int tmp = 0;
		tmp = (produkt_id + name).hashCode();
		return tmp;
	}

}

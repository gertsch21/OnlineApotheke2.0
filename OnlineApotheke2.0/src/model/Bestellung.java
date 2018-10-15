/**
 * 
 */
/**
 * 
 */
package model;

import java.sql.Date;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public class Bestellung {
	private int bestellung_id;
	private Date bestelldatum;
	private int kunde_id;
	
	
	
	
	public Bestellung() {
		super();
	}

	public Bestellung(int bestellung_id, Date bestelldatum, int kunde_id) {
		super();
		this.bestellung_id = bestellung_id;
		this.bestelldatum = bestelldatum;
		this.kunde_id = kunde_id;
	}

	@Override
	public String toString() {
		return "Bestellung [bestellung_id=" + bestellung_id + ", bestelldatum=" + bestelldatum + ", kunde_id="
				+ kunde_id + "]";
	}
	
	public int getBestellung_id() {
		return bestellung_id;
	}
	public void setBestellung_id(int bestellung_id) {
		this.bestellung_id = bestellung_id;
	}
	public Date getBestelldatum() {
		return bestelldatum;
	}
	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}
	public int getKunde_id() {
		return kunde_id;
	}
	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}
	
}

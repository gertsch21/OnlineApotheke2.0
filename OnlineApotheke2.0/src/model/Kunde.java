/**
 * 
 */
package model;

import java.sql.Date;

/**
 * @author Gerhard
 *
 */
public class Kunde extends Benutzer {
	
	private Date anmeldedatum;
	private String geschlecht;
	private int betreuer_id;
	
	
	
	public Kunde() {
		super();
	}
	
	
	public Kunde(int benutzer_id, String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String tel_nr, String land, int plz, String ort, String strasse,
			int hausNr, String hnr_zusatz, Date anmeldedatum, String geschlecht, int betreuer_id) {
		super(benutzer_id, benutzername, passwort, vorname, nachname, geburtsdatum, email, tel_nr, land, plz, ort, strasse,
				hausNr, hnr_zusatz);
		this.anmeldedatum = anmeldedatum;
		this.geschlecht = geschlecht;
		this.betreuer_id = betreuer_id;
	}
	@Override
	public String toString() {
		return "Kunde [anmeldedatum=" + anmeldedatum + ", geschlecht=" + geschlecht + ", betreuer_id=" + betreuer_id
				+ "]";
	}
	public Date getAnmeldedatum() {
		return anmeldedatum;
	}
	public void setAnmeldedatum(Date anmeldedatum) {
		this.anmeldedatum = anmeldedatum;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	public int getBetreuer_id() {
		return betreuer_id;
	}
	public void setBetreuer_id(int betreuer_id) {
		this.betreuer_id = betreuer_id;
	}
	}

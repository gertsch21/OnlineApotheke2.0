package model;

import java.sql.Date;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public class Benutzer {
	private int benutzer_id;
	private String benutzername;
	private String passwort;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String email;
	private String tel_nr;
	
	private String land;
	private int plz;
	private String ort;
	private String strasse;
	private int hausNr;
	private String hnr_zusatz;
	
	
	
	
	public Benutzer() {
		super();
	}
	
	
	public Benutzer(int benutzer_id, String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String tel_nr, String land, int plz, String ort, String strasse,
			int hausNr, String hnr_zusatz) {
		super();
		this.benutzer_id = benutzer_id;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.email = email;
		this.tel_nr = tel_nr;
		this.land = land;
		this.plz = plz;
		this.ort = ort;
		this.strasse = strasse;
		this.hausNr = hausNr;
		this.hnr_zusatz = hnr_zusatz;
	}
	
	
	@Override
	public String toString() {
		return "Benutzer [benutzer_id=" + benutzer_id + ", benutzername=" + benutzername + ", passwort=" + passwort
				+ ", vorname=" + vorname + ", nachname=" + nachname + ", geburtsdatum=" + geburtsdatum + ", email="
				+ email + ", tel_nr=" + tel_nr + ", land=" + land + ", plz=" + plz + ", ort=" + ort + ", strasse="
				+ strasse + ", hausNr=" + hausNr + ", hnr_zusatz=" + hnr_zusatz + "]";
	}
	
	
	public int getBenutzer_id() {
		return benutzer_id;
	}
	public void setBenutzer_id(int benutzer_id) {
		this.benutzer_id = benutzer_id;
	}
	public String getBenutzername() {
		return benutzername;
	}
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_nr() {
		return tel_nr;
	}
	public void setTel_nr(String tel_nr) {
		this.tel_nr = tel_nr;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public int getHausNr() {
		return hausNr;
	}
	public void setHausNr(int hausNr) {
		this.hausNr = hausNr;
	}
	public String getHnr_zusatz() {
		return hnr_zusatz;
	}
	public void setHnr_zusatz(String hnr_zusatz) {
		this.hnr_zusatz = hnr_zusatz;
	}
	
}

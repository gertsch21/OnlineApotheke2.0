/**
 * 
 */
package model;

import java.sql.Date;

/**
 * 
 * @author Gerhard
 * 
 */
public class Mitarbeiter extends Benutzer {
	
	private int mitarbeiter_id;
	private int svnr;
	private double gehalt; 
	private Date eintrittsdatum;
	private int vertreter_id_von;
	
	public Mitarbeiter() {
		super();
	}
	
	public Mitarbeiter(int benutzer_id, String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String tel_nr, String land, int plz, String ort, String strasse,
			int hausNr, String hnr_zusatz, int mitarbeiter_id, int svnr, double gehalt, Date eintrittsdatum, int vertreter_id_von) {
		super(benutzer_id, benutzername, passwort, vorname, nachname, geburtsdatum, email, tel_nr, land, plz, ort, strasse,
				hausNr, hnr_zusatz);
		this.mitarbeiter_id = mitarbeiter_id;
		this.svnr = svnr;
		this.gehalt = gehalt;
		this.eintrittsdatum = eintrittsdatum;
		this.vertreter_id_von = vertreter_id_von;
	}


	@Override
	public String toString() {
		return "Mitarbeiter [mitarbeiter_id=" + mitarbeiter_id + ", svnr=" + svnr + ", gehalt=" + gehalt
				+ ", eintrittsdatum=" + eintrittsdatum + ", vertreter_id_von=" + vertreter_id_von + "]";
	}
	
	
	public int getMitarbeiter_id() {
		return mitarbeiter_id;
	}
	public void setMitarbeiter_id(int mitarbeiter_id) {
		this.mitarbeiter_id = mitarbeiter_id;
	}
	public int getSvnr() {
		return svnr;
	}
	public void setSvnr(int svnr) {
		this.svnr = svnr;
	}
	public double getGehalt() {
		return gehalt;
	}
	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
	public Date getEintrittsdatum() {
		return eintrittsdatum;
	}
	public void setEintrittsdatum(Date eintrittsdatum) {
		this.eintrittsdatum = eintrittsdatum;
	}
	public int getVertreter_id_von() {
		return vertreter_id_von;
	}
	public void setVertreter_id_von(int vertreter_id_von) {
		this.vertreter_id_von = vertreter_id_von;
	}

	
}

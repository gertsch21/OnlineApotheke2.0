package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * Die Klasse Administrator dient zur Verwaltung aller Benutzer und Produkte die
 * registriert sind.
 */
@Entity
@Table(name="Mitarbeiter")
@PrimaryKeyJoinColumn(name="benutzer_id")
public class Mitarbeiter extends Benutzer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mitarbeiter_id;
	private String svnr;
	private double gehalt;
	private Date eintrittsdatum;

	@OneToOne(fetch = FetchType.EAGER)
	private Mitarbeiter chef_id;


	public Mitarbeiter(String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus, String svnr, double gehalt, Date eintrittsdatum) {
		super(benutzername, passwort, vorname, nachname,
				geburtsdatum, email, telnr, land, plz, ort, strasse,
				hausnummer, hausnummer_zus);
		
		this.svnr = svnr;
		this.gehalt = gehalt;
		this.eintrittsdatum = eintrittsdatum;
	}

	@Override
	public String toString() {
		return "Mitarbeiter [mitarbeiter_id=" + mitarbeiter_id + ", svnr=" + svnr + ", gehalt=" + gehalt
				+ ", eintrittsdatum=" + eintrittsdatum + ", toString()=" + super.toString() + ", getBenutzer_id()="
				+ getBenutzer_id() + ", getBenutzername()=" + getBenutzername() + ", getPasswort()=" + getPasswort()
				+ ", getVorname()=" + getVorname() + ", getNachname()=" + getNachname() + ", getGeburtsdatum()="
				+ getGeburtsdatum() + ", getEmail()=" + getEmail() + ", getTelnr()=" + getTelnr() + ", getLand()="
				+ getLand() + ", getPlz()=" + getPlz() + ", getOrt()=" + getOrt() + ", getStrasse()=" + getStrasse()
				+ ", getHausnummer()=" + getHausnummer() + ", getHausnummer_zus()=" + getHausnummer_zus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", Chef: "+chef_id+"]";
	}

	public Mitarbeiter getChef_id() {
		return chef_id;
	}

	public void setChef_id(Mitarbeiter chef_id) {
		this.chef_id = chef_id;
	}

	public Mitarbeiter() {
		super();
	}

	public int getMitarbeiter_id() {
		return mitarbeiter_id;
	}

	public void setMitarbeiter_id(int mitarbeiter_id) {
		this.mitarbeiter_id = mitarbeiter_id;
	}

	public String getSvnr() {
		return svnr;
	}

	public void setSvnr(String svnr) {
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
}

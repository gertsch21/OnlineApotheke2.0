package model;

import java.sql.Date;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
@Entity
@Table(name = "Benutzer")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Benutzer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int benutzer_id;

	private String benutzername;
	private String passwort;
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String email;
	private String telnr;

	private String land;
	private int plz;
	private String ort;
	private String strasse;
	private int hausnummer;
	private String hausnummer_zus;

	@Override
	public String toString() {
		return "Benutzer [benutzer_id=" + benutzer_id + ", benutzername=" + benutzername + ", passwort=" + passwort
				+ ", vorname=" + vorname + ", nachname=" + nachname + ", geburtsdatum=" + geburtsdatum + ", email="
				+ email + ", telnr=" + telnr + ", land=" + land + ", plz=" + plz + ", ort=" + ort + ", strasse="
				+ strasse + ", hausnummer=" + hausnummer + ", hausnummer_zus=" + hausnummer_zus + "]";
	}

	public Benutzer() {};
	
	public Benutzer(String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus) {
		super();
		this.benutzer_id = benutzer_id;
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.email = email;
		this.telnr = telnr;
		this.land = land;
		this.plz = plz;
		this.ort = ort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.hausnummer_zus = hausnummer_zus;
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

	public String getTelnr() {
		return telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
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

	public int getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getHausnummer_zus() {
		return hausnummer_zus;
	}

	public void setHausnummer_zus(String hausnummer_zus) {
		this.hausnummer_zus = hausnummer_zus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
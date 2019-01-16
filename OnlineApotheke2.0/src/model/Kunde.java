package model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Kunde")
@PrimaryKeyJoinColumn(name = "benutzer_id")
public class Kunde extends Benutzer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date anmeldedatum;
	private String geschlecht;

	@OneToMany(mappedBy="kaeufer", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Spezielle_salbe> beauftragteSalben;

	@OneToMany(mappedBy="kunde", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Einkaufswagen> einkaufswagen;

	@OneToMany(mappedBy="kritiker", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Reklamation> reklamationen;

	public Kunde(String benutzername, String passwort, String vorname, String nachname, Date geburtsdatum, String email,
			String telnr, String land, int plz, String ort, String strasse, int hausnummer, String hausnummer_zus,
			Date anmeldedatum, String geschlecht) {
		super(benutzername, passwort, vorname, nachname, geburtsdatum, email, telnr, land, plz, ort, strasse,
				hausnummer, hausnummer_zus);
		this.anmeldedatum = anmeldedatum;
		this.geschlecht = geschlecht;
	}

	@Override
	public String toString() {
		return "Kunde [anmeldedatum=" + anmeldedatum + ", geschlecht=" + geschlecht + ", toString()=" + super.toString()
				+ ", getBenutzer_id()=" + getBenutzer_id() + ", getBenutzername()=" + getBenutzername()
				+ ", getPasswort()=" + getPasswort() + ", getVorname()=" + getVorname() + ", getNachname()="
				+ getNachname() + ", getGeburtsdatum()=" + getGeburtsdatum() + ", getEmail()=" + getEmail()
				+ ", getTelnr()=" + getTelnr() + ", getLand()=" + getLand() + ", getPlz()=" + getPlz() + ", getOrt()="
				+ getOrt() + ", getStrasse()=" + getStrasse() + ", getHausnummer()=" + getHausnummer()
				+ ", getHausnummer_zus()=" + getHausnummer_zus() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", Salben beauftragt: " + getBeauftragteSalben() + ", Einkaufswagen: "+ einkaufswagen + 
				", Reklamationen: " + reklamationen +" ]";
	}

	public Kunde() {
		super();
	}

	public Set<Einkaufswagen> getEinkaufswagen() {
		return einkaufswagen;
	}

	public void setEinkaufswagen(Set<Einkaufswagen> einkaufswagen) {
		this.einkaufswagen = einkaufswagen;
	}

	public Set<Spezielle_salbe> getBeauftragteSalben() {
		return beauftragteSalben;
	}

	public void setBeauftragteSalben(Set<Spezielle_salbe> beauftragteSalben) {
		this.beauftragteSalben = beauftragteSalben;
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

}

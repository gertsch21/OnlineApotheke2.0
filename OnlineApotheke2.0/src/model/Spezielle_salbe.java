package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "spezielle_salbe")
@PrimaryKeyJoinColumn(name = "produkt_id")
public class Spezielle_salbe extends Produkt {
	private double volumen;
	private String zusatzinformationen;
	private Date erstelldatum;

	@ManyToOne
	@JoinColumn(name="kaeufer_id", nullable=true)
	private Kunde kaeufer;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "salbe_hat_inhaltsstoff", joinColumns = {
			@JoinColumn(name = "salben_id") }, inverseJoinColumns = { @JoinColumn(name = "inhaltsstoff_id") })
	private Set<Inhaltsstoff> enthaltene_inhaltsstoffe;

	
	public Spezielle_salbe() {
		super();
	}
	
	public Spezielle_salbe(String name, double preis, String anmerkung, double volumen, String zusatzinformationen, Date erstelldatum,
			Set<Inhaltsstoff> enthaltene_inhaltsstoffe) {
		super(name, preis, anmerkung);
		this.volumen = volumen;
		this.zusatzinformationen = zusatzinformationen;
		this.erstelldatum = erstelldatum;
		this.enthaltene_inhaltsstoffe = enthaltene_inhaltsstoffe;
		this.kaeufer=null;
	}



	@Override
	public String toString() {
		return "Spezielle_salbe [volumen=" + volumen + ", zusatzinformationen=" + zusatzinformationen
				+ ", erstelldatum=" + erstelldatum + ", enthaltene_inhaltsstoffe=" + enthaltene_inhaltsstoffe + ", toString()=" + ", getProdukt_id()=" + getProdukt_id() + ", getName()="
						+ getName() + ", getPreis()=" + getPreis() + ", getAnmerkung()=" + getAnmerkung()  
						+ ", getReklamationen()=" + getReklamationen() + ", getClass()=" + getClass()
						+ ", hashCode()=" + hashCode() + "]";
	}

	public Kunde getKaeufer() {
		return kaeufer;
	}

	public void setKaeufer(Kunde kaeufer) {
		this.kaeufer = kaeufer;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public String getZusatzinformationen() {
		return zusatzinformationen;
	}

	public void setZusatzinformationen(String zusatzinformationen) {
		this.zusatzinformationen = zusatzinformationen;
	}

	public Date getErstelldatum() {
		return erstelldatum;
	}

	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	public Set<Inhaltsstoff> getEnthaltene_inhaltsstoffe() {
		return enthaltene_inhaltsstoffe;
	}

	public void setEnthaltene_inhaltsstoffe(Set<Inhaltsstoff> enthaltene_inhaltsstoffe) {
		this.enthaltene_inhaltsstoffe = enthaltene_inhaltsstoffe;
	}

}

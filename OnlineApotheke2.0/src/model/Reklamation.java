package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reklamation")
public class Reklamation implements Serializable{
	@Id
	private int reklamation_id;
	@Id
	private int kunden_id;
	private String kurzbeschreibung;
	private Date erstelldatum;
	
	@ManyToOne
	@JoinColumn(name="kunden_id", nullable=false)
	private Kunde kritiker;
	
	@ManyToOne
	@JoinColumn(name="produkt_id", nullable=false)
	private Produkt zuBeschwerendesProdukt;

	
	
	
	
	@Override
	public String toString() {
		return "Reklamation [reklamation_id=" + reklamation_id + ", kunden_id=" + kunden_id + ", kurzbeschreibung="
				+ kurzbeschreibung + ", erstelldatum=" + erstelldatum + ", kritiker=" + kritiker.getBenutzername()
				+ ", zuBeschwerendesProdukt=" + zuBeschwerendesProdukt.getName() + "]";
	}

	public int getReklamation_id() {
		return reklamation_id;
	}

	public void setReklamation_id(int reklamation_id) {
		this.reklamation_id = reklamation_id;
	}

	public int getKunden_id() {
		return kunden_id;
	}

	public void setKunden_id(int kunden_id) {
		this.kunden_id = kunden_id;
	}

	public String getKurzbeschreibung() {
		return kurzbeschreibung;
	}

	public void setKurzbeschreibung(String kurzbeschreibung) {
		this.kurzbeschreibung = kurzbeschreibung;
	}

	public Date getErstelldatum() {
		return erstelldatum;
	}

	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	public Kunde getKritiker() {
		return kritiker;
	}

	public void setKritiker(Kunde kritiker) {
		this.kritiker = kritiker;
	}

	public Produkt getZuBeschwerendesProdukt() {
		return zuBeschwerendesProdukt;
	}

	public void setZuBeschwerendesProdukt(Produkt zuBeschwerendesProdukt) {
		this.zuBeschwerendesProdukt = zuBeschwerendesProdukt;
	}
	
	
	
	
}

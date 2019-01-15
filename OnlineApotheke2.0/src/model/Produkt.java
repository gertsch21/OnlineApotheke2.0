package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Produkt")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produkt {
	@Id
	private long produkt_id;
	private String name;
	private double preis;
	private String anmerkung;
	
	
	
	public Produkt() {
		super();
	}
	public Produkt(long id, String name, double preis, String anmerkung) {
		super();
		this.produkt_id = id;
		this.name = name;
		this.preis = preis;
		this.anmerkung = anmerkung;
	}
	public long getId() {
		return produkt_id;
	}
	public void setId(long id) {
		this.produkt_id = id;
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
	public String getAnmerkung() {
		return anmerkung;
	}
	public void setAnmerkung(String anmerkung) {
		this.anmerkung = anmerkung;
	}
	
	
}

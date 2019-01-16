package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Produkt")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produkt {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long produkt_id;
	private String name;
	private double preis;
	private String anmerkung;
	
	@OneToMany(mappedBy="produkt", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Item> items;
	
	@OneToMany(mappedBy="zuBeschwerendesProdukt", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Reklamation> reklamationen;
	
	
	public Produkt() {}
	public Produkt(String name, double preis, String anmerkung) {
		super();
		this.name = name;
		this.preis = preis;
		this.anmerkung = anmerkung;
		this.items = new HashSet<Item>();
		this.reklamationen = new HashSet<Reklamation>();
	}


	@Override
	public String toString() {
		return "Produkt [produkt_id=" + produkt_id + ", name=" + name + ", preis=" + preis + ", anmerkung=" + anmerkung
				+ ", Reklamationen: " + reklamationen + "]";
	}


	public long getProdukt_id() {
		return produkt_id;
	}


	public void setProdukt_id(long produkt_id) {
		this.produkt_id = produkt_id;
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


	public Set<Item> getItems() {
		return items;
	}


	public void setItems(Set<Item> items) {
		this.items = items;
	}


	public Set<Reklamation> getReklamationen() {
		return reklamationen;
	}


	public void setReklamationen(Set<Reklamation> reklamationen) {
		this.reklamationen = reklamationen;
	}	
	
}

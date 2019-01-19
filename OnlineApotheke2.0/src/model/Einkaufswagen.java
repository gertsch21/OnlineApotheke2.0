package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Einkaufswagen")
public class Einkaufswagen {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int einkaufswagen_id;
	private Date bestelldatum;
	
	@ManyToOne
	@JoinColumn(name="benutzer_id", nullable=false)
	private Kunde kunde;
	
	@OneToMany(mappedBy="einkaufswagen", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Item> items;

	public Einkaufswagen(Kunde kunde) {
		this.kunde = kunde;
	}
	public Einkaufswagen() {
	}
	@Override
	public String toString() {
		return "Einkaufswagen [einkaufswagen_id=" + einkaufswagen_id + ", bestelldatum=" + bestelldatum + ", items=" + items + "]";
	}

	public int getEinkaufswagen_id() {
		return einkaufswagen_id;
	}

	public void setEinkaufswagen_id(int einkaufswagen_id) {
		this.einkaufswagen_id = einkaufswagen_id;
	}

	public Date getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	
}

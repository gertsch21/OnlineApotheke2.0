package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable{
	@Id
	private int einkaufswagen_id;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int item_id;
	private int anzahl;
	
	@ManyToOne
	@JoinColumn(name="einkaufswagen_id", nullable=false)
	private Einkaufswagen einkaufswagen;

	@ManyToOne
	@JoinColumn(name="produkt_id", nullable=false)
	private Produkt produkt;

	@Override
	public String toString() {
		return "Item [einkaufswagen_id=" + einkaufswagen_id + ", item_id=" + item_id + ", anzahl=" + anzahl
				+ ", produkt: " + produkt + "]";
	}

	public int getEinkaufswagen_id() {
		return einkaufswagen_id;
	}

	public void setEinkaufswagen_id(int einkaufswagen_id) {
		this.einkaufswagen_id = einkaufswagen_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public Einkaufswagen getEinkaufswagen() {
		return einkaufswagen;
	}

	public void setEinkaufswagen(Einkaufswagen einkaufswagen) {
		this.einkaufswagen = einkaufswagen;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
	
}

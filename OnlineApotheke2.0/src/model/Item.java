package model;

import java.io.Serializable;

import javax.persistence.Entity;
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
	
	
}

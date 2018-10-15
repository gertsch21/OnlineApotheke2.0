package model;

public class Bestellungsteil {
	private int bestellungsteil_id;
	private int bestellung_id;
	private int produkt_id;
	private int anzahl;
	
	public Bestellungsteil() {
		super();
	}
	
	public Bestellungsteil(int bestellungsteil_id, int bestellung_id, int produkt_id, int anzahl) {
		super();
		this.bestellungsteil_id = bestellungsteil_id;
		this.bestellung_id = bestellung_id;
		this.produkt_id = produkt_id;
		this.anzahl = anzahl;
	}
	
	@Override
	public String toString() {
		return "Bestellungsteil [bestellungsteil_id=" + bestellungsteil_id + ", bestellung_id=" + bestellung_id
				+ ", produkt_id=" + produkt_id + ", anzahl=" + anzahl + "]";
	}
	public int getBestellungsteil_id() {
		return bestellungsteil_id;
	}
	public void setBestellungsteil_id(int bestellungsteil_id) {
		this.bestellungsteil_id = bestellungsteil_id;
	}
	public int getBestellung_id() {
		return bestellung_id;
	}
	public void setBestellung_id(int bestellung_id) {
		this.bestellung_id = bestellung_id;
	}
	public int getProdukt_id() {
		return produkt_id;
	}
	public void setProdukt_id(int produkt_id) {
		this.produkt_id = produkt_id;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	
	
}

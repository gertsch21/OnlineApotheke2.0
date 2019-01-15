package model;

public class Item {
	private long id;
	private long einkaufswagen_id;
	private int anzahl;
	
	
	
	public Item() {
		super();
	}

	public Item(long id, long einkaufswagen_id, int anzahl) {
		super();
		this.id = id;
		this.einkaufswagen_id = einkaufswagen_id;
		this.anzahl = anzahl;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEinkaufswagen_id() {
		return einkaufswagen_id;
	}
	public void setEinkaufswagen_id(long einkaufswagen_id) {
		this.einkaufswagen_id = einkaufswagen_id;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
}

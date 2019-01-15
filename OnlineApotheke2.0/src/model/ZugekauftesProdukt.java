package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="zugekauftes_produkt")
@PrimaryKeyJoinColumn(name="produkt_id")
public class ZugekauftesProdukt extends Produkt{
	private int menge_enthalten;
	private String hersteller;
	private String wirkstoff;
	private String wirkungsweise;
	private String anwendungsweise;
	
	public ZugekauftesProdukt() {
		super();
	}
	public ZugekauftesProdukt(long id, String name, double preis, String anmerkung, int menge_enthalten, String hersteller, String wirkstoff, String wirkungsweise,
			String anwendungsweise) {
		super(id, name, preis, anmerkung);
		this.menge_enthalten = menge_enthalten;
		this.hersteller = hersteller;
		this.wirkstoff = wirkstoff;
		this.wirkungsweise = wirkungsweise;
		this.anwendungsweise = anwendungsweise;
	}
	public int getMenge_enthalten() {
		return menge_enthalten;
	}
	public void setMenge_enthalten(int menge_enthalten) {
		this.menge_enthalten = menge_enthalten;
	}
	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	public String getWirkstoff() {
		return wirkstoff;
	}
	public void setWirkstoff(String wirkstoff) {
		this.wirkstoff = wirkstoff;
	}
	public String getWirkungsweise() {
		return wirkungsweise;
	}
	public void setWirkungsweise(String wirkungsweise) {
		this.wirkungsweise = wirkungsweise;
	}
	public String getAnwendungsweise() {
		return anwendungsweise;
	}
	public void setAnwendungsweise(String anwendungsweise) {
		this.anwendungsweise = anwendungsweise;
	}
	
}

package model;

import java.util.Date;

public class Einkaufswagen {
	private long id;
	private Date bestelldatum;
	private long benutzer_id;
	
	public Einkaufswagen() {
		
	}
	
	public Einkaufswagen(long id, Date bestelldatum, long benutzer_id) {
		super();
		this.id = id;
		this.bestelldatum = bestelldatum;
		this.benutzer_id = benutzer_id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getBestelldatum() {
		return bestelldatum;
	}
	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}
	public long getBenutzer_id() {
		return benutzer_id;
	}
	public void setBenutzer_id(long benutzer_id) {
		this.benutzer_id = benutzer_id;
	}
	
}

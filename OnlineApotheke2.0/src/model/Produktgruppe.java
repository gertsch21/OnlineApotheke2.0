package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produktgruppe {
	@Id
	@GeneratedValue
	@Column(name = "produktgruppe_id")
	private int produktgruppe_id;
	private String name;
	private String beschreibung;
	private Integer aktion_id;
	private Set produkte;

	public Produktgruppe() {
		super();
	}

	public Produktgruppe(int produktgruppe_id, String name, String beschreibung, int aktion_id, Set produkte) {
		super();
		this.produktgruppe_id = produktgruppe_id;
		this.name = name;
		this.beschreibung = beschreibung;
		this.aktion_id = aktion_id;
		this.produkte = produkte;
	}

	@Override
	public String toString() {
		return "Produktgruppe [produktgruppe_id=" + produktgruppe_id + ", name=" + name + ", beschreibung="
				+ beschreibung + ", aktion_id=" + aktion_id + "]"+ produkte;
		
	}

	public int getProduktgruppe_id() {
		return produktgruppe_id;
	}

	public void setProduktgruppe_id(int produktgruppe_id) {
		this.produktgruppe_id = produktgruppe_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public int getAktion_id() {
		if (aktion_id != null)
			return aktion_id.intValue();
		else
			return -1;

	}

	public void setAktion_id(Integer aktion_id) {
		if (aktion_id != null) aktion_id = -1; 
		else this.aktion_id = aktion_id;
	}

	public Set getProdukte() {
		return produkte;
	}

	public void setProdukte(Set produkte) {
		this.produkte = produkte;
	}

}

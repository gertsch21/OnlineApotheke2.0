package model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="Inhaltsstoff")
public class Inhaltsstoff {
	@Id
	private int inhaltsstoff_id;
	private Date erstelldatum;
	private String stoff_name;
	
	@ManyToMany(mappedBy = "enthaltene_inhaltsstoffe")
	private Set<Spezielle_salbe> in_salben_enthalten_liste;

	@Override
	public String toString() {
		return "Inhaltsstoff [inhaltsstoff_id=" + inhaltsstoff_id + ", erstelldatum=" + erstelldatum + ", stoff_name="
				+ stoff_name + "]";
	}

	public Inhaltsstoff() {
		super();
	}

	public int getInhaltsstoff_id() {
		return inhaltsstoff_id;
	}

	public void setInhaltsstoff_id(int inhaltsstoff_id) {
		this.inhaltsstoff_id = inhaltsstoff_id;
	}

	public Date getErstelldatum() {
		return erstelldatum;
	}

	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	public String getStoff_name() {
		return stoff_name;
	}

	public void setStoff_name(String stoff_name) {
		this.stoff_name = stoff_name;
	}

	public Set<Spezielle_salbe> getIn_salben_enthalten_liste() {
		return in_salben_enthalten_liste;
	}

	public void setIn_salben_enthalten_liste(Set<Spezielle_salbe> in_salben_enthalten_liste) {
		this.in_salben_enthalten_liste = in_salben_enthalten_liste;
	}
	
	
	
	
}

package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "einkaufswagen")
public class Einkaufswagen {
	@Id
	private int einkaufswagen_id;
	private Date bestelldatum;
	
	@ManyToOne
	@JoinColumn(name="benutzer_id", nullable=false)
	private Kunde kunde;
	
	@OneToMany(mappedBy="einkaufswagen", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<Item> items;

	@Override
	public String toString() {
		return "Einkaufswagen [einkaufswagen_id=" + einkaufswagen_id + ", bestelldatum=" + bestelldatum + ", items=" + items + "]";
	}
	
	
}

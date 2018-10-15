package model;

import java.util.Set;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public class Aktion {
	private int aktion_id;
	private double prozentrabattwert;
	private Set produktgruppen;
	
	
	
	
	public Aktion() {
		super();
	}


	public Aktion(int aktion_id, double prozentrabattwert, Set produktgruppen) {
		super();
		this.aktion_id = aktion_id;
		this.prozentrabattwert = prozentrabattwert;
		this.produktgruppen = produktgruppen;
	}
	
	
	@Override
	public String toString() {
		return "Aktion [aktion_id=" + aktion_id + ", prozentrabattwert=" + prozentrabattwert + ", produktgruppen="
				+ produktgruppen + "]";
	}
	public int getAktion_id() {
		return aktion_id;
	}
	public void setAktion_id(int aktion_id) {
		this.aktion_id = aktion_id;
	}
	public double getProzentrabattwert() {
		return prozentrabattwert;
	}
	public void setProzentrabattwert(double prozentrabattwert) {
		this.prozentrabattwert = prozentrabattwert;
	}
	public Set getProduktgruppen() {
		return produktgruppen;
	}
	public void setProduktgruppen(Set produktgruppen) {
		this.produktgruppen = produktgruppen;
	}
	
}

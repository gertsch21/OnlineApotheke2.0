package dao;

import java.util.List;

import model.Produkt;
import model.Produktgruppe;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public interface ProduktDAO {
	public boolean speichereProdukt(Produkt p);
	public boolean speichereProduktgruppe(Produktgruppe pg);
	
	public List<Produkt> getProduktListe();
	public List<Produktgruppe> geProduktgruppenListe();
	
	public Produkt getProduktByProduktID(String produktgruppe_id);
	public Produktgruppe getProduktgruppeByProduktgruppeID(String produktgruppe_id);
    
	public boolean loescheProduktByProduktID(String produkt_id);
	public boolean loescheProduktgruppeByProduktgruppeID(String produktgruppe_id);
	
}

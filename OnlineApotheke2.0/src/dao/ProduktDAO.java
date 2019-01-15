package dao;

import java.util.List;

import model.Produkt_mit_annotation;
import model.Produktgruppe;

/**
 * 
 * @author Gerhard Schmidt
 *
 */
public interface ProduktDAO {
	public boolean speichereProdukt(Produkt_mit_annotation p);
	public boolean speichereProduktgruppe(Produktgruppe pg);
	
	public List<Produkt_mit_annotation> getProduktListe();
	public List<Produktgruppe> geProduktgruppenListe();
	
	public Produkt_mit_annotation getProduktByProduktID(String produktgruppe_id);
	public Produktgruppe getProduktgruppeByProduktgruppeID(String produktgruppe_id);
    
	public boolean loescheProduktByProduktID(String produkt_id);
	public boolean loescheProduktgruppeByProduktgruppeID(String produktgruppe_id);
	
}

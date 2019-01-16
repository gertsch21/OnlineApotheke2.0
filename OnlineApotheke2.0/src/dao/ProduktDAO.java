package dao;

import java.util.List;

import model.Produkt;
import model.Spezielle_salbe;
import model.ZugekauftesProdukt;
/**
 * 
 * @author Gerhard Schmidt
 *
 */
public interface ProduktDAO {
	
	public void closeConnection();
	
	public boolean speichereProdukt(Produkt p);
	
	public List<Produkt> getProduktListe();
	public List<Spezielle_salbe> getSpezielleSalbenListe();
	public List<ZugekauftesProdukt> getZugekauftesProduktListe();
	
	public Produkt getProduktByProduktID(int produkt_id);
    
	public void loescheProduktByProduktID(int produkt_id);	
}

package dao;

import java.util.List;
import java.util.Map;

import model.Inhaltsstoff;
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
	public boolean updateProdukt(Produkt p);
	
	
	public List<Produkt> getProduktListe();
	public List<Inhaltsstoff> getInhaltsstoffListe();
	public List<Spezielle_salbe> getSpezielleSalbenListe();
	public List<ZugekauftesProdukt> getZugekauftesProduktListe();
	public Map<Long, Integer> getMenge();
	public int updateMenge(int produkt_id, int menge);
	public Produkt getProduktByProduktID(int produkt_id);
	public void speichereSalbe(Spezielle_salbe salbe);
    
	public void loescheProduktByProduktID(int produkt_id);	
}

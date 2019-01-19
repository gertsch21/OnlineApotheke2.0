/**
 * 
 */
package dao;

import java.util.List;
import java.util.Set;

import model.*;

/**
 * 
 * @author Gerhard Schmidt
 *
 *EINE BESTELLUNG IST GLEICH EIN ABGESCHLOSSENER EINKAUFWAGEN
 */
public interface BestellungDAO {
	
	public void closeConnection();
	
	public boolean speichereItem(Item i);
	public boolean speichereEinkaufswagen(Einkaufswagen e);
	
	public Set<Item> getAllItems();
	public Set<Item> getItemListbyEinkaufswagen(int einkaufswagen_id);
	public List<Einkaufswagen> getEinkaufswagenList();
	public Einkaufswagen getEinkaufswagenByBenutzerID(int benutzer_id);
	public Item getItemByID(int item_id, int einkaufswagen_id);
	public Einkaufswagen getEinkaufswagenByID(int einkaufswagen_id);
	
	public void loescheItem(int item_id, int einkaufswagen_id);
	public void loescheEinkaufswagen(int einkaufswagen_id);

	public boolean aktualisiereEinkaufswagen(Einkaufswagen e);
}

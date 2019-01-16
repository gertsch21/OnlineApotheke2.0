/**
 * 
 */
package dao;

import java.util.List;
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
	
	public List<Item> getItemListbyEinkaufswagen(int einkaufswagen_id);
	public List<Einkaufswagen> getEinkaufswagenList();
	public Item getItemByID(int item_id, int einkaufswagen_id);
	public Einkaufswagen getEinkaufswagenByID(int einkaufswagen_id);
	
	public void loescheItem(int item_id, int einkaufswagen_id);
	public void loescheEinkaufswagen(int einkaufswagen_id);
}

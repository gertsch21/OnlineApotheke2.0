/**
 * 
 */
package management;

import dao.BestellungDAO;
import dao.DBBestellungDAO;
import dao.DBProduktDAO;
import dao.ProduktDAO;
import model.Einkaufswagen;
import model.Item;
import model.Produkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Gerhard
 *
 */
public class Bestellungsmanagement {
	private static Bestellungsmanagement bestellungsmanagementInstance = null;
	private BestellungDAO dao;
	private ProduktDAO produkt_dao;
	private Benutzermanagement benman;

	/**
	 * Konstruktor der Bestellungsmanagementverwaltung
	 */
	private Bestellungsmanagement() {
		this.dao = new DBBestellungDAO();
		this.produkt_dao = new DBProduktDAO();
	}

	/**
	 * Singleton Design-pattern
	 * 
	 * @return Referenz auf das Benutzerverwaltungsobjekt
	 */
	public static Bestellungsmanagement getInstance() {
		if (bestellungsmanagementInstance == null)
			bestellungsmanagementInstance = new Bestellungsmanagement();
		return bestellungsmanagementInstance;
	}

	// Hier sollte noch die Bestellungslogik eingef�gt werden
	// DB Inhalte abrufen, pruefen od. ver�ndern

	/**
	 * Speichert die �bergebene Position in die Datenbank. Retourniert false falls
	 * ein Fehler auftritt.
	 * 
	 * @param p: zu speichernde Position
	 * @return true/false
	 */
	public boolean speichereItem(int anzahl, int einkaufswagen_id, int produkt_id) {
		Einkaufswagen e = dao.getEinkaufswagenByID(einkaufswagen_id);
		Produkt p = produkt_dao.getProduktByProduktID(produkt_id);
		return dao.speichereItem(new Item(anzahl, e, p));
	}

	/**
	 * Speichert die �bergebene Bestellung in die Datenbank. Retourniert false falls
	 * ein Fehler auftritt.
	 * 
	 * @param b: zu speichernde Bestellung
	 * @return true/false
	 */
	public boolean speichereEinkaufswagen(Einkaufswagen e) {
		return dao.speichereEinkaufswagen(e);
	}

	/**
	 * Retourniert eine Liste mit allen Positionen einer Bestellungen
	 * 
	 * @param oID: ID der Bestellung
	 * @return Liste der Positionen einer Bestellung
	 */
	public Set<Item> getAllItemsOfEinkaufswagen(int einkaufswagen_id) {
		return dao.getEinkaufswagenByID(einkaufswagen_id).getItems();
	}

	/**
	 * Retourniert eine Liste mit allen Bestellungen
	 * 
	 * @return Liste mit allen Bestellungen
	 */
	public List<Einkaufswagen> getAllEinkaufswagen() {
		return dao.getEinkaufswagenList();
	}

	public List<Einkaufswagen> getAllEinkaufswagenFromKunde(String benutzername) {
		return new ArrayList<>(benman.getKundeByUname(benutzername).getEinkaufswagen());
	}

	/**
	 * Retourniert, die Position aus der ensprechenden Bestellung mit der
	 * entsprechenden Nummer
	 * 
	 * @param oID: BestellungsID
	 * @param pID: PosititionsNr
	 * @return Position
	 */
	public Item getItemByID(int einkaufswagen_id, int item_id) {
		return dao.getItemByID(item_id, einkaufswagen_id);
	}

	/**
	 * L�scht die Position an der Stelle der �bergebenen Positionnummer aus der
	 * �bergebenen Bestellung aus der Datenbank. Retourniert False wenn ein Fehler
	 * aufgetreten ist od. kein Eintrag gefunden wurde.
	 * 
	 * @param oID: BesllungsID
	 * @param pID: PositionsNr
	 * @return true: Erledigt od. false: Fehlgeschlagen
	 */
	public void deletePosition(int item_id, int einkaufswagen_id) {
		dao.loescheItem(item_id, einkaufswagen_id);
	}

	/**
	 * L�scht Bestellung mit der �bergebenen BestellungsID aus der Datenbank.
	 * Retourniert False, wenn die Bestellung nicht vorhanden ist oder ein Fehler
	 * aufgetreten ist.
	 * 
	 * @param oID: BestellungsID
	 * @return true: Erledigt od. false: Fehlgeschlagen
	 */
	public void deleteBestellung(int einkaufswagen_id) {
		dao.loescheEinkaufswagen(einkaufswagen_id);
	}

}

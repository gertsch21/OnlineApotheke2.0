/**
 * 
 */
package management;

import dao.BestellungDAO;
import dao.DBBestellungDAO;
import model.Bestellung;
import model.Position;

import java.util.List;

/**
 * @author Katrin
 *
 */
public class Bestellungsmanagement {
	private static Bestellungsmanagement bestellungsmanagementInstance = null;
	private BestellungDAO dao;

	/**
	 * Konstruktor der Bestellungsmanagementverwaltung
	 */
	private Bestellungsmanagement() {
		this.dao = new DBBestellungDAO();
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
	 * Speichert die �bergebene Position in die Datenbank. Retourniert false falls ein Fehler auftritt.
	 * @param p: zu speichernde Position
	 * @return true/false
	 */
	public boolean speicherePosition(Position p){
		return dao.speicherePosition(p);
	}
	
	/**
	 * Speichert die �bergebene Bestellung in die Datenbank. Retourniert false falls ein Fehler auftritt.
	 * @param b: zu speichernde Bestellung
	 * @return true/false
	 */
	public boolean speichereBestellung(Bestellung b){
		return dao.speichereBestellung(b);
	}
	
	
	/**
	 * Retourniert eine Liste mit allen Positionen einer Bestellungen
	 * 
	 * @param oID:
	 *            ID der Bestellung
	 * @return Liste der Positionen einer Bestellung
	 */
	public List<Position> getAllPositionOfBestellung(String oID) {
		return dao.getPositionListbyBestellung(oID);
	}

	/**
	 * Retourniert eine Liste mit allen Bestellungen
	 * 
	 * @return Liste mit allen Bestellungen
	 */
	public List<Bestellung> getAllBestellung() {
		return dao.getBestellungList();
	}

	/**
	 * Retourniert, die Position aus der ensprechenden Bestellung mit der
	 * entsprechenden Nummer
	 * 
	 * @param oID:
	 *            BestellungsID
	 * @param pID:
	 *            PosititionsNr
	 * @return Position
	 */
	public Position getPositionByID(String oID, int pID) {
		return dao.getPositionByID(pID, oID);
	}

	/**
	 * L�scht die Position an der Stelle der �bergebenen Positionnummer aus der
	 * �bergebenen Bestellung aus der Datenbank. Retourniert False wenn ein
	 * Fehler aufgetreten ist od. kein Eintrag gefunden wurde.
	 * 
	 * @param oID:
	 *            BesllungsID
	 * @param pID:
	 *            PositionsNr
	 * @return true: Erledigt od. false: Fehlgeschlagen
	 */
	public boolean deletePosition(String oID, int pID) {
		return dao.loeschePosition(pID, oID);
	}

	/**
	 * L�scht Bestellung mit der �bergebenen BestellungsID aus der Datenbank.
	 * Retourniert False, wenn die Bestellung nicht vorhanden ist oder ein
	 * Fehler aufgetreten ist.
	 * 
	 * @param oID:
	 *            BestellungsID
	 * @return true: Erledigt od. false: Fehlgeschlagen
	 */
	public boolean deleteBestellung(String oID) {
		return dao.loescheBestellung(oID);
	}

}

/**
 * 
 */
package management;

import java.sql.Date;
import java.util.List;

import model.Benutzer;
import model.Kunde;
import model.Mitarbeiter;
import dao.BenutzerDAO;
import dao.DBBenutzerDAO;

/**
 * @author Gerhard
 *
 */
public class Benutzermanagement {
	private static Benutzermanagement benutzerverwaltungInstance = null;
	private BenutzerDAO dao;

	/**
	 * Konstruktor der Benutzerverwaltung, hier wird die private Variable vom Typ
	 * PersonDAO angelegt.
	 * 
	 */
	private Benutzermanagement() {
		dao = new DBBenutzerDAO();
	}

	/**
	 * Singleton Design-pattern
	 * 
	 * @return Referenz auf das Benutzerverwaltungsobjekt
	 */
	public static Benutzermanagement getInstance() {
		if (benutzerverwaltungInstance == null)
			benutzerverwaltungInstance = new Benutzermanagement();
		return benutzerverwaltungInstance;
	}

	public boolean kundeAnlegen(String benutzername, String passwort, String vorname, String nachname,
			Date geburtsdatum, String email, String telnr, String land, int plz, String ort, String strasse,
			int hausnummer, String hausnummer_zus, Date anmeldedatum, String geschlecht) {
		try {
			Kunde neu = new Kunde(benutzername, passwort, vorname, nachname, geburtsdatum, email, telnr, land, plz, ort,
					strasse, hausnummer, hausnummer_zus, anmeldedatum, geschlecht);
			dao.speichereKunde(neu);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean mitarbeiterAnlegen(String benutzername, String passwort, String vorname, String nachname, Date geburtsdatum, 
	String email, String telnr, String land, int plz, String ort, String strasse, int hausnummer, String hausnummer_zus, String svnr, double gehalt, Date eintrittsdatum){
		try {
			Mitarbeiter neu = new Mitarbeiter(benutzername, passwort, vorname, nachname, geburtsdatum, email, telnr, land, plz, ort, strasse, hausnummer, hausnummer_zus, svnr, gehalt, eintrittsdatum);
			dao.speichereMitarbeiter(neu);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public boolean pruefeLogin(String username, String passwort) {

		try {
			Benutzer p = dao.getBenutzerByUName(username);

			System.out.println("Prüfe login von: " + username + ", korrektes pwd: " + p.getPasswort());

			if (p.getPasswort().equals(passwort)) {
				return true;
			}
			return false;// falls kombi nicht passt
		} catch (NullPointerException e) {
			System.out.println("Benutzerverwaltung: Benutzer mit dem Username '" + username + "' nicht gefunden");
			return false;// null, da p null ist und p.getPassword aufgerufen wird(es gibt den benutzer
							// nicht)
		}
	}

	public List<Benutzer> getAlleBenutzer() {
		return dao.getBenutzerList();
	}

	public List<Kunde> getAlleKunden() {
		return dao.getKundenList();
	}

	public Benutzer getBenutzerByUname(String uName) {
		return dao.getBenutzerByUName(uName);
	}

	public Kunde getKundeByUname(String uName) {
		return dao.getKundeByUsername(uName);
	}

	public Mitarbeiter getEmployeeByUname(String uName) {
		return dao.getMitarbeiterByUsername(uName);
	}

	public boolean loescheKunden(String uName) {
		try {
			dao.loescheKundeByUname(uName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean loescheMitarbeiter(String uName) {
		try {
			dao.loescheMitarbeiterByUname(uName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean reklamationErstellen(Reklamation r) {
		return dao.reklamationErstellen(r);
	}

}

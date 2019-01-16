
import dao.BenutzerDAO;
import dao.BestellungDAO;
import dao.DBBenutzerDAO;
import dao.DBBestellungDAO;
import dao.DBProduktDAO;
import dao.ProduktDAO;
import model.Benutzer;
import model.Einkaufswagen;

public class HibernateTest {

	public static void main(String[] args) {

		BenutzerDAO benutzer_dao = new DBBenutzerDAO();
		ProduktDAO produkt_dao = new DBProduktDAO();
		BestellungDAO bestell_dao = new DBBestellungDAO();
		
		
		for (Benutzer i : benutzer_dao.getBenutzerList())
			System.out.println(i);
		
		for(Einkaufswagen i : bestell_dao.getEinkaufswagenList())
			System.out.println(i);

		System.out.println(bestell_dao.getItemByID(1, 1));
		bestell_dao.loescheItem(1, 1);
		System.out.println("nach: " +bestell_dao.getItemByID(1, 1));
		benutzer_dao.loescheKundeByUname("gertsch");
		benutzer_dao.loescheMitarbeiterByUname("gertsch");
		
		
		for (Benutzer i : benutzer_dao.getBenutzerList())
			System.out.println(i);
		

	
	}

	
}
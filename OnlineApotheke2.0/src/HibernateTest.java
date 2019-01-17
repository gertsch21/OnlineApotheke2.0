import java.util.List;

import management.Benutzermanagement;
import management.Produktmanagement;

public class HibernateTest {

	public static void main(String[] args) {

		Benutzermanagement benman = Benutzermanagement.getInstance();
		Produktmanagement prodman = Produktmanagement.getInstance();
		
		printList(benman.getAlleBenutzer());
		printList(prodman.getAlleProdukt());
		prodman.ZugekauftesProduktAnlegen("neues Produkt", 1000, "meine Anmerkung", 10, "Gerhards GesmBH", "Wrikstoff", "wirkungsweise", "anwendung");
		printList(prodman.getAlleProdukt());
		
	}

	public static void printList(List liste) {
		for (Object o : liste)
			System.out.println(o);
	}

}
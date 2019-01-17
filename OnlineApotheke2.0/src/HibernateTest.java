import java.util.List;

import management.Benutzermanagement;
import management.Produktmanagement;
import model.Benutzer;

public class HibernateTest {

	public static void main(String[] args) {

		Benutzermanagement benman = Benutzermanagement.getInstance();
		Produktmanagement prodman = Produktmanagement.getInstance();
		
		String username = "gertsch";
		Benutzer k = benman.getBenutzerByUname(username);
		System.out.println(k);
	}

	public static void printList(List liste) {
		for (Object o : liste)
			System.out.println(o);
	}

}
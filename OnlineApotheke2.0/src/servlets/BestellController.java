package servlets;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;
import management.Benutzermanagement;
import management.Bestellungsmanagement;

import model.*;

/**
 * Servlet implementation class BestellController
 */
@WebServlet("/BestellController")
public class BestellController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestellController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bestellungsmanagement bestman = Bestellungsmanagement.getInstance();
		Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
		bestman.aktualisiereEinkaufswagen(einkaufswagen);
		
		einkaufswagen = new Einkaufswagen((Kunde) session.getAttribute("Kunde"));	
		Date datum = new Date();
		einkaufswagen.setBestelldatum(datum);
		System.out.println("Neure Einkaufswagen nacht BEstellung: " + einkaufswagen);
		Bestellungsmanagement.getInstance().speichereEinkaufswagen(einkaufswagen);
		Kunde kunde = (Kunde) session.getAttribute("Kunde");
		einkaufswagen = Bestellungsmanagement.getInstance().getEinkaufswagen(kunde.getBenutzer_id());
		System.out.println(einkaufswagen);
		session.setAttribute("Einkaufswagen", einkaufswagen);
		session.setAttribute("cartOut", null);
   	 	request.getRequestDispatcher("BestellungErfolgt.jsp").include(request, response);
		response.setContentType("text/html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

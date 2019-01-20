package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;

/**
 * Servlet implementation class ProduktHinzufuegenController
 */
@WebServlet("/ProduktHinzufuegenController")
public class ProduktHinzufuegenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktHinzufuegenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Im get vom ProduktHinzufuegenController");
			HttpSession session = request.getSession(true);

	    	if (session.getAttribute("username") == null) {
				response.sendRedirect(request.getContextPath() + "/Logincontroller");
				response.setContentType("text/html");
				return;
			}

			request.getRequestDispatcher("HauptseiteMitarbeiter.jsp").include(request, response);
			response.setContentType("text/html");
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.getSession().setAttribute("fehler", "");
			
			Produktmanagement prover = Produktmanagement.getInstance(); 
			
		
			String name=request.getParameter("name");
			String anmerkung=request.getParameter("anmerkung");
			String priceAsString=request.getParameter("preis");
			String wirkungsweise=request.getParameter("wirkungsweise");
			String vorhandene_menge=request.getParameter("vorhandene_menge");
			String wirkstoff=request.getParameter("wirkstoff");
			String hersteller=request.getParameter("hersteller");
			String enthaltene_menge=request.getParameter("enthaltene_menge");
			String anwendungsweise=request.getParameter("anwendungsweise");
			
			
			if(name.isEmpty()){
				System.out.println("ProduktHinzufuegencontroller: Es wurde kein Name für das Produkt eingegeben!");
				request.getSession().setAttribute("fehler", "Es wurde kein Name für das Produkt eingegeben!");
				request.getRequestDispatcher("ProduktAnlegen.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			if(priceAsString.isEmpty()){
				System.out.println("ProduktHinzufuegencontroller: Es wurde kein Preis für das Produkt eingegeben!");
				request.getSession().setAttribute("fehler", "Es wurde kein Preis für das Produkt eingegeben!");
				request.getRequestDispatcher("ProduktAnlegen.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			
			
			//Nachdem Benutzer angelegt wurde, wird er automatisch(nicht über Login) zur Hauptseite.jsp weitergeleitet.
			if(prover.ZugekauftesProduktAnlegen(name, Double.parseDouble(priceAsString), anmerkung, Integer.parseInt(enthaltene_menge), hersteller, wirkstoff, wirkungsweise, anwendungsweise)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("message", "Produkt '"+name+"' wurde soeben angelegt!");
				System.out.println("ProdukthinzufuegeController: Produkt angelegt: "+name);
				session.setAttribute("fehler", null);
				response.sendRedirect(request.getContextPath() + "/MitarbeiterController");//Damit Produktliste in session gleich aktualisiert wird
				response.setContentType("text/html");
				return;
			}
			//eingabe nicht erfolgreich:
			else{
				System.out.println("ProdukthinzufuegeController: Produkt konnte nicht angelegt werden: "+name+", Price: "+priceAsString+", Anmerkung: "+anmerkung);
				request.getSession(true).setAttribute("fehler", "Grober Fehler beim Anlegen des Produktes aufgetreten!");
				response.sendRedirect("ProduktAnlegen.jsp");
			}
			
			
		}
}

package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Benutzermanagement;



/**
 * Servlet implementation class MitarbeiterRegistrierController
 */
@WebServlet("/MitarbeiterRegistrierController")
public class MitarbeiterRegistrierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MitarbeiterRegistrierController() {
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
		
		Benutzermanagement benman = Benutzermanagement.getInstance();
		
	
		String vorname=request.getParameter("vorname");
		String nachname=request.getParameter("nachname");
		String email=request.getParameter("email");
		String svnr=request.getParameter("svnr");
		String gehalt=request.getParameter("gehalt");
		String land=request.getParameter("land");
		String plz=request.getParameter("plz");
		String wohnort=request.getParameter("wohnort");
		String strasse=request.getParameter("strasse");
		String nummer=request.getParameter("nummer");
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		if(username.isEmpty()){
			System.out.println("MitarbeiterRegistrierController: Es wurde kein Name für das Produkt eingegeben!");
			request.getSession().setAttribute("fehler", "Es wurde kein username eingegeben!");
			request.getRequestDispatcher("RegistrierenMitarbeiter.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		if(password.isEmpty()){
			System.out.println("MitarbeiterRegistrierController: Es wurde kein password für das Produkt eingegeben!");
			request.getSession().setAttribute("fehler", "Es wurde kein Passwort angegeben!");
			request.getRequestDispatcher("RegistrierenMitarbeiter.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		
		
		
		//Nachdem Benutzer angelegt wurde, wird er automatisch(nicht über Login) zur Hauptseite.jsp weitergeleitet.
		if(benman.mitarbeiterAnlegen(username, password, vorname, nachname, new Date(System.currentTimeMillis()), email, "", land, Integer.parseInt(plz), wohnort, strasse, Integer.parseInt(nummer), "", svnr, Double.parseDouble(gehalt), new Date(System.currentTimeMillis()))) {
			HttpSession session = request.getSession(true);
			session.setAttribute("message", "Mitarbeiter '"+username+"' wurde soeben angelegt!");
			System.out.println("MitarbeiterRegistrierController: Mitarbeiter angelegt: "+username);
			session.setAttribute("fehler", null);
			response.sendRedirect(request.getContextPath() + "/MitarbeiterController");//Damit Produktliste in session gleich aktualisiert wird
			response.setContentType("text/html");
			return;
		}
		//eingabe nicht erfolgreich:
		else{
			System.out.println("MitarbeiterRegistrierController: Mitarbeiter konnte nicht angelegt werden: "+username);
			request.getSession(true).setAttribute("fehler", "Grober Fehler beim Anlegen des Produktes aufgetreten!");
			response.sendRedirect("ProduktAnlegen.jsp");
		}
		
	}

}

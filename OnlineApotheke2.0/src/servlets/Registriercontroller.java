package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Benutzermanagement;

/**
 * Servlet implementation class Registriercontroller
 */
@WebServlet("/Registriercontroller")
public class Registriercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registriercontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Falls ein Get request kommt, soll auf die Registrierseite verwiesen werden.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Im get vom Registriercontroller");
		request.getSession().invalidate();
		System.out.println("Weiterleiten zum Registrieren!");
		request.getRequestDispatcher("Registrieren.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * Hier wird geprüft ob die eingegebenen Daten korrekt sind, falls ja, war die Registrieren erfolgreich und es wird zur Hauptseite verwiesen,
	 * falls nein, dann wird die jeweilige Fehlermeldung ausgegeben.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzermanagement benver = Benutzermanagement.getInstance();
		
		
		
		if(request.getParameter("username")==null){//registrierbutton gedrückt
			System.out.println("RegistrierController: keinUsername:Weiterleiten zum Registrieren!");
			request.getRequestDispatcher("Registrieren.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		else{
			String vorname=request.getParameter("vorname");
			String nachname=request.getParameter("nachname");
			String email=request.getParameter("email");
			String birthday=request.getParameter("bday");
			
			String land=request.getParameter("land");
			String plzString=request.getParameter("plz");
			String ort=request.getParameter("wohnort");
			String strasse=request.getParameter("strasse");
			String hausNrString=request.getParameter("nummer");
			
			String geschlecht = request.getParameter("gender");
			
			String benutzername=request.getParameter("username");
			String passwort=request.getParameter("password");
			String passwordW=request.getParameter("passwordW");
			
			
			
			System.out.println("RegistrierController: Post: geschlecht: "+geschlecht+", Geb: "+birthday);
			
			int hausNr = 0;
			int plz = 0;
			try{
				hausNr = Integer.parseInt(hausNrString);
				plz = Integer.parseInt(plzString);
			}catch(Exception e){
				request.getSession(true).setAttribute("fehler", "Hausnummer oder PLZ ist keine Nummer oder zu lang!");
				System.out.println("RegistrierungsController: Hausnummer oder PLZ ist keine Nummer!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			if(benutzername.length()<5  || passwort.length()<5 ){
				request.getSession(true).setAttribute("fehler", "Username od. Passwort zu kurz(mindestens 5 Zeichen benötigt)!");
				System.out.println("RegistrierungsController: Pwd od. Username  <  5 Zeichen!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			//Wiederholtes Passwort nicht korrekt
			if(!passwort.equals(passwordW) ){
				request.getSession(true).setAttribute("fehler", "Passwortwiederholung nicht korrekt!");
				System.out.println("RegistrierungsController: Passwortwiederholung nicht korrekt!!");
				request.getRequestDispatcher("Registrieren.jsp").include(request, response);
				response.setContentType("text/html");
				return;
			}
			
			//Username enthält Abstände
			if(benutzername.length()!=benutzername.replaceAll(" ","").length()){
				request.getSession(true).setAttribute("fehler", "Fehler: Username darf keine Leerzeichen enthalten!");
				System.out.println("RegistrierungsController: Leerzeichen im Username!");
				response.sendRedirect("Registrieren.jsp");
				return;
			}
			
			
			java.sql.Date geburtsdatum = new java.sql.Date(System.currentTimeMillis());
			try {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(birthday);
				geburtsdatum = new java.sql.Date(date.getTime()); 
			}catch (Exception e) {
				geburtsdatum = new java.sql.Date(System.currentTimeMillis());
			}
			System.out.println("benver.kundeAnlegen("+benutzername+","+passwort+","+vorname+","+nachname+","+geburtsdatum+","+email+","+""+","+land+","+plz+","+ort+","+strasse+","+hausNr+","+""+","+new Date(System.currentTimeMillis())+","+geschlecht.substring(0, 1));
			//Nachdem Benutzer angelegt wurde, wird er automatisch(nicht über Login) zur Hauptseite.jsp weitergeleitet.
			if(benver.kundeAnlegen(benutzername, passwort, vorname, nachname, geburtsdatum, email, "", land, plz, ort, strasse, hausNr, "", new Date(System.currentTimeMillis()), geschlecht.substring(0, 1))){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", benutzername);
				session.setAttribute("message", "Wilkommen "+benutzername+" bei uns!");
				session.setAttribute("Kunde", Benutzermanagement.getInstance().getBenutzerByUname(benutzername));
				System.out.println("RegistrierungsController: Kunde angelegt: "+vorname+" "+nachname+" "+email+" "+strasse+" "+ort+" "+benutzername+" "+passwort);
				session.setAttribute("fehler", null);
				response.sendRedirect(request.getContextPath() + "/ShopController");//Damit Produktliste in session gleich aktualisiert wird
				response.setContentType("text/html");
				return;
			}
			//eingabe nicht erfolgreich:
			else{
				System.out.println("RegistrierungsController: Person konnte nicht angelegt werden: "+vorname+" "+nachname+" "+email+" "+strasse+" "+ort+" "+benutzername+" "+passwort);
				System.out.println(geburtsdatum);
				request.getSession(true).setAttribute("fehler", "Fehler: Der Username ist leider schon vergeben!");
				response.sendRedirect("Registrieren.jsp");
			}
		}
		
		
	
	}

}

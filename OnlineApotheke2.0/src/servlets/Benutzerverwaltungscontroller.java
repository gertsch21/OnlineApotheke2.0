package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.Benutzermanagement;
import model.Benutzer;
import model.Kunde;

/**
 * Servlet implementation class Benutzerverwaltungscontroller
 */
@WebServlet("/Benutzerverwaltungscontroller")
public class Benutzerverwaltungscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Benutzerverwaltungscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Falls ein Get request kommt, soll auf die Loginseite verwiesen werden.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Benutzerverwaltungscontroller: Weiterleiten zu Login.jsp");
		request.getRequestDispatcher("Login.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * Bei einem Postrequest werden alle Kunden des Systems aufgelistet, falls der Button zuLoeschen gedrückt wurde, wird zuerst der entsprechende Kunde gelöscht.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Benutzermanagement benver = Benutzermanagement.getInstance();
 
		if(request.getParameter("zuLoeschen") != null){
			benver.loescheKunden(request.getParameter("zuLoeschen"));
		}
		if(request.getParameter("zuUpdaten") != null){
			String username = request.getParameter("zuUpdaten");
			Benutzer k = Benutzermanagement.getInstance().getKundeByUname(username);
			k.setPasswort(request.getParameter("kundePasswort"+username));
			k.setVorname(request.getParameter("kundeVorname"+username));
			k.setNachname(request.getParameter("kundeNachname"+username));
			k.setLand(request.getParameter("kundeLand"+username));
			k.setPlz(Integer.parseInt(request.getParameter("kundePLZ"+username)));
			k.setOrt(request.getParameter("kundeOrt"+username));
			k.setStrasse(request.getParameter("kundeStrasse"+username));
			k.setHausnummer(Integer.parseInt(request.getParameter("kundeHausnummer"+username)));
			
			System.err.println(k);
			Benutzermanagement.getInstance().updateKunde(k);
			
		}
		
		
		List<Kunde> alleKunden = benver.getAlleKunden();
		
		request.getSession().setAttribute("alleKunden", alleKunden); //Benutzer an JSP übergeben
		
		request.getRequestDispatcher("Kundenanzeige.jsp").include(request, response);
		response.setContentType("text/html");
		
	}

}

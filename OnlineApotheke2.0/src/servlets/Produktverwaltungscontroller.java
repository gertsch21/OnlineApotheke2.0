package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Benutzermanagement;
import management.Produktmanagement;
import model.Benutzer;
import model.Produkt;

/**
 * Servlet implementation class Produktverwaltungscontroller
 */
@WebServlet("/Produktverwaltungscontroller")
public class Produktverwaltungscontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Produktverwaltungscontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 * Bei einem Postrequest werden alle Kunden des Systems aufgelistet, falls der Button zuLoeschen gedrückt wurde, wird zuerst der entsprechende Kunde gelöscht.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Produktmanagement prover = Produktmanagement.getInstance();
		
		if(request.getParameter("istEinzufuegen")!=null) {
			
		}
			
		
		if(request.getParameter("anmerkung_aendern") != null){
			Produkt p = Produktmanagement.getInstance().getProduktByProduktID(Integer.parseInt(request.getParameter("anmerkung_aendern")));
			p.setAnmerkung(request.getParameter("anmerkung"));
			Produktmanagement.getInstance().updateProdukt(p);
		}
		
		
		request.getRequestDispatcher("Produktanzeige.jsp").include(request, response);

		response.setContentType("text/html");
		
	}

}

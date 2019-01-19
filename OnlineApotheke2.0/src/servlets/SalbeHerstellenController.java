package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;
import model.Inhaltsstoff;
import model.Produkt;

/**
 * Servlet implementation class SalbeHerstellenController
 */
@WebServlet("/SalbeHerstellenController")
public class SalbeHerstellenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalbeHerstellenController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Produktmanagement proman = Produktmanagement.getInstance();
		List<Inhaltsstoff> inhaltsstoffListe = new ArrayList<Inhaltsstoff>();
		inhaltsstoffListe = proman.getAlleInhaltsstoffe();

		session.setAttribute("liste", inhaltsstoffListe);
		
		
		
		request.getRequestDispatcher("SalbeHerstellen.jsp").include(request, response);
		response.setContentType("text/html");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String inhaltsStoff1 = request.getParameter("InhaltsStoff1");
		String inhaltsStoff2 = request.getParameter("InhaltsStoff2");
		String inhaltsStoff3 = request.getParameter("InhaltsStoff3");
		String inhaltsStoff4 = request.getParameter("InhaltsStoff4");
		String inhaltsStoff5 = request.getParameter("InhaltsStoff5");
		
		String salbenName 	 = request.getParameter("Salbenname");	
		double preis = 12;
		String anmerkung = request.getParameter("Anmerkung");
		double volumen = 500;
		String zusatzinformation = request.getParameter("Zusatzinformation");
		java.sql.Date erstelldatum = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	
		Set<Inhaltsstoff> enthaltene_inhaltsstoffe = new HashSet<Inhaltsstoff>();
		Produktmanagement proman = Produktmanagement.getInstance();
		
		List<String> liste = new ArrayList<String>();
		liste.add(inhaltsStoff1);
		liste.add(inhaltsStoff2);
		liste.add(inhaltsStoff3);
		liste.add(inhaltsStoff4);
		liste.add(inhaltsStoff5);
		
		for(int i = 0; i < 5; i++) {
			Inhaltsstoff inhaltsstoff = proman.getInhaltsstoffByName(liste.get(i));
			if(inhaltsstoff != null) {
				enthaltene_inhaltsstoffe.add(inhaltsstoff);
			}
		}
		
		proman.SalbeAnlegen(salbenName, preis, anmerkung, volumen, zusatzinformation, erstelldatum, enthaltene_inhaltsstoffe);

		session.setAttribute("HinweisText", "Auftrag wurde akzeptiert, Sie hören in den nächsten Tagen von uns");
	
		request.getRequestDispatcher("SalbeInVorbereitung.jsp").include(request, response);
		response.setContentType("text/html");
		
	}

}

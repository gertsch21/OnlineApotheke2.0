package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;
import model.Produkt;

/**
 * Servlet implementation class ProduktsucheController
 */
@WebServlet("/ProduktsucheController")
public class ProduktsucheController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktsucheController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suchkriterium = request.getParameter("suchwert");
		System.out.println("ProduktsucheController:Suchwert: "+suchkriterium);
		List<Produkt> ausgewaehlteProdukte = Produktmanagement.getInstance().getProduktByProduktName(suchkriterium);
		System.out.println("Ausgewaehlte Produkte: ");
		for(Produkt x : ausgewaehlteProdukte)
			System.out.println(x.getName());
		HttpSession session = request.getSession(true);
		session.setAttribute("ausgewaehlteProdukte", ausgewaehlteProdukte);
		response.sendRedirect(request.getContextPath() + "/ShopController");
		response.setContentType("text/html");
		return;
	}

}

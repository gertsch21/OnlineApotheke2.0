package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Bestellungsmanagement;
import management.Produktmanagement;
import model.Einkaufswagen;
import model.Item;
import model.Kunde;
import model.Produkt;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("/ShopController")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DecimalFormat df = new DecimalFormat("#.00");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/Logincontroller");
			response.setContentType("text/html");
			return;
		}
		StringBuffer prodOut = new StringBuffer();
		
		Produktmanagement prodman = Produktmanagement.getInstance();
		
		List<Produkt> produkte = null;
		if(session.getAttribute("ausgewaehlteProdukte")==null)
			produkte = prodman.getAlleProdukt();
		else
			produkte = (List<Produkt>) session.getAttribute("ausgewaehlteProdukte");
		
		for(Produkt product : produkte) {
			prodOut.append("<div class=\"product\">"
								+ "<div class=\"row\">"
								+ "<div class=\"col-md-8\">"
								+ "		<h2>" + product.getName() + "</h2>" 
								+ "		<p class=\"description\">" + product.getAnmerkung() + "</p>"
								+ "</div>"
								+ "<div class=\"col-md-4\" style=\"display: table; overflow: hidden;\">"
								+ "		<div class=\"pricebox\">"
								+ "			<div class=\"centered\">"
								+ "			<p class=\"price\"> &nbsp; &nbsp; &nbsp;" + df.format(product.getPreis()) + " &euro; </p>"
								
			);
			
				prodOut.append("			<form action=\"ShopController\" method=\"POST\">"
								+ "			<input class=\"btnAdd2Cart\" name=\"zumWarenkorb\" type=\"submit\" value=\"zum Warenkorb\"/>"
								+ "			<input type=\"hidden\" name=\"product_id\" value=" + product.getProdukt_id() + ">"
								+ "			</form>"
								+ "			</div>"
								+ "		</div>"
								+ "</div>"
								+ "</div>"
								+ "</div>"
			);
		}
		Kunde kunde = (Kunde) session.getAttribute("Kunde");
		
		Einkaufswagen einkaufswagen = Bestellungsmanagement.getInstance().getEinkaufswagen(kunde.getBenutzer_id());
		//Bestellungsmanagement.getInstance().deleteBestellung(2);
		if(einkaufswagen == null) {
			einkaufswagen = new Einkaufswagen((Kunde) session.getAttribute("Kunde"));	
			Date datum = new Date();
			System.out.println("DATE: " + datum);
			einkaufswagen.setBestelldatum(datum);
			Bestellungsmanagement.getInstance().speichereEinkaufswagen(einkaufswagen);
		}
		List<Einkaufswagen> liste = Bestellungsmanagement.getInstance().getAllEinkaufswagen();
		for(Einkaufswagen eink:liste) {
			System.out.println(eink);
		}
		System.out.println("MEin Einkaufswagen: " + einkaufswagen);
		session.setAttribute("Einkaufswagen", einkaufswagen);
		session.setAttribute("prodOut", prodOut);
		request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession(true);

		
		// ---------------- logic of shopping cart ------------------
		String productID = request.getParameter("product_id"); 
		
		Map<Long, Integer> cart = new HashMap<Long, Integer>();
		Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
		Set<Item> itemList = einkaufswagen.getItems();
		boolean contained = false;
		System.out.println("EINKAUFSWAGEN: ALT: " + einkaufswagen);
		for(Item item: itemList) {
			if(item.getProdukt().getProdukt_id()==Long.parseLong(productID)) {
				item.setAnzahl(item.getAnzahl()+1);
				contained = true;
			}
		}
		if(!contained) {
			Produkt produkt = Produktmanagement.getInstance().getProduktByProduktID(Integer.parseInt(productID));
			Item item = new Item(1, einkaufswagen, produkt);
			itemList.add(item);
		}
		session.setAttribute("Einkaufswagen", einkaufswagen);
		session.setAttribute("cart", cart);

		
		// ---------------- generate output for shopping cart ------------------
		Produktmanagement prodman = Produktmanagement.getInstance();
		StringBuffer cartOut = new StringBuffer();
		
	        	cartOut.append(
	        			"<table class=\"cart\">"
    	        		);
	        	 for(Item item:einkaufswagen.getItems()) {
	        	        cartOut.append(""
	        	        		+ "<tr>"
	        	        		+ "<td>" + /*prodman.getProduktByProduktID(Integer.parseInt(keyValue)).getName()*/ item.getProdukt().getName() + "</td>"
	        	        		+ "<td>&nbsp; x &nbsp;</td>"
	        	        		+ "<td>" + /*pair.getValue().toString()*/item.getAnzahl() + "</td>"
	        	        		+ "</tr>");
	        	    
	        	        
	        	 }
        	 
        	    cartOut.append(
    	        		 "</table>"
    	        		 + " <form action=\"KassaController\" method=\"GET\"> "
						 + "	<input class=\"btnGreen\" type=\"submit\" " 
						 + "		value=\"zur Kasse\"/>"
						 + "  </form>");
		
		session.setAttribute("cartOut", cartOut);
		
		
		
		request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
		response.setContentType("text/html");
		
		return;
		
	
	}
	

}

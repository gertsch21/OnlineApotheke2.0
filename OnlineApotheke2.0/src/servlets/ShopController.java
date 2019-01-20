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
		
		Map<Long,Integer> mengenMap = (Map<Long, Integer>) session.getAttribute("MengenMap");
		if(mengenMap==null) {
			mengenMap = Produktmanagement.getInstance().getMenge();
		}
		Kunde kunde = (Kunde) session.getAttribute("Kunde");
		Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
		if(einkaufswagen ==null) {
			einkaufswagen = Bestellungsmanagement.getInstance().getEinkaufswagen(kunde.getBenutzer_id());
			//Bestellungsmanagement.getInstance().deleteBestellung(2);
			if(einkaufswagen == null) {
				einkaufswagen = new Einkaufswagen((Kunde) session.getAttribute("Kunde"));	
				Date datum = new Date();
				System.out.println("DATE: " + datum);
				einkaufswagen.setBestelldatum(datum);
				Bestellungsmanagement.getInstance().speichereEinkaufswagen(einkaufswagen);
			}
		}
		for(Produkt product : produkte) {
			System.out.println(product.getProdukt_id());
			if(mengenMap.containsKey(product.getProdukt_id())){
				System.out.println("ProduktMenge: " +mengenMap.get(product.getProdukt_id()));
			}
			Set<Item> itemSet = einkaufswagen.getItems();
			Item item = null;
			for(Item entry:itemSet) {
				if(entry.getProdukt().getProdukt_id()==(product.getProdukt_id())) {
					item = entry;
				}
				
			}
			boolean onStock = false;
			if(item!=null) {
				if(mengenMap.get(product.getProdukt_id())<=item.getAnzahl()) {
					onStock = true;
				}
			}
			if(mengenMap.containsKey(product.getProdukt_id()) && (mengenMap.get(product.getProdukt_id())<=0 ||onStock)) {
				System.out.println("ProdktID: " +product.getProdukt_id());
				continue;
			}
			prodOut.append("<div class=\"product\">"
								+ "<div class=\"row\">"
								+ "<div class=\"col-md-8\">"
								+ "		<h2>" + product.getProdukt_id() + ", " + product.getName() + "</h2>" 
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

			
		List<Einkaufswagen> liste = Bestellungsmanagement.getInstance().getAllEinkaufswagen();
		for(Einkaufswagen eink:liste) {
			System.out.println(eink);
		}
		System.out.println("MEin Einkaufswagen: " + einkaufswagen);
		
		for(Long key:mengenMap.keySet()) {
			System.out.println("KEY: " + key + ", Value: " + mengenMap.get(key));
		}
		session.setAttribute("MengenMap", mengenMap);
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
		Item item = null;
		for(Item entry: itemList) {
			if(entry.getProdukt().getProdukt_id()==Long.parseLong(productID)) {
				entry.setAnzahl(entry.getAnzahl()+1);
				contained = true;
				item = entry;
			}
		}
		if(!contained) {
			Produkt produkt = Produktmanagement.getInstance().getProduktByProduktID(Integer.parseInt(productID));
			item = new Item(1, einkaufswagen, produkt);
			itemList.add(item);
		}
		Map<Long,Integer> mengenMap = (Map<Long, Integer>) session.getAttribute("MengenMap");
		boolean reload = false;
		if(mengenMap.containsKey(Long.parseLong(productID))) {
			int menge = mengenMap.get(Long.parseLong(productID));
			System.out.println("MAP Menge Neu: " +mengenMap.get(Long.parseLong(productID)));
			if(menge<=item.getAnzahl()) {
				reload = true;
			}
		}
		session.setAttribute("MengenMap", mengenMap);
		session.setAttribute("Einkaufswagen", einkaufswagen);
		session.setAttribute("cart", cart);

		
		// ---------------- generate output for shopping cart ------------------
		Produktmanagement prodman = Produktmanagement.getInstance();
		StringBuffer cartOut = new StringBuffer();
		
	        	cartOut.append(
	        			"<table class=\"cart\">"
    	        		);
	        	 for(Item entry:einkaufswagen.getItems()) {
	        	        cartOut.append(""
	        	        		+ "<tr>"
	        	        		+ "<td>" + /*prodman.getProduktByProduktID(Integer.parseInt(keyValue)).getName()*/ entry.getProdukt().getName() + "</td>"
	        	        		+ "<td>&nbsp; x &nbsp;</td>"
	        	        		+ "<td>" + /*pair.getValue().toString()*/entry.getAnzahl() + "</td>"
	        	        		+ "</tr>");
	        	    
	        	        
	        	 }
        	 
        	    cartOut.append(
    	        		 "</table>"
    	        		 + " <form action=\"KassaController\" method=\"GET\"> "
						 + "	<input class=\"btnGreen\" type=\"submit\" " 
						 + "		value=\"zur Kasse\"/>"
						 + "  </form>");
		
		session.setAttribute("cartOut", cartOut);
		
		
		
		System.out.println("Reload: " + reload);
		if(reload) {
			System.out.println("reload done");
			doGet(request, response);
		}
		else {
			request.getRequestDispatcher("HauptseiteKunde.jsp").include(request, response);
			response.setContentType("text/html");
		}
		return;
		
	
	}
	

}

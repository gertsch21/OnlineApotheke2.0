package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Produktmanagement;
import model.Einkaufswagen;
import model.Item;
import model.Produkt;


/**
 * Servlet implementation class KassaController
 */
@WebServlet("/KassaController")
public class KassaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KassaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DecimalFormat df = new DecimalFormat("#.00");
		HttpSession session = request.getSession();
		
		Produktmanagement prodman = Produktmanagement.getInstance();
		Map<String, Integer> cart = new HashMap<String, Integer>();

		// if session variable "cart" is  set, store content in local cart
		if ( session.getAttribute("cart") != null ){
			cart = (Map<String, Integer>) session.getAttribute("cart");
		}
		
		StringBuffer checkCart = new StringBuffer();
	
    	
    	 // Iterate over all Key-Value-Pairs
    	 //Iterator it = cart.entrySet().iterator();
		Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
		System.out.println(einkaufswagen);
		Set<Item> itemSet = einkaufswagen.getItems();
    	 Double sum = 0.0;
    	 //while (it.hasNext()) {
    	    	//Map.Entry pair = (Map.Entry)it.next();
    	    	//int keyValue = pair.getKey();
    	    	//Produkt_mit_annotation product = prodman.getProduktByProduktID();
    	 for(Item item:itemSet) {
    		 	Produkt produkt = item.getProdukt();
    	        double price = produkt.getPreis() * item.getAnzahl();
    	    	sum += price;
    	        checkCart.append(""
    	        		+ "<tr>"
    	        		+ "<td>" + produkt.getProdukt_id()+ "</td> "
    	        		+ "<td>" + produkt.getName() + "</td>"
    	        		+ "<td> "
    	        		+ 		"<input name=\"" + produkt.getName() + "\" type=\"text\" value=\"" + item.getAnzahl() + "\">"
    	        		+ "</td>"
    	        		+ "<td>" + df.format(produkt.getPreis()) + " &euro;</td>"
    	        		+ "<td>" + df.format(price) + " &euro;</td>"
    	        		+ "</tr>");
    	            	        
    	 }
    	 checkCart.append(""
	        		+ "<tr>"
	        		+ "<td></td>" 
	        		+ "<td></td>"
	        		+ "<td></td>"
	        		+ "<td><strong>Gesamt: </strong></td>"
	        		+ "<td><strong>" + df.format(sum) + " &euro;</strong></td>"
	        		+ "</tr>");
	 
	    System.out.println("doGet");
	    request.setAttribute("page", "checkCart");
	    session.setAttribute("checkCart", checkCart);
	    request.getRequestDispatcher("Checkout.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<Long, Integer> cart = new HashMap<Long, Integer>();
		Einkaufswagen einkaufswagen = (Einkaufswagen) session.getAttribute("Einkaufswagen");
		// if session variable "cart" is  set, store content in local cart
		if ( session.getAttribute("cart") != null ){
			cart = (Map<Long, Integer>) session.getAttribute("cart");
		}
		
		//Iterator it = cart.entrySet().iterator();
   	 	for(Item item: einkaufswagen.getItems()) {
   	 	//while (it.hasNext()) {
   	 		//@SuppressWarnings("rawtypes")
			//Map.Entry pair = (Map.Entry)it.next();
   	    	//String mapKey = (String) pair.getKey();
   	        //int mapVal = (int) pair.getValue();
   	    	String mapKey = item.getProdukt().getName();
   	    	int mapVal = item.getAnzahl();
   	    	int formValue = Integer.parseInt(request.getParameter(mapKey));
   	    
   	    	if (formValue != mapVal) {
   	    		if (formValue == 0) {
   	    			einkaufswagen.getItems().remove(item);
   	    			//it.remove();
   	    		} else {
   	    			//pair.setValue(formValue);
   	    			item.setAnzahl(formValue);
   	    		}
   	    	}   
   	 	}
   	 	
   	 	session.setAttribute("Einkaufswagen", einkaufswagen);
   	 	session.setAttribute("cart", cart);
   	 	request.setAttribute("page", "checkCart");
	    doGet(request, response);
   	 	
	}

}

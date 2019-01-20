package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import B2B.B2BBestellung;
import management.B2Bmanagement;
import management.Benutzermanagement;
import management.Bestellungsmanagement;
import management.Produktmanagement;
import model.Einkaufswagen;
import model.Item;
import model.Kunde;
import model.Produkt;

/**
 * Servlet implementation class Bestellen
 */
@WebServlet(description = "B2BUsecaseImplementation", urlPatterns = { "/Bestellen" })
public class Bestellen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bestellen() {
        super();
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
		String xml = B2Bmanagement.getInstance().readB2BRequest(request);
		B2BBestellung b2b = B2Bmanagement.getInstance().getBestellungen();
		String schema = b2b.getSchemaString();
		B2Bmanagement.getInstance().validate(xml, schema);
		System.out.println("Hier");
		try {
			System.out.println("VOR DOKUMENT");
			Document dokument = B2Bmanagement.getInstance().parseXML(xml);
			System.out.println("VERARBEITE");
			verarbeiteBestellung(dokument);
			System.out.println("fertig");
		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("LoginController: Weiterleiten zum Login!");
		response.setContentType("text/html");
		//doGet(request, response);
		
	}
	public void verarbeiteBestellung(Document dokument) {
        NodeList nodelist = dokument.getElementsByTagName("Login");
        Element login = (Element) nodelist.item(0).getChildNodes();
        Element item;
        System.out.println(login.getElementsByTagName("username").item(0).getTextContent());
        System.out.println(login.getElementsByTagName("passwort").item(0).getTextContent());
       
        String username = login.getElementsByTagName("username").item(0).getTextContent();
        String passwort = login.getElementsByTagName("passwort").item(0).getTextContent();
        Kunde kunde = (Kunde) Benutzermanagement.getInstance().getBenutzerByUname(username);
        Produkt produkt = null;
        Item einkaufswagenItem = null;
        if(kunde!=null && kunde.getPasswort().equals(passwort)) {
        	Einkaufswagen einkaufswagen =Bestellungsmanagement.getInstance().getEinkaufswagen(kunde.getBenutzer_id());
        	System.out.println("Existing EK: " + einkaufswagen);
        	System.out.println("EK Size: " + einkaufswagen.getItems().size());
        	if(einkaufswagen==null || einkaufswagen.getItems().size()> 0) {
        		einkaufswagen = new Einkaufswagen(kunde);
        		Bestellungsmanagement.getInstance().speichereEinkaufswagen(einkaufswagen);
        		einkaufswagen =Bestellungsmanagement.getInstance().getEinkaufswagen(kunde.getBenutzer_id());
        		System.out.println("NEUER EINKAUFSWAGEN WIESO??");
        	}
        	System.out.println("Existing EK Neu: " + einkaufswagen);
        	
        	Date datum = new Date();
			einkaufswagen.setBestelldatum(datum);
			Set<Item> itemSet = einkaufswagen.getItems();
	        NodeList itemList = dokument.getElementsByTagName("Item");
	        Map<Long,Integer>mengenMap = Produktmanagement.getInstance().getMenge();
	        for (int i = 0; i < itemList.getLength(); i++) {                
	        	item= (Element) itemList.item(i).getChildNodes();  
	        	  String produkt_id = item.getElementsByTagName("produkt_id").item(0).getTextContent();
	        	  int anzahl = Integer.parseInt(item.getElementsByTagName("anzahl").item(0).getTextContent()); 
	        	  if(mengenMap.containsKey(Long.parseLong(produkt_id)) && mengenMap.get(Long.parseLong(produkt_id))<=anzahl) {
	        		  anzahl = mengenMap.get(Long.parseLong(produkt_id));
	        	  }
	        	  if(anzahl==0) {
	        		  continue;
	        	  }
	        	  produkt = Produktmanagement.getInstance().getProduktByProduktID(Integer.parseInt(produkt_id));
	  			  einkaufswagenItem = new Item(anzahl, einkaufswagen, produkt);
	  			  //itemSet.add(einkaufswagenItem);
	  			  itemSet.add(einkaufswagenItem);
	        }
	        System.out.println(einkaufswagen);
	        Bestellungsmanagement.getInstance().aktualisiereEinkaufswagen(einkaufswagen);
			itemSet  = einkaufswagen.getItems();
			for(Item entry:itemSet){
				int produkt_id = (int) entry.getProdukt().getProdukt_id();
				if(mengenMap.containsKey((long)produkt_id)) {
					Produktmanagement.getInstance().updateMenge(produkt_id, mengenMap.get((long)produkt_id));
					System.out.println("UPDATED");
				}
			}
	        einkaufswagen = new Einkaufswagen(kunde);
	        Bestellungsmanagement.getInstance().speichereEinkaufswagen(einkaufswagen);
	        List<Einkaufswagen> eklist = Bestellungsmanagement.getInstance().getAllEinkaufswagen();
	        for(Einkaufswagen ekw:eklist) {
	        	System.out.println(ekw);
	        }
        
        }
	}

}

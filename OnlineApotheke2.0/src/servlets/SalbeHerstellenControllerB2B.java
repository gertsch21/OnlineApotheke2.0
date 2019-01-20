package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import B2B.B2BSalbeHerstellen;
import management.B2BManagementSalbeHerstellen;
import management.B2Bmanagement;
import management.Benutzermanagement;
import management.Bestellungsmanagement;
import management.Produktmanagement;
import model.Einkaufswagen;
import model.Inhaltsstoff;
import model.Item;
import model.Kunde;
import model.Produkt;

/**
 * Servlet implementation class SalbeHerstellenControllerB2B
 */
@WebServlet("/SalbeHerstellenControllerB2B")
public class SalbeHerstellenControllerB2B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalbeHerstellenControllerB2B() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xml = B2BManagementSalbeHerstellen.getInstance().readB2BRequest(request);
		B2BSalbeHerstellen b2b = B2BManagementSalbeHerstellen.getInstance().getBestellungen();
		String schema = b2b.getSchemaString();
		//System.out.println("Xml String OK: " + B2BManagementSalbeHerstellen.getInstance().validate(xml, schema));
		//System.out.println(xml);
		//System.out.println(schema);
		
		
		try {
			System.out.println("VOR DOKUMENT");
			Document dokument = B2BManagementSalbeHerstellen.getInstance().parseXML(xml);
			System.out.println("VERARBEITE");
			String HinweisText = "";
			HinweisText = verarbeiteBestellung(dokument);
			
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(HinweisText);
			response.getWriter().flush();
			response.getWriter().close();
	
		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
	}
	
	public String verarbeiteBestellung(Document dokument) {
        NodeList nodelist = dokument.getElementsByTagName("Login");
        Element login = (Element) nodelist.item(0).getChildNodes();
        String username = login.getElementsByTagName("username").item(0).getTextContent();
        String passwort = login.getElementsByTagName("passwort").item(0).getTextContent();
        String salbenName = dokument.getElementsByTagName("Name").item(0).getTextContent();
        String volumen = dokument.getElementsByTagName("Volumen").item(0).getTextContent();
        String zusatzinformation = dokument.getElementsByTagName("zusatzinformation").item(0).getTextContent();  
        double preis = 0;
        String anmerkung = "";
        java.sql.Date erstelldatum = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        Element item;
   
//        System.out.println("-------------------------------------------------");
//        System.out.println("Name: " + dokument.getElementsByTagName("Name").item(0).getTextContent());
//        System.out.println("Zusatzinfo: " + dokument.getElementsByTagName("zusatzinformation").item(0).getTextContent());
//        System.out.println("Volumen: " + dokument.getElementsByTagName("Volumen").item(0).getTextContent());
//        System.out.println("-------------------------------------------------");
//        System.out.println();

        NodeList nodelistStoff = dokument.getElementsByTagName("stoff");
        //Element stoff = null;
		Set<Inhaltsstoff> enthaltene_inhaltsstoffe = new HashSet<Inhaltsstoff>();
		Produktmanagement proman = Produktmanagement.getInstance();
		
		//Kunde kunde = (Kunde) Benutzermanagement.getInstance().getBenutzerByUname(username);
		String HinweisText = "";
		boolean allesOk = true;
	    //if(kunde!=null && kunde.getPasswort().equals(passwort)) {
	        for (int i = 0; i < nodelistStoff.getLength(); i++) {   
	        	Element stoff = (Element) nodelistStoff.item(i).getChildNodes();
	        	String stoff_name = stoff.getElementsByTagName("stoff_name").item(0).getTextContent();
	        	int menge = Integer.parseInt(stoff.getElementsByTagName("menge").item(0).getTextContent());
	        	Inhaltsstoff inhaltsstoff = proman.getInhaltsstoffByName(stoff_name);
	        	preis += (menge * 0.4);
	        	if(inhaltsstoff != null) {
					enthaltene_inhaltsstoffe.add(inhaltsstoff);	
				}else {
					HinweisText += ("Der Inhaltsstoff: " + stoff_name + " existiert nicht!\n");
					allesOk = false;
				}
	        	
	        }       
	        //System.out.println("Preis: " + preis);
       // }
	      if(allesOk) {
	    	  return "<SalbenherstellungResponse>Auftrag wird vorraussichtlich akzeptiert, sie hoeren in den naechsten Tagen von usn!</SalbenherstellungResponse>";
	      }
	      return HinweisText;
	}
	
	

}

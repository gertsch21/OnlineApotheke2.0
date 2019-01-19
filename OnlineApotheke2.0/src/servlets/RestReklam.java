package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.sql.Date;

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

import B2B.B2BBestellung;
import management.B2Bmanagement;
import management.Benutzermanagement;
import management.Bestellungsmanagement;
import management.Produktmanagement;
import model.Einkaufswagen;
import model.Item;
import model.Kunde;
import model.Produkt;
import model.Reklamation;

/**
 * Servlet implementation class RestReklam
 */
@WebServlet("/RestReklam")
public class RestReklam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestReklam() {
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
		String xml = B2Bmanagement.getInstance().readB2BRequest(request);
		B2BBestellung b2b = B2Bmanagement.getInstance().getBestellungen();
		String schema = b2b.getSchemaString();
		B2Bmanagement.getInstance().validate(xml, schema);
		System.out.println("Hier");
		try {
			System.out.println("VOR DOKUMENT");
			Document dokument = B2Bmanagement.getInstance().parseXML(xml);
			System.out.println("VERARBEITE");
			verarbeiteReklam(dokument);
			System.out.println("fertig");
		} catch (SAXException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Reklamation!");
		
		String ergebnis = "Ihre Reklamation wurde erfolgreich weitergeleited";
		
		response.setContentType("text/html");
		response.getWriter().append("\n<Reklamationsresponse>").append("\n   <ergebnis>" + ergebnis + "</ergebnis>").append("\n</Reklamationsresponse>");
		

		//doGet(request, response);
		
	}
	public void verarbeiteReklam(Document dokument) {
        NodeList nodelist = dokument.getElementsByTagName("Login");
        Element login = (Element) nodelist.item(0).getChildNodes();
        
        System.out.println(login.getElementsByTagName("username").item(0).getTextContent());
        System.out.println(login.getElementsByTagName("passwort").item(0).getTextContent());
       
        String username = login.getElementsByTagName("username").item(0).getTextContent();
        String passwort = login.getElementsByTagName("passwort").item(0).getTextContent();
        Kunde kunde = (Kunde) Benutzermanagement.getInstance().getBenutzerByUname(username);
        Produkt produkt = null;
        
        if(kunde!=null && kunde.getPasswort().equals(passwort)) {
        	
        	Date date = new Date(System.currentTimeMillis());	
        	Kunde user = Benutzermanagement.getInstance().getKundeByUname(username);
        	String description = dokument.getElementsByTagName("kurzbeschreibung").item(0).getTextContent();
        	
        	String produktid = dokument.getElementsByTagName("produkt_id").item(0).getTextContent();
        	int prodid = Integer.parseInt(produktid);
        	produkt = Produktmanagement.getInstance().getProduktByProduktID(prodid);
        	
			Reklamation rekm = new Reklamation( (String)description, (Date) date, (Kunde) user, (Produkt) produkt);
			System.out.println(rekm);
			//Benutzermanagement.getInstance().reklamationErstellen((Reklamation) rekm);
			
        }
	}

}

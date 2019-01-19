package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import management.Produktmanagement;
import model.Produkt;
import model.ZugekauftesProdukt;

import java.io.ByteArrayInputStream;

/**
 * Servlet implementation class ProduktsucheControllerB2B
 */
@WebServlet("/ProduktsucheControllerB2B")
public class ProduktsucheControllerB2B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktsucheControllerB2B() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("xml_string");
		System.out.println(input);
		
		String schema = getProduktSucheSchemaString();
		System.out.println(input);
		System.out.println(schema);
		if(validate(input, schema) == true) {
			
			StringBuffer sb = new StringBuffer();
			try {
				Document doc = loadXMLFromString(input);
				doc.getDocumentElement().normalize();
				String suchanfrage = doc.getElementsByTagName("Suchkriterium").item(0).getTextContent();
				if(suchanfrage == null || suchanfrage.trim().length()==0) {
					System.err.println("Suchkriterium PASST NICHT!!!!!");
					return;
				}
				Produktmanagement prodman = Produktmanagement.getInstance();
				List<Produkt> ausgewaehlte = prodman.getProdukteByKriterium(suchanfrage);
				
				
				sb.append("<ProduktsucheResponse>");
				sb.append("<produkte>");
				ZugekauftesProdukt p;
				for(Produkt produkt : ausgewaehlte) {
					if(produkt instanceof ZugekauftesProdukt) {
						p = (ZugekauftesProdukt) produkt;
						sb.append("<produkt>");
						sb.append("<produkt_id>"+p.getProdukt_id()+"</produkt_id>");
						sb.append("<name>"+(p.getName() == null ? " " : p.getName())+"</name>");
						sb.append("<preis>"+p.getPreis() +"</preis>");
						sb.append("<wirkstoff>"+(p.getWirkstoff() == null ? " " : p.getWirkstoff())+"</wirkstoff>");
						sb.append("<hersteller>"+(p.getHersteller() == null ? " " : p.getHersteller())+"</hersteller>");
						sb.append("<enthaltene_menge>"+p.getMenge_enthalten() +"</enthaltene_menge>");
						sb.append("<anwendungsweise>"+(p.getAnwendungsweise() == null ? " " : p.getAnwendungsweise())+"</anwendungsweise>");
						sb.append("<wirkungsweise>"+(p.getWirkungsweise() == null ? " " : p.getWirkungsweise())+"</wirkungsweise>");
						sb.append("<anmerkung>"+(p.getAnmerkung() == null ? " " : p.getAnmerkung())+"</anmerkung>");
						sb.append("</produkt>");
					}
				}
				sb.append("</produkte>");
				sb.append("</ProduktsucheResponse>");
				
		        System.out.println("Suchanfrage :" + suchanfrage);
		        
			} catch (Exception e) {
				System.err.println("ProduktsucheControllerB2B: Error: ");
				e.printStackTrace();
			}
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
			response.getWriter().close();
		}else {
			String fehler = "<Fehler>Es wurde ein Fehler bei Ihrem Format des XML festgestellt, eine Weiterverarbeitung ist in weiterer Folge nicht möglich! Wenden Sie sich an unseren Kundendienst, dieser wird Ihnen umgehend weiterhelfen. LG Ihre Apotheke</Fehler>";
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(fehler);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
	
	
	public Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    StringBuilder xmlStringBuilder = new StringBuilder();
	    xmlStringBuilder.append(xml);
	    ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
	    Document doc = builder.parse(input);
	    return doc;
	}
	public boolean validate(String xmlFile, String schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {


        	Source sourceSchema = new StreamSource(new StringReader(schemaFile));
        	Source sourceXML = new StreamSource(new StringReader(xmlFile));

            Schema schema = schemaFactory.newSchema(sourceSchema);

            Validator validator = schema.newValidator();
            validator.validate(sourceXML);
            return true;
        } catch (SAXException | IOException e) {
        	System.err.println("ProduktsucheControllerB2B: validate: Error: falsches Format des input xml: "+xmlFile);
            return false;
        }
	}
	public String getProduktSucheSchemaString() {
		String schema = "<?xml version='1.0'?>\r\n" + 
				"<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>\r\n" + 
				"	<xs:element name='Produktsuche'>\r\n" + 
				"		<xs:complexType>\r\n" + 
				"			<xs:sequence>\r\n" + 
				"				<xs:element name='Suchkriterium' type='xs:string'/>\r\n" +
				"			</xs:sequence>\r\n" + 
				"		</xs:complexType>\r\n" + 
				"	</xs:element>\r\n" + 
				"</xs:schema>";
		return schema;
	}
}

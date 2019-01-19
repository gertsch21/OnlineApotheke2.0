package management;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import B2B.B2BBestellung;
import model.Einkaufswagen;
import model.Item;
import model.Kunde;
import model.Produkt;

public class B2Bmanagement {
	private Document dokument;
	private static B2Bmanagement B2BmanagementInstance = null;
	
	public static B2Bmanagement getInstance() {
		if (B2BmanagementInstance == null)
			B2BmanagementInstance = new B2Bmanagement();
		return B2BmanagementInstance;
	}
	
	public B2BBestellung getBestellungen() {
		return new B2BBestellung();
	}
	

	public String readB2BRequest(HttpServletRequest request)throws ServletException, IOException {
		ServletInputStream body = request.getInputStream();
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
		if (body != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(body));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
        throw ex;
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
    }
    //Store request pody content in 'body' variable
    String xml = stringBuilder.toString();

		System.out.println(xml);
		return xml;
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
            e.printStackTrace();
            return false;
        }
	}
	public Document parseXML(String xml) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        dokument = db.parse(new InputSource(new StringReader(xml)));
        return dokument;
	}
}
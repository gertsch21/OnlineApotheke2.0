package B2B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


public class B2BBestellung {
	String schema = "<?xml version='1.0'?>\r\n" + 
			"<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>\r\n" + 
			"	<xs:element name='Bestellung'>\r\n" + 
			"		<xs:complexType>\r\n" + 
			"			<xs:sequence>\r\n" + 
			"				<xs:element name='Login'>\r\n" + 
			"					<xs:complexType>\r\n" + 
			"						<xs:sequence>\r\n" + 
			"							<xs:element name='username' type='xs:string'/>\r\n" + 
			"							<xs:element name='passwort' type='xs:string'/>\r\n" + 
			"						</xs:sequence>\r\n" + 
			"					</xs:complexType>\r\n" + 
			"				</xs:element>\r\n" + 
			"				<xs:element name='Warenkorb'>\r\n" + 
			"					<xs:complexType>\r\n" + 
			"						<xs:sequence>\r\n" + 
			"							<xs:element name='Item' maxOccurs='unbounded'>\r\n" + 
			"								<xs:complexType>\r\n" + 
			"									<xs:sequence>\r\n" + 
			"										<xs:element name='produkt_id' type='xs:integer'/>\r\n" + 
			"										<xs:element name='anzahl' type='xs:integer'/>\r\n" + 
			"									</xs:sequence>\r\n" + 
			"								</xs:complexType>\r\n" + 
			"							</xs:element>\r\n" + 
			"						</xs:sequence>\r\n" + 
			"					</xs:complexType>\r\n" + 
			"				</xs:element>\r\n" + 
			"			</xs:sequence>\r\n" + 
			"		</xs:complexType>\r\n" + 
			"	</xs:element>\r\n" + 
			"</xs:schema>";
	

	public String getSchemaString() {
		return this.schema;
	}
	
	public void parseBestellung() {
		
	}
}

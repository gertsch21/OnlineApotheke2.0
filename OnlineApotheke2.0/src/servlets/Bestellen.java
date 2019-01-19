package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.B2Bmanagement;

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
		String schema = "<?xml version='1.0'?>\r\n" + 
				"<xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>\r\n" + 
				"	<xs:element name='Request'>\r\n" + 
				"		<xs:complexType>\r\n" + 
				"			<xs:sequence>\r\n" + 
				"				<xs:element name='Login'>\r\n" + 
				"					<xs:complexType>\r\n" + 
				"						<xs:sequence>\r\n" + 
				"							<xs:element name='Username' type='xs:string'/>\r\n" + 
				"							<xs:element name='Passwort' type='xs:string'/>\r\n" + 
				"						</xs:sequence>\r\n" + 
				"					</xs:complexType>\r\n" + 
				"				</xs:element>\r\n" + 
				"			</xs:sequence>\r\n" + 
				"		</xs:complexType>\r\n" + 
				"	</xs:element>\r\n" + 
				"</xs:schema>";
		
		System.out.println(schema);
		B2Bmanagement.getInstance().validate(xml, schema);
		HttpSession session = request.getSession(true);
		System.out.println("LoginController: Weiterleiten zum Login!");
		response.setContentType("text/html");
		//doGet(request, response);
		
	}

}

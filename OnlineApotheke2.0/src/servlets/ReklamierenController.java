package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import management.Benutzermanagement;
import management.Bestellungsmanagement;
import management.Produktmanagement;
import model.Benutzer;
import model.Einkaufswagen;
import model.Item;
import model.Kunde;
import model.Produkt;
import model.Reklamation;;

/**
 * Servlet implementation class ReklamierenController
 */
@WebServlet("/ReklamierenController")
public class ReklamierenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReklamierenController() {
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
		
		String uname=String.valueOf(session.getAttribute("username"));
		Kunde user = Benutzermanagement.getInstance().getKundeByUname(uname);
		
		int prodid = Integer.parseInt(request.getParameter("prodid"));
		Produkt produkt = Produktmanagement.getInstance().getProduktByProduktID(prodid);
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		String description = request.getParameter("descr");
		
		Date date = new Date(System.currentTimeMillis());		
		
		System.out.println(uname);
		System.out.println(produkt);
		System.out.println(description);
		System.out.println(date);
		System.out.println(userid);
		System.out.println(user);
		
		Reklamation rekm = new Reklamation((int) userid, (String)description, (Date) date, (Kunde) user, (Produkt) produkt);
		System.out.println(rekm);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		String uname=String.valueOf(session.getAttribute("username"));
		Kunde user = Benutzermanagement.getInstance().getKundeByUname(uname);
		
		int prodid = Integer.parseInt(request.getParameter("prodid"));
		Produkt produkt = Produktmanagement.getInstance().getProduktByProduktID(prodid);
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		String description = request.getParameter("descr");
		
		Date date = new Date(System.currentTimeMillis());		
		
		System.out.println(uname);
		System.out.println(produkt);
		System.out.println(description);
		System.out.println(date);
		System.out.println(userid);
		System.out.println(user);
		
		Reklamation rekm = new Reklamation((int) userid, (String)description, (Date) date, (Kunde) user, (Produkt) produkt);
		System.out.println(rekm);
	}

}

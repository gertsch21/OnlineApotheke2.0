package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MitarbeiterController
 */
@WebServlet("/MitarbeiterController")
public class MitarbeiterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MitarbeiterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("username") == null) {
			response.sendRedirect(request.getContextPath() + "/Logincontroller");
			response.setContentType("text/html");
			return;
		}

		request.getRequestDispatcher("HauptseiteMitarbeiter.jsp").include(request, response);
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (request.getParameter("prover") != null) {
			request.getRequestDispatcher("Produktanzeige.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		if (request.getParameter("produktReg") != null) {

		}
		if (request.getParameter("mitarbeiterReg") != null) {

		}
		if (request.getParameter("benver") != null) {
			request.getRequestDispatcher("Kundenanzeige.jsp").include(request, response);
			response.setContentType("text/html");
			return;
		}
		
		request.getRequestDispatcher("HauptseiteMitarbeiter.jsp").include(request, response);
		response.setContentType("text/html");
	}

}

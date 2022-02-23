package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.HttpsServer;

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class CambiaPass
 */
@WebServlet("/AggiornaPass")
public class AggiornaPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String pass=request.getParameter("password");

		HttpSession session = request.getSession();
		
		Utente u=(Utente) session.getAttribute("user");
		
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		u.setPassword(pass);
		
		ud.update(u);
		
		session.removeAttribute("user");
		session.setAttribute("user", u);
		
		PrintWriter o = response.getWriter();
		o.print(pass);

		
		//response.sendRedirect(request.getHeader("referer"));
		
//		RequestDispatcher rd=getServletContext().getRequestDispatcher("/profilo.jsp");
//		rd.forward(request, response);
	}

}

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

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AggiornaBio
 */
@WebServlet("/AggiornaBio")
public class AggiornaBio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaBio() {
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
	
		String bio=request.getParameter("bio");
		
		HttpSession session=request.getSession();

		
		Utente ut=(Utente)session.getAttribute("user");
		
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		ut.setBio(bio);
		
		ud.update(ut);
		
		session.removeAttribute("user");
		session.setAttribute("user", ut);
		
		PrintWriter o = response.getWriter();
		o.print(bio);

		
//		RequestDispatcher rd=getServletContext().getRequestDispatcher("/profilo.jsp");
//		rd.forward(request, response);
	}

}

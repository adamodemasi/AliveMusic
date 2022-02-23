package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession sessione=request.getSession();

		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		sessione.removeAttribute("user");

		String mail= request.getParameter("mail");
		String password=request.getParameter("password");

		List<Utente> utenti=ud.findAll();

		for (Utente ut:utenti)
		{
			if (mail.equals(ut.getMail()) && password.equals(ut.getPassword()))
			{
				sessione.setAttribute("user", ut);
				break;
			}
		}

		PrintWriter out = response.getWriter();

		if(sessione.getAttribute("user") == null)
		{
			out.print("fallito");
		}
		else
		{
			out.print("successo");
		}
	}

}

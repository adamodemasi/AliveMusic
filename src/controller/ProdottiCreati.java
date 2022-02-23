package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Prodotto;
import persistenza.DatabaseManager;
import persistenza.UtenteProxy;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class ProdottiCreati
 */
@WebServlet("/ProdottiCreati")
public class ProdottiCreati extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdottiCreati() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String nickname = request.getParameter("utente");
		System.out.println("servlet prodotti create "+nickname);

		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		UtenteProxy utn = (UtenteProxy) utenteDao.findByPrimaryKey(nickname);

		Set<Prodotto> prodotti_realizzati= utn.getProdotti_realizzati();

		JSONArray JsonArrayCreati=new JSONArray();

		for (Prodotto p: prodotti_realizzati)
		{
			JSONObject o=new JSONObject(p);
			JsonArrayCreati.put(o);
		}

		System.out.println(JsonArrayCreati.toString());
		PrintWriter out = response.getWriter();
		out.println(JsonArrayCreati.toString());

	}

}

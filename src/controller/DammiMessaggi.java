package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.MessaggioPrivato;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class DammiMessaggi
 */
@WebServlet("/DammiMessaggi")
public class DammiMessaggi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DammiMessaggi() {
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

		Utente u = (Utente) request.getSession().getAttribute("user");

		MessaggioPrivatoDao messaggioPrivatoDao = DatabaseManager.getInstance().getDaoFactory().getMessaggioPrivatoDAO();

		List<MessaggioPrivato> allMex = messaggioPrivatoDao.findAll(); 


		JSONArray jsonArrayInviati = new JSONArray();
		JSONArray jsonArrayRicevuti = new JSONArray();

		for (MessaggioPrivato m : allMex) 
		{
			if(m.getMittente().equals(u))
			{
				
			// PROBLEMA: se provo a convertire il messaggio con JSONObject o = new JSONObject(messaggio) 
			// non converte le 2 nested class Utente ( mittente e destinatario )
			// se invece creo un oggetto utente e lo setto a mittente e/o destinatario ALLORA lo converte				

//quindi-> JSONObject o = new JSONObject(m) non converte mittente e destinatario
//mentre 			
//				Utente ut = new Utente();
//				ut.setNickname(m.getMittente().getNickname());
//				m.setMittente(ut);
//				m.setDestinatario(ut);
//  JSONObject o = new JSONObject(m); funziona e converte mittente e destinatario come un utente
			
				
				
//				Utente mitt = new Utente();
//				mitt.setNickname(m.getMittente().getNickname());
//				Utente dest = new Utente();
//				dest.setNickname(m.getDestinatario().getNickname());
//				m.setMittente(mitt);
//				m.setDestinatario(dest);
				
				JSONObject o = new JSONObject(m,true);
				jsonArrayInviati.put(o);
			}
			else if(m.getDestinatario().equals(u))
			{

//				Utente mitt = new Utente();
//				mitt.setNickname(m.getMittente().getNickname());
//				Utente dest = new Utente();
//				dest.setNickname(m.getDestinatario().getNickname());
//				m.setMittente(mitt);
//				m.setDestinatario(dest);
				
				JSONObject o = new JSONObject(m,true);
				jsonArrayRicevuti.put(o);
			}
		}

		JSONArray jsonArray = new JSONArray();
		
		jsonArray.put(jsonArrayInviati);
		jsonArray.put(jsonArrayRicevuti);

		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());

	}
}

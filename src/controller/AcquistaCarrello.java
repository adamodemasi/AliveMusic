package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AcquistaCarrello
 */
@WebServlet("/AcquistaCarrello")
public class AcquistaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcquistaCarrello() {
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
		System.out.println("COMPRA CARRELLO");
		HttpSession session = request.getSession();
		if(session.getAttribute("carrello")!=null)
		{
			List<Prodotto> prodottiCarrello = (List<Prodotto>) session.getAttribute("carrello");
			Utente u = (Utente) session.getAttribute("user");

			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

			ProdottoDao prodottoDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();
			
			List<Prodotto> prodottiNonAcquistati = new LinkedList<>();
			
			for (Prodotto prodotto : prodottiCarrello) 
			{
				System.out.println("carrello"+prodotto.getNome()+" "+prodotto.getQuantita()+" t"+prodotto.getTaglia());
				Prodotto p =  prodottoDao.findByPrimaryKey(prodottoDao.getIdByNomeTaglia(prodotto.getNome(), prodotto.getTaglia()));
				System.out.println("db"+p.getNome()+" "+p.getQuantita()+" t"+p.getTaglia());
				if(p.getQuantita()>= prodotto.getQuantita())
					utenteDao.effettuaAcquisto(u, prodotto);
				else
				{
					int quantita = prodotto.getQuantita();
					prodotto.setQuantita(p.getQuantita());
					utenteDao.effettuaAcquisto(u, prodotto);
					prodotto.setQuantita(quantita);
					prodottiNonAcquistati.add(prodotto);
				}
			}
			session.removeAttribute("carrello");
			
			JSONArray jsonProdottiNonAcquistati = new JSONArray();
			for(Prodotto p : prodottiNonAcquistati)
			{
				JSONObject o = new JSONObject(p);
				jsonProdottiNonAcquistati.put(o);
			}
			
			session.setAttribute("carrello",prodottiNonAcquistati);
			
			PrintWriter out = response.getWriter();
			out.println(jsonProdottiNonAcquistati.toString());
			
		}
		
	}

}

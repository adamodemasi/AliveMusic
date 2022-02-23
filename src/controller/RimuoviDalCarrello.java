package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import entita.Prodotto;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;

/**
 * Servlet implementation class RimuoviDalCarrello
 */
@WebServlet("/RimuoviDalCarrello")
public class RimuoviDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimuoviDalCarrello() {
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
		System.out.println("rimuovi dal carrreloo");
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = bufferedReader.readLine();

		while(line!=null)
		{
			jsonReceived.append(line);
			line = bufferedReader.readLine();
		}

		
		try {
			
		JSONObject o = new JSONObject(jsonReceived.toString());

		ProdottoDao prodottoDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();

		int idProdotto;
		
		Prodotto p = null;

			idProdotto = prodottoDao.getIdByNomeTaglia(o.getString("nome"), o.getString("taglia"));


			p = new Prodotto();
			p.setID(idProdotto);
			p.setNome(o.getString("nome"));
			p.setDescrizione(o.getString("descrizione"));
			p.setColore(o.getString("colore"));
			p.setImmagine(o.getString("immagine"));
			p.setPrezzo(o.getDouble("prezzo"));
			p.setQuantita(o.getInt("quantita"));
			p.setTipo(o.getString("tipo"));
			p.setTaglia(o.getString("taglia"));



		HttpSession session = request.getSession();

		List<Prodotto> prodottiCarrello = (List<Prodotto>) session.getAttribute("carrello");

		prodottiCarrello.remove(p);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		
	}

}

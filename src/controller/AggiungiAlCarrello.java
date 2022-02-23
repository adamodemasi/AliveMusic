package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;

/**
 * Servlet implementation class AggiungiAlCarrello
 */
@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiAlCarrello() {
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

		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));		
		String line = reader.readLine();
		while (line != null)
		{
			jsonReceived.append(line);
			line = reader.readLine();
		}
		
		try {
		
			JSONObject o = new JSONObject(jsonReceived.toString());
			
			System.out.println(o.toString());
			ProdottoDao prodottoDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();
			
			int idProdotto = prodottoDao.getIdByNomeTaglia(o.getString("nome"), o.getString("taglia"));

			Prodotto p = new Prodotto();
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
			
			List<Prodotto> prodotti;
			if(session.getAttribute("carrello")==null)
				prodotti = new LinkedList<>();
			else
				prodotti =  (List<Prodotto>) session.getAttribute("carrello");
			
			boolean presente = false;
			for(Prodotto pr: prodotti)
			{
				if(pr.getID() == p.getID())
				{
					pr.setQuantita(pr.getQuantita()+p.getQuantita());
					presente = true;
					break;
				}
			}
			
			if(!presente)
				prodotti.add(p);
			
			session.removeAttribute("carrello");
			session.setAttribute("carrello", prodotti);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}

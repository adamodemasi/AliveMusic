package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AcquistaProdotto
 */
@WebServlet("/AcquistaProdotto")
public class AcquistaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistaProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

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
			ProdottoDao prodottoDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();
			
			System.out.println("ACQUISTo");
			System.out.println(o.toString());
			
			String nome =  o.getString("nome");
			String taglia = o.getString("taglia");
			
			int idProdotto = prodottoDao.getIdByNomeTaglia(nome,taglia);

			Prodotto p = new Prodotto();
			p.setID(idProdotto);
			p.setNome(nome);
			p.setDescrizione(o.getString("descrizione"));
			p.setColore(o.getString("colore"));
			p.setImmagine(o.getString("immagine"));
			p.setPrezzo(o.getDouble("prezzo"));
			p.setQuantita(o.getInt("quantita"));
			p.setTipo(o.getString("tipo"));
			p.setTaglia(taglia);
			
			Utente u = (Utente) request.getSession().getAttribute("user");
			
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			
			utenteDao.effettuaAcquisto(u, p);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

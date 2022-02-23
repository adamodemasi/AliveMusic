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

import entita.Prodotto;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;

/**
 * Servlet implementation class DammiProdotti
 */
@WebServlet("/DammiProdotti")
public class DammiProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DammiProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ProdottoDao prodottoDao = DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();
		
		List<Prodotto> prodotti = prodottoDao.findAll();
		
		JSONArray jsonProdotti = new JSONArray();
		
		for(Prodotto p : prodotti)
		{
			JSONObject o = new JSONObject(p);
			jsonProdotti.put(o);
		}
		
		PrintWriter out = response.getWriter();
		out.println(jsonProdotti.toString());
	}
}

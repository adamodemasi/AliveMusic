package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.ExperienceProxy;
import persistenza.dao.ExperienceDao;

/**
 * Servlet implementation class MostraCarrello
 */
@WebServlet("/MostraCarrello")
public class MostraCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostraCarrello() {
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
		HttpSession session = request.getSession();
		if(session.getAttribute("carrello")!=null)
		{
			List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
			
			JSONArray jsonArray = new JSONArray();

			for(Prodotto p : carrello)
			{
				JSONObject jo = new JSONObject(p);
				jsonArray.put(jo);
			}

			System.out.println("carrello "+jsonArray.toString());
			PrintWriter out = response.getWriter();
			out.println(jsonArray.toString());
		}


}

}

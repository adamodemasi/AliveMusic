package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entita.Artista;
import entita.Evento;
import entita.Location;
import persistenza.DatabaseManager;
import persistenza.dao.EventoDao;

@WebServlet("/DammiEventiArtista")
public class DammiEventiArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DammiEventiArtista() {
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


		JSONObject jsonArtista;
		JSONArray jsonResult = new JSONArray();

		try {
			jsonArtista = new JSONObject(jsonReceived.toString());

			Artista art = new Artista();
			art.setNome(jsonArtista.getString("nome"));
			
			JSONArray jsonEventi = jsonArtista.getJSONArray("eventi");

			EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
			
			for(int i=0;i<jsonEventi.length();i++)
			{
				Evento e = new Evento();
				e.setID(jsonEventi.getJSONObject(i).getInt("ID"));

				System.out.println("evento con id "+e.getID());
				jsonResult.put(jsonEventi.getJSONObject(i));
				
				HashSet<Artista> artisti = eventoDao.getArtistiFromEvento(e);
				Location location = eventoDao.getLocationFromEvento(e);
				
				System.out.println(art.getNome());
				artisti.remove(art);
				
				JSONArray artistiJSON = new JSONArray();
				for(Artista a : artisti)
				{
					JSONObject o = new JSONObject(a,true);
					artistiJSON.put(o);
				}
				jsonResult.put(new JSONObject(location,true));
				jsonResult.put(artistiJSON);
			}

			
			PrintWriter out = response.getWriter();
			out.println(jsonResult.toString());




	} catch (JSONException e1) {
		e1.printStackTrace();
	}
}

}

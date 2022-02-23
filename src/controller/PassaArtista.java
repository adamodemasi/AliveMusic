package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
import persistenza.ArtistaProxy;
import persistenza.DatabaseManager;
import persistenza.dao.ArtistaDao;
import persistenza.dao.LocationDao;

/**
 * Servlet implementation class PassaArtista
 */
@WebServlet("/PassaArtista")
public class PassaArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassaArtista() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Artista art = (Artista) request.getSession().getAttribute("artista");
		ArtistaProxy a = (ArtistaProxy) DatabaseManager.getInstance().getDaoFactory().getArtistaDAO().findByPrimaryKey(art.getNome());

		//PEZZO IN CUI SI PRENDE LE LOCATION
		LocationDao ld=DatabaseManager.getInstance().getDaoFactory().getLocationDAO();
		List<Location> l=ld.findAll();
		
		request.getSession().setAttribute("locations",l);
		
		if (request.getSession().getAttribute("locations").equals(null))
		{
			System.out.println("VUOTO");
		}
		
		else
		{
			System.out.println("PIENO");
		}
		
	//	Artista a = (Artista) request.getSession().getAttribute("artista");

//		System.out.println(artistaCliccato.toString());
//
//		ArtistaDao artistaDao = DatabaseManager.getInstance().getDaoFactory().getArtistaDAO();
//
//		ArtistaProxy artistaProxy = (ArtistaProxy) artistaDao.findByPrimaryKey(artistaCliccato.getNome());
//
//		artistaProxy.setNome(artistaCliccato.getNome());
//		artistaProxy.setOn_tour(artistaCliccato.isOn_tour());
//		artistaProxy.setCopertina_band(artistaCliccato.getCopertina_band());
//		artistaProxy.setGenere(artistaCliccato.getGenere());
//		artistaProxy.setImmagine_band(artistaCliccato.getImmagine_band());
//		artistaProxy.setSito_web(artistaCliccato.getSito_web());
//
//		Artista a = artistaProxy;
//		System.out.println(artistaProxy.toString());

		JSONObject o = new JSONObject(a,true);
//		System.out.println(o.toString());
		PrintWriter out = response.getWriter();
		out.println(o.toString());

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
		System.out.println(jsonReceived.toString());


		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				

			System.out.println("JSON RICEVUTO "+json.toString());

			Artista a = new Artista();
			a.setNome(json.getString("nome"));
			a.setBio(json.getString("bio"));
			System.out.println("BIO DELL'ARTISTA PRESO: " + a.getBio());
			a.setCopertina_band(json.getString("copertina_band"));
			a.setGenere(json.getString("genere"));
			a.setImmagine_band(json.getString("immagine_band"));
			a.setSito_web(json.getString("sito_web"));
			
			HashSet<Evento> eventi = new HashSet<>();
			
			JSONArray eventiJson = json.getJSONArray("eventi");
			for(int i=0;i<eventiJson.length();i++)
			{
				JSONObject evJson = eventiJson.getJSONObject(i);
				Evento ev = new Evento();
				ev.setID(evJson.getInt("id"));
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ITALIAN);
				Date data = null;  // null perchè non settata nel proxy
//				try {
//					data = format.parse(evJson.getString("data")); // per ora null
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				eventi.add(ev);
			}

			a.setEventi(eventi);
			request.getSession().setAttribute("artista", a);
			
			System.out.println(a.toString());

			JSONObject o = new JSONObject(a);
			PrintWriter out = response.getWriter();
			out.println(o.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}

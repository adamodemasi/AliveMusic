package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Artista;
import entita.Evento;
import entita.Location;
import persistenza.DatabaseManager;
import persistenza.LocationProxy;
import persistenza.dao.EventoDao;
import persistenza.dao.LocationDao;

@WebServlet("/DammiEventiLocation")
public class DammiEventiLocation extends HttpServlet {
	private static final long serialVersionUID = 2636913101281752991L;

	public DammiEventiLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EventoDao ed = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();

		String loc = request.getParameter("location");

		LocationDao ld = DatabaseManager.getInstance().getDaoFactory().getLocationDAO();
		
		LocationProxy lp = (LocationProxy) ld.findByPrimaryKey(loc);

		Set<Evento> eventi = lp.getEventi();

		JSONArray eventiJSON = new JSONArray();
		
		for (Evento e : eventi) {
			eventiJSON.put(new JSONObject(e, true));
			HashSet<Artista> artisti = ed.getArtistiFromEvento(e);
			Location l = ed.getLocationFromEvento(e);
			JSONArray artistiJSON = new JSONArray();
			
			for (Artista a : artisti) {
				JSONObject o = new JSONObject(a, true);
				artistiJSON.put(o);
			}
			
			eventiJSON.put(new JSONObject(l, true));
			eventiJSON.put(artistiJSON);

		}

		PrintWriter out = response.getWriter();
		out.println(eventiJSON.toString());

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		doGet(request, response);
	}

}

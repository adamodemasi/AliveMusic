package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Artista;
import entita.Evento;
import entita.Location;
import persistenza.DataSource;
import persistenza.DatabaseManager;
import persistenza.dao.ArtistaDao;
import persistenza.dao.EventoDao;

public class DammiEventi extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException{
	
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
	
		List<Evento> eventi = eventoDao.findAll();
		
		JSONArray eventiJSON = new JSONArray();
		
		for(Evento e : eventi)
		{
			eventiJSON.put(new JSONObject(e,true));
			HashSet<Artista> artisti = eventoDao.getArtistiFromEvento(e);
			Location location = eventoDao.getLocationFromEvento(e);
			JSONArray artistiJSON = new JSONArray();
			for(Artista a : artisti)
			{
				JSONObject o = new JSONObject(a,true);
				artistiJSON.put(o);
			}
			eventiJSON.put(new JSONObject(location,true));
			eventiJSON.put(artistiJSON);

		}
		

		PrintWriter out = response.getWriter();
		out.println(eventiJSON.toString());
		
		
		
//		DataSource ds=new DataSource("jdbc:postgresql://localhost/musicexp","postgres","postgres");
//		
//		Connection connection=ds.getConnection();
//		
//		List<Artista> artisti=new ArrayList<Artista>();
//		
//		List<Location> locations=new ArrayList<Location>();
//		
//		List<Evento> eventi=new ArrayList<Evento>();
//	
//		try {
//					String cerca = "select distinct e.id,artista,data,location from esibizione as es,evento as e where es.evento=e.id";
//					PreparedStatement statement = connection.prepareStatement(cerca);
//					ResultSet result = statement.executeQuery();
//					while (result.next())
//					{
//						Evento e1=new Evento();
//						e1.setID(result.getInt("id"));
//						e1.setData(result.getDate("data"));
//				
//						
//						Artista a=new Artista();
//						a.setNome(result.getString("artista"));
//						
//						Location l=new Location();
//						l.setNome(result.getString("location"));
//					
//						eventi.add(e1);
//						artisti.add(a);
//						locations.add(l);
//					}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		
//		req.setAttribute("eventi",eventi);
//		req.setAttribute("artisti",artisti);
//		req.setAttribute("locations",locations);
//		
//		
//		
//		//ritorna la lista eventi con data luogo e artista
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/eventi.jsp");
//		dispatcher.forward(req, resp);
	}
	
	

	
}

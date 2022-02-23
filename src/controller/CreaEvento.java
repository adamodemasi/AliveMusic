package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entita.Artista;
import entita.Evento;
import entita.Location;
import persistenza.DatabaseManager;
import persistenza.LocationProxy;
import persistenza.dao.ArtistaDao;
import persistenza.dao.EventoDao;
import persistenza.dao.LocationDao;

/**
 * Servlet implementation class CreaEvento
 */
@WebServlet("/CreaEvento")
public class CreaEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaEvento() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArtistaDao ad=DatabaseManager.getInstance().getDaoFactory().getArtistaDAO();
		LocationDao ld=DatabaseManager.getInstance().getDaoFactory().getLocationDAO();
		EventoDao ed=DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		
		Artista a=(Artista) request.getSession().getAttribute("artista");
		
		String data_evento = request.getParameter("data");
		
		System.out.println(data_evento);

		DateFormat format = new SimpleDateFormat
							("yyyy-mm-dd", Locale.ITALIAN);
		Date date = null;
		try {
			date = format.parse(data_evento);
		}catch (ParseException e) {
			
		}
		
		System.out.println("DATA PARSERIZZATA: " + date); //ERRORE NELLA PARSERIZZAZIONE DELLA DATA!!!!!!!!!!!!
		
		String location=request.getParameter("locat");
		
		LocationProxy location_proxy=(LocationProxy) ld.findByPrimaryKey(location);
		
		Location l=ld.findByPrimaryKey(location);
		
		Evento e=new Evento();
		
		Set<Evento> eventi_per_location=location_proxy.getEventi();
		
		boolean trovato=false;
		
		for (Evento ev:eventi_per_location)
		{
			if (ev.getData().equals(date))
			{
				trovato=true;
				e=ev;
			}
		}
		
		if (trovato)
		{
			a.aggiungiEvento(e);
			
			ad.update(a);
		}
		
		else
		{
			e.setData(date);
			
			ed.save(e);
			
			a.aggiungiEvento(e);
			l.aggiungiEvento(e);
			
			ad.update(a);
			ld.update(l);
		}
		
		
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/artistaSingolo.jsp");
		rd.forward(request, response);
	}
}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import entita.Evento;
import entita.Experience;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class InserisciExperience
 */
@WebServlet("/InserisciExperience")
public class InserisciExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciExperience() {
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
		ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
		
		String posti = request.getParameter("numeroPosti");
		String descrizione = request.getParameter("descrizione");
		
		String eventoID = request.getParameter("eventoID");
		
		String s1=request.getParameter("pernottamento");
		String s2=request.getParameter("viaggio");
		String s3=request.getParameter("concerto");
		
		boolean pernottamento,viaggio,concerto;
		
		if (s1==null)
		{
			pernottamento=false;
		}
		
		else
		{
			pernottamento=true;
		}
		
		if (s2==null)
		{
			viaggio=false;
		}
		
		else
		{
			viaggio=true;
		}
		
		if (s3==null)
		{
			concerto=false;
		}
		
		else
		{
			concerto=true;
		}
			
		
		int id = Integer.parseInt(eventoID);

		String limite_adesione = request.getParameter("data");

		DateFormat format = new SimpleDateFormat
							("yyyy-MM-dd", Locale.ITALIAN);
		Date date = null;
		try {
			date = format.parse(limite_adesione);
		}catch (ParseException e) {
			
		}
		
		Experience exp = new Experience(date,Integer.parseInt(posti),descrizione,pernottamento,viaggio,concerto);
		
		
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		Utente utente = (Utente) request.getSession().getAttribute("user");

		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		
		
		
		experienceDao.save(exp);
		experienceDao.update(exp);

		Evento evento = eventoDao.findByPrimaryKey(id);
		
		evento.aggiungiExperience(exp);
		
		eventoDao.update(evento);

		
		utente.aggiungiExperience(exp);
		
		utenteDao.update(utente);
		
		
		JSONObject o = new JSONObject(exp,true);
		PrintWriter out = response.getWriter();
		out.println(o.toString());
		
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo.jsp");
//		dispatcher.forward(request, response);		
		
	}

}

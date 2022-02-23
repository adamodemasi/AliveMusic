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
import org.json.JSONML;
import org.json.JSONObject;

import entita.Experience;
import entita.Utente;
import javafx.scene.chart.PieChart.Data;
import persistenza.DatabaseManager;
import persistenza.EventoProxy;
import persistenza.ExperienceProxy;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;

/**
 * Servlet implementation class DammiExperience
 */
@WebServlet("/DammiExperience")
public class DammiExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DammiExperience() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String stringId = request.getParameter("eventoID");

		int id = Integer.parseInt(stringId);
		
		EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		EventoProxy eventoProxy = (EventoProxy) eventoDao.findByPrimaryKey(id);
		
		ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
		
		HashSet<Experience> experiences =  (HashSet<Experience>) eventoProxy.getExperiences();
		
		Utente organizzatore;
		
		JSONArray jsonArray = new JSONArray();
		
		for (Experience exp : experiences) {
			
			ExperienceProxy experience = (ExperienceProxy) exp;
			JSONObject jo = new JSONObject(experience,true);
			jsonArray.put(jo);
					
			organizzatore = experienceDao.getOrganizzatoreFromExperience(experience);
			JSONObject o = new JSONObject(organizzatore,true);
		
			System.out.println(jo.toString());
			
			jsonArray.put(o);
		}
		
		PrintWriter out = response.getWriter();
		out.println(jsonArray.toString());
		
	}

}

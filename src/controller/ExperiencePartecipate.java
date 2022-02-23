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

import entita.Experience;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

public class ExperiencePartecipate extends HttpServlet{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{

		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			String nickname = request.getParameter("utente");
			
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

			Utente u = utenteDao.findByPrimaryKey(nickname);
			
			ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
			
			List<Experience> experiences = experienceDao.findAll();

			HashSet<Experience> expPart = new HashSet<>();
			HashSet<Utente> partecipanti;
			
			for(Experience e: experiences)
			{
				partecipanti = (HashSet<Utente>) e.getPartecipanti();
				if(partecipanti.contains(u))
					expPart.add(e);
			}
			
			
			JSONArray jsonArray = new JSONArray();
			for (Experience experience : expPart) 
			{
				JSONObject jo = new JSONObject(experience,true);
				jsonArray.put(jo);
			}
			
			PrintWriter out = response.getWriter();
			out.println(jsonArray.toString());

		}

	};
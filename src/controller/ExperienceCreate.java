package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Experience;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.UtenteProxy;
import persistenza.dao.UtenteDao;

public class ExperienceCreate extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		{


		}



		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			String nickname = request.getParameter("utente");
			
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

			UtenteProxy utn = (UtenteProxy) utenteDao.findByPrimaryKey(nickname);
			
			
			HashSet<Experience> experiencesCreate = (HashSet<Experience>) utn.getExperiences_create();

			JSONArray jsonArray = new JSONArray();
			for (Experience experience : experiencesCreate) {
				JSONObject jo = new JSONObject(experience);
				jsonArray.put(jo);
			}
			
			PrintWriter out = response.getWriter();
			out.println(jsonArray.toString());
		}

	};
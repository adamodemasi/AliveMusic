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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.ExperienceProxy;
import persistenza.dao.ExperienceDao;

/**
 * Servlet implementation class MostraExperience
 */
@WebServlet("/MostraExperience")
public class MostraExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostraExperience() {
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
		
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));		
		String line = reader.readLine();
		while (line != null)
		{
			jsonReceived.append(line);
			line = reader.readLine();
		}		
		
		try {
			JSONObject json = new JSONObject(jsonReceived.toString());				
					
			ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
			
			ExperienceProxy experienceProxy = (ExperienceProxy) experienceDao.findByPrimaryKey(json.getInt("id"));
			
			Utente organizzatore = experienceDao.getOrganizzatoreFromExperience(experienceProxy);
		
			JSONArray jsonArray = new JSONArray();
			JSONObject je = new JSONObject(experienceProxy,true);
			jsonArray.put(je);
			
			JSONObject jorg = new JSONObject(organizzatore,true);
			jsonArray.put(jorg);

			
			PrintWriter out = response.getWriter();
			out.println(jsonArray.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
				
	}

}

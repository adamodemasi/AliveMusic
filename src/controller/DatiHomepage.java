package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import persistenza.DatabaseManager;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class DatiHomepage
 */
@WebServlet("/DatiHomepage")
public class DatiHomepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatiHomepage() {
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
		EventoDao ed=DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		ExperienceDao expd=DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
	
		Integer n_exp,n_ev,n_ut;
		
		n_exp=expd.findAll().size();
		
		n_ev=ed.findAll().size();
		
		n_ut=ud.findAll().size();
				
		PrintWriter out = response.getWriter();
		out.println((n_exp+" "+n_ev+" "+n_ut));
	}

}

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import email.Facade;
import entita.Experience;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.ExperienceProxy;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class ModificaExp
 */
@WebServlet("/ModificaExp")
public class ModificaExp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaExp() {
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
		StringBuffer jsonReceived = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));		
		String line = reader.readLine();
		while (line != null)
		{
			jsonReceived.append(line);
			line = reader.readLine();
		}


		try {
			JSONArray jsonArray = new JSONArray(jsonReceived.toString());

			JSONObject jsonExp = jsonArray.getJSONObject(0);
			JSONObject jsonOrganizzatore = jsonArray.getJSONObject(1);

			Experience e = new Experience();
			e.setID(jsonExp.getInt("ID"));
			e.setPosti(jsonExp.getInt("posti"));
			e.setDescrizione(jsonExp.getString("descrizione"));


			String dataString = jsonExp.getString("limite_adesione");
			DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ROOT);
			Date date = null;
			try {
				date = format.parse(dataString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			e.setLimite_adesione(date);

			List<String> nicknamePartecipanti = new LinkedList<>();
			List<String> emailPartecipanti = new LinkedList<>();

			ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();

			experienceDao.update(e);
			JSONArray jsonArrayPartecipanti = jsonExp.getJSONArray("partecipanti");
			for(int i=0;i<jsonArrayPartecipanti.length();i++)
			{
				JSONObject o = jsonArrayPartecipanti.getJSONObject(i);	
				nicknamePartecipanti.add(o.getString("nickname"));
				emailPartecipanti.add(o.getString("mail"));
			}

			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

			Utente organizzatore = utenteDao.findByPrimaryKey(jsonOrganizzatore.getString("organizzatore"));
			String mailOrg = organizzatore.getMail();

			//INVIO MAIL ORGANIZZATORE
			Facade.sendMessage(mailOrg,"CONFERMA MODIFICA DELLA TUA EXPERIENCE CON ID " + e.getID(),"Ti confermiamo le modifiche apportate all'experience. \\n \\n \\n \\n Il team di MusicExp.");
			System.out.println("ORGANIZZATORE "+mailOrg);
			
			//INVIO MAIL ALTRI PARTECIPANTI

							for(int i=0;i<emailPartecipanti.size();i++)
							{
								Facade.sendMessage(emailPartecipanti.get(i), "MODIFICA EXPERIENCE " + e.getID(),"L'experience " + e.getID() + " a cui partecipi è stata modificata: vai sul tuo profilo per controllare. \\n \\n \\n \\n Il team di MusicExp.");
								System.out.println("PARTECIPANTE "+i+" "+emailPartecipanti.get(i));
							}

		} catch (JSONException e) {
			e.printStackTrace();
		}		

	}

}

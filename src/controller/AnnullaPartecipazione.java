package controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
import entita.MessaggioPrivato;
import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.ExperienceDaoJDBC;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class AnnullaPartecipazione
 */
@WebServlet("/AnnullaPartecipazione")
public class AnnullaPartecipazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnullaPartecipazione() {
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
			JSONObject jsonLoggato = jsonArray.getJSONObject(2);

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
			String daRimuovere = jsonLoggato.getString("userLoggato");

			ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
			ExperienceDaoJDBC experienceDaoJDBC = (ExperienceDaoJDBC) experienceDao;


			experienceDaoJDBC.deleteAllPartecipazioneFromExp(e);


			JSONArray jsonArrayPartecipanti = jsonExp.getJSONArray("partecipanti");
			for(int i=0;i<jsonArrayPartecipanti.length();i++)
			{
				JSONObject o = jsonArrayPartecipanti.getJSONObject(i);	
				
				if(!o.getString("nickname").equals(daRimuovere))
				{
					nicknamePartecipanti.add(o.getString("nickname"));
					emailPartecipanti.add(o.getString("mail"));
					Utente u = new Utente();
					u.setNickname(o.getString("nickname"));
					
					e.aggiungiPartecipante(u);
				}
			}

			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

			experienceDao.update(e);


			Utente organizzatore = utenteDao.findByPrimaryKey(jsonOrganizzatore.getString("organizzatore"));
			String mailOrg = organizzatore.getMail();

			
			//INVIO MAIL ORGANIZZATORE
			System.out.println("MAIL ORGANIZZATORE "+organizzatore.getMail());
			
			Facade.sendMessage(mailOrg,"ANNULLAMENTO DELLA PARTECIPAZIONE ALLA TUA CON EXPERIENCE CON "+e.getID(), "L'utente" + daRimuovere + " non partecipa piu alla tua experience! \\n \\n \\n \\n Il team di MusicExp.");

			//INVIO MAIL ALTRI PARTECIPANTI

				for(int i=0;i<emailPartecipanti.size();i++)
				{
					System.out.println("EMAIL PARTECIPANTE "+i+" -> "+emailPartecipanti.get(i));
					Facade.sendMessage(emailPartecipanti.get(i), "RIMOZIONE UTENTE EXPERIENCE A CUI PARTECIPI", "Hai un compagno di viaggio in meno "+daRimuovere+" ha deciso di non venire piu \\n \\n \\n \\n Il team di MusicExp.");
				}

			JSONObject jsonObject =  new JSONObject(e);

			PrintWriter out = response.getWriter();
			out.println(jsonObject.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}		


	}

}
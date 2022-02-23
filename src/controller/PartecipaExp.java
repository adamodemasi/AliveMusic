package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.Utf8Encoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import email.Facade;
import entita.Evento;
import entita.Experience;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.UtenteProxy;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;


/**
 * Servlet implementation class PartecipaExp
 */
@WebServlet("/PartecipaExp")
public class PartecipaExp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartecipaExp() {
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

			JSONArray jsonArrayPartecipanti = jsonExp.getJSONArray("partecipanti");
			for(int i=0;i<jsonArrayPartecipanti.length();i++)
			{
				JSONObject o = jsonArrayPartecipanti.getJSONObject(i);	
				nicknamePartecipanti.add(o.getString("nickname"));
			}


			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			String nickLoggato = jsonLoggato.getString("userLoggato");

			if(nickLoggato.equals("Profilo") || nickLoggato == null)
			{
				//				JSONObject jobj = new JSONObject();
//				String urlToRedirect = "/login.jsp";
				//				jobj.put("url",urlToRedirect);
				//				response.getWriter().write(jobj.toString());
				response.getWriter().print("loggati");
			}
			else if(e.getPosti() - nicknamePartecipanti.size() > 0 )// controllo già fatto in jquery
			{
				UtenteProxy nuovoPartecipante = (UtenteProxy) utenteDao.findByPrimaryKey(nickLoggato);

//				boolean partecipaAdUnAltraExperience = false;

				//non puo partecipare se partecipa gia a un experience per lo stesso evento
				// oppure se ha creato lui un experiece per lo stesso evento
				//VEDERE SE INSERISCI EXPERIENCE PUO FARLO ANCHE SE PARTECIPA A UN EXP

				ExperienceDao experienceDao = DatabaseManager.getInstance().getDaoFactory().getExperienceDAO();
//				EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO(); 
//
//				LinkedList<Evento> allEvent = (LinkedList<Evento>) eventoDao.findAll();
//
//				Evento eventoInQuestione = null;
//
//				for (Iterator itEvento = allEvent.iterator(); itEvento.hasNext() && eventoInQuestione==null;) 
//				{
//					Evento evento = (Evento) itEvento.next();
//
//					HashSet<Experience> allExpAssociate = (HashSet<Experience>) evento.getExperiences();
//					for(Iterator itExperience = allExpAssociate.iterator(); itExperience.hasNext() && !partecipaAdUnAltraExperience;)
//					{
//						Experience experience = (Experience) itExperience.next();
//						if( experience.getID() == e.getID())
//						{
//							eventoInQuestione = evento;
//							if(experience.getPartecipanti().contains(nuovoPartecipante))
//								partecipaAdUnAltraExperience = true;
//						}
//					}
//
//				}
//				if(partecipaAdUnAltraExperience)
//					response.getWriter().println("STAI PARTECIPANDO AD UN ALTRA EXPERIECE RELATIVA A QUESTO EVENTO con id:"+eventoInQuestione.getID());
//				else
//				{
//
//					//controllo se sta organizzando un exp sullo stesso ev
//					HashSet<Experience> experienceCreate = (HashSet<Experience>) nuovoPartecipante.experiences_create();
//
//					HashSet<Experience> experiencesDellEventoInQuestione = (HashSet<Experience>) eventoInQuestione.getExperiences();
//
//					boolean organizzatoreDiExperienceConStessoEvento = false;
//
//					for (Iterator itExpInQUestione = experiencesDellEventoInQuestione.iterator(); itExpInQUestione.hasNext() && !organizzatoreDiExperienceConStessoEvento;) 
//					{
//						Experience experienceInQuestione = (Experience) itExpInQUestione.next();
//						for (Iterator itExpCreate = experienceCreate.iterator(); itExpCreate.hasNext() && !organizzatoreDiExperienceConStessoEvento;) 
//						{
//							Experience experienceCreata = (Experience) itExpCreate.next();
//							if(experienceCreata.getID() == experienceInQuestione.getID())
//								organizzatoreDiExperienceConStessoEvento =true;
//						}
//					}
//
//
//					if(organizzatoreDiExperienceConStessoEvento)
//						response.getWriter().print("SEI ORGANIZZATORE DI UN ALTRA EXPERIECE RELATIVA A QUESTO EVENTO con id:"+eventoInQuestione.getID());
//
//					else  {
//
						e.aggiungiPartecipante(nuovoPartecipante);
						experienceDao.update(e);

						Utente organizzatore = utenteDao.findByPrimaryKey(jsonOrganizzatore.getString("organizzatore"));
						String mailOrg = organizzatore.getMail();

						//INVIO MAIL ORGANIZZATORE
						Facade.sendMessage(mailOrg,"AGGIUNTA NUOVO MEMBRO ALLA TUA EXPERIENCE "+e.getID(), "C'e' un nuovo utente che partecipa alla tua experience! Il suo nickname e' "+nickLoggato + "\n \n \n \n Il team di MusicExp.");
						//INVIO MAIL ALTRI PARTECIPANTI

										for(int i=0;i<nicknamePartecipanti.size();i++)
										{
											if(!nicknamePartecipanti.get(i).equals(nickLoggato))
											{
												Facade.sendMessage(utenteDao.findByPrimaryKey(nicknamePartecipanti.get(i)).getMail(), "HAI UN NUOVO COMPAGNO DI VIAGGIO", nickLoggato + "ha deciso di unirsi all'experience " + e.getID() + "a cui partecipi. \\n \\n \\n \\n Il team di MusicExp.");
												
												System.out.println(utenteDao.findByPrimaryKey(nicknamePartecipanti.get(i)).getMail());
											}
										}



						response.getWriter().print("PARTECIPAZIONE AVVENUTA CON SUCCESSO");
			}
			else
			{
				response.getWriter().print("Non ci sono più posti");
			}


		} catch (JSONException e) {
			e.printStackTrace();
		}		


	}

}

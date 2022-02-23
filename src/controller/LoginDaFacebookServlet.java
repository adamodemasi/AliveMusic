package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import email.Facade;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;
/**
 * Servlet implementation class LoginDaFacebookServlet
 */
@WebServlet("/LoginDaFacebookServlet")
public class LoginDaFacebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDaFacebookServlet() {
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
		// TODO Auto-generated method stub
UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		String nome=new String();
		
		if (request.getParameterNames().hasMoreElements()) {
			String jsonString = request.getParameter("jsonUtenteFacebook");
			if (jsonString != null) {
				ObjectMapper mapper = new ObjectMapper();
				Utente u = mapper.readValue(jsonString, Utente.class);
				HttpSession httpSession = request.getSession();
				
				List<Utente> utenti=ud.findAll();
				
				System.out.println(u.toString());
				
				boolean trovato=false,uguale=false;
				
				for (Utente utente:utenti)
				{
					if (utente.getNickname().equals(u.getNickname()))
					{
						trovato=true;
						break;
					}
					
					else if ((!utente.getNickname().equals(u.getNickname()) && utente.getMail().equals(u.getMail())))
					{
						uguale=true;
						nome=utente.getNickname();
						break;
					}
				}

				
				
				if (trovato && !uguale)
				{
					httpSession.setAttribute("user", u);
					response.setStatus(HttpServletResponse.SC_OK);
				}
				
				else if (!trovato && uguale)
				{
					Utente ut1=ud.findByPrimaryKey(nome);
					httpSession.setAttribute("user",ut1);
					response.setStatus(HttpServletResponse.SC_OK);
				}
				
				else if (!trovato || !uguale)
				{
					Utente ute=new Utente(u.getNickname(),u.getNome(),u.getCognome(),u.getCitta_residenza(),u.getMail(),u.getPassword(),u.getAvatar(),u.getBio(),u.getModeratore());
					DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().save(ute);
					
					Facade.sendMessage(u.getMail(),"REGISTRAZIONE EFFETTUATA", "Ti comunichiamo che la registrazione è avvenuta con successo" + 
					"Questi sono i tuoi dati: " + 
					"Nickname: " + u.getNickname() + 
					"Indirizzo mail: " + u.getMail() + 
					"Password: " + u.getPassword());
					
					httpSession.setAttribute("user", u);
					response.setStatus(HttpServletResponse.SC_OK);
				}					
				}

			}
	}

}

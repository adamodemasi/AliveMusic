

package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.Facade;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class RegistraUtente
 */
@WebServlet("/RegistraUtente")
public class RegistraUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mail=request.getParameter("mail");
		String password=request.getParameter("password");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String nickname=request.getParameter("nickname");
	//	String data_nascita=request.getParameter("data");
		String citta=request.getParameter("citta");
		
		Date d;
		
	//	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 //		d=formatter.parse(data_nascita);
			
			UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			
			List<Utente> utenti=ud.findAll();
			
			boolean ugualeNick= false, ugualeMail = false;
			
			for (Iterator<Utente> it = utenti.iterator();it.hasNext() && !ugualeNick && !ugualeMail;) 
			{
				Utente u = it.next();
				if (u.getNickname().equals(nickname) )
				{
					ugualeNick = true;
				}
				else if(u.getMail().equals(mail))
				{
					ugualeMail = true;
				}
			}
		
			PrintWriter out = response.getWriter();
			if(ugualeMail)
				out.print("puoiLoggare");
			else if(ugualeNick)
				out.print("nicknameUguale");
			else
			{
			Utente u=new Utente();
			u.setNickname(nickname);
			u.setNome(nome);
			u.setCognome(cognome);
			u.setMail(mail);
			u.setPassword(password);
		//	u.setData_nascita(d);
			u.setCitta_residenza(citta);
			u.setModeratore(false);
			ud.save(u);
			
			String testo="Ti comunichiamo che la registrazione al nostro sito e' stata effettuata correttamente." +
			"Questi sono i tuoi dati d'accesso: " +
			"Indirizzo Mail: " + u.getMail() + 
			"Password: " + u.getPassword()
			+"Lo staff di MusicExp";
			
			Facade.sendMessage(u.getMail(),"REGISTRAZIONE AVVENUTA CON SUCCESSO",testo);
			//forse
//			u = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO().findByPrimaryKey(u.getNickname());
			//forse
			
			request.getSession().setAttribute("user", u);
			out.print(nickname);
			}
		
	}

}

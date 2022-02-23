package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class CambiaPassword
 */
@WebServlet("/CambiaPassword")
public class CambiaPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail=request.getParameter("mail");
		
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		
		List<Utente> utenti=ud.findAll();
		
		Utente utente_da_modificare=new Utente();
		
		for (Utente u:utenti)
		{
			if (mail.equals(u.getMail()))
			{
				utente_da_modificare=u;
				break;
			}
		}
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder(8);
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String new_pass = sb.toString();
		
		utente_da_modificare.setPassword(new_pass);
		
		ud.update(utente_da_modificare);
		
		Facade.sendMessage(mail, "CAMBIO PASSWORD", "Ecco , come richiesto, la tua nuova password: " + new_pass + ". Ti ricordiamo che puoi cambiarla quando vuoi dal tuo profilo");
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/inviomail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				String mail=request.getParameter("mail");
				
				UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				
				List<Utente> utenti=ud.findAll();
				
				Utente utente_da_modificare=new Utente();
				
				for (Utente u:utenti)
				{
					if (mail.equals(u.getMail()))
					{
						utente_da_modificare=u;
						break;
					}
				}
				
				PrintWriter out=response.getWriter();
				
				if (utente_da_modificare.getNickname()==null)
				{
					out.print("fallito");
				}
				
				else
				{
					char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
					StringBuilder sb = new StringBuilder(8);
					Random random = new Random();
					for (int i = 0; i < 8; i++) {
					    char c = chars[random.nextInt(chars.length)];
					    sb.append(c);
					}
					String new_pass = sb.toString();
					
					utente_da_modificare.setPassword(new_pass);
					
					ud.update(utente_da_modificare);
					
					Facade.sendMessage(mail, "CAMBIO PASSWORD", "Ecco , come richiesto, la tua nuova password: " + new_pass + ". Ti ricordiamo che puoi cambiarla quando vuoi dal tuo profilo");
				
					out.print("successo");
				}
	}

}

package controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entita.Prodotto;
import persistenza.DatabaseManager;
import persistenza.UtenteProxy;
import persistenza.dao.UtenteDao;

public class MostraAltroUtente extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String nicknameCercato = req.getParameter("nickname");
		
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();

		UtenteProxy u = (UtenteProxy) utenteDao.findByPrimaryKey(nicknameCercato);
		
//		Set<Prodotto> prodotti_creati = u.getProdotti_realizzati();

		HttpSession session = req.getSession();
		
//		session.setAttribute("prodotti", prodotti_creati);

		session.setAttribute("utente_trovato",u);
		
	}
	
}

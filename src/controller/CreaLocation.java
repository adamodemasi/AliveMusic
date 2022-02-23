package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entita.Location;
import persistenza.DatabaseManager;
import persistenza.dao.LocationDao;

/**
 * Servlet implementation class CreaLocation
 */
@WebServlet("/CreaLocation")
public class CreaLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaLocation() {
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
		
		LocationDao ld=DatabaseManager.getInstance().getDaoFactory().getLocationDAO();
		
		String location=request.getParameter("nome");
		String citta=request.getParameter("citta");
		String stato=request.getParameter("stato");
		
		Location l=new Location();
		
		l.setCitta(citta);
		l.setNome(location);
		l.setStato(stato);
		
		ld.save(l);
		
		
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/eventi.jsp");
		rd.forward(request, response);
		
	}

}

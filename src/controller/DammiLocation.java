package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entita.Location;
import persistenza.DatabaseManager;
import persistenza.dao.LocationDao;

@WebServlet("/DammiLocation")
public class DammiLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DammiLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LocationDao ld = DatabaseManager.getInstance().getDaoFactory().getLocationDAO();

		List<Location> location = ld.findAll();

		location.toString();

		request.setAttribute("locations", location);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/eventi.jsp");
		dispatcher.forward(request, response);
	}

}
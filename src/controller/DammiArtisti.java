package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entita.Artista;
import persistenza.DatabaseManager;
import persistenza.dao.ArtistaDao;

public class DammiArtisti extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		ArtistaDao artistaDao = DatabaseManager.getInstance().getDaoFactory().getArtistaDAO();

		List<Artista> artisti = artistaDao.findAll();
		
		JSONArray jsonArtisti = new JSONArray();

		for (Artista a : artisti)
		{
			JSONObject o = new JSONObject(a,true);
			jsonArtisti.put(o);
		}


		PrintWriter out = resp.getWriter();
		out.println(jsonArtisti.toString());
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

};
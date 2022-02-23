package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entita.Artista;

import persistenza.DatabaseManager;
import persistenza.dao.ArtistaDao;
/**
 * Servlet implementation class AggiungiServlet
 */
@WebServlet("/AggiungiArtista")
@MultipartConfig
public class AggiungiArtista extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private File file;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiArtista() {
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
		ArtistaDao ad=DatabaseManager.getInstance().getDaoFactory().getArtistaDAO();
		
		Artista art=new Artista();
		
		boolean isMultipart;
		String filePath;
		int maxFileSize=1000*1024;
		int maxMemSize=5*1024;
		filePath=System.getProperty("user.home") + "\\git\\SIW\\AliveMusic\\WebContent\\img\\";
		
		isMultipart=ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		if (!isMultipart)
		{
			out.print("file not uploaded");
			return;
		}
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(maxFileSize);
		factory.setRepository(new File(System.getProperty("user.home") + "\\git\\SIW\\AliveMusic\\WebContent\\img\\"));
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		
		try {
			
			List fileItems=upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			
			while (i.hasNext())
			{
				FileItem fi=(FileItem) i.next();
				if (!fi.isFormField())
				{
					String fieldname=fi.getFieldName();
					String filename=fi.getName();
					String contentType=fi.getContentType();
					boolean isInMemory=fi.isInMemory();
					long sizeInBytes=fi.getSize();
					if (filename.lastIndexOf("\\")>=0)
					{
						file=new File(filePath + filename.substring(filename.lastIndexOf("\\")));
					}
					
					else
					{
						file=new File(filePath + filename.substring(filename.lastIndexOf("\\")+1));
					}
					
					fi.write(file);
					out.println("FILE UPLOADED" + filename);
					out.print("PATH DEL FILE: " + file.getPath());
					
					System.out.println(file.getPath().substring(45,file.getPath().length()));
					
					//MODIFICO IL NOME DEL PATH PER FARLO ADERIRE AL DATABASE
					StringBuilder stringbuilder=new StringBuilder(file.getPath().substring(45,file.getPath().length()));
					stringbuilder.setCharAt(3,'/');
					
					String path_da_memorizzare=String.valueOf(stringbuilder);
					
					if (fieldname.equals("img_artista"))
					{	
						art.setImmagine_band(path_da_memorizzare);
					}
					
					else if (fieldname.equals("cop_artista"))
					{
						art.setCopertina_band(path_da_memorizzare);
					}
				}
				
				else
				{
					String fieldname=fi.getFieldName();
					
					if (fieldname.equals("nome"))
					{
						art.setNome(fi.getString());
					}
					
					else if (fieldname.equals("sito"))
					{
						art.setSito_web(fi.getString());
					}
					
					else if (fieldname.equals("genere"))
					{
						art.setGenere(fi.getString());
					}
					
					else if (fieldname.equals("bio"))
					{
						art.setBio(fi.getString());
					}
				}
				
			
			}
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/artisti.jsp");
			rd.forward(request, response);
			
		}catch(Exception e)
		{
			out.println(e);
		}
		
		ad.save(art);
	}

}

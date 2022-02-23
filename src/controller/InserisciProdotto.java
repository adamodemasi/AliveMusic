package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import entita.Prodotto;
import entita.Utente;
import persistenza.DatabaseManager;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

/**
 * Servlet implementation class InserisciProdotto
 */
@WebServlet("/InserisciProdotto")
@MultipartConfig
public class InserisciProdotto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private File file;
	
	Prodotto p1=new Prodotto();
	Prodotto p2=new Prodotto();
	Prodotto p3=new Prodotto();
	Prodotto p4=new Prodotto();
	
	Random randomXSL=new Random();
	Random randomSM=new Random();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		throw new ServletException("GET method used with " +
	            getClass( ).getName( )+": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nXSL=randomXSL.nextInt(15)+1;
		int nSM=randomSM.nextInt(25)+1;
		
		p1.setQuantita(nXSL);
		p2.setQuantita(nSM);
		p3.setQuantita(nSM);
		p4.setQuantita(nXSL);
		
		p1.setTaglia("XS");
		p2.setTaglia("S");
		p3.setTaglia("M");
		p4.setTaglia("L");
		
		UtenteDao ud=DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		ProdottoDao pd=DatabaseManager.getInstance().getDaoFactory().getProdottoDAO();
		Utente u=(Utente) request.getSession().getAttribute("user");
		
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
					
					p1.setImmagine(path_da_memorizzare);
					p2.setImmagine(path_da_memorizzare);
					p3.setImmagine(path_da_memorizzare);
					p4.setImmagine(path_da_memorizzare);
					
				}
				
				else
				{
					String fieldname=fi.getFieldName();
					
					if (fieldname.equals("nome_p"))
					{
						p1.setNome(fi.getString());
						p2.setNome(fi.getString());
						p3.setNome(fi.getString());
						p4.setNome(fi.getString());
					}
					
					else if (fieldname.equals("descrizione_p"))
					{
						p1.setDescrizione(fi.getString());
						p2.setDescrizione(fi.getString());
						p3.setDescrizione(fi.getString());
						p4.setDescrizione(fi.getString());
					}
					
					else if (fieldname.equals("prezzo_p"))
					{
						p1.setPrezzo(Integer.parseInt(fi.getString()));
						p2.setPrezzo(Integer.parseInt(fi.getString()));
						p3.setPrezzo(Integer.parseInt(fi.getString()));
						p4.setPrezzo(Integer.parseInt(fi.getString()));
					}
					
					else if (fieldname.equals("colore_p"))
					{
						p1.setColore(fi.getString());
						p2.setColore(fi.getString());
						p3.setColore(fi.getString());
						p4.setColore(fi.getString());
					}
					
					else if (fieldname.equals("tipologia_p"))
					{
						p1.setTipo(fi.getString());
						p2.setTipo(fi.getString());
						p3.setTipo(fi.getString());
						p4.setTipo(fi.getString());
					}
					
					//AGGIUNGERE TAGLIA
					//AGGIUNGERE QUANTITA'
				}
				
			
			}
			
		}catch(Exception e)
		{
			out.println(e);
		}
		
		pd.save(p1);
		pd.save(p2);
		pd.save(p3);
		pd.save(p4);
		
		u.aggiungiProdottoRealizzato(p1);
		u.aggiungiProdottoRealizzato(p2);
		u.aggiungiProdottoRealizzato(p3);
		u.aggiungiProdottoRealizzato(p4);
		
		ud.update(u);
		
		request.getSession().setAttribute("user",u);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/shop.jsp");
		rd.forward(request, response);
		
	}
		
		
		
		
		
	}



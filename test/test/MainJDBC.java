package test;

import java.util.Calendar;

import java.util.Date;

import entita.Artista;
import entita.Evento;
import entita.Experience;
import entita.Location;
import entita.MessaggioPrivato;
import entita.Prodotto;
import entita.Utente;
import persistenza.DAOFactory;
import persistenza.UtenteProxy;
import persistenza.UtilDao;
import persistenza.dao.ArtistaDao;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public class MainJDBC {

	public static void main(String args[]) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(1995, Calendar.JANUARY, 21); // // 21 marzo 1995
		Date date1 = cal.getTime();
		cal.set(1996, Calendar.FEBRUARY, 12); // 12 aprile 1996
		Date date2 = cal.getTime();
		cal.set(1998, Calendar.MARCH, 1); // 1 ottobre 1998
		Date date3 = cal.getTime();
		cal.set(1986,Calendar.APRIL, 24);
		Date date4=cal.getTime();
		cal.set(2018, Calendar.MAY,16);
		Date date5=cal.getTime();
		cal.set(2018,Calendar.JUNE,23);
		Date date6=cal.getTime();
		cal.set(2018,Calendar.JULY,5);
		Date date7=cal.getTime();
		cal.set(2018,Calendar.AUGUST,3);
		Date date8=cal.getTime();
		cal.set(2018,Calendar.SEPTEMBER,16);
		Date date9=cal.getTime();
		cal.set(2018, Calendar.OCTOBER,23);
		Date date10=cal.getTime();
		cal.set(2018,Calendar.NOVEMBER,6);
		Date date11=cal.getTime();
		cal.set(2018, Calendar.DECEMBER,25);
		Date date12=cal.getTime();
		
		
		// istanza singleton per fabbrica di oggetti DAO
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);

		// istanza per creare oggetti DAO
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		util.createDatabase();

		// per ogni entita va creata la propria DAO factory
		ArtistaDao artistaDao = factory.getArtistaDAO();
		UtenteDao utenteDao = factory.getUtenteDAO();
		LocationDao locationDao = factory.getLocationDAO();
		EventoDao eventoDao = factory.getEventoDAO();
		ExperienceDao experienceDao = factory.getExperienceDAO();
		MessaggioPrivatoDao msg_pvtDao = factory.getMessaggioPrivatoDAO();
		ProdottoDao prodottoDao = factory.getProdottoDAO();
		

		//DICHIARO UTENTI 
		Utente u = new Utente("turuzzu", "manuel", "donato","Catanzaro","futura95@live.it", "ciao","img/diamond.png","",true);
		Utente u2 = new Utente("xgabry94", "gabriele", "noto","Molochio", "gabnt8@gmail.com", "ciao","img/pearljam.jpg","",true);
		Utente u3 = new Utente("pacciu86", "adamo", "demasi", "Siderno", "pacciu86@mammt.lol", "ciao","img/foof.png","",true);
		Utente u4 = new Utente("violi_hate","domenico","violi","Catanzaro","dome.violi90@gmail.com","ciao","","",false);

		//DICHIARO EVENTI
		Evento ev1 = new Evento(date1);
		Evento ev2= new Evento(date2);
		Evento ev3= new Evento(date3);
		Evento ev4 = new Evento(date4);
		Evento ev5= new Evento(date5);
		Evento ev6= new Evento(date6);
		Evento ev7 = new Evento(date7);
		Evento ev8= new Evento(date8);
		Evento ev9= new Evento(date9);
		Evento ev10 = new Evento(date10);
		Evento ev11= new Evento(date11);
		Evento ev12= new Evento(date12);
		
		
		//DICHIARO LOCATION.
		Location l  = new Location("Bataclan", "Parigi", "Francia",48.8630134f,2.3684271f);
		Location l2 = new Location("Ippodromo del Visarno","Firenze","Italia",43.781215f,11.2236607f);
		Location l3 = new Location("Alcatraz","Milano","Italia",45.494689f,9.1804693f);
	    Location l4 = new Location("PalaLottomatica", "Roma", "Italia", 41.8253202f, 12.46432f);
	    Location l5 = new Location("Auditorium Live", "Roma", "Italia", 41.8171933f, 12.4607955f);
	    Location l6 = new Location("Auditorium Parco della Musica", "Roma", "Italia", 41.929556f, 12.4724258f);
	    Location l7 = new Location("Orion", "Ciampino", "Italia", 41.808872f, 12.5948439f);

		//DICHIARO ARTISTI
		Artista a1 = new Artista("Avenged Sevenfold","http://avengedsevenfold.com","img/a7x.jpg","Alternative Metal","img/a7x.jpg","Orange County,CA");
		Artista a2 = new Artista("Deftones","http://deftones.com","img/deftones.jpeg","Alternative Metal","img/trasferimento.png","Sacramento,CA");
		Artista a3 = new Artista("Slipknot","http://www.slipknot1.com","img/slipknot.jpg","Alternative/Death metal","img/deftones.jpeg","Des Moines,IO");
		
		//DICHIARO EXPERIENCES
		Experience e1 = new Experience(date5,10,"Viaggio in autobus",true,true,true);
		Experience e2 = new Experience(date6,8,"Viaggio in treno",false,true,false);
		Experience e3 = new Experience(date7,3,"Tresca feroce",false,false,true);
		Experience e4 = new Experience(date8,4,"Gangbang a tutto spiano",true,false,false);
		Experience e5 = new Experience(date9,12,"Eddie Van Halen che fa cose",false,true,true);
		Experience e6 = new Experience(date10,3,"Tom Morello dio",true,true,false);
		Experience e1b = new Experience(date1,10,"Viaggio inwdkfkwelfjq autobus",true,false,true);
		Experience e2b = new Experience(date2,8,"Viaggiowdq in treno",false,true,true);
		Experience e3b = new Experience(date3,3,"Treqwdsca feroce",false,false,true);
		Experience e4b = new Experience(date4,4,"Gangbang a tutwqddwdwqqto spiano",true,true,false);
		Experience e5b = new Experience(date5,12,"Eddie saddsadsVan Halen che fa cose",true,false,true);
		Experience e6b = new Experience(date6,3,"Tasdsadom Morello dio",true,true,false);

		
		//DICHIARO PRODOTTI
		Prodotto p1a = new Prodotto();
		p1a.setNome("Felpa Slipknot");
		p1a.setDescrizione("descsorgjsojosgkoew1");
		p1a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p1a.setTipo("felpa");
		p1a.setQuantita(6);
		p1a.setColore("black");
		p1a.setTaglia("S");
		p1a.setPrezzo(2);
		
		
		Prodotto p1b = new Prodotto();
		p1b.setNome("Felpa Slipknot");
		p1b.setDescrizione("descsorgjsojosgkoew1");
		p1b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p1b.setTipo("felpa");
		p1b.setQuantita(23);
		p1b.setColore("black");
		p1b.setTaglia("XL");
		p1b.setPrezzo(2);

		Prodotto p1c = new Prodotto();
		p1c.setNome("Felpa Slipknot");
		p1c.setDescrizione("descsorgjsojosgkoew1");
		p1c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p1c.setTipo("felpa");
		p1c.setQuantita(10);
		p1c.setColore("black");
		p1c.setTaglia("L");
		p1c.setPrezzo(2);

		Prodotto p1d = new Prodotto();
		p1d.setNome("Felpa Slipknot");
		p1d.setDescrizione("descsorgjsojosgkoew1");
		p1d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p1d.setTipo("felpa");
		p1d.setQuantita(53);
		p1d.setColore("black");
		p1d.setTaglia("M");
		p1d.setPrezzo(2);

		Prodotto p2a = new Prodotto();
		p2a.setNome("Maglia Avenged");
		p2a.setDescrizione("descrizione111");
		p2a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p2a.setTipo("tshirt");
		p2a.setQuantita(12);
		p2a.setColore("blue");
		p2a.setTaglia("S");
		p2a.setPrezzo(4);

		Prodotto p2b = new Prodotto();
		p2b.setNome("Maglia Avenged");
		p2b.setDescrizione("descrizione111");
		p2b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p2b.setTipo("tshirt");
		p2b.setQuantita(19);
		p2b.setColore("blue");
		p2b.setTaglia("L");
		p2b.setPrezzo(4);

		Prodotto p2c = new Prodotto();
		p2c.setNome("Maglia Avenged");
		p2c.setDescrizione("descrizione111");
		p2c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p2c.setTipo("tshirt");
		p2c.setQuantita(2);
		p2c.setColore("blue");
		p2c.setTaglia("XL");
		p2c.setPrezzo(4);

		
		Prodotto p2d = new Prodotto();
		p2d.setNome("Maglia Avenged");
		p2d.setDescrizione("descrizione111");
		p2d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p2d.setTipo("tshirt");
		p2d.setQuantita(72);
		p2d.setColore("blue");
		p2d.setTaglia("M");
		p2d.setPrezzo(4);

		
		Prodotto p3a = new Prodotto();
		p3a.setNome("Maglia Slipknot");
		p3a.setDescrizione("descirojiadsjdsia242432");
		p3a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p3a.setTipo("tshirt");
		p3a.setQuantita(32);
		p3a.setColore("black");
		p3a.setTaglia("M");
		p3a.setPrezzo(3);

		
		Prodotto p3b = new Prodotto();
		p3b.setNome("Maglia Slipknot");
		p3b.setDescrizione("descirojiadsjdsia242432");
		p3b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p3b.setTipo("tshirt");
		p3b.setQuantita(90);
		p3b.setColore("black");
		p3b.setTaglia("S");
		p3b.setPrezzo(3);
		
		
		Prodotto p3c = new Prodotto();
		p3c.setNome("Maglia Slipknot");
		p3c.setDescrizione("descirojiadsjdsia242432");
		p3c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p3c.setTipo("tshirt");
		p3c.setQuantita(31);
		p3c.setColore("black");
		p3c.setTaglia("XL");
		p3c.setPrezzo(3);
		
		Prodotto p3d = new Prodotto();
		p3d.setNome("Maglia Slipknot");
		p3d.setDescrizione("descirojiadsjdsia242432");
		p3d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p3d.setTipo("tshirt");
		p3d.setQuantita(9);
		p3d.setColore("black");
		p3d.setTaglia("L");
		p3d.setPrezzo(3);

		Prodotto p4a = new Prodotto();
		p4a.setNome("Felpa 4");
		p4a.setDescrizione("descsorgjsojosgkoew4");
		p4a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p4a.setTipo("felpa");
		p4a.setQuantita(6);
		p4a.setColore("black");
		p4a.setTaglia("S");
		p4a.setPrezzo(1);
		
		
		Prodotto p4b = new Prodotto();
		p4b.setNome("Felpa 4");
		p4b.setDescrizione("descsorgjsojosgkoew4");
		p1b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p4b.setTipo("felpa");
		p4b.setQuantita(23);
		p4b.setColore("black");
		p4b.setTaglia("XL");
		p4b.setPrezzo(1);

		Prodotto p4c = new Prodotto();
		p4c.setNome("Felpa 4");
		p4c.setDescrizione("descsorgjsojosgkoew4");
		p4c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p4c.setTipo("felpa");
		p4c.setQuantita(40);
		p4c.setColore("black");
		p4c.setTaglia("L");
		p4c.setPrezzo(1);

		Prodotto p4d = new Prodotto();
		p4d.setNome("Felpa 4");
		p4d.setDescrizione("descsorgjsojosgkoew4");
		p4d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p4d.setTipo("felpa");
		p4d.setQuantita(53);
		p4d.setColore("black");
		p4d.setTaglia("M");
		p4d.setPrezzo(1);

		Prodotto p5a = new Prodotto();
		p5a.setNome("Maglia 5");
		p5a.setDescrizione("descrizione111");
		p5a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p5a.setTipo("tshirt");
		p5a.setQuantita(15);
		p5a.setColore("blue");
		p5a.setTaglia("S");
		p5a.setPrezzo(7);

		Prodotto p5b = new Prodotto();
		p5b.setNome("Maglia 5");
		p5b.setDescrizione("descrizione111");
		p5b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p5b.setTipo("tshirt");
		p5b.setQuantita(19);
		p5b.setColore("blue");
		p5b.setTaglia("L");
		p5b.setPrezzo(7);

		Prodotto p5c = new Prodotto();
		p5c.setNome("Maglia 5");
		p5c.setDescrizione("descrizione111");
		p5c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p5c.setTipo("tshirt");
		p5c.setQuantita(5);
		p5c.setColore("blue");
		p5c.setTaglia("XL");
		p5c.setPrezzo(7);

		
		Prodotto p5d = new Prodotto();
		p5d.setNome("Maglia 5");
		p5d.setDescrizione("descrizione111");
		p5d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p5d.setTipo("tshirt");
		p5d.setQuantita(75);
		p5d.setColore("blue");
		p5d.setTaglia("M");
		p5d.setPrezzo(7);

		
		Prodotto p6a = new Prodotto();
		p6a.setNome("Maglia 6");
		p6a.setDescrizione("descirojiadsjdsia242462");
		p6a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p6a.setTipo("tshirt");
		p6a.setQuantita(62);
		p6a.setColore("black");
		p6a.setTaglia("M");
		p6a.setPrezzo(6);

		
		Prodotto p6b = new Prodotto();
		p6b.setNome("Maglia 6");
		p6b.setDescrizione("descirojiadsjdsia242462");
		p6b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p6b.setTipo("tshirt");
		p6b.setQuantita(90);
		p6b.setColore("black");
		p6b.setTaglia("S");
		p6b.setPrezzo(6);
		
		
		Prodotto p6c = new Prodotto();
		p6c.setNome("Maglia 6");
		p6c.setDescrizione("descirojiadsjdsia242462");
		p6c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p6c.setTipo("tshirt");
		p6c.setQuantita(61);
		p6c.setColore("black");
		p6c.setTaglia("XL");
		p6c.setPrezzo(6);
		
		Prodotto p6d = new Prodotto();
		p6d.setNome("Maglia 6");
		p6d.setDescrizione("descirojiadsjdsia242462");
		p6d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p6d.setTipo("tshirt");
		p6d.setQuantita(9);
		p6d.setColore("black");
		p6d.setTaglia("L");
		p6d.setPrezzo(6);		

		
		Prodotto p7a = new Prodotto();
		p7a.setNome("Felpa 7");
		p7a.setDescrizione("descsorgjsojosgkoew1");
		p7a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p7a.setTipo("felpa");
		p7a.setQuantita(6);
		p7a.setColore("black");
		p7a.setTaglia("S");
		p7a.setPrezzo(9);
		
		
		Prodotto p7b = new Prodotto();
		p7b.setNome("Felpa 7");
		p7b.setDescrizione("descsorgjsojosgkoew1");
		p7b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p7b.setTipo("felpa");
		p7b.setQuantita(23);
		p7b.setColore("black");
		p7b.setTaglia("XL");
		p7b.setPrezzo(9);

		Prodotto p7c = new Prodotto();
		p7c.setNome("Felpa 7");
		p7c.setDescrizione("descsorgjsojosgkoew1");
		p7c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p7c.setTipo("felpa");
		p7c.setQuantita(10);
		p7c.setColore("black");
		p7c.setTaglia("L");
		p7c.setPrezzo(9);

		Prodotto p7d = new Prodotto();
		p7d.setNome("Felpa 7");
		p7d.setDescrizione("descsorgjsojosgkoew1");
		p7d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p7d.setTipo("felpa");
		p7d.setQuantita(53);
		p7d.setColore("black");
		p7d.setTaglia("M");
		p7d.setPrezzo(9);

		
		Prodotto p8a = new Prodotto();
		p8a.setNome("Felpa 8");
		p8a.setDescrizione("descsorgjsojosgkoew1");
		p8a.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p8a.setTipo("felpa");
		p8a.setQuantita(6);
		p8a.setColore("black");
		p8a.setTaglia("S");
		p8a.setPrezzo(31);
		
		
		Prodotto p8b = new Prodotto();
		p8b.setNome("Felpa 8");
		p8b.setDescrizione("descsorgjsojosgkoew1");
		p8b.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p8b.setTipo("felpa");
		p8b.setQuantita(23);
		p8b.setColore("black");
		p8b.setTaglia("XL");
		p8b.setPrezzo(31);

		Prodotto p8c = new Prodotto();
		p8c.setNome("Felpa 8");
		p8c.setDescrizione("descsorgjsojosgkoew1");
		p8c.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p8c.setTipo("felpa");
		p8c.setQuantita(10);
		p8c.setColore("black");
		p8c.setTaglia("L");
		p8c.setPrezzo(31);

		Prodotto p8d = new Prodotto();
		p8d.setNome("Felpa 8");
		p8d.setDescrizione("descsorgjsojosgkoew1");
		p8d.setImmagine("https://guideimg.alibaba.com/images/shop/75/08/28/2/universal-music-shirts-pearl-jam-stickman-unisex-sweatshirt_3931492.jpg");
		p8d.setTipo("felpa");
		p8d.setQuantita(53);
		p8d.setColore("black");
		p8d.setTaglia("M");
		p8d.setPrezzo(31);





		//SALVO PRODOTTO 
		prodottoDao.save(p1a);
		prodottoDao.save(p1b);
		prodottoDao.save(p1c);
		prodottoDao.save(p1d);
		prodottoDao.save(p2a);
		prodottoDao.save(p2b);
		prodottoDao.save(p2c);
		prodottoDao.save(p2d);
		prodottoDao.save(p3a);
		prodottoDao.save(p3b);
		prodottoDao.save(p3c);
		prodottoDao.save(p3d);
		prodottoDao.save(p4a);
		prodottoDao.save(p4b);
		prodottoDao.save(p4c);
		prodottoDao.save(p4d);
		prodottoDao.save(p5a);
		prodottoDao.save(p5b);
		prodottoDao.save(p5c);
		prodottoDao.save(p5d);
		prodottoDao.save(p6a);
		prodottoDao.save(p6b);
		prodottoDao.save(p6c);
		prodottoDao.save(p6d);

		
		
		//DICHIARO MESSAGGI
		MessaggioPrivato m1 = new MessaggioPrivato(date1,"MSG1","msg1",u,u2);
		MessaggioPrivato m2 = new MessaggioPrivato(date2,"MSG2","msg2",u,u3);
		MessaggioPrivato m3 = new MessaggioPrivato(date3,"MSG3","msg3",u,u4);
		MessaggioPrivato m4 = new MessaggioPrivato(date4,"MSG4","msg4",u2,u);
		MessaggioPrivato m5 = new MessaggioPrivato(date5,"MSG5","msg5",u3,u);
		MessaggioPrivato m6 = new MessaggioPrivato(date6,"MSG6","msg6",u4,u);
		
		
		//SALVO UTENTI
		utenteDao.save(u);
		utenteDao.save(u2);
		utenteDao.save(u3);
		utenteDao.save(u4);
		
		//SALVO EVENTI
		eventoDao.save(ev1);
		eventoDao.save(ev2);
		eventoDao.save(ev3);
		eventoDao.save(ev4);
		eventoDao.save(ev5);
		eventoDao.save(ev6);
		eventoDao.save(ev7);
		eventoDao.save(ev8);
		eventoDao.save(ev9);
		eventoDao.save(ev10);
		eventoDao.save(ev11);
		eventoDao.save(ev12);

		
		//SALVO LOCATIONS
		locationDao.save(l);
		locationDao.save(l2);
		locationDao.save(l3);
		locationDao.save(l4);
		locationDao.save(l5);
		locationDao.save(l6);
		locationDao.save(l7);
		
		//SALVO ARTISTI
		artistaDao.save(a1);
		artistaDao.save(a2);
		artistaDao.save(a3);
		
		//SALVO EXPERIENCES
		experienceDao.save(e1);
		experienceDao.save(e2);
		experienceDao.save(e3);
		experienceDao.save(e4);
		experienceDao.save(e5);
		experienceDao.save(e6);
		experienceDao.save(e1b);
		experienceDao.save(e2b);
		experienceDao.save(e3b);
		experienceDao.save(e4b);
		experienceDao.save(e5b);
		experienceDao.save(e6b);
		
		
		//SALVO PRODOTTO 
		prodottoDao.save(p1a);
		prodottoDao.save(p1b);
		prodottoDao.save(p1c);
		prodottoDao.save(p1d);
		prodottoDao.save(p2a);
		prodottoDao.save(p2b);
		prodottoDao.save(p2c);
		prodottoDao.save(p2d);
		prodottoDao.save(p3a);
		prodottoDao.save(p3b);
		prodottoDao.save(p3c);
		prodottoDao.save(p3d);

		//SALVO MESSAGGI
		msg_pvtDao.save(m1);
		msg_pvtDao.save(m2);
		msg_pvtDao.save(m3);
		msg_pvtDao.save(m4);
		msg_pvtDao.save(m5);
		msg_pvtDao.save(m6);

		//AGGIUNGO EVENTO 2018 ALL'IPPODROMO 
		l2.aggiungiEvento(ev1);
		l2.aggiungiEvento(ev2);
		l2.aggiungiEvento(ev3);
		l2.aggiungiEvento(ev4);

		
		
		//AGGIUNGO EVENTO 2018 AGLI AVENGED
		a1.aggiungiEvento(ev1);
		a1.aggiungiEvento(ev2);
		a1.aggiungiEvento(ev3);
		
		//AGGIUNGO EVENTO 1998 ALL'ALCATRAZ
		l3.aggiungiEvento(ev5);
		l3.aggiungiEvento(ev6);
		l3.aggiungiEvento(ev7);
		l3.aggiungiEvento(ev8);
		l3.aggiungiEvento(ev9);
		
		//AGGIUNGO EVENTO 1998 AGLI AVENGED
		a1.aggiungiEvento(ev4);
		a3.aggiungiEvento(ev4);
		a3.aggiungiEvento(ev5);
		a3.aggiungiEvento(ev6);
		
		
		//AGGIUNGO EVENTO 1995 AL BATACLAN
		l.aggiungiEvento(ev10);
		l.aggiungiEvento(ev11);
		l.aggiungiEvento(ev12);
		
		//AGGIUNGO EVENTO 1995 AGLI AVENGED
		a1.aggiungiEvento(ev7);
		a2.aggiungiEvento(ev8);
		a3.aggiungiEvento(ev9);		
		a2.aggiungiEvento(ev10);
		a1.aggiungiEvento(ev11);
		a2.aggiungiEvento(ev12);
		a3.aggiungiEvento(ev11);		
		a2.aggiungiEvento(ev12);
		
		
		//AGGIORNO AVENGED
		artistaDao.update(a1);
		artistaDao.update(a2);
		artistaDao.update(a3);
		
		//AGGIORNO LOCATION
		locationDao.update(l2);
		locationDao.update(l3);
		locationDao.update(l);
		
		//SETTO IMMAGINE AL MIO PROFILO
		u.setAvatar("img/diamond.png");
		utenteDao.update(u);
//		
		//SETTO IMMAGINE AL PROFILO DI GABRIELE
		u2.setAvatar("img/pearljam.jpg");
		utenteDao.update(u2);
//		
		//SETTO IMMAGINE AL PROFILO DI ADAMO
		u3.setAvatar("img/foof.png");
		utenteDao.update(u3);
		
		//AGGIUNGO EXPERIENCE AGLI UTENTI
		u.aggiungiExperience(e1);
		u2.aggiungiExperience(e2);
		u3.aggiungiExperience(e3);
		u4.aggiungiExperience(e4);
		u.aggiungiExperience(e5);
		u2.aggiungiExperience(e6);
		u.aggiungiExperience(e1b);
		u2.aggiungiExperience(e2b);
		u3.aggiungiExperience(e3b);
		u4.aggiungiExperience(e4b);
		u.aggiungiExperience(e5b);
		u2.aggiungiExperience(e6b);
		
		
		//AGGIUNGO EXPERIENCE ALL'EVENTO
		ev1.aggiungiExperience(e1);
		ev1.aggiungiExperience(e2);
		ev1.aggiungiExperience(e3);
		ev1.aggiungiExperience(e4);
		ev1.aggiungiExperience(e5);
		ev1.aggiungiExperience(e6);
		ev1.aggiungiExperience(e1b);
		ev8.aggiungiExperience(e2b);
		ev9.aggiungiExperience(e3b);
		ev10.aggiungiExperience(e4b);
		ev11.aggiungiExperience(e5b);
		ev12.aggiungiExperience(e6b);
	
		
		//UPDATE UTENTI
		utenteDao.update(u);
		utenteDao.update(u2);
		utenteDao.update(u3);
		utenteDao.update(u4);
		
		//UPDATE EVENTI
		eventoDao.update(ev1);
		eventoDao.update(ev2);
		eventoDao.update(ev3);
		eventoDao.update(ev4);
		eventoDao.update(ev5);
		eventoDao.update(ev6);
		eventoDao.update(ev7);
		eventoDao.update(ev8);
		eventoDao.update(ev9);
		eventoDao.update(ev10);
		eventoDao.update(ev11);
		eventoDao.update(ev12);
		
		u.setBio("Il mio nome e' Manuel, amo il metal e vengo da Catanzaro! Non vedo l'ora di partire per una nuova avventura insieme a tutti voi");
		utenteDao.update(u);
		
		//AGGIUNGO PRODOTTI REALIZZATI 
		u.aggiungiProdottoRealizzato(p1a);
		u.aggiungiProdottoRealizzato(p1b);
		u.aggiungiProdottoRealizzato(p1c);
		u.aggiungiProdottoRealizzato(p1d);

		u.aggiungiProdottoRealizzato(p2a);
		u.aggiungiProdottoRealizzato(p2b);
		u.aggiungiProdottoRealizzato(p2c);
		u.aggiungiProdottoRealizzato(p2d);

		u2.aggiungiProdottoRealizzato(p3a);
		u2.aggiungiProdottoRealizzato(p3b);
		u2.aggiungiProdottoRealizzato(p3c);
		u2.aggiungiProdottoRealizzato(p3d);

		
		//AGGIUNGO PRODOTTO COME ACQUISTO
		utenteDao.effettuaAcquisto(u, p3a);
		
		//AGGIUNGO MESSAGGI RICEVUTI ALL'UTENTE
//		u.add_messaggio_ricevuto(m1);
//		u.add_messaggio_ricevuto(m2);
//		
//		
//		u2.aggiungiMessaggioPrivato(m1);
//		u2.aggiungiMessaggioPrivato(m2);
//		
//		u.aggiungiMessaggioPrivato(m3);
//		u.aggiungiMessaggioPrivato(m4);
//		
//		u2.add_messaggio_ricevuto(m3);
//		u2.add_messaggio_ricevuto(m4);
		
		//UPDATE UTENTI
		utenteDao.update(u);
		utenteDao.update(u2);
		
		//AGGIUNGI PARTECIPAZIONE
		e3.aggiungiPartecipante(u);
		e6.aggiungiPartecipante(u);
		e1.aggiungiPartecipante(u3);
		e4.aggiungiPartecipante(u4);
		e2.aggiungiPartecipante(u3);
		e1.aggiungiPartecipante(u4);
		e1.aggiungiPartecipante(u2);
//		e1.aggiungiPartecipante(u);
		
		//UPDATE EXPERIENCES
		experienceDao.update(e1);
		experienceDao.update(e2);
		experienceDao.update(e3);
		experienceDao.update(e4);
		experienceDao.update(e5);
		experienceDao.update(e6);
		experienceDao.update(e1b);
		experienceDao.update(e2b);
		experienceDao.update(e3b);
		experienceDao.update(e4b);
		experienceDao.update(e5b);
		experienceDao.update(e6b);
	
	}
}

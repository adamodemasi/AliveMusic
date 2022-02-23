package persistenza;

import persistenza.dao.ArtistaDao;

import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public class PostgresDAOFactory extends DAOFactory{

private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//questi vanno messi in file di configurazione!!!	
//			dataSource=new DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/musicexp","postgres","postgres");
		}
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	@Override
	public UtilDao getUtilDAO() {
		return new UtilDao(dataSource);
	}

	@Override
	public UtenteDao getUtenteDAO() {
		return new UtenteDaoJDBC(dataSource);
	}

	@Override
	public ArtistaDao getArtistaDAO() {
		return new ArtistaDaoJDBC(dataSource);
	}

	@Override
	public LocationDao getLocationDAO() {
		return new LocationDaoJDBC(dataSource);
	}

	@Override
	public EventoDao getEventoDAO() {
		return new EventoDaoJDBC(dataSource);
	}

	@Override
	public ExperienceDao getExperienceDAO() {
		return new ExperienceDaoJDBC(dataSource);
	}

	@Override
	public MessaggioPrivatoDao getMessaggioPrivatoDAO() {
		return new MessaggioPrivatoDaoJDBC(dataSource);
	}

	@Override
	public ProdottoDao getProdottoDAO() {
		return new ProdottoDaoJDBC(dataSource);
	}
	
}

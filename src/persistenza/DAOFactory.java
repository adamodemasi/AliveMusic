package persistenza;

import persistenza.dao.ArtistaDao;

import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public abstract class DAOFactory {

	// --- List of supported DAO types ---

	
		/**
		 * Numeric constant '1' corresponds to explicit Hsqldb choice
		 */
		public static final int HSQLDB = 1;
		
		/**
		 * Numeric constant '2' corresponds to explicit Postgres choice
		 */
		public static final int POSTGRESQL = 2;
		
		
		// --- Actual factory method ---
		
		/**
		 * Depending on the input parameter
		 * this method returns one out of several possible 
		 * implementations of this factory spec 
		 */
		public static DAOFactory getDAOFactory(int whichFactory) {
			switch ( whichFactory ) {
			
			case HSQLDB:
				return null;//new HsqldbDAOFactory();
			case POSTGRESQL:
				return new PostgresDAOFactory();
			default:
				return null;
			}
		}
		
		
		
		// --- Factory specification: concrete factories implementing this spec must provide this methods! ---
		
		/**
		 * Method to obtain a DATA ACCESS OBJECT
		 * for the datatype 'Student'
		 */

		public abstract UtilDao getUtilDAO();
		
		public abstract UtenteDao getUtenteDAO();
		
		public abstract ArtistaDao getArtistaDAO();
		
		public abstract LocationDao getLocationDAO();
		
		public abstract EventoDao getEventoDAO();
		
		public abstract ExperienceDao getExperienceDAO();
		
		public abstract MessaggioPrivatoDao getMessaggioPrivatoDAO();
		
		public abstract ProdottoDao getProdottoDAO();
	
}

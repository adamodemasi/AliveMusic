package persistenza;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final private String dbURI;// = "jdbc:postgresql://localhost:5432/musicexp";
	final private String userName;// = "postgres";
	final private String password;// = "postgres";
	
	public DataSource(String dbURI, String userName, String password) {
		this.dbURI=dbURI;
		this.userName=userName;
		this.password=password;
	}
	
	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    connection = DriverManager.getConnection(dbURI,userName, password);
			
		
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
	
}

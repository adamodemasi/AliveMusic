package persistenza;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entita.Evento;
import entita.Location;

public class LocationProxy extends Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	public LocationProxy(DataSource ds)
	{
		this.ds=ds;
	}

	@Override
	public Set<Evento> getEventi()
	{
//		if(super.getEventi()==null)
//		{
			Set<Evento> eventi = new HashSet<>();
			Connection connection = this.ds.getConnection();
			try {
				PreparedStatement statement;
				String query = "select * from evento where location = ?";
				statement = connection.prepareStatement(query);
				statement.setString(1, this.getNome());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					Evento evento = new Evento();
					evento.setData(result.getDate("data"));
					evento.setID(result.getInt("id"));
					eventi.add(evento);
				}
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}	
			this.setEventi(eventi);
//		}
		return super.getEventi();
	}

}

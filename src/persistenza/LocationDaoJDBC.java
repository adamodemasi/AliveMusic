package persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Evento;
import entita.Experience;
import entita.Location;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;

public class LocationDaoJDBC implements LocationDao {

	//AGGIUNGI UPDAT
	
	private DataSource datasource;
	
	public LocationDaoJDBC(DataSource ds){
		this.datasource=ds;
	}
	
	@Override
	public void save(Location loc) {
		Connection connection = this.datasource.getConnection();
		try {
			String insert = "insert into location(nome,citta,stato,lat,lng) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, loc.getNome());
			statement.setString(2, loc.getCitta());
			statement.setString(3, loc.getStato());
			statement.setFloat(4, loc.getLat());
			statement.setFloat(5, loc.getLng());
			statement.executeUpdate();
//			if(loc.getEventi()!=null)
			this.updateEventi(loc, connection);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	private void updateEventi(Location l,Connection c) throws SQLException
	{
		EventoDao eventoDao = new EventoDaoJDBC(datasource);
		for (Evento ev : l.getEventi()) {
			if (eventoDao.findByPrimaryKey(ev.getID()) == null){
				eventoDao.save(ev);
			}
			String update = "update evento SET location = ? WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(update);
			statement.setString(1, l.getNome());
			statement.setInt(2, ev.getID());
			int s=statement.executeUpdate();}
	}
	
	private void removeForeignKeyFromEvento(Location l,Connection c) throws SQLException
	{
		for (Evento ev : l.getEventi()) {
			String update = "update evento SET location=NULL WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(update);
			statement.setInt(1, ev.getID());
			statement.executeUpdate();
		}	
	}
	
	@Override
	public void update(Location loc) {
		// TODO Auto-generated method stub
		Connection connection = this.datasource.getConnection();
		try {
			String update = "update location SET citta = ?, stato = ?, lat=?, lng=? WHERE nome=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, loc.getCitta());
			statement.setString(2, loc.getStato());
			statement.setFloat(3, loc.getLat());
			statement.setFloat(4, loc.getLng());
			statement.setString(5, loc.getNome());
			
			statement.executeUpdate();
			
			if(!(loc instanceof LocationProxy))
				this.updateEventi(loc,connection);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Location loc) {
		// TODO Auto-generated method stub
		Connection connection = this.datasource.getConnection();
		try {
			String delete = "delete FROM location WHERE nome = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, loc.getNome());
			
			this.removeForeignKeyFromEvento(loc, connection);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public Location findByPrimaryKey(String nome) {
		Connection connection = this.datasource.getConnection();
		Location location = null;
		try {
			PreparedStatement statement;
			String query = "select * from location where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,nome);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				location = new LocationProxy(datasource);
				location.setNome(result.getString("nome"));				
				location.setCitta(result.getString("citta"));
				location.setStato(result.getString("stato"));
				location.setLat(result.getFloat("lat"));
				location.setLng(result.getFloat("lng"));
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
		return location;
	}

	@Override
	public List<Location> findAll() {
		Connection connection = this.datasource.getConnection();
		List<Location> locations = new LinkedList<>();
		try {
			Location location;
			PreparedStatement statement;
			String query = "select * from location";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				location = new LocationProxy(datasource);
				location.setNome(result.getString("nome"));				
				location.setCitta(result.getString("citta"));
				location.setStato(result.getString("stato"));
				location.setLat(result.getFloat("lat"));
				location.setLng(result.getFloat("lng"));
				locations.add(location);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return locations;
	}

}

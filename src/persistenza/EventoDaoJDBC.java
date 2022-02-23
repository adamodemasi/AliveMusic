package persistenza;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.AEADBadTagException;

import entita.Artista;
import entita.Evento;
import entita.Experience;
import entita.Location;
import entita.Utente;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.LocationDao;
import persistenza.dao.UtenteDao;

public class EventoDaoJDBC implements EventoDao {

	private DataSource ds;
	
	public EventoDaoJDBC(DataSource ds)
	{
		this.ds=ds;
	}
	
	@Override
	public void save(Evento ev) {
		Connection connection = this.ds.getConnection();
		try {
			int ID=AssegnaID.getId(connection);
			ev.setID(ID);
			String insert = "insert into evento(id,data) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, ev.getID());
			long secs = ev.getData().getTime();
			statement.setDate(2, new java.sql.Date(secs));
			statement.executeUpdate();
//			if(ev.getUtenti_interessati()!=null)
				this.updateUtenti(ev, connection);
//			if(ev.getExperiences()!=null)
				this.updateExperiencesCreate(ev, connection);
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

	private void updateUtenti(Evento ev, Connection connection) throws SQLException{
		UtenteDao utenteDao = new UtenteDaoJDBC(ds);
		for (Utente utente : ev.getUtenti_interessati()) {
			if (utenteDao.findByPrimaryKey(utente.getNickname()) == null){
				utenteDao.save(utente);
			}
			
			String interessato = "select id from interesse where utente=? AND evento=?";
			PreparedStatement statementInteressato = connection.prepareStatement(interessato);
			statementInteressato.setString(1, utente.getNickname());
			statementInteressato.setInt(2, ev.getID());
			ResultSet result = statementInteressato.executeQuery();
			if(result.next()){
				String update = "update utente SET evento= ? WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(update);
				statement.setLong(1, ev.getID());
				statement.setLong(2, result.getLong("ID"));
				statement.executeUpdate();
			}else{			
				String interessati = "insert into interesse(id,utente,evento) values (?,?,?)";
				PreparedStatement statementInteressati = connection.prepareStatement(interessati);
				int id = AssegnaID.getId(connection);
				statementInteressati.setInt(1, id);
				statementInteressati.setString(2,utente.getNickname());
				statementInteressati.setInt(3,ev.getID());
				statementInteressati.executeUpdate();
			}
		}
		
	}
	
	private void removeForeignKeyFromUtente(Evento ev,Connection connection) throws SQLException
	{
		for (Utente utente : ev.getUtenti_interessati()) {
			String update = "update interesse SET evento = NULL WHERE utente = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getNickname());
			statement.executeUpdate();
		}	
	}
	
	private void updateExperiencesCreate(Evento ev, Connection connection) throws SQLException {
		ExperienceDao experienceDao = new ExperienceDaoJDBC(ds);
		for (Experience exp : ev.getExperiences()) {
			if (experienceDao.findByPrimaryKey(exp.getID()) == null){
				experienceDao.save(exp);
			}
			String update = "update experience SET evento = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, ev.getID());
			statement.setInt(2, exp.getID());
			statement.executeUpdate();}
	}
	
	private void removeForeignKeyFromExperience(Evento ev,Connection c) throws SQLException{
		for (Experience exp : ev.getExperiences()) {
			String update = "update experience SET evento=NULL WHERE id = ?";
			PreparedStatement statement = c.prepareStatement(update);
			statement.setInt(1, exp.getID());
			statement.executeUpdate();
		}	
	}

	@Override
	public Evento findByPrimaryKey(int id) {
		Connection connection = this.ds.getConnection();
		Evento evento = null;
		try {
			PreparedStatement statement;
			String query = "select * from evento where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				evento = new EventoProxy(ds);
				evento.setID(result.getInt("id"));				
				
				long secs = result.getDate("data").getTime();
				evento.setData(new java.util.Date(secs));
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
		return evento;
	}

	@Override
	public List<Evento> findAll() {
		Connection connection = this.ds.getConnection();
		List<Evento> eventi = new LinkedList<>();
		try {
			Evento evento;
			PreparedStatement statement;
			String query = "select * from evento order by data";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				evento = new EventoProxy(ds);
				evento.setID(result.getInt("id"));				
				long secs = result.getDate("data").getTime();
				evento.setData(new java.util.Date(secs));
				
				
				eventi.add(evento);
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
		return eventi;
	}

	@Override
	public void update(Evento ev) {
		Connection connection = this.ds.getConnection();
		try {
			String update = "update evento SET data = ? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			long secs = ev.getData().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setInt(2, ev.getID());
			statement.executeUpdate();
			this.updateExperiencesCreate(ev, connection);
			//this.updateUtenti(ev, connection);
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
	public void delete(Evento ev) {
		Connection connection = this.ds.getConnection();
		try {
			String delete = "delete FROM evento WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, ev.getID());
			statement.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
			this.removeForeignKeyFromUtente(ev, connection);
			this.removeForeignKeyFromExperience(ev, connection);
			connection.commit();
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
	public HashSet<Artista> getArtistiFromEvento(Evento evento) 
	{
		HashSet<Artista> artisti = new HashSet<>();
		Connection connection = this.ds.getConnection();
		try {
			PreparedStatement statement;
			String query = "select artista from esibizione where evento = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, evento.getID());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Artista artista = new ArtistaProxy(ds);
				artista.setNome(result.getString("artista"));
				artisti.add(artista);
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
		return artisti;
	}

	@Override
	public Location getLocationFromEvento(Evento evento) 
	{
		Location location = null;
		Connection connection = this.ds.getConnection();
		try {
			PreparedStatement statement;
			String query = "select nome,citta from evento as e, location as l  where id = ? and e.location= l.nome";
			statement = connection.prepareStatement(query);
			statement.setInt(1, evento.getID());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				location = new LocationProxy(ds);
				location.setNome(result.getString("nome"));
				location.setCitta(result.getString("citta"));
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
		return location;
	}
	

	
	
}
package persistenza;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import entita.MessaggioPrivato;
import entita.Utente;
import persistenza.dao.MessaggioPrivatoDao;
import persistenza.dao.UtenteDao;

public class MessaggioPrivatoDaoJDBC implements MessaggioPrivatoDao{

	private DataSource ds;
	
	public MessaggioPrivatoDaoJDBC(DataSource ds)
	{
		this.ds=ds;
	}
	
	@Override
	public void save(MessaggioPrivato msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			int id=AssegnaID.getId(connection);
			msg.setID(id);
			String insert = "insert into messaggio_privato(id,mittente,data,oggetto,testo,destinatario) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			long secs = msg.getData_invio().getTime();
			statement.setInt(1, msg.getID());
			statement.setString(2, msg.getMittente().getNickname());
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, msg.getOggetto());
			statement.setString(5, msg.getTesto());
			statement.setString(6, msg.getDestinatario().getNickname());
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
	public void update(MessaggioPrivato msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			String update = "update messaggio_privato SET oggetto=?, testo = ?, data=?, mittente=?, destinatario=? WHERE id=?;";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, msg.getOggetto());
			statement.setString(2, msg.getTesto());
			long secs = msg.getData_invio().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, msg.getMittente().getNickname());
			statement.setString(5, msg.getDestinatario().getNickname());
			statement.setInt(6, msg.getID());
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
	public void delete(MessaggioPrivato msg) {
		// TODO Auto-generated method stub
		Connection connection = this.ds.getConnection();
		try {
			String delete = "delete FROM messaggio_privato WHERE ID=? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, msg.getID());
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
	public MessaggioPrivato findByPrimaryKey(int id) {
		Connection connection = this.ds.getConnection();
		MessaggioPrivato msg = null;
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		try {
			PreparedStatement statement;
			String query = "select * from messaggio_privato where id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1,id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				msg = new MessaggioPrivato();
				msg.setID(result.getInt("id"));
				msg.setOggetto(result.getString("oggetto"));
				msg.setTesto(result.getString("testo"));
				msg.setData_invio(result.getDate("data"));
				msg.setMittente(utenteDao.findByPrimaryKey(result.getString("mittente")));
				msg.setDestinatario(utenteDao.findByPrimaryKey(result.getString("mittente")));				
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
		return msg;
	}

	@Override
	public List<MessaggioPrivato> findAll() {
		Connection connection = this.ds.getConnection();
		List<MessaggioPrivato> messaggi = new LinkedList<>();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
		HashMap<String, Utente> utenti = new HashMap<>();
		try {
			MessaggioPrivato msg;
			PreparedStatement statement;
			String query = "select * from messaggio_privato";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				msg = new MessaggioPrivato();
				msg.setID(result.getInt("ID"));
				msg.setData_invio(result.getDate("data"));
				msg.setOggetto(result.getString("oggetto"));
				msg.setTesto(result.getString("testo"));
				String mittente = result.getString("mittente");
				String destinatario = result.getString("destinatario");
				
				if(!utenti.containsKey(mittente))
					utenti.put(mittente, utenteDao.findByPrimaryKey(mittente));
				
				msg.setMittente(utenti.get(mittente));
				
				if(!utenti.containsKey(destinatario))
					utenti.put(destinatario, utenteDao.findByPrimaryKey(destinatario));

				msg.setDestinatario(utenti.get(destinatario));
				
				messaggi.add(msg);
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
		return messaggi;
	}

}

package persistenza;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Artista;
import entita.Evento;
import persistenza.dao.ArtistaDao;
import persistenza.dao.EventoDao;

public class ArtistaDaoJDBC implements ArtistaDao {

	private DataSource dataSource;
	
	public ArtistaDaoJDBC(DataSource ds)
	{
		this.dataSource=ds;
	}
	
	@Override
	public void save(Artista art) {
		// TODO Auto-generated method stub
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into artista(nome,sito_web,immagine,genere,copertina,bio) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, art.getNome());
			statement.setString(2, art.getSito_web());
			statement.setString(3, art.getImmagine_band());
			statement.setString(4, art.getGenere());
			statement.setString(5, art.getCopertina_band());
			statement.setString(6, art.getBio());
			statement.executeUpdate();
			
//			if (art.getEventi()!=null)
				this.updateEventi(art,connection);
		
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

	private void updateEventi(Artista art, Connection connection) throws SQLException{
		EventoDao EventoDao = new EventoDaoJDBC(dataSource);
		for (Evento evento : art.getEventi()) {
			if (EventoDao.findByPrimaryKey(evento.getID()) == null){
				EventoDao.save(evento);
			}
			
			String esibizione = "select id from esibizione where artista=? AND evento=?";
			PreparedStatement statementEsibizione = connection.prepareStatement(esibizione);
			statementEsibizione.setString(1, art.getNome());
			statementEsibizione.setInt(2, evento.getID());
			ResultSet result = statementEsibizione.executeQuery();
			if(result.next()){
				String update = "update esibizione SET artista = ? WHERE id = ?";
				PreparedStatement statement = connection.prepareStatement(update);
				statement.setString(1, art.getNome());
				statement.setInt(2, result.getInt("ID"));
				statement.executeUpdate();
			}else{			
				String si_esibisce = "insert into esibizione(id,artista,evento) values (?,?,?)";
				PreparedStatement statement_si_esibisce = connection.prepareStatement(si_esibisce);
				int id = AssegnaID.getId(connection);
				statement_si_esibisce.setInt(1, id);
				statement_si_esibisce.setString(2,art.getNome());
				statement_si_esibisce.setLong(3, evento.getID());
				statement_si_esibisce.executeUpdate();
			}
		}
		
	}

	private void removeForeignKeyFromEvento(Artista art, Connection connection) throws SQLException {
		for (Evento evento : art.getEventi()) {
			String update = "update esibizione SET artista = NULL WHERE evento = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, evento.getID());
			statement.executeUpdate();
		}	
	}
	
	@Override
	public void update(Artista art) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update artista SET sito_web = ?,immagine=? ,genere=?, copertina=?,bio=? WHERE nome=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, art.getSito_web());
			statement.setString(2, art.getImmagine_band());
			statement.setString(3, art.getGenere());
			statement.setString(4, art.getCopertina_band());
			statement.setString(5, art.getBio());
			statement.setString(6, art.getNome());
			statement.executeUpdate();
			if(!(art instanceof ArtistaProxy))
				this.updateEventi(art,connection);
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
	public void delete(Artista ut) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM artista WHERE nome = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, ut.getNome());
			
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
			this.removeForeignKeyFromEvento(ut, connection);   
			
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
	public Artista findByPrimaryKey(String nome) {
		Connection connection = this.dataSource.getConnection();
		Artista artista = null;
		try {
			PreparedStatement statement;
			String query = "select * from artista where nome = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,nome);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				artista = new ArtistaProxy(dataSource);
				artista.setNome(result.getString("nome"));				
				artista.setSito_web(result.getString("sito_web"));
				artista.setImmagine_band(result.getString("immagine"));
				artista.setGenere(result.getString("genere"));
				artista.setCopertina_band(result.getString("copertina"));
				artista.setBio(result.getString("bio"));
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
		return artista;
	}

	@Override
	public List<Artista> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Artista> artisti = new LinkedList<>();
		try {
			Artista artista;
			PreparedStatement statement;
			String query = "select * from artista";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) 
			{
				artista = new ArtistaProxy(dataSource);
				artista.setNome(result.getString("nome"));				
				artista.setSito_web(result.getString("sito_web"));
				artista.setImmagine_band(result.getString("immagine"));
				artista.setGenere(result.getString("genere"));
				artista.setCopertina_band(result.getString("copertina"));
				artista.setBio(result.getString("bio"));
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

}

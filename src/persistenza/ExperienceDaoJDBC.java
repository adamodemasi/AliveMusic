package persistenza;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Evento;
import entita.Experience;
import entita.Utente;
import javafx.scene.chart.PieChart.Data;
import persistenza.dao.EventoDao;
import persistenza.dao.ExperienceDao;
import persistenza.dao.UtenteDao;

public class ExperienceDaoJDBC implements ExperienceDao {

	private DataSource ds;

	public ExperienceDaoJDBC(DataSource ds)
	{
		this.ds=ds;
	}

	@Override
	public void save(Experience exp) {

		/*if (exp.getPartecipanti()==null)
		{
			throw new PersistenceException("I partecipanti non sono stati istanziati");
		}*/

		Connection connection = this.ds.getConnection();
		try {
			int id = AssegnaID.getId(connection);
			exp.setID(id);
			String insert = "insert into experience(id,limite_adesione,posti,descrizione,pernottamento,viaggio,concerto) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1,exp.getID());
			long secs = exp.getLimite_adesione().getTime();
			statement.setDate(2, new java.sql.Date(secs));
			statement.setInt(3, exp.getPosti());
			statement.setString(4, exp.getDescrizione());
			statement.setBoolean(5,exp.getPernottamento());
			statement.setBoolean(6, exp.getViaggio());
			statement.setBoolean(7, exp.getConcerto());
			statement.executeUpdate();
//			if(exp.getPartecipanti()!=null)
				this.updatePartecipanti(exp, connection);
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

	private void updatePartecipanti(Experience exp,Connection c)throws SQLException{
		UtenteDao utenteDao = new UtenteDaoJDBC(ds);
		for (Utente utente : exp.getPartecipanti()) {
			if (utenteDao.findByPrimaryKey(utente.getNickname()) == null)
				utenteDao.save(utente);

			String partecipante = "select id from partecipazione where partecipante=? AND exp=?";
			PreparedStatement statementPartecipante = c.prepareStatement(partecipante);
			statementPartecipante.setString(1, utente.getNickname());
			statementPartecipante.setInt(2, exp.getID());
			ResultSet result = statementPartecipante.executeQuery();
			if(result.next()){
				String update = "update partecipazione SET exp = ? WHERE id = ?";
				PreparedStatement statement = c.prepareStatement(update);
				statement.setInt(1, exp.getID());
				statement.setInt(2, result.getInt("id"));
				statement.executeUpdate();
			}else{			
				String iscrivi = "insert into partecipazione(id,partecipante,exp) values (?,?,?)";
				PreparedStatement statementIscrivi = c.prepareStatement(iscrivi);
				int id = AssegnaID.getId(c);
				statementIscrivi.setInt(1,id);
				statementIscrivi.setString(2,utente.getNickname());
				statementIscrivi.setInt(3,exp.getID());
				statementIscrivi.executeUpdate();
			}
		}
	}

	private void removeForeignKeyFromUtente(Experience exp, Connection connection) throws SQLException {
		for (Utente utente : exp.getPartecipanti())
		{
			String update = "update partecipazione SET exp = NULL WHERE partecipante = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, utente.getNickname());
			statement.executeUpdate();
		}	
	}

	@Override
	public void update(Experience exp) 
	{
		Connection connection = this.ds.getConnection();
		try {
			String update = "update experience SET limite_adesione = ?, posti = ?, descrizione=?, pernottamento=?, viaggio=?, concerto=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			long secs = exp.getLimite_adesione().getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setInt(2, exp.getPosti());
			statement.setString(3, exp.getDescrizione());
			statement.setBoolean(4, exp.getPernottamento());
			statement.setBoolean(5, exp.getViaggio());
			statement.setBoolean(6, exp.getConcerto());
			statement.setInt(7,exp.getID());
			statement.executeUpdate();
			if(!(exp instanceof ExperienceProxy))
				this.updatePartecipanti(exp, connection);
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
	public void delete(Experience exp) {
		Connection connection = this.ds.getConnection();
		try {
			String delete = "delete FROM experience WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1,exp.getID());

			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);			
//			this.removeForeignKeyFromUtente(exp, connection); 

			statement.executeUpdate();
//spostato qui

			connection.commit();
			
			
			this.removeForeignKeyFromUtente(exp, connection); 

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
	public Experience findByPrimaryKey(int ID) {
		Connection connection = this.ds.getConnection();
		Experience exp = null;
		try {
			PreparedStatement statement;
			String query = "select * from experience where id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, ID);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				exp = new ExperienceProxy(ds); 
				exp.setID(result.getInt("id"));
				long secs = result.getDate("limite_adesione").getTime();
				exp.setLimite_adesione(new java.util.Date(secs));				
				exp.setPosti(result.getInt("posti"));
				exp.setDescrizione(result.getString("descrizione"));
				exp.setPernottamento(result.getBoolean("pernottamento"));
				exp.setViaggio(result.getBoolean("viaggio"));
				exp.setConcerto(result.getBoolean("concerto"));
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
		return exp;
	}

	@Override
	public List<Experience> findAll() {
		Connection connection = this.ds.getConnection();
		List<Experience> experiences = new LinkedList<>();
		try {
			Experience experience;
			PreparedStatement statement;
			String query = "select * from experience";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				experience = new ExperienceProxy(ds);
				experience.setID(result.getInt("id"));				
				long secs = result.getDate("limite_adesione").getTime();
				experience.setLimite_adesione(new java.util.Date(secs));
				experience.setPosti(result.getInt("posti"));
				experience.setDescrizione(result.getString("descrizione"));
				experience.setPernottamento(result.getBoolean("pernottamento"));
				experience.setViaggio(result.getBoolean("viaggio"));
				experience.setConcerto(result.getBoolean("concerto"));
				experience.setPernottamento(result.getBoolean("pernottamento"));
				experience.setViaggio(result.getBoolean("viaggio"));
				experience.setConcerto(result.getBoolean("concerto"));

				experiences.add(experience);
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
		return experiences;
	}


	public void deleteAllPartecipazioneFromExp(Experience exp) 
	{
		try {
			Connection connection = this.ds.getConnection();
			String update = "delete from partecipazione WHERE exp = ?";
			PreparedStatement statement;
			statement = connection.prepareStatement(update);
			statement.setInt(1, exp.getID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Evento getEventoFromExperience(Experience exp)
	{
		Evento evento = null;
		Connection connection=this.ds.getConnection();
		try {
			String cercaId = "select evento from experience where id = ?";
			PreparedStatement statement = connection.prepareStatement(cercaId);
			statement.setInt(1, exp.getID());
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				EventoDao eventoDao = DatabaseManager.getInstance().getDaoFactory().getEventoDAO();
				evento = eventoDao.findByPrimaryKey(result.getInt("evento"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return evento;
	}

	public Utente getOrganizzatoreFromExperience(Experience exp)
	{
		Utente organizzatore = null;
		Connection connection = this.ds.getConnection();
		try {
			String cercaOrganizzatore = "select organizzatore from experience where id=?";
			PreparedStatement statement = connection.prepareStatement(cercaOrganizzatore);
			statement.setInt(1, exp.getID());
			ResultSet result = statement.executeQuery();
			if (result.next()) 
			{
				UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
				organizzatore = utenteDao.findByPrimaryKey(result.getString("organizzatore"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return organizzatore;
	}


}

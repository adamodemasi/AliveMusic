package persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entita.Prodotto;
import entita.Utente;
import persistenza.dao.ProdottoDao;
import persistenza.dao.UtenteDao;

public class ProdottoDaoJDBC implements ProdottoDao{

	private DataSource ds;
	
	public ProdottoDaoJDBC(DataSource ds)
	{
		this.ds=ds;
	}
	
	
	@Override
	public void save(Prodotto pr) {
		Connection connection = this.ds.getConnection();
		try {
			int id = AssegnaID.getId(connection);
			pr.setID(id); 			
			String insert = "insert into prodotto(id,nome,prezzo,colore,tipo,descrizione,quantita,immagine,taglia) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1,pr.getID());
			statement.setString(2,pr.getNome());
			statement.setDouble(3, pr.getPrezzo());
			statement.setString(4, pr.getColore());
			statement.setString(5,pr.getTipo());
			statement.setString(6, pr.getDescrizione());
			statement.setInt(7, pr.getQuantita());
			statement.setString(8, pr.getImmagine());
			statement.setString(9, pr.getTaglia());
			//connection.setAutoCommit(false);
			//serve in caso gli studenti non siano stati salvati. Il DAO studente apre e chiude una transazione nuova.
			//connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);			
			statement.executeUpdate();
			// salviamo anche tutti gli studenti del gruppo in CASACATA
			//connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch(SQLException excep) {
					throw new PersistenceException(e.getMessage());
				}
			} 
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		
	}

	@Override
	public void update(Prodotto pr) {
		Connection connection = this.ds.getConnection();
		try {
			String update = "update prodotto SET nome=?, prezzo=?, colore=?, tipo=?, descrizione=?, quantita=?, immagine=?,taglia=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, pr.getNome());
			statement.setDouble(2, pr.getPrezzo());
			statement.setString(3, pr.getColore());
			statement.setString(4, pr.getTipo());
			statement.setString(5, pr.getDescrizione());
			statement.setInt(6, pr.getQuantita());
			statement.setString(7, pr.getImmagine());
			statement.setString(8, pr.getTaglia());			
			statement.setInt(9, pr.getID());
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
	public void delete(Prodotto pr) {
		Connection connection = this.ds.getConnection();
		try {
			String delete = "delete FROM prodotto WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, pr.getID());
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
	public Prodotto findByPrimaryKey(int ID) {
		Connection connection = this.ds.getConnection();
		Prodotto prodotto = null;
		try {
			PreparedStatement statement;
			String query = "select * from prodotto where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, ID);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				prodotto = new Prodotto();
				prodotto.setID(result.getInt("id"));				
				prodotto.setNome(result.getString("nome"));
				prodotto.setPrezzo(result.getInt("prezzo"));
				prodotto.setColore(result.getString("colore"));
				prodotto.setTipo(result.getString("tipo"));
				prodotto.setDescrizione(result.getString("descrizione"));
				prodotto.setQuantita(result.getInt("quantita"));
				prodotto.setImmagine(result.getString("immagine"));
				prodotto.setTaglia(result.getString("taglia"));
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
		return prodotto;
	}

	@Override
	public List<Prodotto> findAll() {
		Connection connection = this.ds.getConnection();
		List<Prodotto> prodotti = new LinkedList<>();
		try {
			Prodotto prodotto;
			PreparedStatement statement;
			String query = "select * from prodotto";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				prodotto = new Prodotto();
				prodotto.setID(result.getInt("id"));				
				prodotto.setNome(result.getString("nome"));
				prodotto.setPrezzo(result.getDouble("prezzo"));
				prodotto.setColore(result.getString("colore"));
				prodotto.setTipo(result.getString("tipo"));
				prodotto.setDescrizione(result.getString("descrizione"));
				prodotto.setQuantita(result.getInt("quantita"));
				prodotto.setImmagine(result.getString("immagine"));
				prodotto.setTaglia(result.getString("taglia"));
				prodotti.add(prodotto);
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
		return prodotti;
	}


	@Override
	public int getIdByNomeTaglia(String nome, String taglia) {

		Connection connection = this.ds.getConnection();
		int idProdotto = -1;
		try {
			PreparedStatement statement;
			String query = "select id from prodotto where nome=?  and taglia=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, nome);
			statement.setString(2, taglia);
			ResultSet result = statement.executeQuery();
			if (result.next())
				idProdotto = result.getInt("id");
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return idProdotto;
	}

}

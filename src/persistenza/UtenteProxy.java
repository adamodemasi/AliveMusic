package persistenza;

import java.io.Serializable;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entita.Experience;
import entita.Prodotto;
import entita.Utente;

public class UtenteProxy extends Utente implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public UtenteProxy(DataSource ds)
	{
		this.ds=ds;
	}
	
	public Set<Experience> getExperiences_create()
	{
		Set<Experience> experiences_create = new HashSet<>();
		Connection connection = this.ds.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from experience where organizzatore=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, this.getNickname());;
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Experience exp = new Experience();
				exp.setID(result.getInt("id"));		
				exp.setPosti(result.getInt("posti"));
				exp.setLimite_adesione(result.getDate("limite_adesione"));				
				exp.setDescrizione(result.getString("descrizione"));
				experiences_create.add(exp);
				
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
		this.setExperiences_create(experiences_create);
		return super.getExperiences_create();
	}
	


	public Set<Prodotto> getProdotti_realizzati()
	{
		Set<Prodotto> prodotti_realizzati = new HashSet<>();
		Connection connection = this.ds.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from prodotto where realizzatore=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, this.getNickname());;
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto prod = new Prodotto();
				prod.setID(result.getInt("id"));	
				prod.setColore(result.getString("colore"));
				prod.setPrezzo(result.getDouble("prezzo"));
				prod.setNome(result.getString("nome"));
				prod.setQuantita(result.getInt("quantita"));
				prod.setDescrizione(result.getString("descrizione"));
				prod.setTipo(result.getString("tipo"));
				prod.setImmagine(result.getString("immagine"));
				prodotti_realizzati.add(prod);
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
		this.setProdotti_realizzati(prodotti_realizzati);
		return super.getProdotti_realizzati();
	}

	@Override
	public Set<Prodotto> getAcquisti()
	{
		Set<Prodotto> acquisti=new HashSet<>();
		
		Connection c=this.ds.getConnection();
		
		try
		{
			PreparedStatement statement;
			String query="SELECT p.id,p.nome,p.prezzo,p.colore,p.tipo,p.quantita,p.realizzatore,p.immagine,p.descrizione FROM prodotto as p, acquisto as a WHERE p.id=a.prodotto and a.utente=?";
			statement = c.prepareStatement(query);
			statement.setString(1, this.getNickname());;
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto prod = new Prodotto();
				prod.setID(result.getInt("id"));	
				prod.setColore(result.getString("colore"));
				prod.setPrezzo(result.getDouble("prezzo"));
				prod.setNome(result.getString("nome"));
				prod.setQuantita(result.getInt("quantita"));
				prod.setDescrizione(result.getString("descrizione"));
				prod.setTipo(result.getString("tipo"));
				prod.setImmagine(result.getString("immagine"));
				acquisti.add(prod);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		this.setAcquisti(acquisti);;
		return super.getAcquisti();
	}
	
}
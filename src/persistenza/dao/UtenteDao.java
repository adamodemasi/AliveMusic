package persistenza.dao;

import java.util.List;

import entita.Prodotto;
import entita.Utente;

public interface UtenteDao {

	public void save(Utente ut);
	public void update(Utente ut);
	public void delete(Utente ut);
	public void saveFB(Utente ut);
	public Utente findByPrimaryKey(String nick);
	public List<Utente> findAll();
	public void effettuaAcquisto(Utente ut,Prodotto p);	
}

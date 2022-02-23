package persistenza.dao;

import java.util.Date;
import java.util.List;

import entita.MessaggioPrivato;
import entita.Utente;

public interface MessaggioPrivatoDao {
	
	public void save(MessaggioPrivato msg);
	public void update(MessaggioPrivato msg);
	public void delete(MessaggioPrivato msg);
	public MessaggioPrivato findByPrimaryKey(int id);
	public List<MessaggioPrivato> findAll();

}

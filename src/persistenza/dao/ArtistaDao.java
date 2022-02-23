package persistenza.dao;

import java.util.List;

import entita.Artista;
import entita.Utente;

public interface ArtistaDao {

	public void save(Artista ut);
	public void update(Artista ut);
	public void delete(Artista ut);
	public Artista findByPrimaryKey(String nome);
	public List<Artista> findAll();
}

package persistenza.dao;

import java.util.HashSet;
import java.util.List;

import entita.Location;
import entita.Artista;
import entita.Evento;

public interface EventoDao {

	public void save(Evento ev);  // Create
	public Evento findByPrimaryKey(int id);     // Retrieve
	public List<Evento> findAll();       
	public void update(Evento ev); //Update
	public void delete(Evento ev); //Delete
	
	public HashSet<Artista> getArtistiFromEvento(Evento evento);
	public Location getLocationFromEvento(Evento evento);
}

package persistenza.dao;

import java.util.List;
import java.util.Set;

import entita.Evento;
import entita.Experience;
import entita.Utente;

public interface ExperienceDao {

	public void save(Experience exp);
	public void update(Experience exp);
	public void delete(Experience exp);
	public Experience findByPrimaryKey(int ID);
	public List<Experience> findAll();
	
	public Utente getOrganizzatoreFromExperience(Experience experience);
	public void deleteAllPartecipazioneFromExp(Experience e);
	public Evento getEventoFromExperience(Experience e);
	
}

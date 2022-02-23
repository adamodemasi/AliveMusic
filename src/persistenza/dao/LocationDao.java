package persistenza.dao;

import java.util.List;

import entita.Location;

public interface LocationDao {

	public void save(Location loc);
	public void update(Location loc);
	public void delete(Location loc);
	public Location findByPrimaryKey(String nome);
	public List<Location> findAll();
	
}

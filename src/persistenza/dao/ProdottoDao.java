package persistenza.dao;

import java.util.List;

import entita.Prodotto;

public interface ProdottoDao {

	public void save(Prodotto pr);
	public void update(Prodotto pr);
	public void delete(Prodotto ut);
	public Prodotto findByPrimaryKey(int ID);
	public List<Prodotto> findAll();
	
	public int getIdByNomeTaglia(String nome, String taglia);
	
}

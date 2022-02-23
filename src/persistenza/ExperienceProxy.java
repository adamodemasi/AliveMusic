package persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entita.Evento;
import entita.Experience;
import entita.Utente;
import persistenza.dao.UtenteDao;

public class ExperienceProxy extends Experience{

	private DataSource dataSource;

	public ExperienceProxy(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public Set<Utente> getPartecipanti() 
	{
//		if(super.getPartecipanti()==null)
//		{
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDAO();
			Set<Utente> partecipanti = new HashSet<>();
			Connection connection=dataSource.getConnection();
			try {
				String cerca = "select partecipante from partecipazione where exp= ?";
				PreparedStatement statement = connection.prepareStatement(cerca);
				statement.setInt(1, this.getID());
				ResultSet result = statement.executeQuery();
				while (result.next())
				{
					UtenteProxy u = (UtenteProxy) utenteDao.findByPrimaryKey(result.getString("partecipante"));
					partecipanti.add(u);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new PersistenceException(e.getMessage());
				}
			}	
			this.setPartecipanti(partecipanti);
//		}
		return super.getPartecipanti();
	}
}

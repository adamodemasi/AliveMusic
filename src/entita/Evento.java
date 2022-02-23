package entita;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Evento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private Date data;
	private Set<Utente> utenti_interessati;
	private Set<Experience> experiences; 
	
	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	public Set<Utente> getUtenti_interessati() {
		return utenti_interessati;
	}

	public void setUtenti_interessati(Set<Utente> utenti_interessati) {
		this.utenti_interessati = utenti_interessati;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Evento(Date data)
	{
		this();
		this.data=data;
	}
	
	public Evento()
	{
		utenti_interessati = new HashSet<>();
		experiences = new HashSet<>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	public void aggiungiExperience(Experience exp)
	{
		this.getExperiences().add(exp);
	}
	
	public void rimuoviExperience(Experience exp)
	{
		this.getExperiences().remove(exp);
	}
	
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Evento[" + this.getID() + ", " + 
						sdf.format(this.getData()) + ", " +"]";
	}
	
//	public Set<Experience> getExperiences_NoProxy() 
//	{
//		return experiences;
//	}
//
//	public Set<Utente> getUtenti_interessati_NoProxy() {
//		return utenti_interessati;
//	}
}

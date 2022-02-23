package entita;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Experience implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private Date limite_adesione;
	private int posti;
	private Set<Utente> partecipanti;
	private String descrizione;
	private boolean pernottamento;
	private boolean viaggio;
	private boolean concerto;


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Experience()
	{
		partecipanti = new HashSet<>();
	}

	public Set<Utente> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(Set<Utente> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public Experience(Date limite_adesione,int posti,String descrizione, boolean pernottamento, boolean viaggio, boolean concerto)
	{
		this();
		this.limite_adesione=limite_adesione;
		this.posti=posti;
		this.descrizione=descrizione;
		this.pernottamento=pernottamento;
		this.viaggio=viaggio;
		this.concerto=concerto;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}

	public Date getLimite_adesione() {
		return limite_adesione;
	}

	public void setLimite_adesione(Date limite_adesione) {
		this.limite_adesione = limite_adesione;
	}
	
	public void aggiungiPartecipante(Utente u)
	{
		this.getPartecipanti().add(u);
	}
	
	public void rimuoviPartecipante(Utente u)
	{
		this.getPartecipanti().remove(u);
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
		Experience other = (Experience) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder s = new StringBuilder("Experience[" + this.getID() + ", " +
		this.getPosti() + "," +
		sdf.format(this.getLimite_adesione()) + ",");
		for (Utente utente : partecipanti) {
			s.append(utente.getNickname()+" - ");
		}
		return s.toString();
	}

//	public Set<Utente> getPartecipanti_NoProxy() {
//		return partecipanti;
//	}

	public boolean getPernottamento() {
		return pernottamento;
	}

	public void setPernottamento(boolean pernottamento) {
		this.pernottamento = pernottamento;
	}

	public boolean getViaggio() {
		return viaggio;
	}

	public void setViaggio(boolean viaggio) {
		this.viaggio = viaggio;
	}

	public boolean getConcerto() {
		return concerto;
	}

	public void setConcerto(boolean concerto) {
		this.concerto = concerto;
	}
}

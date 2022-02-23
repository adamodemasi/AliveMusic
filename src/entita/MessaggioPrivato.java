package entita;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessaggioPrivato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Utente mittente;
	private Utente destinatario;
	private Date data_invio;
	private String oggetto;
	private String testo;
	private int ID;
	
	public Utente getMittente() {
		return mittente;
	}

	public void setMittente(Utente mittente) {
		this.mittente = mittente;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((data_invio == null) ? 0 : data_invio.hashCode());
//		result = prime * result + ((mittente == null) ? 0 : mittente.hashCode());
//		result = prime * result + ((destinatario == null) ? 0 : destinatario.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		MessaggioPrivato other = (MessaggioPrivato) obj;
//		if (data_invio == null) {
//			if (other.data_invio != null)
//				return false;
//		} else if (!data_invio.equals(other.data_invio))
//			return false;
//		if (mittente == null) {
//			if (other.mittente != null)
//				return false;
//		} else if (!mittente.equals(other.mittente))
//			return false;
//		if (destinatario== null) {
//			if (other.destinatario!= null)
//				return false;
//		} else if (!destinatario.equals(other.destinatario))
//			return false;
//		return true;
//	}

	public Date getData_invio() {
		return data_invio;
	}

	public void setData_invio(Date data_invio) {
		this.data_invio = data_invio;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public MessaggioPrivato(){};
	
	public MessaggioPrivato(Date data_invio,String oggetto,String testo, Utente m, Utente d)
	{
		this.data_invio=data_invio;
		this.oggetto=oggetto;
		this.testo=testo;
		this.mittente = m;
		this.destinatario = d;
	}
	
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'alle' HH:mm:ss");
		
		return "Messaggio[" +this.getID()+ ", " +
				sdf.format(this.getData_invio()) + ", " +
				this.getMittente().toString()+ ", "+
				this.getDestinatario().toString()+", "+
				this.getOggetto() + ", " + 
				this.getTesto() + "]";
	}

	public Utente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Utente destinatario) {
		this.destinatario = destinatario;
	}
}

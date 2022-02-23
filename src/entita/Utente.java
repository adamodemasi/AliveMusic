package entita;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Utente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String nome;//
	private String cognome;//
	//private Date data_nascita;//
	private String citta_residenza;//
	private String mail;//
	private String password;
	private String avatar;
	private String bio;
	
	private Set<Prodotto> acquisti;
	private Set<Prodotto> prodotti_realizzati;
	private Set<Experience> experiences_create;
	private boolean moderatore;
	
	public String getCitta_residenza() {
		return citta_residenza;
	}

	public void setCitta_residenza(String citta_residenza) {
		this.citta_residenza = citta_residenza;
	}

	public Set<Prodotto> getProdotti_realizzati() {
		return prodotti_realizzati;
	}

	public void setProdotti_realizzati(Set<Prodotto> prodotti_realizzati) {
		this.prodotti_realizzati = prodotti_realizzati;
	}

	public Set<Experience> getExperiences_create() {
		return experiences_create;
	}

	public void setExperiences_create(Set<Experience> experiences_create) {
		this.experiences_create = experiences_create;
	}

	public Utente()  
	{	
		acquisti = new HashSet<>();
		prodotti_realizzati = new HashSet<>();
		experiences_create = new HashSet<>();	
	}

	public Set<Prodotto> getAcquisti() {
		return acquisti;
	}

	public void setAcquisti(Set<Prodotto> acquisti) {
		this.acquisti = acquisti;
	}

	public Utente(String nickname, String nome, String cognome, String citta_residenza, String mail, String pass, String avatar, String bio, boolean moderatore) {
		this();
		this.nickname = nickname;
		this.nome = nome;
		this.cognome = cognome;
		//this.data_nascita = data_nascita;
		this.citta_residenza=citta_residenza;
		this.avatar = avatar;
		this.bio = bio;
		this.mail = mail;
		this.password = pass;
		this.moderatore = moderatore;
	}

	public Utente(Utente utente) 
	{
		this();
		this.nickname = utente.nickname;
		this.nome = utente.nome;
		this.cognome = utente.cognome;
	//	this.data_nascita = utente.data_nascita;
		this.citta_residenza = utente.citta_residenza;
		this.mail = utente.mail;
		this.password = utente.password;
		this.acquisti.addAll(utente.acquisti);
		this.prodotti_realizzati.addAll(utente.prodotti_realizzati);
		this.experiences_create.addAll(utente.experiences_create);
	}
	
	public Utente(String nickname,String mail,String password)
	{
		this();
		this.nickname=nickname;
		this.mail=mail;
		this.password=password;
	}

	public String getNickname() {
		return nickname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/*public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}*/

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void aggiungiAcquisto(Prodotto pr) {

		boolean trovato = false;
		HashSet<Prodotto> prodotti = (HashSet<Prodotto>) getAcquisti();
		for(Iterator<Prodotto> it = prodotti.iterator();it.hasNext();)
		{
			Prodotto p = it.next();
			System.out.println(p.toString());
			if(p.equals(pr))
			{
				p.setQuantita(pr.getQuantita()+p.getQuantita());
				trovato = true;
				break;
			}
		}
		
		if(!trovato)
			this.getAcquisti().add(pr);
	}

	public void aggiungiProdottoRealizzato(Prodotto pr) 
	{
		this.getProdotti_realizzati().add(pr);
	}

	public void rimuoviProdottoRealizzato(Prodotto pr) {
		this.getProdotti_realizzati().remove(pr);
	}

	public void aggiungiExperience(Experience exp) 
	{
		this.getExperiences_create().add(exp);
	}

	public void rimuoviExperience(Experience exp) {
		this.getExperiences_create().remove(exp);
	}

//	public void aggiungiMessaggioPubblico(MessaggioPubblico msg) {
//
//		this.getMessaggi_pubblici_inviati().add(msg);
//	}
//
//	public void rimuoviMessaggioPubblico(MessaggioPubblico msg) {
//
//		this.getMessaggi_pubblici_inviati().remove(msg);
//	}

	public boolean getModeratore() {
		return moderatore;
	}

	public void setModeratore(boolean admin) {
		this.moderatore = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		Utente other = (Utente) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	public String toString() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Utente[" + this.getNickname() + ", " + this.getNome() + ", " + this.getCognome() + ", "
			+ this.getMail() + ", " +  this.getPassword()+", "+this.getCitta_residenza()+", "
				+ this.getBio() +", "+this.getAvatar() + "]";
	}

	//ritorna senza fare query nel db, usato in update e delete per non uppare le cose che già sono nel db
	public Set<Experience> getExperiences_create_NoProxy()
	{
		return experiences_create;
	}
	
	public Set<Prodotto> getProdotti_realizzati_NoProxy()
	{
		return prodotti_realizzati;
	}
	
	public Set<Prodotto> getAcquisti_NoProxy()
	{
		return acquisti;
	}
}

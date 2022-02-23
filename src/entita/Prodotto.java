package entita;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prodotto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ID;
	private String nome;
	private double prezzo;
	private String colore;
	private int quantita;
	private String tipo;
	private String descrizione;
	private String immagine;
	private String taglia;
	
	public Prodotto(String nome,double prezzo,String col,String tipo,String dscr,String img)
	{
		this();
		this.nome=nome;
		this.prezzo=prezzo;
		this.colore=col;
		this.tipo=tipo;
		this.descrizione=dscr;
		this.immagine=img;
		this.quantita=50;
	}

	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Prodotto(){}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		Prodotto other = (Prodotto) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	public String toString()
	{
		return "Prodotto[" + this.getID() + ", " + 
				this.getNome() + ", " + 
					this.getPrezzo() + ", " +
							this.getTipo() + ", QUANTITA=" +
								this.getQuantita() + ", " +
									this.getDescrizione() + ", "+"]";
	}

	public String getTaglia() {
		return taglia;
	}


	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
}

package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;		//true se è stato invocato il metodo saluta()
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPresentazione() {
		return this.presentazione;
	}

	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		String risposta = "Ciao, io sono "+this.getNome()+". ";
		if (!haSalutato)
			risposta += this.presentazione;
		else
			risposta += "Ci siamo gia' presentati.";
		this.haSalutato = true;
		return risposta;
	}
	
	/**
	 * Metodo astratto- Viene implementato in modo specifico da ogni
	 * personaggio concreto per descrivere il suo comportamento
	 * @param partita
	 * @return String 
	 */
	public abstract  String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	public String toString() {
		return this.getNome();
	}

}

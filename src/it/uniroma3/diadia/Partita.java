package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;


/**
 * Questa classe modella una partita del gioco
 *
 * @author  Paolo Merialdo, Valter Crescenzi (da un'idea di Michael Kolling and David J. Barnes)
 * @see Stanza
 * @version 0.1
 */

public class Partita {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
		
	public Partita(){
		//Si crea il labirinto per questa Partita
		this.labirinto = new Labirinto();
		this.stanzaCorrente = labirinto.getStanzaIniziale(); 	//all' inizio la stanza corrente è l'ingresso al labirinto
		this.stanzaVincente = labirinto.getStanzaFinale();		//la stanza vincente è la stanza finale del labirinto
		//Si crea il giocatore per questa Partita
		this.giocatore = new Giocatore();
		
		this.finita = false;
	}


	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	
	/**
	 * Restituisce i Cfu relativi al Giocatore di questa Partita
	 */
	public int getCfuPartita() {
		return this.giocatore.getCfu();
	}
	
	public Borsa getBorsaGiocatore() {
		return this.giocatore.getBorsa();
	}
	
	/**
	 * Restituisce true se e solo se il giocatore è ancora in gioco
	 * ovvero ha ancora mosse a disposizione
	 */
	public boolean ancoraInGioco() {
		return this.giocatore.getCfu()>0;
	}
}

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
	private Giocatore giocatore;
	private int cfu;
	private static int CFU_INIZIALI = 20;		//non può essere modificata
	
	private boolean finita;
	
	public Partita(Labirinto labirinto){
		//Si crea il labirinto per questa Partita
		this.stanzaCorrente = labirinto.getStanzaIniziale(); 	//all' inizio la stanza corrente è l'ingresso al labirinto
		this.stanzaVincente = labirinto.getStanzaFinale();		//la stanza vincente è la stanza finale del labirinto
		
		//Si crea il giocatore per questa Partita
		this.giocatore = new Giocatore();
		//in questa partita il giocatore ha 20 cfu iniziali
		this.cfu = CFU_INIZIALI;
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
	
	/*Gestione Cfu*/
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
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
		return finita || vinta() || (this.cfu == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
		
	public Borsa getBorsaGiocatore() {
		return this.giocatore.getBorsa();
	}
	
	/**
	 * Restituisce true se e solo se il giocatore è ancora in gioco
	 * ovvero ha ancora mosse a disposizione
	 */
	public boolean ancoraInGioco() {
		return this.getCfu()>0;
	}
}

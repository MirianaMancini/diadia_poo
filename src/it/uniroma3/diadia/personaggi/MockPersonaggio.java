package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe creata solo ai fini del Testing
 * la classe mock dà un corpo fittizio al metodo astratto agisci
 * ed eredita da AbstractPersongaggio tutti i metodi dotati di 
 * implementazione che dovranno essere testati
 * 
 */
public class MockPersonaggio extends AbstractPersonaggio {
	
	public MockPersonaggio(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	//eredita tutti i metodi non astratti che potranno così essere testati
	
	@Override
	public String agisci(Partita partita){
		return "done";						//corpo fittizio
	}
	@Override 
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "done";
	}

}

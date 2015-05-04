package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class MockComando extends AbstractComando {
	
	//eredita i metodi non astratti che potranno così essere testati
	
	@Override
	public String esegui(Partita partita) {
		return "done";
	}
	
	
	
}

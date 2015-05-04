package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public interface Comando {
	
	public String esegui(Partita partita);		/* esecuzione del comando */
	
	public void setParametro(String parametro);
	
}



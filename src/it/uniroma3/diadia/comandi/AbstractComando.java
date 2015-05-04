package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * una classe astratta AbstractComando per eliminare le implementazioni “vuote” dei metodi
 * setParametro() dalle classi concrete che estendono l'interfaccia Comando ma modellano 
 * comandi privi di parametri
 */

/* i Comandi senza parametro la estendono ereditando il metodo setParametro con corpo vuoto */

public abstract class AbstractComando implements Comando{
	private String parametro;
	
	@Override
	public abstract String esegui(Partita partita);		/*dichiarandolo abstract non da corpo*/

	@Override
	public void setParametro(String parametro) {
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
}

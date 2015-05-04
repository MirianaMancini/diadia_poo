package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/*togliamo implements Comando e usiamo extends AbstractComando
  eredita il metodo setParametro con corpo vuoto*/

public class ComandoAiuto extends AbstractComando {
	
	private static String[] elencoComandi = {"guarda", "vai", "prendi", "posa", "interagisci", "saluta", "regala", "aiuto", "fine"};

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		
		for(int i=0; i< elencoComandi.length; i++) {
			msg.append(elencoComandi[i]+" ");
		}
		return msg.toString();
	}


}

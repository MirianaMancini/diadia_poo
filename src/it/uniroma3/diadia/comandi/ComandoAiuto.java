package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private static String[] elencoComandi = {"guarda", "vai", "prendi", "posa", "aiuto", "fine"};

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	@Override
	public void setParametro(String parametro) {
		//corpo vuoto: Comando aiuto() non ha parametri
	}

}

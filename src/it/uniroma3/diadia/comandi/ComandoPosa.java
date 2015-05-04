package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Il giocatore posa l'attrezzo, togliendolo dalla sua borsa e ponendolo 
 * nella stanza dove si trova (Stanza corrente)
 * @param nomeattrezzo
 */

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if(!(partita.getBorsaGiocatore().hasAttrezzo(nomeAttrezzo))){
    		System.out.println("Attrezzo non presente nella borsa!");
    	}
    	//l'attrezzo è presente nella borsa del giocatore
    	Stanza corrente = partita.getStanzaCorrente();
    	Borsa borsaGiocatore = partita.getBorsaGiocatore();
    	//Variante: removeAttrezzo di borsa restituisce l'attrezzo restituito
    	Attrezzo daPosare = borsaGiocatore.removeAttrezzo(nomeAttrezzo);
    	if(daPosare!=null){
    	   	if(corrente.addAttrezzo(daPosare)){
    	   		System.out.println("Attrezzo Posato!");
    	   		System.out.println(borsaGiocatore.toString());
     	   	}
    	}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}

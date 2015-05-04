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

public class ComandoPosa implements Comando  {
	private String nomeAttrezzoDaPosare; 
	
	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		if(!(partita.getBorsaGiocatore().hasAttrezzo(this.nomeAttrezzoDaPosare))){
    		msg.append("Attrezzo non presente nella borsa!");
    		return msg.toString();
    	}
       	Stanza corrente = partita.getStanzaCorrente();
    	Borsa borsaGiocatore = partita.getBorsaGiocatore();
    	//Variante: removeAttrezzo di borsa restituisce l'attrezzo restituito
       	Attrezzo daPosare = borsaGiocatore.removeAttrezzo(this.nomeAttrezzoDaPosare);
    	if(daPosare!=null){
    	   	if(corrente.addAttrezzo(daPosare)){
    	   		msg.append("Attrezzo Posato!\n");
    	   		msg.append(borsaGiocatore.toString());
     	   	}
    	}
    	return msg.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPosare = parametro;
	}
	
}

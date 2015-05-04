package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Il giocatore prende l'attrezzo, ponendolo nella sua borsa e togliendola dalla stanza corrente
 * @param nomeAttrezzo
 */
public class ComandoPrendi implements Comando {
	private String nomeAttrezzo;  
	
	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		if( !(partita.getStanzaCorrente().hasAttrezzo(this.nomeAttrezzo)) ) {	//se l'attrezzo non è presente
    		msg.append("Attrezzo non presente nella stanza!");
    		return msg.toString();
      	}
    	//l'attrezzo è presente nella stanza
    	Stanza corrente = partita.getStanzaCorrente();
    	Attrezzo daPrendere = corrente.getAttrezzo(this.nomeAttrezzo);
    	Borsa borsaGiocatore = partita.getBorsaGiocatore();
		
    	//i metodi addAttrezzo di Borsa e removeAttrezzo di Stanza effettuano l'operazione
    	//richiesta e restituiscono true se è avvenuta con successo
    	if(daPrendere!=null){
    		if(borsaGiocatore.addAttrezzo(daPrendere) && corrente.removeAttrezzo(daPrendere)){
    			msg.append("Attrezzo Preso!\n");
    			msg.append(borsaGiocatore.toString());
    		}
    	}
    	return msg.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
}

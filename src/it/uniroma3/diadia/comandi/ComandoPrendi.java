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
	public void esegui(Partita partita) {
		if( !(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) ) {	//se l'attrezzo non è presente
    		System.out.println("Attrezzo non presente nella stanza!");
    		return;
      	}
    	//l'attrezzo è presente nella stanza
    	Stanza corrente = partita.getStanzaCorrente();
    	Attrezzo daPrendere = corrente.getAttrezzo(nomeAttrezzo);
    	Borsa borsaGiocatore = partita.getBorsaGiocatore();
		
    	//i metodi addAttrezzo di Borsa e removeAttrezzo di Stanza effettuano l'operazione
    	//richiesta e restituiscono true se è avvenuta con successo
    	if(daPrendere!=null){
    		if(borsaGiocatore.addAttrezzo(daPrendere) && corrente.removeAttrezzo(daPrendere)){
    			System.out.println("Attrezzo Preso!");
    			System.out.println(borsaGiocatore.toString());
    		}
    	}
    	
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}

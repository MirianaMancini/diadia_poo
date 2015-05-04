package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private String messaggio;
	
	public String getMessaggio() {
		return this.messaggio;
	}
	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder(); 
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null){
			this.messaggio = personaggio.agisci(partita);
		}
		msg.append(this.messaggio);
		return msg.toString();
	}
	
}

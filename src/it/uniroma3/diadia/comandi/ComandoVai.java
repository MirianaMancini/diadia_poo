package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 * @param String direzione
 */

public class ComandoVai implements Comando  {
	private String direzione;

	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (this.direzione==null) {
			msg.append("Dove vuoi andare? Devi specificare una direzione");
			return msg.toString();
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			msg.append("Direzione inesistente");
			return msg.toString();
		}
		partita.setStanzaCorrente(prossimaStanza);
		int cfu = partita.getCfu();
		cfu--;
		partita.setCfu(cfu);
		msg.append(partita.getStanzaCorrente().getNome()+"\n");
		if(!partita.vinta())
			msg.append("CFU: "+cfu+". (Hai ancora "+ cfu +" mosse a disposizione)");
		return msg.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

}

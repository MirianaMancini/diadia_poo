package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoSaluta extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		//invoca il metodo saluta del personaggio
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasPersonaggio()) {
			msg.append(stanzaCorrente.getPersonaggio().saluta());
		}
		else
			msg.append("A chi? Non c'è nessuno nella stanza!");
		return msg.toString();
	}

}

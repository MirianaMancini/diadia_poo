package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append("Grazie di aver giocato!");
		return msg.toString();
	}

}

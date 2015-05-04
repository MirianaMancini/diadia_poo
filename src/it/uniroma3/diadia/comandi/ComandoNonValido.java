package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append("Comando sconosciuto");
		return msg.toString();
	}

}

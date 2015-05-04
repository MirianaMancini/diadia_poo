package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String nome;		
	
	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		if(this.nome==null){
			msg.append("Cosa vuoi guardare? (stanza - borsa)");
			return msg.toString();
		}
		if(this.nome.equals("stanza")){
			msg.append(partita.getStanzaCorrente().getDescrizione());
			return msg.toString();
		}
		if(this.nome.equals("borsa")){
			msg.append(partita.getBorsaGiocatore().toString());
			return msg.toString();
		}
		msg.append("Comando non Valido! (Puoi guarda la stanza corrente o la borsa)");
		return msg.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;	
	}
	
}

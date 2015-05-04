package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	private String nome;		//nome indica cosa guardare
	
	@Override
	public void esegui(Partita partita) {
		if(this.nome==null){
			System.out.println("Cosa vuoi guardare? (stanza - borsa)");
			return;
		}
		if(this.nome.equals("stanza")){
			System.out.println(partita.getStanzaCorrente().getDescrizione());
			return;
		}
		if(this.nome.equals("borsa")){
			System.out.println(partita.getBorsaGiocatore().toString());
			return;
		}
		System.out.println("Comando non Valido! (Puoi guarda la stanza corrente o la borsa)");
	}

	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;	
	}

}

package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala implements Comando {
	private String nomeAttrezzoDaRegalare;	

	@Override
	public String esegui(Partita partita) {
		StringBuilder msg = new StringBuilder();
		//invoca il metodo riceviRegalo del personaggio
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasPersonaggio()) {
			//remove Borsa = remove l'attrezzo dalla borsa e restituisce l'attrezzo rimosso oppure null
			Attrezzo daRegalare = partita.getBorsaGiocatore().removeAttrezzo(this.nomeAttrezzoDaRegalare);
			if(daRegalare!=null){
				msg.append(stanzaCorrente.getPersonaggio().riceviRegalo(daRegalare, partita));
			}
			else{
				msg.append("Non puoi regalare quest'attrezzo, poichè non è presente nella tua Borsa!");
			}
		}
		else
			msg.append("A chi devi regalarlo? Non c'è nessuno nella stanza!");
		return msg.toString();
	}


	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaRegalare = parametro;
	}

}

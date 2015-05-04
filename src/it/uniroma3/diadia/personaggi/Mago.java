package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Un mago è un personaggio che possiede un attrezzo che ci può donare se interagiamo con lui
 * 
 * Quando un mago riceve un regalo, gli dimezza il peso e lo lascia cadere nella stanza
 */
public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla da darti ...";
	private static final String MESSAGGIO_REGALO ="Grazie del regalo. Sei gentilissimo! Ti restituisco il regalo dimezzando il suo peso! Cercalo nella stanza";
	
	private Attrezzo attrezzoDaDonare;

	public Mago(String nome, String presentazione, Attrezzo dono) {
		super(nome, presentazione);
		this.attrezzoDaDonare = dono;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (attrezzoDaDonare!=null) {
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			stanzaCorrente.addAttrezzo(attrezzoDaDonare);
			this.attrezzoDaDonare = null;
			msg = MESSAGGIO_DONO;
			msg += "\n" + stanzaCorrente.toString();
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append(MESSAGGIO_REGALO);
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		attrezzo.setPeso(attrezzo.getPeso()/2);
		stanzaCorrente.addAttrezzo(attrezzo);
		
		return msg.toString();
	}

}

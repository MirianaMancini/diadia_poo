package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Una strega è un personaggio che ci trasferisce in una stanza tra quelle adiacenti se intergiamo con lui
 * Poiché è permalosa, se non la abbiamo salutata, ci “trasferisce” nella stanza che contiene meno attrezzi;
 * se la abbiamo salutata in quella che contiene più attrezzi
 * 
 * Quando una strega riceve un regalo, se lo tiene e scoppia a ridere.
 */
public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_NONSALUTO = "Poichè non mi hai nemmeno salutato ti trasferirò nella stanza con meno attrezzi!\n";
	private static final String MESSAGGIO_SALUTO = "Grazie di avermi salutato! Ti trasferirò nella stanza con più attrezzi\n";
	private static final String MESSAGGIO_REGALO = "Ahahahahah.. Grazie, Bel regalo! \n";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	
	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(haSalutato()){
			Stanza stanzaConPiuAttrezzi = stanzaCorrente.getStanzaAdicenteConPiuAttrezzi();
			partita.setStanzaCorrente(stanzaConPiuAttrezzi);			
			msg.append(MESSAGGIO_SALUTO);
			msg.append(partita.getStanzaCorrente().toString());
		}
		else {
			Stanza stanzaConMenoAttrezzi = stanzaCorrente.getStanzaAdicenteConMenoAttrezzi();
			partita.setStanzaCorrente(stanzaConMenoAttrezzi);	
			msg.append(MESSAGGIO_NONSALUTO);
			msg.append(partita.getStanzaCorrente().toString());
		}
		return msg.toString();
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder msg = new StringBuilder();
		msg.append(MESSAGGIO_REGALO);
		
		return msg.toString();
	}
}

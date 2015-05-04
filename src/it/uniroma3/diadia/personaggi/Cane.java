package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Quando un cane riceve un regalo, se questo è il suo cibo preferito lo accetta, 
 * e butta a terra un attrezzo, altrimenti non lo accetta e lascia cadere il regalo nella stanza
 */
public class Cane extends AbstractPersonaggio {
	private static final String MESSAGGIO_MORSO = "Grrrrrr..";
	private static final String MESSAGGIO_REGALO_GRADITO ="Gnam Gnam! (Guarda a terra c'è un nuovo attrezzo) \n";
	private static final String MESSAGGIO_REGALO_NONGRADITO = "Bleh! (Guarda a terra c'è il tuo regalo) \n";
	
	private String nomeCiboPreferito;
	private Attrezzo regalo; 
			
	public Cane(String nome, String presentazione, String nomeCiboPreferito, Attrezzo regalo) {
		super(nome, presentazione);
		this.nomeCiboPreferito = nomeCiboPreferito;
		this.regalo = regalo;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.setCfu(partita.getCfu()-1);
		String msg = MESSAGGIO_MORSO;
		msg += "\nCFU: "+partita.getCfu();
		
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita){
		StringBuilder msg = new StringBuilder();
		if(attrezzo.getNome().equals(nomeCiboPreferito)){
			msg.append(MESSAGGIO_REGALO_GRADITO);
			partita.getStanzaCorrente().addAttrezzo(regalo);
		}
		else {
			msg.append(MESSAGGIO_REGALO_NONGRADITO);
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
		}
		return msg.toString();
	}
}

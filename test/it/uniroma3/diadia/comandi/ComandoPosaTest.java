package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiSemplice;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {
	private Labirinto labirinto;
	private Partita partita;
	private Attrezzo spada = new Attrezzo("spada", 4);

	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
    	this.partita = new Partita(this.labirinto);
    	this.partita.getBorsaGiocatore().addAttrezzo(spada);
	}

	@Test
	public void testEsegui() {
		//l'attrezzo spada è nella borsa e non nella stanza
		assertTrue(this.partita.getBorsaGiocatore().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		Comando comando = factory.costruisciComando("posa spada");
		comando.esegui(this.partita);
		//l'attrezzo è stato posato nella stanza
		assertFalse(this.partita.getBorsaGiocatore().hasAttrezzo("spada"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

}

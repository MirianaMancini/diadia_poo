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

public class ComandoPrendiTest {
	private Labirinto labirinto;
	private Partita partita;
	private Attrezzo mantello = new Attrezzo("mantello", 3);
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
    	this.partita = new Partita(this.labirinto);
    	this.partita.getStanzaCorrente().addAttrezzo(mantello);
	}

	@Test
	public void testEsegui() {
		//l'attrezzo mantello è nella stanza
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("mantello"));
		assertFalse(this.partita.getBorsaGiocatore().hasAttrezzo("mantello"));
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		Comando comando = factory.costruisciComando("prendi mantello");
		comando.esegui(this.partita);
		//l'attrezzo mantello è preso dal giocatore e ora si trova nella sua borsa
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("mantello"));
		assertTrue(this.partita.getBorsaGiocatore().hasAttrezzo("mantello"));
	}
	
	@Test
	public void testEseguiPrendi_AttrezzoNonPresente() {
		Attrezzo spada = new Attrezzo("spada", 4);
		this.partita.getBorsaGiocatore().addAttrezzo(spada);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(this.partita.getBorsaGiocatore().hasAttrezzo("spada"));
		//l'attrezzo è nella borsa del giocatore perciò non può essere prese dalla stanza
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		Comando comando = factory.costruisciComando("prendi spada");
		comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertTrue(this.partita.getBorsaGiocatore().hasAttrezzo("spada"));
	}

}

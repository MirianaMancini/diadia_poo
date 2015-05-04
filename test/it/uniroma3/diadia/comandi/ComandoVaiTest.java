package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiSemplice;

import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {
	private Labirinto labirinto;
	private Partita partita;
	

	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
    	this.partita = new Partita(this.labirinto);
	}

	@Test
	public void testEsegui() {
		assertEquals(new Stanza("Atrio"), this.partita.getStanzaCorrente());
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		Comando comando = factory.costruisciComando("vai sud");
		comando.esegui(this.partita);
		assertEquals(new Stanza("Aula N10"), this.partita.getStanzaCorrente());
		
		Comando comando2 = factory.costruisciComando("vai est");
		comando2.esegui(this.partita);
		assertEquals(new Stanza("Aula N11"), this.partita.getStanzaCorrente());
	}
	
}

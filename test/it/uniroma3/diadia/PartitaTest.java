package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	private Partita partita;
	private Stanza corrente;
	
	@Before
	public void setUp() throws Exception {
		this.partita = new Partita();			//impostazione Stanze come da codice di Labirinto
												//per cui StanzaCorrente=StanzaIniziale="Atrio"
		this.corrente = new Stanza("bar");
			
	}	
	
	@Test
	public void testGetStanzaCorrente() {
		assertEquals(this.partita.getLabirinto().getStanzaIniziale(), this.partita.getStanzaCorrente());	
	}
	
	@Test
	public void testSetStanzaCorrente() {
		this.partita.setStanzaCorrente(corrente);
		assertEquals(corrente, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVinta() {
		assertEquals(false, this.partita.vinta());		//poichè stanza corrente è bar
	}

	@Test
	public void testIsFinita() {
		assertEquals(false, this.partita.isFinita());
	}
	
	@Test
	public void testGetCfuPartita() {
		assertEquals(20, this.partita.getCfuPartita());
	}

}

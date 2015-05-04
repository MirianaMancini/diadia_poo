package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	private Partita partita;
	private Stanza corrente;
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.partita = new Partita(labirinto);			//impostazione Stanze come da codice di Labirinto
												        //per cui StanzaCorrente=StanzaIniziale="Atrio"
		this.corrente = new Stanza("bar");
	}	
	
	@Test
	public void testGetStanzaCorrente() {
		assertEquals(new Stanza("Atrio"), this.partita.getStanzaCorrente());	
	}
	
	@Test
	public void testSetStanzaCorrente() {
		this.partita.setStanzaCorrente(corrente);
		assertEquals(corrente, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals(new Stanza("Biblioteca"), this.partita.getStanzaVincente());
		
	}
	
	@Test
	public void testGetCfu() {
		assertEquals(20, this.partita.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		this.partita.setCfu(5);
		assertEquals(5, this.partita.getCfu());
	}

	@Test
	public void testVinta() {
		assertEquals(false, this.partita.vinta());		//poichè stanza corrente è bar
	}

	@Test
	public void testIsFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	
}

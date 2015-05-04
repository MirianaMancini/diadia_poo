package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
	}

	@Test
	public void testGetStanzaIniziale() {
		assertEquals(new Stanza("Atrio") , this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testGetStanzaFinale() {
		assertEquals(new Stanza("Biblioteca"), this.labirinto.getStanzaFinale());
	}
	
	@Test 
	public void testSetStanzaIniziale() {
		Stanza s = new Stanza("bar");
		this.labirinto.setStanzaIniziale(s);
		assertEquals(s, this.labirinto.getStanzaIniziale());
	}

	@Test
	public void testSetStanzaFinale() {
		Stanza s = new Stanza("mensa");
		this.labirinto.setStanzaFinale(s);
		assertEquals(s, this.labirinto.getStanzaFinale());
	}
}

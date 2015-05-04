package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Labirinto;

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
		assertEquals("Atrio" , labirinto.getStanzaIniziale().getNome());
	}

	@Test
	public void testGetStanzaFinale() {
		assertEquals("Biblioteca", labirinto.getStanzaFinale().getNome());
	}

}

package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.giocatore.Giocatore;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatore;
	
	@Before
	public void setUp() throws Exception {
		this.giocatore = new Giocatore();
	}

	@Test
	public void testGetCfu() {
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(5);
		assertEquals(5, this.giocatore.getCfu());
	}
}

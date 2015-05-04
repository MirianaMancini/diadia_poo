package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class AttrezzoTest {
	
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() throws Exception {
		this.attrezzo = new Attrezzo("borsa", 1);
	}

	@Test
	public void testGetNome() {
		assertEquals("borsa", this.attrezzo.getNome());
	}
	
	@Test
	public void testGetPeso() {
		assertEquals(this.attrezzo.toString(),1, this.attrezzo.getPeso());
	}

	@Test
	public void testToString() {
		assertEquals("borsa (1kg)", this.attrezzo.toString());
	}
	
	@Test
	public void testEquals() {
		Attrezzo a2 = new Attrezzo("sasso", 2);
		Attrezzo a3 = new Attrezzo("borsa", 1);
		
		assertFalse(a2.equals(attrezzo));
		assertTrue(a3.equals(attrezzo));
	}

}

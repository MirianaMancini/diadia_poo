package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;
import org.junit.Test;

public class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa(10);					//pesoMax 10
		this.attrezzo = new Attrezzo("corda", 1);
		this.borsa.addAttrezzo(attrezzo);
	}

	@Test
	public void testGetPeso() {
		assertEquals(1, this.borsa.getPeso());
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(10, this.borsa.getPesoMax());
	}
	
	@Test
	public void testGetAttrezzo() {
		assertEquals(attrezzo, this.borsa.getAttrezzo("corda"));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(this.borsa.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}

	@Test
	public void testToString() {
		assertEquals("Contenuto borsa (1kg/10kg): corda (1kg) ", this.borsa.toString());
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo2 = new Attrezzo("falce",2);		//solo per questo metodo: Attrezzo è locale
		this.borsa.addAttrezzo(attrezzo2);
		assertTrue(this.borsa.hasAttrezzo("falce"));
	}
	
	@Test
	public void removeAttrezzo() {
		assertEquals(attrezzo, this.borsa.removeAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("corda"));	 	
		assertEquals(null, this.borsa.getAttrezzo("corda"));	
	}

}

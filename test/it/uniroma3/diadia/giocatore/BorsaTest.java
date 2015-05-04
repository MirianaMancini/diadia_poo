package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;
import org.junit.Test;

public class BorsaTest {
	private Borsa borsa;
	private Borsa vuota;
	private Attrezzo corda;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa(8);					//pesoMax 10
		this.corda = new Attrezzo("corda", 1);
		this.borsa.addAttrezzo(corda);
		
		this.vuota = new Borsa();
	}

	@Test
	public void testGetPeso() {
		assertEquals(1, this.borsa.getPeso());
		assertEquals(0, this.vuota.getPeso());
		Attrezzo spada = new Attrezzo("spada", 5);
		this.borsa.addAttrezzo(spada);
		assertEquals(6, this.borsa.getPeso());
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(8, this.borsa.getPesoMax());
		assertEquals(10, this.vuota.getPesoMax());
	}
	
	@Test
	public void testGetAttrezzo() {
		assertEquals(corda, this.borsa.getAttrezzo("corda"));
		assertEquals(null, this.borsa.getAttrezzo("spada"));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(this.borsa.isEmpty());
		assertTrue(this.vuota.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}

	@Test
	public void testToString() {
		assertEquals("Contenuto borsa (1kg/8kg): corda (1kg) ", this.borsa.toString());
		Attrezzo spada = new Attrezzo("spada", 5);
		this.borsa.addAttrezzo(spada);
		assertEquals("Contenuto borsa (6kg/8kg): corda (1kg) spada (5kg) ", this.borsa.toString());
	}
	
	@Test 
	public void testToStringVuota(){
		assertEquals("Borsa vuota", this.vuota.toString()); 
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo2 = new Attrezzo("falce",2);		//solo per questo metodo: Attrezzo è locale
		this.borsa.addAttrezzo(attrezzo2);
		assertTrue(this.borsa.hasAttrezzo("falce"));
	}
	
	@Test
	public void testAddAttrezzo_TroppoPesante() {
		Attrezzo incudine = new Attrezzo("incudine", 10);
		this.borsa.addAttrezzo(incudine);
		//attrezzo non aggiunto perchè troppo pesante
		assertFalse(this.borsa.hasAttrezzo("incudine"));
;	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertEquals(new Attrezzo("corda",1), this.borsa.removeAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("corda"));	 
		assertEquals(null, this.borsa.getAttrezzo("corda"));
	}
	
	@Test
	public void testRemoveAttrezzo_NonPresente() {
		assertEquals(null, this.borsa.removeAttrezzo("falce"));
	}

}

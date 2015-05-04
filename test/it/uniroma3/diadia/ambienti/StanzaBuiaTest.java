package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaBuiaTest {
	private StanzaBuia buia;
	private Attrezzo lampada;

	@Before
	public void setUp() throws Exception {
		this.lampada = new Attrezzo("lampada", 2);
		this.buia = new StanzaBuia("Sala teatro", "lampada");
	}

	@Test
	public void testGetDescrizione_AttrezzoPresente() {
		this.buia.addAttrezzo(lampada);
		assertEquals("Sala teatro\nUscite: \nAttrezzi nella stanza: lampada (2kg) " ,this.buia.getDescrizione());
	}

	@Test
	public void testGetDescrizione_AttrezzoAssente() {
		assertEquals("Sala teatro\nQui c'è un buio pesto!\nPer vedere cerca lampada"
				+ " e posalo in questa stanza!", this.buia.getDescrizione());
	}
	
	@Test
	public void testGetAttrezzoPerVedere() {
		assertEquals("lampada" ,this.buia.getNomeAttrezzoPerVedere());
	}
	
	@Test
	public void testSetAttrezzoPerVedere() {
		this.buia.setNomeAttrezzoPerVedere("torcia");
		assertEquals("torcia" ,this.buia.getNomeAttrezzoPerVedere());
	}
	
	
}

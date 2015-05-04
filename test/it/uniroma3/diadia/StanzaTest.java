package it.uniroma3.diadia;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	private Stanza stanza;
	private Attrezzo ombrello;
	private Attrezzo tappeto;
	private Stanza bar;
	private Stanza mensa;
	
	@Before
	public void setUp() throws Exception {
		//inizializzazione variabili d'istanza
		this.stanza = new Stanza("atrio");
		this.ombrello = new Attrezzo("ombrello", 2);
		this.tappeto = new Attrezzo("tappeto", 1);
		this.bar = new Stanza("bar");
		this.mensa = new Stanza("mensa");
		
		//creo la stanza da testare
		this.stanza.addAttrezzo(ombrello);
		this.stanza.addAttrezzo(tappeto);
		this.stanza.impostaStanzaAdiacente("nord", bar);
		this.stanza.impostaStanzaAdiacente("sud", mensa);
		
		
	}
	
	@Test
	public void testToString() {
		assertEquals("atrio\nUscite:  nord sud\nAttrezzi nella stanza: ombrello (2kg) tappeto (1kg) ", stanza.toString());
	}
	@Test
	public void testGetStanzaAdiacente() {
		assertEquals(bar, this.stanza.getStanzaAdiacente("nord"));
		assertEquals(mensa, this.stanza.getStanzaAdiacente("sud"));
	}

	@Test
	public void testGetDescrizione() {
		assertEquals("atrio\nUscite:  nord sud\nAttrezzi nella stanza: ombrello (2kg) tappeto (1kg) ", this.stanza.getDescrizione());
	}

	@Test
	public void testGetAttrezzo() {
		assertEquals(ombrello, this.stanza.getAttrezzo("ombrello"));
		assertEquals(tappeto, this.stanza.getAttrezzo("tappeto"));
		
	}
	
	@Test
	public void testHasAttrezzo() {
		assertTrue(this.stanza.hasAttrezzo("ombrello"));
		assertFalse(this.stanza.hasAttrezzo("lanterna"));
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		Stanza s = new Stanza("ufficio");
		//pre
		assertEquals(bar, this.stanza.getStanzaAdiacente("nord"));
		assertEquals(mensa, this.stanza.getStanzaAdiacente("sud"));
		assertEquals(null, this.stanza.getStanzaAdiacente("est"));
		this.stanza.impostaStanzaAdiacente("est", s);
		assertEquals(s, this.stanza.getStanzaAdiacente("est"));
	}
	
	@Test
	public void removeAttrezzo() {
		//pre
		assertTrue(this.stanza.hasAttrezzo("ombrello"));	//la stanza ha l'ombrello
		
		this.stanza.removeAttrezzo(ombrello);
		
		//post
		assertFalse(this.stanza.hasAttrezzo("ombrello"));	//la stanza NON deve avere più l'ombrello
		
	}
}

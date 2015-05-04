package it.uniroma3.diadia.ambienti;

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
	public void testGetNome() {
		assertEquals("atrio", this.stanza.getNome());
		assertEquals("bar", this.bar.getNome());
		assertEquals("mensa", this.mensa.getNome());
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
	public void testGetStanzaAdiacente_Inesistente(){
		Stanza aulaN10 = new Stanza("N10");
		assertEquals(null, aulaN10.getStanzaAdiacente("nord"));
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
	public void testAddAttrezzo() {
		Attrezzo martello = new Attrezzo("martello", 1);
		Attrezzo spada = new Attrezzo("spada", 2);
		this.stanza.addAttrezzo(martello);
		assertEquals(martello, this.stanza.getAttrezzo("martello"));
		this.stanza.addAttrezzo(spada);
		assertEquals(spada, this.stanza.getAttrezzo("spada"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		//pre
		assertTrue(this.stanza.hasAttrezzo("ombrello"));	//la stanza ha l'ombrello
				
		//post
		assertTrue(this.stanza.removeAttrezzo(ombrello));
		assertFalse(this.stanza.hasAttrezzo("ombrello"));	//la stanza NON deve avere pi� l'ombrello
	}
	
	@Test
	public void testRemoveAttrezzo_assente() {
		Attrezzo martello = new Attrezzo("martello", 1);
		assertFalse(this.stanza.removeAttrezzo(martello));
	}
}
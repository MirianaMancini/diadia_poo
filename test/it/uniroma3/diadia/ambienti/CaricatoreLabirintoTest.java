package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CaricatoreLabirintoTest {
	private String labirintoMonolocale;
	private String labirintoBilocale;
	private String labirintoTrilocale;
	private CaricatoreLabirinto c, c2, c3;
	
	@Before
	public void setUp() throws Exception {
		this.labirintoMonolocale = "Stanze: biblioteca\nInizio: biblioteca\nVincente: biblioteca\nAttrezzi: martello 10 biblioteca\nUscite: ";
		this.labirintoBilocale = "Stanze: biblioteca, N10\nInizio: biblioteca\nVincente: N10\nAttrezzi: martello 10 biblioteca, pinza 2 N10\nUscite: biblioteca nord N10, N10 sud biblioteca";
		this.labirintoTrilocale = "Stanze: biblioteca, N10, N11\nInizio: biblioteca\nVincente: N10\nAttrezzi: martello 10 biblioteca, pinza 2 N10\nUscite: biblioteca nord N10, N10 sud biblioteca, biblioteca sud N11, N11 nord biblioteca";
		this.c = new CaricatoreLabirinto(labirintoMonolocale, true);	
		this.c2 = new CaricatoreLabirinto(labirintoBilocale, true);
		this.c3 = new CaricatoreLabirinto(labirintoTrilocale, true); 
	}

	@Test
	public void testLeggiRigaCheCominciaPer() {
		try {
			assertEquals(" biblioteca", c.leggiRigaCheCominciaPer("Stanze:"));
			assertEquals(" biblioteca", c.leggiRigaCheCominciaPer("Inizio:"));
			assertEquals(" biblioteca", c.leggiRigaCheCominciaPer("Vincente:"));
			assertEquals(" martello 10 biblioteca", c.leggiRigaCheCominciaPer("Attrezzi:"));
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCarica_Monolocale() {
		try {
			this.c.carica();
			assertTrue(this.c.getNome2Stanza().containsKey("biblioteca"));
			assertEquals("biblioteca", this.c.getStanzaIniziale().getNome());
			assertEquals("biblioteca", this.c.getStanzaVincente().getNome());
			assertTrue(this.c.getNome2Stanza().get("biblioteca").hasAttrezzo("martello"));
			
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Test
	public void testCarica_Bilocale() {
		try {
			this.c2.carica();
			assertEquals(2, this.c2.getNome2Stanza().size());
			assertTrue(this.c2.getNome2Stanza().containsKey("biblioteca"));
			assertTrue(this.c2.getNome2Stanza().containsKey("N10"));
			assertEquals("biblioteca", this.c2.getStanzaIniziale().getNome());
			assertEquals("N10", this.c2.getStanzaVincente().getNome());
			assertTrue(this.c2.getNome2Stanza().get("biblioteca").hasAttrezzo("martello"));
			assertTrue(this.c2.getNome2Stanza().get("N10").hasAttrezzo("pinza"));
			assertEquals("N10", this.c2.getNome2Stanza().get("biblioteca").getStanzaAdiacente("nord").getNome());
			assertEquals("biblioteca", this.c2.getNome2Stanza().get("N10").getStanzaAdiacente("sud").getNome());
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Test
	public void testCarica_Trilocale() {
		try {
			this.c3.carica();
			assertEquals(3, this.c3.getNome2Stanza().size());
			assertTrue(this.c3.getNome2Stanza().containsKey("biblioteca"));
			assertTrue(this.c3.getNome2Stanza().containsKey("N10"));
			assertTrue(this.c3.getNome2Stanza().containsKey("N11"));
			assertEquals("biblioteca", this.c3.getStanzaIniziale().getNome());
			assertEquals("N10", this.c3.getStanzaVincente().getNome());
			assertTrue(this.c3.getNome2Stanza().get("biblioteca").hasAttrezzo("martello"));
			assertTrue(this.c3.getNome2Stanza().get("N10").hasAttrezzo("pinza"));
			assertEquals(0, this.c3.getNome2Stanza().get("N11").getAttrezzi().length);
			assertEquals("N10", this.c3.getNome2Stanza().get("biblioteca").getStanzaAdiacente("nord").getNome());
			assertEquals("biblioteca", this.c3.getNome2Stanza().get("N10").getStanzaAdiacente("sud").getNome());
			assertEquals("N11", this.c3.getNome2Stanza().get("biblioteca").getStanzaAdiacente("sud").getNome());
			assertEquals("biblioteca", this.c3.getNome2Stanza().get("N11").getStanzaAdiacente("nord").getNome());
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
			return;
		}
	}
	
	
	

}

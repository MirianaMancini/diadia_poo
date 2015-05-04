package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaBloccataTest {
	StanzaBloccata atrio;
	Stanza segreteria;
	Attrezzo chiave;
	
	@Before
	public void setUp() throws Exception {
		this.atrio = new StanzaBloccata("Atrio", "ovest", "chiave");
		this.segreteria = new Stanza("Segreteria");
		this.chiave = new Attrezzo("chiave", 1);
		
		this.atrio.impostaStanzaAdiacente("ovest", segreteria);
		this.segreteria.impostaStanzaAdiacente("est", atrio);
		
	}

	@Test
	public void testGetStanzaAdiacente_ChiavePresente() {
		this.atrio.addAttrezzo(chiave);
		assertEquals(segreteria ,this.atrio.getStanzaAdiacente("ovest"));
	}
	
	@Test
	public void testGetStanzaAdiacente_ChiaveAssente() {
		//chiave non presente per cui ritorna la stanza corrente
		assertEquals(this.atrio ,this.atrio.getStanzaAdiacente("ovest"));
	}

	@Test
	public void testGetDescrizione_ChiaveAssente() {
		assertEquals("Atrio\nUscite:  ovest\nAttrezzi nella stanza: \nDirezione bloccata: ovest"
				+ "\nCerca l'oggetto chiave e posalo in questa stanza!", this.atrio.getDescrizione());
	}
	
	@Test
	public void testGetDescrizione_ChiavePresente() {
		this.atrio.addAttrezzo(chiave);
		assertEquals("Atrio\nUscite:  ovest\nAttrezzi nella stanza: chiave (1kg) \nDirezione bloccata: ovest"
				+ "\nchiave presente nella stanza. Puoi andare nella direzione bloccata", this.atrio.getDescrizione());
	}

	@Test
	public void testGetDirezioneBloccata() {
		assertEquals("ovest", this.atrio.getDirezioneBloccata());
	}

	@Test
	public void testGetNomeAttrezzoChiave() {
		assertEquals("chiave", this.atrio.getNomeAttrezzoChiave());
	}

	@Test
	public void testSetDirezioneBloccata() {
		this.atrio.setDirezioneBloccata("est");
		assertEquals("est", this.atrio.getDirezioneBloccata());
	}

	@Test
	public void testSetNomeAttrezzoChiave() {
		this.atrio.setNomeAttrezzoChiave("mazzo chiavi");
		assertEquals("mazzo chiavi", this.atrio.getNomeAttrezzoChiave());
	}

}

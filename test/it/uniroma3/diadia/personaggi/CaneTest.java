package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;


import org.junit.Before;
import org.junit.Test;

public class CaneTest {
	private Labirinto labirinto;
	private Partita partita;
	
	private Cane cane;
	private Attrezzo regalo;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.regalo = new Attrezzo("telo",2);
		this.cane = new Cane("Achille", "Bau!", "pappa", regalo);
		this.partita = new Partita(this.labirinto);
    	
		this.partita.getStanzaCorrente().setPersonaggio(cane);
	}

	@Test
	public void testAgisci() {
		assertEquals(20, this.partita.getCfu());
		assertEquals("Grrrrrr..\nCFU: 19",this.cane.agisci(partita));
		assertEquals(19, this.partita.getCfu());
	}

	@Test
	public void testRiceviRegalo_Gradito() {
		Attrezzo attrezzo = new Attrezzo("pappa", 1);
		this.partita.getBorsaGiocatore().addAttrezzo(attrezzo);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("telo"));
		assertEquals("Gnam Gnam! (Guarda a terra c'è un nuovo attrezzo) \n", this.cane.riceviRegalo(attrezzo, this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("telo"));	
	}
	
	@Test
	public void testRiceviRegalo_NonGradito() {
		Attrezzo attrezzo = new Attrezzo("corda", 1);
		this.partita.getBorsaGiocatore().addAttrezzo(attrezzo);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("telo"));
		assertEquals("Bleh! (Guarda a terra c'è il tuo regalo) \n", this.cane.riceviRegalo(attrezzo, partita));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("telo"));	
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("corda"));
	}

}

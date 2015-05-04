package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StregaTest {
	private Labirinto labirinto;
	private Partita partita;
	private Strega strega;
	private Attrezzo regalo;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.strega = new Strega("Clezia", "Ciao sono una strega");
		this.partita.getStanzaCorrente().setPersonaggio(strega);
		
		this.regalo = new Attrezzo("regalo",2);
	}

	
	@Test
	public void testAgisci_Salutata() {
		assertFalse(this.strega.haSalutato());
		this.strega.saluta();
		assertTrue(this.strega.haSalutato());
		assertEquals("Atrio",this.partita.getStanzaCorrente().getNome());
		assertEquals("Grazie di avermi salutato! Ti trasferirò nella stanza con più attrezzi\nLaboratorio Campus\nUscite:  est ovest\nAttrezzi nella stanza: chiave (2kg) spada (4kg) ", this.strega.agisci(partita));
		assertEquals("Laboratorio Campus",this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testRiceviRegalo() {
		assertTrue(this.partita.getBorsaGiocatore().addAttrezzo(regalo));
		assertTrue(this.partita.getBorsaGiocatore().hasAttrezzo("regalo"));
		assertEquals("Ahahahahah.. Grazie, Bel regalo! \n", this.strega.riceviRegalo(regalo, this.partita));
	}

}

package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class MagoTest {
	private Labirinto labirinto;
	private Partita partita;
	private Mago mago;
	private Attrezzo attrezzoDaDonare;
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.attrezzoDaDonare = new Attrezzo("regalo",2);
		this.mago = new Mago("Carlo", "io sono un mago", attrezzoDaDonare);
		this.partita.getStanzaCorrente().setPersonaggio(mago);
	}

	@Test
	public void testAgisci() {
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("regalo"));
		assertEquals("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo bel borsone!\nAtrio\nUscite:  sud est nord ovest\nAttrezzi nella stanza: osso (1kg) regalo (2kg) \nPersonaggio nella stanza: Carlo",this.mago.agisci(partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("regalo"));
		assertEquals("Mi spiace, ma non ho piu' nulla da darti ...",this.mago.agisci(partita));
	}

	@Test
	public void testRiceviRegalo() {
		Attrezzo attrezzo = new Attrezzo("stereo", 4);
		this.partita.getBorsaGiocatore().addAttrezzo(attrezzo);
		assertEquals("Grazie del regalo. Sei gentilissimo! Ti restituisco il regalo dimezzando il suo peso! Cercalo nella stanza", this.mago.riceviRegalo(attrezzo, this.partita));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("stereo"));
		assertEquals(2, attrezzo.getPeso());
	}

}

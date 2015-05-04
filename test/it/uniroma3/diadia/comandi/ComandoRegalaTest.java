package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;

import org.junit.Before;
import org.junit.Test;

public class ComandoRegalaTest {
	private Labirinto labirinto;
	private Partita partita;
	
	private Cane cane;
	private Attrezzo regalo;
	
	//caso particolare di regalo a un Personaggio Cane
	
	@Before
	public void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.regalo = new Attrezzo("telo",2);
		this.cane = new Cane("Achille", "Bau!", "pappa", regalo);
		this.partita = new Partita(this.labirinto);
    	
		this.partita.getStanzaCorrente().setPersonaggio(cane);
	}

	@Test
	public void testEsegui() {
		Attrezzo attrezzo = new Attrezzo("pappa", 1);
		this.partita.getBorsaGiocatore().addAttrezzo(attrezzo);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("telo"));
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		Comando comando = factory.costruisciComando("regala pappa");
		comando.esegui(this.partita);
		assertFalse(this.partita.getBorsaGiocatore().hasAttrezzo("pappa"));	
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("telo"));
	}

}

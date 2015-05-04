package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {
	private FabbricaDiComandiRiflessiva fabbrica;
	
	@Before
	public void setUp() throws Exception {
		this.fabbrica = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testCostruisciComando_ComandoVai() {
		assertSame(ComandoVai.class, this.fabbrica.costruisciComando("vai sud").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoAiuto() {
		assertSame(ComandoAiuto.class, this.fabbrica.costruisciComando("aiuto").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoFine() {
		assertSame(ComandoFine.class, this.fabbrica.costruisciComando("fine").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoGuarda() {
		assertSame(ComandoGuarda.class, this.fabbrica.costruisciComando("guarda stanza").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoInteragisci() {
		assertSame(ComandoInteragisci.class, this.fabbrica.costruisciComando("interagisci").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoNonValido() {
		assertSame(ComandoNonValido.class, this.fabbrica.costruisciComando("ciao").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoPrendi() {
		assertSame(ComandoPrendi.class, this.fabbrica.costruisciComando("prendi osso").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoPosa() {
		assertSame(ComandoPosa.class, this.fabbrica.costruisciComando("posa martello").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoRegala() {
		assertSame(ComandoRegala.class, this.fabbrica.costruisciComando("regala dono").getClass());
	}
	
	@Test
	public void testCostruisciComando_ComandoSaluta() {
		assertSame(ComandoSaluta.class, this.fabbrica.costruisciComando("saluta").getClass());
	}
	

}

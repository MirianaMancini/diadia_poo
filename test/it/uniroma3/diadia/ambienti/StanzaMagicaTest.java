package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;
import org.junit.Test;

public class StanzaMagicaTest {
	private StanzaMagica lab;

	private Attrezzo ombrello;
	private Attrezzo tappeto;
	private Attrezzo ascia;

	@Before
	public void setUp() throws Exception {
		this.lab = new StanzaMagica("Laboratorio", 2);
		this.ombrello = new Attrezzo("ombrello", 2);
		this.tappeto = new Attrezzo("tappeto", 1);
		this.ascia = new Attrezzo("ascia", 3);
	}
	
	//testiamo alcuni metodi ereditati
	@Test
	public void testGetNome() {
		assertEquals("Laboratorio", lab.getNome());
	}
	
	@Test
	public void testGetAttrezzo() {
		lab.addAttrezzo(ombrello);
		assertEquals(new Attrezzo("ombrello", 2) , lab.getAttrezzo("ombrello"));
	}
	
	//testiamo i metodi propri di StanzaMagica
	@Test
	public void testGetSogliaMagica() {
		assertEquals(2, lab.getSogliaMagica());
	}
	
	@Test
	public void testSetSogliaMagica() {
		lab.setSogliaMagica(4);
		assertEquals(4, lab.getSogliaMagica());
	}
	
	
	//testiamo il comportamento magico
	@Test
	public void testAddAttrezzo() {
		lab.addAttrezzo(ombrello);	//un attrezzo posato
		assertTrue(lab.hasAttrezzo("ombrello"));
		lab.addAttrezzo(tappeto);	//due attrezzi posati
		assertTrue(lab.hasAttrezzo("tappeto"));
		//MAGIA ATTIVATA
		lab.addAttrezzo(ascia);
		//aggiunge ascia in lab attivando su ascia il comportamento magico
		assertFalse(lab.hasAttrezzo("ascia"));
		assertTrue(lab.hasAttrezzo("aicsa"));					//nome invertito
		assertEquals(6, lab.getAttrezzo("aicsa").getPeso());	//peso raddoppiato
	}

}

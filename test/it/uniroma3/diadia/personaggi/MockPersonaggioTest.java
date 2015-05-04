package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MockPersonaggioTest {
	MockPersonaggio personaggioTest;
	
	@Before
	public void setUp() throws Exception {
		this.personaggioTest = new MockPersonaggio("Mickey Mouse", "Sono un topolino");
	}

	@Test
	public void testGetNome() {
		assertEquals("Mickey Mouse", personaggioTest.getNome());
	}

	@Test
	public void testGetPresentazione() {
		assertEquals("Sono un topolino", personaggioTest.getPresentazione());
	}

	@Test
	public void testHaSalutato() {
		assertFalse(personaggioTest.haSalutato());
		personaggioTest.saluta();
		assertTrue(personaggioTest.haSalutato());
	}

	@Test
	public void testSaluta_NonGiaSalutati() {
		assertFalse(personaggioTest.haSalutato());
		assertEquals("Ciao, io sono Mickey Mouse. Sono un topolino", personaggioTest.saluta());
	}
	
	@Test
	public void testSaluta_GiaSalutati() {
		MockPersonaggio paperino = new MockPersonaggio("Paperino", "Sono un papero");
		assertFalse(paperino.haSalutato());
		paperino.saluta();
		assertTrue(paperino.haSalutato());
		assertEquals("Ciao, io sono Paperino. Ci siamo gia' presentati.", paperino.saluta());
	}

	@Test
	public void testToString() {
		assertEquals("Mickey Mouse", personaggioTest.toString());
	}

	//NB: I METODI ASTRATTI NON POSSONO ESSERE TESTATI DALLA CLASSE ASTRATTA
	//    SI POSSONO TESTARE GLI OVERRIDE NELLE CLASSI ESTESE
}

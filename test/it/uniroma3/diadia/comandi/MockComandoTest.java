package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MockComandoTest {
	private MockComando comandoTest;
	
	@Before
	public void setUp() throws Exception {
		this.comandoTest = new MockComando();
		this.comandoTest.setParametro("parametro");
	}

	@Test
	public void testSetParametro() {
		assertEquals(null ,this.comandoTest.getParametro());
		this.comandoTest.setParametro("nuovo parametro");
		assertEquals(null ,this.comandoTest.getParametro());
	}

}

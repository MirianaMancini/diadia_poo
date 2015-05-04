package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.Map;

public class BorsaTest {
	private Borsa borsa;
	private Borsa vuota;
	private Attrezzo corda;
	
	@Before
	public void setUp() throws Exception {
		this.borsa = new Borsa(8);					//pesoMax 10
		this.corda = new Attrezzo("corda", 1);
		this.borsa.addAttrezzo(corda);
		
		this.vuota = new Borsa();
	}

	@Test
	public void testGetPeso() {
		assertEquals(1, this.borsa.getPeso());
		assertEquals(0, this.vuota.getPeso());
		Attrezzo spada = new Attrezzo("spada", 5);
		this.borsa.addAttrezzo(spada);
		assertEquals(6, this.borsa.getPeso());
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(8, this.borsa.getPesoMax());
		assertEquals(10, this.vuota.getPesoMax());
	}
	
	@Test
	public void testGetAttrezzo() {
		assertEquals(corda, this.borsa.getAttrezzo("corda"));
		assertEquals(null, this.borsa.getAttrezzo("spada"));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(this.borsa.isEmpty());
		assertTrue(this.vuota.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("osso"));
	}

	@Test
	public void testToString() {
		assertEquals("Contenuto borsa (1kg/8kg): corda (1kg) ", this.borsa.toString());
		Attrezzo spada = new Attrezzo("spada", 5);
		this.borsa.addAttrezzo(spada);
		//toString() stampa contenuto della borsa ordinato per peso
		assertEquals("Contenuto borsa (6kg/8kg): corda (1kg) spada (5kg) ", this.borsa.toString());
	}
	
	@Test 
	public void testToStringVuota(){
		assertEquals("Borsa vuota", this.vuota.toString()); 
	}
	
	@Test
	public void testAddAttrezzo() {
		Attrezzo attrezzo2 = new Attrezzo("falce",2);		//solo per questo metodo: Attrezzo è locale
		assertTrue(this.borsa.addAttrezzo(attrezzo2));
	}
	
	@Test
	public void testAddAttrezzoNomeDuplicato() {
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertEquals(1, this.borsa.getAttrezzo("corda").getPeso());
		this.borsa.addAttrezzo(new Attrezzo("corda", 5));
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertEquals(5, this.borsa.getAttrezzo("corda").getPeso());
		//l'attrezzo con nome corda è stato sostituito da quello nuovo con lo stesso nome
	}
	
	@Test
	public void testAddAttrezzo_TroppoPesante() {
		assertFalse(this.borsa.hasAttrezzo("incudine"));
		
		Attrezzo incudine = new Attrezzo("incudine", 10);
		this.borsa.addAttrezzo(incudine); 	//attrezzo non aggiunto perchè troppo pesante
		
		assertFalse(this.borsa.hasAttrezzo("incudine"));
;	}
	
	@Test
	public void testRemoveAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("corda"));
		assertEquals(new Attrezzo("corda",1), this.borsa.removeAttrezzo("corda"));
		assertFalse(this.borsa.hasAttrezzo("corda"));	 
	}
	
	@Test
	public void testRemoveAttrezzo_NonPresente() {
		assertEquals(null, this.borsa.removeAttrezzo("falce"));
	}
	

	
	@Test
	public void testGetContenutoOrdinatoPerNome_Minimale() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",8);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		
		System.out.println(daOrdinare.toString());
		
		List<Attrezzo> borsaOrdinata = daOrdinare.getContenutoOrdinatoPerNome();
		assertEquals(piombo,borsaOrdinata.get(0));
		assertEquals(piuma,borsaOrdinata.get(1));
		System.out.println("Borsa ordinata per Nome: "+borsaOrdinata+"\n");
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",4);
		Attrezzo ps = new Attrezzo("ps",3);
		Attrezzo libro = new Attrezzo("libro",2);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		daOrdinare.addAttrezzo(ps);
		daOrdinare.addAttrezzo(libro);
		
		System.out.println(daOrdinare.toString());
		
		List<Attrezzo> borsaOrdinata = daOrdinare.getContenutoOrdinatoPerNome();
		assertEquals(libro,borsaOrdinata.get(0));
		assertEquals(piombo,borsaOrdinata.get(1));
		assertEquals(piuma,borsaOrdinata.get(2));
		assertEquals(ps,borsaOrdinata.get(3));
		System.out.println("Borsa ordinata per Nome: "+borsaOrdinata+"\n");
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_Minimale() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",4);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		System.out.println(daOrdinare.toString());
		
		List<Attrezzo> borsaOrdinata = daOrdinare.getContenutoOrdinatoPerPeso();
		assertEquals(piuma,borsaOrdinata.get(0));
		assertEquals(piombo,borsaOrdinata.get(1));
		System.out.println("Borsa ordinata per Peso: "+borsaOrdinata+"\n");
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",4);
		Attrezzo ps = new Attrezzo("ps",3);
		Attrezzo libro = new Attrezzo("libro",2);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		daOrdinare.addAttrezzo(ps);
		daOrdinare.addAttrezzo(libro);
		
		System.out.println(daOrdinare.toString());
		
		List<Attrezzo> borsaOrdinata = daOrdinare.getContenutoOrdinatoPerPeso();
		assertEquals(piuma,borsaOrdinata.get(0));
		assertEquals(libro,borsaOrdinata.get(1));
		assertEquals(ps,borsaOrdinata.get(2));
		assertEquals(piombo,borsaOrdinata.get(3));
		System.out.println("Borsa ordinata per Peso: "+borsaOrdinata+"\n");
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_ParitàPeso() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",4);
		Attrezzo ps = new Attrezzo("ps",1);
		Attrezzo libro = new Attrezzo("libro",1);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		daOrdinare.addAttrezzo(ps);
		daOrdinare.addAttrezzo(libro);
		
		System.out.println(daOrdinare.toString());
		
		List<Attrezzo> borsaOrdinata = daOrdinare.getContenutoOrdinatoPerPeso();
		//piuma libro e ps hanno lo stesso peso, perciò sono ordinati per nome
		assertEquals(libro,borsaOrdinata.get(0));
		assertEquals(piuma,borsaOrdinata.get(1));
		assertEquals(ps,borsaOrdinata.get(2));
		assertEquals(piombo,borsaOrdinata.get(3));
		System.out.println("Borsa ordinata per Peso: "+borsaOrdinata+"\n");
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_Minimale() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		daOrdinare.addAttrezzo(piuma);
		System.out.println(daOrdinare.toString());
		Map<Integer, Set<Attrezzo>> mappaOrdinata = daOrdinare.getContenutoRaggruppatoPerPeso();
		assertEquals(1, mappaOrdinata.get(1).size());
		assertTrue(mappaOrdinata.get(1).contains(piuma));
		System.out.println("Borsa con attrezzi raggruppati per Peso: "+mappaOrdinata+"\n");
		
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_Minimale2() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo libro = new Attrezzo("libro",1);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(libro);
		System.out.println(daOrdinare.toString());
		Map<Integer, Set<Attrezzo>> mappaOrdinata = daOrdinare.getContenutoRaggruppatoPerPeso();
		assertTrue(mappaOrdinata.get(1).contains(piuma));
		assertTrue(mappaOrdinata.get(1).contains(libro));
		System.out.println("Borsa con attrezzi raggruppati per Peso: "+mappaOrdinata+"\n");
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Borsa daOrdinare = new Borsa();
		Attrezzo piuma = new Attrezzo("piuma",1);
		Attrezzo piombo = new Attrezzo("piombo",4);
		Attrezzo ps = new Attrezzo("ps",2);
		Attrezzo libro = new Attrezzo("libro",1);
		daOrdinare.addAttrezzo(piuma);
		daOrdinare.addAttrezzo(piombo);
		daOrdinare.addAttrezzo(ps);
		daOrdinare.addAttrezzo(libro);
		
		System.out.println(daOrdinare.toString());
		
		Map<Integer, Set<Attrezzo>> mappaOrdinata = daOrdinare.getContenutoRaggruppatoPerPeso();
		
		assertEquals(2, mappaOrdinata.get(1).size());
		assertTrue(mappaOrdinata.get(1).contains(piuma));
		assertTrue(mappaOrdinata.get(1).contains(libro));
		assertEquals(1, mappaOrdinata.get(2).size());
		assertTrue(mappaOrdinata.get(2).contains(ps));
		assertEquals(1, mappaOrdinata.get(4).size());
		assertTrue(mappaOrdinata.get(4).contains(piombo));
		
		System.out.println("Borsa con attrezzi raggruppati per Peso: "+mappaOrdinata+"\n");
		
	}
	
	
}

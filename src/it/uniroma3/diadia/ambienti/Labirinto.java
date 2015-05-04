package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;

/**
 * Questa classe si occupa della creazione del labirinto
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;		//è la stanza vincente della Partita
	
	public Labirinto(){
		creaStanze();					//crea il labirinto attraverso il metodo creaStanze()
	}
	
	/*NB: Labirinto invoca metodi che posso lanciare eccezioni Checked e deve perciò gestrile */
	
	public Labirinto(String nomeFile) throws FileNotFoundException {			
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		try {
			c.carica();									//crea il labirinto caricandolo da file
		} catch (FormatoFileNonValidoException e) {
			e.printStackTrace();
			return;
		}
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaFinale = c.getStanzaVincente();
	}
	
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 * Inizializza il labirinto
	 */
	private void creaStanze() {
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave", 2);
		Attrezzo dono = new Attrezzo("bacchetta", 2);
		Attrezzo spada = new Attrezzo("spada", 4);
		Attrezzo regalo = new Attrezzo("corda",3);
				
		/* crea personaggi */
		Mago mago = new Mago("Merlino", "Ho un dono da darti!", dono);
		Cane cane = new Cane("Fido", "Bau Bau!", "osso", regalo);
		Strega strega = new Strega("Morgana", "Sono una potente strega e posso trasferirti in una stanza adiacente!");
		
		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio", "nord", "chiave");
		Stanza aulaN11 = new StanzaMagica("Aula N11", 2);	//AULA N11 E' LA STANZA MAGICA con soglia 2
		Stanza aulaN10 = new StanzaBuia("Aula N10", "lanterna");	//AULA N10 E' LA STANZA BUIA  
		Stanza laboratorio = new Stanza("Laboratorio Campus");	
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);
		laboratorio.addAttrezzo(spada);
		
		/* pone i personaggi nelle stanze */
		atrio.setPersonaggio(mago);
		aulaN10.setPersonaggio(cane);
		aulaN11.setPersonaggio(strega);
								
		// il gioco comincia nell'atrio e la biblioteca è la stanza vincente
		this.stanzaIniziale = atrio;
		this.stanzaFinale = biblioteca;
		
	}
	
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}
	
}

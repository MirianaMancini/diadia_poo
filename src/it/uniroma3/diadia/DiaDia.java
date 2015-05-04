package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiSemplice;
import it.uniroma3.diadia.giocatore.Borsa;

import java.util.Scanner;

/**
 *  Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 *  Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 *  Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  Paolo Merialdo (da un'idea di Michael Kolling and David J. Barnes) *
 * @version 0.1
 */

public class DiaDia {
	private Partita partita;
	private Labirinto labirinto;
	private static final String MESSAGGIO_BENVENUTO = 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
  //  public static String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

    public DiaDia() {
    	//DiaDia ha la responsabilitò di creare il labirinto e avviare la partita
    	this.labirinto = new Labirinto();
    	this.partita = new Partita(this.labirinto);
    	
    }

	public void gioca() {
		String istruzione; 
	    Scanner scannerDiLinee;
	    
	    System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   
 
        
	/**
	 * Processa una istruzione 
	 * @param String istruzione 
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiSemplice();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			System.out.println("Hai vinto!");
		if (!this.partita.ancoraInGioco())
			System.out.println("Hai esaurito i CFU: Hai finito le mosse a disposizione!");
		return this.partita.isFinita();
	}
    
	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
	/**
	 * Ritorna un riferimento alla Partita corrente
	 */
	public Partita getPartita() {
		return partita;
	}
	/**
	 * Ritorna un riferimento al Labirinto corrente
	 */
	public Labirinto getLabirinto() {
		return labirinto;
	}
}
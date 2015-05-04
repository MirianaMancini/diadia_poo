package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.io.InterfacciaUtenteConsole;

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
	private InterfacciaUtenteConsole console; 
	private static final String MESSAGGIO_BENVENUTO = 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	public DiaDia() {
//    	this.labirinto = new Labirinto();
    	String nomeFile = "C:\\Users\\Margherita\\Eclipse Workspace\\Diadia_base\\src\\labirintoGioco.txt";
    	try {
			this.labirinto = new Labirinto(nomeFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
    	this.partita = new Partita(this.labirinto);
    	this.console = new InterfacciaUtenteConsole();
    }

	public void gioca() {
		String istruzione;
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.console.prendiIstruzione();
		while(!processaIstruzione(istruzione));
	}   
 
        
	/**
	 * Processa una istruzione 
	 * @param String istruzione
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		String messaggioDiEsecuzione = comandoDaEseguire.esegui(this.partita);
		this.console.mostraMessaggio(messaggioDiEsecuzione);
				
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		if (!this.partita.ancoraInGioco())
			this.console.mostraMessaggio("Hai esaurito i CFU: Hai finito le mosse a disposizione!");
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
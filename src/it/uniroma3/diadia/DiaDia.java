package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
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
    private static String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

    public DiaDia() {
    	this.partita = new Partita();
    }

	public void gioca() {
		String istruzione; 
	    Scanner scannerDiLinee;
	    
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
		int cfu_giocatore = this.partita.getCfuPartita();
		//oppure EQUIVALENTEMENTE---> int cfu_giocatore = this.partita.getGiocatore().getCfu();
		
		if(cfu_giocatore > 0){
			Comando comandoDaEseguire = new Comando(istruzione);

			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
					this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
			else
				System.out.println("Comando sconosciuto");
			if (this.partita.vinta()) {
				System.out.println("Hai vinto!");
				return true;
			} else
				return false;
		}//end if
		else {
			System.out.println("Hai finito le mosse a disposizione!");
			this.partita.setFinita();
			//invoco il comando fine()
			this.fine();
			return true;
		}
	}
    
    // implementazioni dei comandi dell'utente:

    /**
     * Stampa informazioni di aiuto.
     */
    private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	    }

    /**
     * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
     * e ne stampa il nome, altrimenti stampa un messaggio di errore
     * @param String direzione
     */
	 private void vai(String direzione) {
		 if(direzione==null)
			 System.out.println("Dove vuoi andare ?");
		 Stanza prossimaStanza = null;
		 prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		 if (prossimaStanza == null)
			 System.out.println("Direzione inesistente");
		 else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			cfu--;
			this.partita.getGiocatore().setCfu(cfu);
			System.out.println("Hai ancora "+ cfu +" mosse a disposizione");
		 }
		 System.out.println(partita.getStanzaCorrente().getDescrizione());
	 }

    /**
     * Comando "Fine".
     */
    private void fine() {
    	System.out.println("Grazie di aver giocato!");  // si desidera smettere
    }
    
    /**
     * Restituisce vero se il giocatore prende l'attrezzo, ponendo nella sua borsa
     * e togliendola dalla stanza corrente
     * @param nomeAttrezzo
     * @return true se l'attrezzo è stato preso dal giocatore
     */
    private boolean prendi(String nomeAttrezzo){
    	if( !(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) ) {	//se l'attrezzo non è presente
    		System.out.println("Attrezzo non presente nella stanza!");
    		return false;
    	}
    	//l'attrezzo è presente nella stanza
    	Stanza corrente = this.partita.getStanzaCorrente();
    	Attrezzo daPrendere = corrente.getAttrezzo(nomeAttrezzo);
    	Borsa borsaGiocatore = this.partita.getBorsaGiocatore();
		
    	//i metodi addAttrezzo di Borsa e removeAttrezzo di Stanza effettuano l'operazione
    	//richiesta e restutuiscono true se è avvenuta con successo
    	if(borsaGiocatore.addAttrezzo(daPrendere) && corrente.removeAttrezzo(daPrendere)){
    		System.out.println("Attrezzo Preso!");
    		System.out.println(borsaGiocatore.toString());
    		return true;
    	}
    	return false;	
    }
    
    /**
     * Restituisce vero se il giocatore posa l'attrezzo, togliendolo dalla sua borsa
     * e ponendolo nella stanza dove si trova (Stanza corrente)
     * @param nomeattrezzo
     * @return true se il giocatore ha posato l'attrezzo
     */
     private boolean posa(String nomeattrezzo){
    	if(!(this.partita.getBorsaGiocatore().hasAttrezzo(nomeattrezzo))){
    		System.out.println("Attrezzo non presente nella borsa!");
    		return false;
    	}
    	//l'attrezzo è presente nella borsa del giocatore
    	Stanza corrente = this.partita.getStanzaCorrente();
    	Borsa borsaGiocatore = this.partita.getBorsaGiocatore();
    	//Variante: removeAttrezzo di borsa restituisce l'attrezzo restituito
    	Attrezzo daPosare = borsaGiocatore.removeAttrezzo(nomeattrezzo);
    	if(daPosare!=null){
    	   	if(corrente.addAttrezzo(daPosare)){
    	   		System.out.println("Attrezzo Posato!");
    	   		System.out.println(borsaGiocatore.toString());
    	   		return true;
    	   	}
    	}
    	return false;    	
    }
    
	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}
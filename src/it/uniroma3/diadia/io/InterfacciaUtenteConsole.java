package it.uniroma3.diadia.io;

import java.util.Scanner;

/**
 * Classe che si occupa della gestione dell'interazione con l'utente.
 * ( Gestione dell I/O )
 */
public class InterfacciaUtenteConsole implements InterfacciaUtente {

	@Override
	public void mostraMessaggio(String messaggio) {			/* INPUT: System.in */
		System.out.println(messaggio);

	}

	@Override
	public String prendiIstruzione() {						/* OUTPUT: System.out */
		String istruzione; 
	    Scanner scannerDiLinee = new Scanner(System.in);		
		istruzione = scannerDiLinee.nextLine();
//		scannerDiLinee.close(); 							//non va chiuso altrimenti non funziona
		return istruzione;
	}

}

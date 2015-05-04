package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Caso particolare di Stanza
 * @see Stanza
 * Stanza Magica è una Stanza in cui dopo N volte che in tale stanza viene posato (aggiunto)
 * un qualsiasi attrezzo dal giocatore, la stanza inizierà a comportarsi “magicamente”
 *
 */
public class StanzaMagica extends Stanza {
	
	private final static int SOGLIA_DEFAULT = 1;
	private int contatoreAttrezziPosati;		//memorizza il numero di attrezzi posati (aggiunti)
	private int sogliaMagica;					//memorizza il numero di attrezzi da posare prima che
												//si attivi il comportamento "magico" della stanza
	
	/*DOPO CHE SI SONO POSATI UN NUMERO DI ATTREZZI PARI A SOGLIA MAGICA LA MAGIA SI ATTIVA 
	E SUL PROSSIMO ATTREZZO POSATO SE NE VEDE L'EFFETTO*/
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_DEFAULT);
	}
	
	
	
	public int getSogliaMagica() {
		return this.sogliaMagica;
	}

	public void setSogliaMagica(int sogliaMagica) {
		this.sogliaMagica = sogliaMagica;
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoDoppio = attrezzo.getPeso()*2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoDoppio);
		return attrezzo;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati==this.getSogliaMagica())
			System.out.println("COMPORTAMENTO MAGICO DELLA STANZA "+ this.getNome() +" ATTIVATO!");
		
		if (magiaAttivata()) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			System.out.println("Magia Attivata su: "+attrezzo.getNome());
		}
		return super.addAttrezzo(attrezzo);		//INVOCA IL METODO ADDATTREZZO DELLA CLASSE BASE STANZA
	}											//CON PARAMETRO ATTREZZO EVENTUALMENTE MODIFICATO
	
	/**
	 * Metodo che verifica se il comportamento magico è stato attivato in questa StanzaMagica
	 * @return true se il comportamento magico è attivo
	 */
	private boolean magiaAttivata() {
		return this.contatoreAttrezziPosati > this.sogliaMagica;
	}
	
	
}

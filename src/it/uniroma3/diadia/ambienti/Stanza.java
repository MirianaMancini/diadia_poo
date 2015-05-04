package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J. Barnes)
 * @see Attrezzo
 * @version 0.1
*/

public class Stanza {
	private static final int NUMERO_MASSIMO_DIREZIONI = 4;
	private static final int NUMERO_MASSIMO_ATTREZZI = 10;
	private String nome;
    protected Attrezzo[] attrezzi;		//campo modificato a protected
    protected int numeroAttrezzi;		//campo modificato a protected
    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
	private String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
        	}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni[numeroStanzeAdiacenti] = direzione;
    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
    		    this.numeroStanzeAdiacenti++;
    		}
    }
    
    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     * @return stanza adiacente
     */
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;
	}

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }
    
    /**
     * Metodo Privato che ci indica la posizione di un attrezzo nell'array attrezzi
     */
    private int trovaIndiceAttrezzo(String nomeAttrezzo) {
    	for(int i = 0; i < this.numeroAttrezzi; i++){
    		if(this.attrezzi[i].getNome().equals(nomeAttrezzo))
    			return i;
     	}
    	return -1;
    }
    
    /**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		int indiceAttrezzoCercato = this.trovaIndiceAttrezzo(nomeAttrezzo);
		if(indiceAttrezzoCercato != -1)			//attrezzo trovato
			attrezzoCercato = this.attrezzi[indiceAttrezzoCercato];
		return attrezzoCercato;
	}	
	

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
//		int indiceAttrezzoCercato = this.trovaIndiceAttrezzo(nomeAttrezzo);
//		return indiceAttrezzoCercato != -1;
		return (this.trovaIndiceAttrezzo(nomeAttrezzo))!=-1;
	}

	
    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	System.out.println("Stanza piena! Non è possibile aggiungere attrezzi");
        	return false;
        }
    }

   	
	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		int indiceAttrezzoDaRimuovere = this.trovaIndiceAttrezzo(attrezzo.getNome());
		if(indiceAttrezzoDaRimuovere!=-1){					//elemento presente
			for (int i = 0; i < this.numeroAttrezzi; i++)
				if (attrezzo.equals(this.attrezzi[i])) {	//abbiamo trovato l'attrezzo da eliminare
					for (i++; i < this.numeroAttrezzi; i++)
						this.attrezzi[i-1] = this.attrezzi[i];

					this.numeroAttrezzi--;
					this.attrezzi[this.numeroAttrezzi] = null;
					return true;
				}
		}
        return false;
	}
	
//  L'ORDINE DEGLI ATTREZZI NON E' SIGNIFICATIVO PER CUI E' POSSIBILE ELIMINARE L'ELEMENTO
//  SENZA SCALARE TUTTI GLI ELEMENTI SPOSTARE L'UTLIMO ELEMENTO NELLA POSIZIONE DI QUELLO ELIMINATO
//
//    public boolean removeAttrezzo(Attrezzo attrezzo) {
//    	int indiceAttrezzoDaRimuovere = this.trovaIndiceAttrezzo(attrezzo.getNome());
//    	if(indiceAttrezzoDaRimuovere==-1)
//    		return false;				//elemento non presente
//    	this.attrezzi[indiceAttrezzoDaRimuovere] = this.attrezzi[this.numeroAttrezzi-1];
//    	this.numeroAttrezzi--;
//    	//c
//    	return true;
//    }
	
	
	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }
	
	/**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }
	
	@Override
	/*Due stanza sono uguali se hanno lo stesso nome*/
	public boolean equals(Object o){
		Stanza s = (Stanza) o;		//downcasting
		return this.getNome().equals(s.getNome());
	}
	
	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	String s = new String();
    	s += this.nome;
    	s += "\nUscite: ";
    	for (String direzione : this.direzioni) {
    		if (direzione!=null)
    			s += " " + direzione;
    	}
    	s += "\nAttrezzi nella stanza: ";
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null)						//IMPORTANTE
    		    s += attrezzo.toString()+" ";
    	}
    	return s;
    }
}
package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J. Barnes)
 * @see Attrezzo
 * @version 0.1
*/

/*NON posso esistere due oggetti Attrezzo con lo stesso nome in stanze dello dello stesso Labirinto*/

public class Stanza {
	private String nome;
	protected List<Attrezzo> attrezzi;			
	private Map<String, Stanza> stanzeAdiacenti;	//mappa che associa alle direzioni le stanze adicenti			
			//OK: il tipo formale della chiave è String che per default implementa equals e hashCode
    private AbstractPersonaggio personaggio;
	
	
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<String, Stanza>();
        this.attrezzi = new ArrayList<Attrezzo>(10);
        this.personaggio = null;
    }
    
     
    /**
     * Restituisce il nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }
    
    public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public boolean hasPersonaggio() {
		return this.personaggio!=null;
	}
	
	
	  /**
     * @return il numero di attrezzzi presenti nella stanza
     */
    public int numeroAttrezzi() {
    	return this.attrezzi.size();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	this.stanzeAdiacenti.put(direzione, stanza);
    }
    
    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     * @return stanza adiacente
     */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
	public Attrezzo[] getAttrezzi() {
		Attrezzo[] risultato = new Attrezzo[this.attrezzi.size()];	//creo array vuoto
		risultato = this.attrezzi.toArray(risultato);				//toArray lo inizializza
		return risultato;
		
		//OPPURE		return this.attrezzi.toArray(new Attrezzo[0]);
	}
	


    
    /**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = new Attrezzo(nomeAttrezzo, -1);	//oggettifico la Stringa nomeAttrezzo
		int index = this.attrezzi.indexOf(attrezzoCercato);
		
		return (index==-1 ? null : this.attrezzi.get(index));
	}

	
    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	
    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.hasAttrezzo(attrezzo.getNome()))
    		return false;							//non permette l'inserimento di attrezzi duplicati
    	return this.attrezzi.add(attrezzo);
    }

   	
	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */ 
    public boolean removeAttrezzo(Attrezzo attrezzo) {
    	return this.attrezzi.remove(attrezzo);
    }

	
	public String[] getDirezioni() {
		Set<String> insieme_direzioni = this.stanzeAdiacenti.keySet();
		return insieme_direzioni.toArray(new String[0]);
    }
	
	/**
	 * Restituisce a partire da questa stanza, la stanza con meno attrezzi tra quelle Adiacenti
	 */
	public Stanza getStanzaAdicenteConMenoAttrezzi() {
		Stanza stanzaConMenoAttrezzi = null;
		int min = 100; 
		for(String direzione : this.getDirezioni()){
			if(this.getStanzaAdiacente(direzione).numeroAttrezzi() < min) {
				min = this.getStanzaAdiacente(direzione).numeroAttrezzi();
				stanzaConMenoAttrezzi = this.getStanzaAdiacente(direzione);
			}
		}
		return stanzaConMenoAttrezzi;
	}
	
	/**
	 * Restituisce a partire da questa stanza, la stanza con più attrezzi tra quelle Adiacenti
	 */
	public Stanza getStanzaAdicenteConPiuAttrezzi() {
		Stanza stanzaConPiuAttrezzi = null;
		int max = 0; 
		for(String direzione : this.getDirezioni()){
			if(this.getStanzaAdiacente(direzione).numeroAttrezzi() > max) {
				max = this.getStanzaAdiacente(direzione).numeroAttrezzi();
				stanzaConPiuAttrezzi = this.getStanzaAdiacente(direzione);
			}
		}
		return stanzaConPiuAttrezzi;
	}
	
	
	
	/**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }
	

	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
	@Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append(this.nome);
    	s.append("\nUscite: ");
    	//Set<String> direzioni = this.stanzeAdiacenti.keySet();	//non si può iterare una mappa iteriamo l'insieme delle sue chiavi
    	for(String direzione : this.getDirezioni()){						
    		if(direzione!=null)
    			s.append(" " + direzione);	
    	}
    	s.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.getAttrezzi()) {		//for-each vale anche per le collezioni
    		if(attrezzo!=null)							//IMPORTANTE
    		    s.append(attrezzo.toString()+" ");
    	}
    	if(this.personaggio!=null){
    		s.append("\nPersonaggio nella stanza: ");
    		s.append(this.personaggio.toString());
    	}
    	return s.toString();
    }
	
	@Override
	public boolean equals(Object o){
		Stanza s = (Stanza) o;	
		return this.getNome().equals(s.getNome());		/*Due stanza sono uguali se hanno lo stesso nome*/
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
	
}
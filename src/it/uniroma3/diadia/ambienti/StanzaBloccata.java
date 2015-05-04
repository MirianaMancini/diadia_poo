package it.uniroma3.diadia.ambienti;


/**
 * Caso particolare di Stanza
 * @see Stanza
 * Stanza Bloccata è una Stanza in cui una delle direzioni della stanza non può essere seguita
 * a meno che nella stanza non sia presente un oggetto con un nome particolare
 */
public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;		//il nome della direzione bloccata
	private String nomeAttrezzoChiave;  	//il nome dell'attrezzo che consente 
											//di sbloccare la direzione bloccata
	public StanzaBloccata(String nome, String direzione, String nomeChiave) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.nomeAttrezzoChiave = nomeChiave;
	}
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	public String getNomeAttrezzoChiave() {
		return this.nomeAttrezzoChiave;
	}
	public void setDirezioneBloccata(String direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}
	public void setNomeAttrezzoChiave(String nomeAttrezzoChiave) {
		this.nomeAttrezzoChiave = nomeAttrezzoChiave;
	}

	/**
     * Restituisce la stanza adiacente nella direzione specificata se la direzione NON è bloccata
     * @param direzione
     * @return stanza adiacente, altrimenti se la direzione è bloccata ritorna la stanza corrente this
     */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.getDirezioneBloccata().equals(direzione)){
			if (!this.hasAttrezzo(this.nomeAttrezzoChiave)) {	 //se non è presente l'attrezzo sbloccante
				System.out.println("Direzione Bloccata!");
				return this;			   //	<-------- ritorna questa stanza che è quella corrente
			}
		}
		Stanza stanza = super.getStanzaAdiacente(direzione);
		return stanza;
	}
	
	@Override
	public String getDescrizione() {
		String s = new String();
    	s += super.getDescrizione();
    	s += "\nDirezione bloccata: " + this.getDirezioneBloccata();
    	    	
    	if(this.hasAttrezzo(nomeAttrezzoChiave))
    		s += "\n"+this.getNomeAttrezzoChiave()+" presente nella stanza. Puoi andare nella direzione bloccata";
    	else
    		s += "\nCerca l'oggetto " + this.getNomeAttrezzoChiave() + " e posalo in questa stanza!";
    	return s;
	}
	
}

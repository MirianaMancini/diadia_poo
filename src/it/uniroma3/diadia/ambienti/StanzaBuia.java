package it.uniroma3.diadia.ambienti;

/**
 * Caso particolare di Stanza:
 * @see Stanza
 * Stanza Buia è una Stanza in cui il metodo getDescrizione() ritorna la stringa "qui c'è un buio pesto",
 * se nella stanza non è presente un attrezzo con un nome particolare 
 */
public class StanzaBuia extends Stanza {
	private String nomeAttrezzoPerVedere;		//il nome dell'attrezzo che consente di avere 
												//la descrizione completa della stanza

	public StanzaBuia(String nome, String nomeAttrezzo){
		super(nome);
		this.nomeAttrezzoPerVedere = nomeAttrezzo;
	}

		
	public String getNomeAttrezzoPerVedere() {
		return nomeAttrezzoPerVedere;
	}

	public void setNomeAttrezzoPerVedere(String nomeAttrezzoPerVedere) {
		this.nomeAttrezzoPerVedere = nomeAttrezzoPerVedere;
	}

	@Override
	public String getDescrizione() {
		String s = new String();
		if(!this.hasAttrezzo(nomeAttrezzoPerVedere)){		//se non è presente
			s += this.getNome()+"\nQui c'è un buio pesto!";
			s += "\nPer vedere cerca "+this.getNomeAttrezzoPerVedere()+" e posalo in questa stanza!";
		}
		else
			s += super.getDescrizione();
		return s;
	}
	
}

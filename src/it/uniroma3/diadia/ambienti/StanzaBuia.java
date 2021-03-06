package it.uniroma3.diadia.ambienti;

/**
 * Caso particolare di Stanza:
 * @see Stanza
 * Stanza Buia � una Stanza in cui il metodo getDescrizione() ritorna la stringa "qui c'� un buio pesto",
 * se nella stanza non � presente un attrezzo con un nome particolare 
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
		StringBuilder s = new StringBuilder();
		if(!this.hasAttrezzo(nomeAttrezzoPerVedere)){		//se non � presente
			s.append(this.getNome()+"\nQui c'� un buio pesto!");
			s.append("\nPer vedere cerca "+this.getNomeAttrezzoPerVedere()+" e posalo in questa stanza!");
		}
		else
			s.append(super.getDescrizione());
		return s.toString();
	}
	
}

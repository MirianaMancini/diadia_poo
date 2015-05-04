package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaMagicaProtected extends Stanza {
	private final static int SOGLIA_DEFAULT = 3;
	private int contatoreAttrezziPosati;		
	private int sogliaMagica;		
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	public StanzaMagicaProtected(String nome) {
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
			System.out.println("COMPORTAMENTO MAGICO DELLA STANZA ATTIVATO!");
		
		if (magiaAttivata()) {
			attrezzo = this.modificaAttrezzo(attrezzo);
			System.out.println("Magia Attivata su: "+attrezzo.getNome());
		}
		this.attrezzi.add(attrezzo);		//in stanzaProtected abbiamo dovuto introdurre questa modifica 
											//dopo aver modificato stanza con List<Attrezzo>
		return true;
	}
	
	/**
	 * Metodo che verifica se il comportamento magico è stato attivato in questa StanzaMagica
	 * @return true se il comportamento magico è attivo
	 */
	private boolean magiaAttivata() {
		return this.contatoreAttrezziPosati > this.sogliaMagica;
	}
	
}

package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;			//peso max supportato dalla borsa
	
	/**
	 * Costruttore che imposta tutte la variabili d'istanza in default
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);		
	}
	
	/**
	 * Costruttore che crea una borsa di peso massimo pesoMax
	 */
	public Borsa(int pesoMax) {				
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa
	 * @param attrezzo
	 * @return true se l'attrezzo è stato aggiunto alla borsa
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()){
			System.out.println("Non è possibile prendere l'attrezzo! (la borsa non supporta il suo peso)");
			return false;
		}
		
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * Ritorna il peso corrente della borsa
	 * @return peso corrente della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for(Attrezzo attrezzo : attrezzi){
			if(attrezzo!=null)
				peso += attrezzo.getPeso();
		}
		return peso;
	}
	
	/**
	 * Ritorna il peso massimo sopportabile dalla borsa
	 * @return peso massimo
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Restituisce un attrezzo dalla borsa
	 * @param nomeAttrezzo
	 * @return attrezzo richiesto
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}
	
	/**
	 * Verifica se la borsa è vuota
	 * @return true se la borsa non ha attrezzi
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Verifica se la borsa contiene l'attrezzo richesto
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo è presente nella borsa
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Rimuove un attrezzo dall'array di Attrezzi (ricerca in base al nome) e lo restiuisce
	 * @param nomeAttrezzo
	 * @return Attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a =null;
		
		for(int i=0; i<this.numeroAttrezzi;i++){
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo))	{
				a = this.attrezzi[i];							//è l'attrezzo da rimuovere e restituire
				for(i++; i<this.numeroAttrezzi; i++){			//eliminare l'attrezzo in posizione i
					this.attrezzi[i-1] = this.attrezzi[i];					
				}
				this.numeroAttrezzi--;
				this.attrezzi[numeroAttrezzi] = null;
			}
		}
		return a;
	}
	
	/**
	 * Ritorna una descrizione della borsa
	 * @return la stringa che descrive il contenuto della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}

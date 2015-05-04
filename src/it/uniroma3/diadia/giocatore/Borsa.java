package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> attrezzi;			//MAPPA: array associativo per nome dell'attrezzo
	private int pesoMax;							//peso max supportato dalla borsa
	
	/**
	 * Costruttore che imposta tutte la variabili d'istanza in default
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);		//invoca l'altro costruttore passando
											//come parametro il peso di default
	}
	
	/**
	 * Costruttore che crea una borsa di peso massimo pesoMax
	 */
	public Borsa(int pesoMax) {				
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String,Attrezzo>();		
	}
	
	
	/**
	 * Ritorna il peso corrente della borsa
	 * @return peso corrente della borsa
	 */
	public int getPeso() {
		//Il ciclo non può essere tolto poichè abbiamo la necessità di conoscere tutti gli attrezzi
		int peso = 0;
		//Non è possibile iterare una Mappa
		/*Iteriamo l'insieme delle chiavi e usiamo get per ottenere i valori= Attrezzi)*/
		Set<String> nomiAttrezzi = this.attrezzi.keySet();
		for(String nome : nomiAttrezzi){
			if(nome!=null){
				Attrezzo a = this.attrezzi.get(nome);
				peso += a.getPeso();
			}
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
	 * Ritorna il numero di attrezzi presenti nella Borsa
	 * @return numero attrezzi
	 */
	public int numeroAttrezzi() {
		return this.attrezzi.size();
	}

	
	/**
	 * Verifica se la borsa è vuota
	 * @return true se la borsa non ha attrezzi
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi()==0;	
	}

	
	/**
	 * Restituisce un attrezzo dalla borsa
	 * @param nomeAttrezzoCercato
	 * @return attrezzo richiesto, oppure null se l'attrezzo non è presente nella borsa
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzoCercato) {
		return this.attrezzi.get(nomeAttrezzoCercato);
	}	

	
	/**
	 * Verifica se la borsa contiene l'attrezzo richiesto
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo è presente nella borsa, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return (this.getAttrezzo(nomeAttrezzo) != null);
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
		this.attrezzi.put(attrezzo.getNome(), attrezzo);	//se l'attrezzo già esiste viene aggiornato il suo peso
		return true;
	}
	
	
	/**
	 * Rimuove un attrezzo dall'array di Attrezzi (ricerca in base al nome) e lo restiuisce
	 * @param String nomeAttrezzoDaRimuovere
	 * @return Attrezzo rimosso, oppure null se l'attrezzo da rimuovere non è presente nella borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzoDaRimuovere) {
		return this.attrezzi.remove(nomeAttrezzoDaRimuovere);
	}

	
	/**
	 * Ritorna una descrizione della borsa
	 * @return la stringa che descrive il contenuto della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			List<Attrezzo> lista = this.getContenutoOrdinatoPerPeso();
			for(Attrezzo a : lista){
				if(a!=null)
					s.append(a.toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	/**
	 * Metodo che prende la lista degli attrezzi contenuti nella borsa e la resituisce ordinata per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		Collection<Attrezzo> valori = this.attrezzi.values();
		List<Attrezzo> attrezzi = new ArrayList<>(valori);
		Collections.sort(attrezzi, new ComparatoreAttrezziPerPeso());
		return attrezzi;
	}
	
	
	
	/**
	 * Metodo che restituisce la lista degli attrezzi nella borsa ordinati per nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerNome() {
		Collection<Attrezzo> valori = this.attrezzi.values();
		List<Attrezzo> attrezzi = new ArrayList<>(valori);		//costruttore sovraccarico
		
		Collections.sort(attrezzi, new ComparatoreAttrezziPerNome());
		return attrezzi;
	}
	
	/**
	 * Metodo che restituisce una mappa che associa ad un intero un insieme
	 * (comunque non vuoto) di attrezzi: tutti gli attrezzi nell'insieme 
	 * hanno lo stesso peso ed è pari all'intero che figura come chiave nella mappa
	 * 
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Collection<Attrezzo> valori = this.attrezzi.values();
		List<Attrezzo> lista_attrezzi = new ArrayList<>(valori);  //lista degli attrezzi da riorganizzare
		
		Set<Attrezzo> tmp;									
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();	//Ok: Integer default equals e hashCode
		
		for(Attrezzo attrezzo : lista_attrezzi) {
			if(mappa.containsKey(attrezzo.getPeso())){
				//Set associato a questo peso già esiste - allora me la faccio restituire da get
				tmp = mappa.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				//Set associato a questo peso ancora non esiste
				tmp = new HashSet<Attrezzo>();			//OK: Attrezzo implementa equals e hashCode
				tmp.add(attrezzo);
				mappa.put(attrezzo.getPeso(), tmp);
			}
		}
		return mappa;
	}
}

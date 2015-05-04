package it.uniroma3.diadia.giocatore;


public class Giocatore {
			
	private Borsa borsa;
	
	public Giocatore() {
		this.borsa = new Borsa();			//Costruttore "Default"
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}

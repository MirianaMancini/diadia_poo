package it.uniroma3.diadia.giocatore;


public class Giocatore {
	private int cfu;							
	private Borsa borsa;
	private static int CFU_INIZIALI = 20;		//non può essere modificata
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();			//Costruttore "Default"
	}
	
	/*Gestione Cfu*/
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
}

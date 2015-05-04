package it.uniroma3.diadia.comandi;

import java.util.Scanner;

/**
 * Costruisce i comandi attraverso la tecnica della riflessione.
 * Elimina la fastidiosa fisarmonica
 */
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if(scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();
		if(scannerDiParole.hasNext())
			parametro = scannerDiParole.next();
		
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			nomeClasse += nomeComando.substring(1);
			comando = (Comando) Class.forName(nomeClasse).newInstance();
			comando.setParametro(parametro);
			
			
		} catch (Exception e) {
			comando = new ComandoNonValido();
			comando.setParametro("comando inesistente");
		}
		finally {
			scannerDiParole.close();
		}
		return comando;
			
	}

}

package it.unibs.ingesw;

import it.unibs.ingesw.InputDati;

public class MenuAmpliato {

	final private static String CORNICE = "-----------------------------------------------";
	  final private static String VOCE_USCITA = "0\tEsci";
	  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

	  private String titolo;
	  private String [] voci;
 
	  public MenuAmpliato(String titolo, String[] voci) {
		super();
		this.titolo = titolo;
		this.voci = voci;
	}

	  public int scegli ()
	  {
		stampaMenu();
		return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);	 
	  }
			
	  public void stampaMenu ()
	  {
		System.out.println("\n" + CORNICE);
		System.out.println(titolo);
		System.out.println(CORNICE);
	    for (int i=0; i<voci.length; i++)
		 {
		  System.out.println( (i+1) + "\t" + voci[i]);
		 }
	    System.out.println();
		System.out.println(VOCE_USCITA);
	    System.out.println();
	  }
	
}


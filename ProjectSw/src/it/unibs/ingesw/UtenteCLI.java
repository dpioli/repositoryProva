package it.unibs.ingesw;

public class UtenteCLI {
	private static final String BENVENUTO = "Benvenuto nel Centro Sportivo .......";
	private static final String FINE_PROGRAMMA = "Grazie per averci scelti, alla prossima!";
	
	private static final String TITOLO_UTENTE = "Scegli il tipo di utente";
	private static final String MSG_CONFIGUARTORE = "Configuartore";
	private static final String MSG_FRUITORE = "Fruitore";
	private static final String MSG_USCITA = "Esci";
	
	private static final String [] SCELTA_UTENTE = {
			MSG_CONFIGUARTORE,
			MSG_FRUITORE
	};
	
	final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
	final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
	
	private static final String TITOLO_ACCESSO = "Scegliere";
	private static final String MSG_ACCEDI = "Accedi";
	private static final String MSG_REGISTRATI = "registrati";
	
	private static final String [] SCELTA_ACCESSO = {
			MSG_ACCEDI,
			MSG_REGISTRATI
	};
	
	
	
	public static void main(String[] args) {
		
		System.out.println(BENVENUTO);
		
		MenuAmpliato menuUtente = new MenuAmpliato(TITOLO_UTENTE, SCELTA_UTENTE);
		boolean fine = false;
		
		do {
			int sceltaUtente = menuUtente.scegli();
			fine = eseguiScelta(sceltaUtente);
			
		}while(!fine);
		
		
		
		System.out.println(FINE_PROGRAMMA);
		
	}
	
public static boolean eseguiScelta(int scelta) {
		
		switch (scelta) {
		
		case 0:
			return InputDati.yesOrNo(MSG_USCITA);
			
		case 1:
			
			MenuAmpliato menuAccesso = new MenuAmpliato(TITOLO_ACCESSO, SCELTA_ACCESSO);
			boolean fine = false;
			
			do {
				int sceltaAccesso = menuAccesso.scegli();
				fine = eseguiScelta(sceltaAccesso);
				
			}while(!fine);
			break;
			
		default:
			System.out.println(ERRORE_FUNZIONE);
			System.out.println(MESS_ALTRA_OPZIONE);
			break;
		
		}
			
		
		return false;
	}
	
}	
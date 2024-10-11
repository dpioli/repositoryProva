package it.unibs.ingesw;

public class UtenteCLI {
	private static final String BENVENUTO = "Crea la tua configuazione";
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
	private static final String MSG_REGISTRATI = "Registrati";
	
	private static final String [] SCELTA_ACCESSO = {
			MSG_ACCEDI,
			MSG_REGISTRATI
	};
	private static final String TITOLO_MENU_CONFIGUARTORE = "Scegliere quale azione eseguire";
	private static final String MSG_VISUALIZZARE_GERARCHIE = "Visualizza le gerarchie";
	private static final String MSG_CERARE_GERARCHIA = "Crea una nuova gerarchia";
	private static final String MSG_VISUALIZZARE_COMPRENSORI = "Visualizza i compensori geografici";
	private static final String MSG_AGGIUGERE_COMPRENSORI = "Aggiungere compensori geografici";
	private static final String MSG_VISUALIZZARE_FATTORI = "Visualizza tutti i fattori di conbversione che coinvolgono come prima categoria una categoria foglia assegnata";
	private static final String[] SCELTA_MENU_CONFIGURATORE = {
			MSG_VISUALIZZARE_GERARCHIE,
			MSG_CERARE_GERARCHIA,
			MSG_VISUALIZZARE_COMPRENSORI,
			MSG_AGGIUGERE_COMPRENSORI,
			MSG_VISUALIZZARE_FATTORI
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
			tipoAccessoConfiguratore();
			break;
			
		case 2:
			tipoAccessoFruitore();
			break;
			
		default:
			System.out.println(ERRORE_FUNZIONE);
			System.out.println(MESS_ALTRA_OPZIONE);
			break;
		
		}
			
		
		return false;
	}

	public static void tipoAccessoConfiguratore() {
	
		MenuAmpliato menuAccesso = new MenuAmpliato(TITOLO_ACCESSO, SCELTA_ACCESSO);
		boolean fine = false;
	
		do {
			int sceltaAccesso = menuAccesso.scegli();
			fine = eseguiSceltaTipoAccesso(sceltaAccesso);
			
		}while(!fine);
	
		menuConfiguratore();
		
	}
	
	public static void tipoAccessoFruitore() {
	
	}
	
	public static boolean eseguiSceltaTipoAccesso(int scelta) {
		
		switch (scelta) {
		
		case 0:
			return InputDati.yesOrNo(MSG_USCITA);
			
		case 1:
			return accedi();
			
		case 2:
			return registrati();
			
		default:
			System.out.println(ERRORE_FUNZIONE);
			System.out.println(MESS_ALTRA_OPZIONE);
			break;
		}
		
		return false;
	}

	public static boolean accedi() {
		
		return true;
	
	}
	
	public static boolean registrati() {
		return true;
		
	}

	public static void menuConfiguratore() {
		
		MenuAmpliato menuConfiguartore = new MenuAmpliato(TITOLO_MENU_CONFIGUARTORE, SCELTA_MENU_CONFIGURATORE);
		boolean fine = false;
		
		do {
			int sceltaAzioniConfiguratore = menuConfiguartore.scegli();
			fine = eseguiScelta(sceltaAzioniConfiguratore);
			
		}while(!fine);
	}
	
}	
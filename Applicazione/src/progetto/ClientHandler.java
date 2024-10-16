package progetto;
import java.io.*;
import java.net.Socket;
import java.util.*;


//Gestore di client individuale eseguito su thread separato

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Gerarchia> gerarchie;
    private FattoreConversione fattoriConversione;
    private Map<String, Comprensorio> comprensori;
    
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    
    public ClientHandler(Socket socket, List<Gerarchia> gerarchie, FattoreConversione fattoriConversione) {
        this.clientSocket = socket;
        this.gerarchie = gerarchie;
        this.fattoriConversione = fattoriConversione;
    }
    
    public ClientHandler(Socket socket, List<Gerarchia> gerarchie, FattoreConversione fattoriConversione, Map<String, Comprensorio> comprensori) {
        this.clientSocket = socket;
        this.gerarchie = gerarchie;
        this.fattoriConversione = fattoriConversione;
        this.comprensori = comprensori;
    }


    @Override
    public void run() {
        try {
            // Stream per ricevere dati dal client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Stream per inviare dati al client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Dialogo con il client
            String messageFromClient;
            while ((messageFromClient = in.readLine()) != null) {
                System.out.println("Messaggio ricevuto dal client: " + messageFromClient);

                // Gestisce il messaggio in base al tipo di client
                if (messageFromClient.startsWith("Configuratore")) {
                    // Gestione specifica per il Configuratore
                    out.println("Configurazione ricevuta e applicata.");
                } else if (messageFromClient.startsWith("Fruitore")) {
                    // Gestione specifica per il Fruitore
                    out.println("Dati aggiornati inviati al Fruitore.");
            	} else {
            		out.println("Messaggio non riconosciuto.");
            	}
            }
          
            String richiesta = in.readLine();

            if (richiesta.startsWith("Configuratore")) {
                // Riceve le configurazioni dal configuratore
                ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
                gerarchie = (List<Gerarchia>) objectInput.readObject();
                fattoriConversione = (FattoreConversione) objectInput.readObject();
                objectInput.close();
                System.out.println("Configurazioni e fattori di conversione ricevuti dal Configuratore.");

            } else if (richiesta.startsWith("Fruitore")) {
            	/* metodo per verifica comprensorio
            	Estrae le informazioni dalla richiesta
                String[] partiRichiesta = richiesta.split(" ");
                String categoriaRichiesta = partiRichiesta[4];
                String comprensorioRichiesto = partiRichiesta[7]; // Estrai il comprensorio
				*/
                
                // Estrarre il nome della categoria foglia dalla richiesta
                String categoriaRichiesta = richiesta.substring(richiesta.indexOf("per") + 4);
                boolean categoriaTrovata = false;

                // Controlla se la categoria foglia esiste nelle gerarchie
                for (Gerarchia gerarchia : gerarchie) {
                    if (trovaCategoriaFoglia(gerarchia.getRadice(), categoriaRichiesta)) {
                        categoriaTrovata = true;
                        break;
                    }
                }

                if (categoriaTrovata) {
                    out.println("Scambio possibile per la categoria " + categoriaRichiesta);
                } else {
                    out.println("Categoria foglia " + categoriaRichiesta + " non trovata.");
                }
                /*
             	// Verifica se il comprensorio esiste
                if (categoriaTrovata && comprensori.containsKey(comprensorioRichiesto)) {
                    out.println("Scambio possibile per la categoria " + categoriaRichiesta + " nel comprensorio " + comprensorioRichiesto);
                } else if (!categoriaTrovata) {
                    out.println("Categoria foglia " + categoriaRichiesta + " non trovata.");
                } else {
                    out.println("Comprensorio " + comprensorioRichiesto + " non trovato.");
                }
                */

                out.close();
            } else if (richiesta.startsWith("Conversione")) {
                //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Estrazione delle categorie
                String[] partiRichiesta = richiesta.split(" ");
                String categoria1 = partiRichiesta[3];
                String categoria2 = partiRichiesta[5];

                // Ottieni il fattore di conversione
                double fattore = fattoriConversione.getFattore(categoria1, categoria2);
                if (fattore != -1) {
                    out.println("Il fattore di conversione tra " + categoria1 + " e " + categoria2 + " è " + fattore);
                } else {
                    out.println("Fattore di conversione non trovato tra " + categoria1 + " e " + categoria2);
                }

                out.close();
            } else if (richiesta.startsWith("Comprensorio")) {
                ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
                Comprensorio comprensorio = (Comprensorio) objectInput.readObject();
                comprensori.put(comprensorio.getNome(), comprensorio);
                objectInput.close();
                System.out.println("Comprensorio " + comprensorio.getNome() + " ricevuto dal Configuratore.");
            }


            // Chiude le connessioni
            in.close();
            out.close();
            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
 // Metodo ricorsivo per trovare una categoria foglia
    private boolean trovaCategoriaFoglia(Categoria categoria, String nomeFoglia) {
        if (categoria.isFoglia() && categoria.getNome().equals(nomeFoglia)) {
            return true;
        }
        if (!categoria.isFoglia()) {
            CategoriaNonFoglia categoriaNonFoglia = (CategoriaNonFoglia) categoria;
            for (Categoria sottocategoria : categoriaNonFoglia.getSottocategorie().values()) {
                if (trovaCategoriaFoglia(sottocategoria, nomeFoglia)) {
                    return true;
                }
            }
        }
        return false;
	}
}

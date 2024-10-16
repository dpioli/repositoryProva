package progetto;
import java.io.*;
import java.net.*;
import java.util.*;

public class Configuratore {
	
	private List<Gerarchia> gerarchie = new ArrayList<>();
    private FattoreConversione fattoriConversione = new FattoreConversione();
	
    public static void main(String[] args) {
        try {
            // Connessione al server sulla porta 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Configuratore connesso al server.");

            // Stream per inviare messaggi al server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Stream per ricevere messaggi dal server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Invia un messaggio di configurazione al server
            out.println("Configuratore: Imposta parametro A = 42");

            // Legge la risposta del server
            String response = in.readLine();
            System.out.println("Risposta dal server: " + response);

            // Chiude le connessioni
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void creaGerarchia() {
        // Esempio di creazione della gerarchia
        CategoriaNonFoglia lezioniMusica = new CategoriaNonFoglia("Lezioni di musica", "tipo");
        lezioniMusica.aggiungiSottocategoria("teoria", new CategoriaFoglia("Lezioni di teoria musicale"));
        lezioniMusica.aggiungiSottocategoria("strumento", new CategoriaNonFoglia("Lezioni di strumento", "livello"));

        Gerarchia gerarchiaMusica = new Gerarchia(lezioniMusica);
        gerarchie.add(gerarchiaMusica);
    }

    public void inviaConfigurazioniAlServer() {
        try {
            Socket socket = new Socket("localhost", 1234);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // Invia le gerarchie al server
            out.writeObject(gerarchie);

            // Invia i fattori di conversione
            out.writeObject(fattoriConversione);

            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void definisciComprensorio(String nomeComprensorio, List<String> comuni) {
        Comprensorio comprensorio = new Comprensorio(nomeComprensorio);
        for (String comune : comuni) {
            comprensorio.aggiungiComune(comune);
        }
        
        inviaComprensoriAlServer(comprensorio);
    }

    public void inviaComprensoriAlServer(Comprensorio comprensorio) {
        try {
            Socket socket = new Socket("localhost", 1234);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // Invia il comprensorio al server
            out.writeObject(comprensorio);

            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

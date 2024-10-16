package progetto;
import java.io.*;
import java.net.*;

public class Fruitore {
	
	private String nome;
    private Comprensorio comprensorio;

    public Fruitore(String nome, Comprensorio comprensorio) {
        this.nome = nome;
        this.comprensorio = comprensorio;
    }
	
    public static void main(String[] args) {
        try {
            // Connessione al server sulla porta 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Fruitore connesso al server.");

            // Stream per inviare messaggi al server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Stream per ricevere messaggi dal server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Invia una richiesta di dati al server
            out.println("Fruitore: Richiede dati aggiornati.");

            // Legge la risposta dal server
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
 
    public void richiediScambio(String categoriaFoglia) {
        try {
            Socket socket = new Socket("localhost", 1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Invia richiesta al server per verificare lo scambio
            //out.println("Fruitore: Richiesta scambio per " + categoriaFoglia);
            out.println("Fruitore: Richiesta scambio per " + categoriaFoglia + " nel comprensorio " + comprensorio.getNome());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String risposta = in.readLine();
            System.out.println("Risposta dal server: " + risposta);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void richiediConversione(String categoria1, String categoria2) {
        try {
            Socket socket = new Socket("localhost", 1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Invia richiesta di conversione
            out.println("Fruitore: Conversione da " + categoria1 + " a " + categoria2);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String risposta = in.readLine();
            System.out.println("Risposta dal server: " + risposta);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

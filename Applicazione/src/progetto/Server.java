package progetto;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private List<Gerarchia> gerarchie = new ArrayList<>();
	private FattoreConversione fattoriConversione = new FattoreConversione();
	 private Map<String, Comprensorio> comprensori = new HashMap<>(); // Mappa dei comprensori
	
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    
    public void start() {
    	try {
            // Crea un ServerSocket che ascolta sulla porta 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server in attesa di connessioni...");

            // Il server continua ad accettare nuovi client
            while (true) {
                // Accetta un nuovo client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo client connesso!");

                // Crea un nuovo thread per gestire il client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                 // new Thread(clientHandler).start(); Gestisce solo l'accesso di nuovi client
                new Thread(new ClientHandler(clientSocket, gerarchie, fattoriConversione, comprensori)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


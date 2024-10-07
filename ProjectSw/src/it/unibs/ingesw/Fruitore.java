package it.unibs.ingesw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fruitore extends Utente{
	
		public static void main(String[] args) throws Exception {
			String host = "local host";
			int port = 1234;
			
			try {
				Socket utente = new Socket(host, port); //--> ho creato automaticamente una connessione con il server
				System.out.println("Server connesso : /n ");
				
				Fruitore fruitore = new Fruitore();
				fruitore.clientToServer(utente);
				fruitore.serverToClient(utente);
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		@Override
		public void clientToServer(Socket client) {
			try (
					PrintWriter out = new PrintWriter(client.getOutputStream());)
			{

				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				
				String request;
				while ((request = stdin.readLine()) != null) {
					out.println(request);
					out.flush(); 
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		@Override
		public void serverToClient(Socket client) {
			try (
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));	)
			{
				
				System.out.println("Server connesso : /n ");
				String request;
				while ((request = in.readLine()) != null) {
					String response = in.readLine();
					System.out.printf("risposta : %s\n", response);
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

	}

package it.unibs.ingesw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Configuratore extends Utente {

		public static void main(String[] args) {
			String host = "local host";
			int port = 1234;
			
			try {
				Socket utente = new Socket(host, port); 
				System.out.println("Server connesso : /n ");
				
				Configuratore configuratore = new Configuratore();
				configuratore.clientToServer(utente);
				configuratore.serverToClient(utente);
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}//VOGLIO CHE DUE METODI DISTINTI GESTISCANO I DUE FLUSSI
		
		public void clientToServer(Socket client) {
			try (PrintWriter out = new PrintWriter(client.getOutputStream())) {

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

	/*	    public void modificaConfigurazione(String xmlPath) throws SAXException, IOException, ParserConfigurationException {
		        ConfigurazioneComprensori config = new ConfigurazioneComprensori();
		        Document document = (Document) config.costruisciDocumento(xmlPath);
		    //    config.setDocument(document);

		        // Perform modifications on the document using config methods
		        config.aggiungiComprensorio("Nuovo Comprensorio");
		        config.rimuoviComprensorio("Vecchio Comprensorio");

		        // Save the modified document (implementation not shown here)
		    }*/

}

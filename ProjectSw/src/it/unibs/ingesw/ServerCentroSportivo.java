package it.unibs.ingesw;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerCentroSportivo {
		public static void main(String[] args) throws Exception {
			int port = 1234;
			System.out.println("Avvio server . . . ");
			
			try {
				ServerSocket server = new ServerSocket(port);	
				
				while(true) {
					System.out.println("In attesa che un client si colleghi ...");
					Socket cliente = server.accept();	
					ProtocolloServer protocollo = new ProtocolloServer(cliente);
					protocollo.run();
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

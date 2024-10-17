package it.unibs.ingesw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ProtocolloServer implements Runnable {
	
	private ArrayList<ProtocolloServer> listaUtenti = new ArrayList<>();
	public ArrayList<ComprensorioGeografico> compensorioGeografico= new ArrayList<>();
	private ArrayList<Categoria> categorie = new ArrayList<>();
	//private ArrayList<Scambio> listaUtenti = new ArrayList<>();
	private Socket cliente;
	private BufferedReader in;
	private PrintWriter out;
	
	public ProtocolloServer(Socket cliente) {
		this.cliente = cliente;
		listaUtenti.add(this);
	}
	
	public void inviaMessaggio(String msg) {
			out.println(msg);
	}
	
	public void inviaMessaggioATutti(String msg) {
		for(ProtocolloServer b : listaUtenti)
			out.println(msg);
	}

	@Override
	public void run() {
		try {
			out = new PrintWriter(cliente.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			System.out.printf("Utente connesso : %s , %s\n", cliente.getInetAddress()
				, cliente.getPort());
		
			String richiesta;
		
		while((richiesta = in.readLine()) != null){	
			System.out.printf("richiesta : %s\n", richiesta);
			//cose da fare
			//
		}
		
		} catch ( Exception exc) {
			
		}
	}
}

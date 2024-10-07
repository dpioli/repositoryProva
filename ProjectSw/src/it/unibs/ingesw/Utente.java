package it.unibs.ingesw;

import java.net.Socket;

public abstract class Utente implements Runnable {
	public String nome;
	private String psw;
	public abstract void clientToServer(Socket client);
	public abstract void serverToClient(Socket client);
//	public boolean login(String nome, String psw) {};
	public void logout() {};
}

package it.unibs.ingesw;

import java.util.ArrayList;

public class Categoria {
	public String nome;
	public String campoCaratteristico;
	public ArrayList<Categoria> categorie = new ArrayList<>();
	
	public Categoria(String nome, String campoCaratteristico) {
		this.nome = nome;
		this.campoCaratteristico = "0";
	}
	
	public void aggiungiCategoria(Categoria nuova) {
		//bla bla controlli e cose
		categorie.add(nuova);
		
	}
	
	
}

package it.unibs.ingesw;

public abstract class Categoria {
	
    protected String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
	
}

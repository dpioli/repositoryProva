package it.unibs.ingesw;

public class Gerarchia {
    private String nomeRadice;
    private Categoria radice;

    public Gerarchia(String nomeRadice, Categoria radice) {
        this.nomeRadice = nomeRadice;
        this.radice = radice;
    }

    public String getNomeRadice() {
        return nomeRadice;
    }

    public Categoria getRadice() {
        return radice;
    }
}
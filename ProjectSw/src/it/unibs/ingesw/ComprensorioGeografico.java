package it.unibs.ingesw;

import java.util.*;

public class ComprensorioGeografico {
	private String nome;
    private List<Comune> comuni;

    public ComprensorioGeografico(String nome) {
        this.nome = nome;
        this.comuni = new ArrayList<>();
    }

    public void aggiungiComune(Comune comune) {
        comuni.add(comune);
    }

    public boolean contieneComune(Comune comune) {
        return comuni.contains(comune);
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Comune> getComuni() {
        return comuni;
    }
    
    public void setComuni(List<Comune> comuni) {
        this.comuni = comuni;
    }

}

package it.unibs.ingesw;
import java.util.*;

public class Comprensorio {
	
	
	private String nome;
    private Set<String> comuni;

    public Comprensorio(String nome, Set<String> comuni) {
        this.nome = nome;
        this.comuni = comuni;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getComuni() {
        return comuni;
    }

    public boolean contieneComune(String comune) {
        return comuni.contains(comune);
    }
	
}

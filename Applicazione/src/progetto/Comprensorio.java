package progetto;
import java.util.*;

public class Comprensorio {
    private String nome;
    private Set<String> comuni;

    public Comprensorio(String nome) {
        this.nome = nome;
        this.comuni = new HashSet<>();
    }

    public void aggiungiComune(String comune) {
        comuni.add(comune);
    }

    public boolean contieneComune(String comune) {
        return comuni.contains(comune);
    }

    public String getNome() {
        return nome;
    }
}

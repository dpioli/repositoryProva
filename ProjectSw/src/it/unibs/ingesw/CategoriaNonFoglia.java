package it.unibs.ingesw;
import java.util.*;

public class CategoriaNonFoglia extends Categoria {
    private String nomeCampo;
    private Map<String, Categoria> sottoCategorie;

    public CategoriaNonFoglia(String nome, String nomeCampo) {
        super(nome);
        this.nomeCampo = nomeCampo;
        this.sottoCategorie = new HashMap<>();
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public Map<String, Categoria> getSottoCategorie() {
        return sottoCategorie;
    }

    public void aggiungiSottoCategoria(String valoreCampo, Categoria categoria) {
        sottoCategorie.put(valoreCampo, categoria);
    }

    public void rimuoviSottoCategoria(String valoreCampo) {
        sottoCategorie.remove(valoreCampo);
    }
}


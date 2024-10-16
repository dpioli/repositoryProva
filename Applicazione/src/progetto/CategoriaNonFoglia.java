package progetto;
import java.util.*;

public class CategoriaNonFoglia extends Categoria {
    private String campoCaratteristico;  // Il campo su cui si articola la categoria
    private Map<String, Categoria> sottocategorie;  // Sottocategorie

    public CategoriaNonFoglia(String nome, String campoCaratteristico) {
        super(nome);
        this.campoCaratteristico = campoCaratteristico;
        this.sottocategorie = new HashMap<>();
    }

    public void aggiungiSottocategoria(String valoreCampo, Categoria sottocategoria) {
        sottocategorie.put(valoreCampo, sottocategoria);
    }

    public Categoria getSottocategoria(String valoreCampo) {
        return sottocategorie.get(valoreCampo);
    }

    @Override
    public boolean isFoglia() {
        return false;
    }

    public String getCampoCaratteristico() {
        return campoCaratteristico;
    }

    public Map<String, Categoria> getSottocategorie() {
        return sottocategorie;
    }
}

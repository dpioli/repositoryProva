package progetto;
import java.util.*;

public class FattoreConversione {
	private Map<String, Map<String, Double>> conversioni; // mappa che contiene fattori di conversione

    public FattoreConversione() {
        this.conversioni = new HashMap<>();
    }

    public void aggiungiConversione(String c1, String c2, double fattore) {
        if (!conversioni.containsKey(c1)) {
            conversioni.put(c1, new HashMap<>());
        }
        conversioni.get(c1).put(c2, fattore);

        // Aggiungi la conversione inversa
        if (!conversioni.containsKey(c2)) {
            conversioni.put(c2, new HashMap<>());
        }
        conversioni.get(c2).put(c1, 1 / fattore);
    }

    public double getFattore(String c1, String c2) {
        if (conversioni.containsKey(c1) && conversioni.get(c1).containsKey(c2)) {
            return conversioni.get(c1).get(c2);
        }
        return -1;  // Fattore di conversione non trovato
    }
}


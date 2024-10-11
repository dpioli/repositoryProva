package it.unibs.ingesw;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GestionePrestazioniOpera {
    private Map<String, Gerarchia> gerarchie;
    private Set<String> nomiCategorieFoglia;
    private Map<String, Comprensorio> comprensori;
    private Map<String, Map<String, Double>> fattoriConversione;
   
    public GestionePrestazioniOpera() {
        gerarchie = new HashMap<>();
        nomiCategorieFoglia = new HashSet<>();
        comprensori = new HashMap<>();
        fattoriConversione = new HashMap<>();
    }

    // Aggiunta di una gerarchia con controllo dell'unicità del nome della radice
    public boolean aggiungiGerarchia(Gerarchia gerarchia) {
        if (gerarchie.containsKey(gerarchia.getNomeRadice())) {
            return false;  // Nome radice già esistente
        }
        gerarchie.put(gerarchia.getNomeRadice(), gerarchia);
        return true;
    }

    // Rimozione di una gerarchia
    public void rimuoviGerarchia(String nomeRadice) {
        Gerarchia gerarchia = gerarchie.remove(nomeRadice);
        if (gerarchia != null) {
            rimuoviCategorieFoglia(gerarchia.getRadice());
        }
    }

    // Rimozione ricorsiva delle categorie foglia da una gerarchia
    private void rimuoviCategorieFoglia(Categoria categoria) {
        if (categoria instanceof CategoriaFoglia) {
            nomiCategorieFoglia.remove(categoria.getNome());
        } else if (categoria instanceof CategoriaNonFoglia) {
            CategoriaNonFoglia nonFoglia = (CategoriaNonFoglia) categoria;
            for (Categoria sottoCategoria : nonFoglia.getSottoCategorie().values()) {
                rimuoviCategorieFoglia(sottoCategoria);
            }
        }
    }

    // Aggiunta di un fattore di conversione tra due categorie foglia
    public boolean aggiungiFattoreConversione(String c1, String c2, double fattore) {
        if (fattore < 0.5 || fattore > 2.0 || c1.equals(c2)) {
            return false;  // Fattore non valido o categorie identiche
        }

        fattoriConversione.putIfAbsent(c1, new HashMap<>());
        fattoriConversione.putIfAbsent(c2, new HashMap<>());

        fattoriConversione.get(c1).put(c2, fattore);
        fattoriConversione.get(c2).put(c1, 1 / fattore);
        return true;
    }

    // Metodo per verificare l'unicità del nome di una categoria foglia
    public boolean aggiungiCategoriaFoglia(String nome) {
        return nomiCategorieFoglia.add(nome);  // Ritorna false se già esistente
    }

    // Aggiunta di un comprensorio geografico
    public boolean aggiungiComprensorio(Comprensorio comprensorio) {
        if (comprensori.containsKey(comprensorio.getNome())) {
            return false;  // Comprensorio già esistente
        }
        comprensori.put(comprensorio.getNome(), comprensorio);
        return true;
    }

    // Verifica se un comune appartiene a un comprensorio
    public boolean comuneInComprensorio(String nomeComprensorio, String comune) {
        Comprensorio comprensorio = comprensori.get(nomeComprensorio);
        return comprensorio != null && comprensorio.contieneComune(comune);
    }

    // Calcolo del fattore di conversione tra due categorie foglia tramite transizione
    public Double calcolaFattoreConversione(String c1, String c2) {
        if (fattoriConversione.containsKey(c1) && fattoriConversione.get(c1).containsKey(c2)) {
            return fattoriConversione.get(c1).get(c2);
        }
        return null;  // Fattore di conversione non disponibile
    }

    // Metodo per salvare le gerarchie su un file
    public void salvaGerarchieSuFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Gerarchia gerarchia : gerarchie.values()) {
                writer.write("Radice: " + gerarchia.getNomeRadice() + "\n");
                salvaCategoria(writer, gerarchia.getRadice(), 1);
                writer.write("\n");
            }
            System.out.println("Salvataggio delle gerarchie completato.");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio delle gerarchie: " + e.getMessage());
        }
    }

    // Metodo ricorsivo per salvare la struttura delle categorie
    private void salvaCategoria(BufferedWriter writer, Categoria categoria, int livello) throws IOException {
        String indentazione = "  ".repeat(livello); // Indenta per rappresentare la gerarchia
        writer.write(indentazione + "Categoria: " + categoria.getNome() + "\n");

        if (categoria instanceof CategoriaNonFoglia) {
            CategoriaNonFoglia nonFoglia = (CategoriaNonFoglia) categoria;
            writer.write(indentazione + "Campo caratteristico: " + nonFoglia.getNomeCampo() + "\n");
            for (Map.Entry<String, Categoria> entry : nonFoglia.getSottoCategorie().entrySet()) {
                writer.write(indentazione + "Valore: " + entry.getKey() + "\n");
                salvaCategoria(writer, entry.getValue(), livello + 1);
            }
        }
    }

    // Metodo per salvare gli scambi di prestazioni d'opera su un file
    public void salvaScambiSuFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Map<String, Double>> entry1 : fattoriConversione.entrySet()) {
                String categoria1 = entry1.getKey();
                for (Map.Entry<String, Double> entry2 : entry1.getValue().entrySet()) {
                    String categoria2 = entry2.getKey();
                    Double fattore = entry2.getValue();
                    writer.write(categoria1 + " -> " + categoria2 + ": fattore = " + fattore + "\n");
                }
            }
            System.out.println("Salvataggio degli scambi completato.");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio degli scambi: " + e.getMessage());
        }
    }
}
package progetto;

public abstract class Categoria {

	protected String nome;  // Nome della categoria
    protected String descrizione;  // Descrizione opzionale

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public abstract boolean isFoglia();
}


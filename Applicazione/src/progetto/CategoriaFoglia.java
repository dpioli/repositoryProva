package progetto;
public class CategoriaFoglia extends Categoria {
    
	public CategoriaFoglia(String nome) {
        super(nome);
    }

    @Override
    public boolean isFoglia() {
        return true;
    }
}
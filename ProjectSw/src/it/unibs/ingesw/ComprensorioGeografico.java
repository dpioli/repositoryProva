package it.unibs.ingesw;

import java.util.*;

public class ComprensorioGeografico {
	
	    private String nome;
	    private Set<Comune> comuni;

	    public ComprensorioGeografico(String nome) {
	        this.nome = nome;
	        this.comuni = new HashSet<>();
	    }

	    public void aggiungiComune(String comune) {
	    	if(!contieneComune(comune))
	    		comuni.add(new Comune(comune));
	    	else 
	    		System.out.println("Comune già presente.");
	    }

		public void rimuoviComune(String comune) {
			if(contieneComune(comune))
				comuni.remove(new Comune(comune));
			else
				System.out.println("il comune che stai cercando di eliminare non esiste.");
	    }

	    public boolean contieneComune(String comune) {
	    	Comune daCercare = new Comune(comune);
	    	return comuni.contains(daCercare);
	    }
	    public Set<Comune> getComuni() {
	    	return comuni;
	    }

	    public String getNome() {
	        return nome;
	    }
}

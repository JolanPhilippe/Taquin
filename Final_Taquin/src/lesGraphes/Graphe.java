package lesGraphes;

import java.util.HashMap;

public abstract class Graphe<E> implements IGraphe<E>{
	protected HashMap<E,E> peres;					
	protected HashMap<E,String> etats; 

	public Graphe(){
		setPeres(new HashMap<E,E>());
		etats = new HashMap <E,String>();
	}

	public HashMap<E,E> getPeres() {
		return peres;
	}

	public void setPeres(HashMap<E, E> hashMap) {
		this.peres = hashMap;
	}
	
	public HashMap<E,String> getEtats() {
		return etats;
	}

	public void setEtats(HashMap<E,String> etats) {
		this.etats = etats;
	}
	
	
}

package lesGraphes;

import java.util.TreeMap;

public abstract class Graphe<E> implements IGraphe<E>{
	protected TreeMap<E,E> peres;					
	protected TreeMap<E,String> etats; 

	public Graphe(){
		setPeres(new TreeMap<E,E>());
		etats = new TreeMap <E,String>();
	}

	public TreeMap<E,E> getPeres() {
		return peres;
	}

	public void setPeres(TreeMap<E,E> peres) {
		this.peres = peres;
	}
	
	public TreeMap<E,String> getEtats() {
		return etats;
	}

	public void setEtats(TreeMap<E,String> etats) {
		this.etats = etats;
	}
	
	
}

package lesGraphes;

import java.util.TreeMap;

public abstract class Graphe<E> implements IGraphe<E>{
	protected TreeMap<E,E> peres;					
	protected TreeMap<E,String> etats; 

	public Graphe(){
		peres  = new TreeMap<E,E>();
		etats = new TreeMap <E,String>();
	}
	
	
}

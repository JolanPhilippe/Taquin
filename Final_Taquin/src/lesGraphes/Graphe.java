package lesGraphes;
import java.util.ArrayList;
import java.util.TreeMap;

public interface Graphe <E> {
	
	public ArrayList<E> getAdjacent (E u);
	public void ajouterArc(E a, E b);
	public Graphe<E> supprimerArc (E a, E b);
	
	public Triplet<TreeMap<E,E>,TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf();
	public Couple<TreeMap<E,E>,TreeMap<E,Integer>> parcoursLarg(E s);
	public String toString();

}


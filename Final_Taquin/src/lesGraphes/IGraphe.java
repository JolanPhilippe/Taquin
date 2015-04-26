package lesGraphes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public interface IGraphe <E> {
	public ArrayList<E> getAdjacent (E u);
	public void ajouterArc(E a, E b);
	public IGraphe<E> supprimerArc (E a, E b);
	
	public Couple<TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf();
	public TreeMap<E,Integer> parcoursLarg(E s);
	public String toString();

}


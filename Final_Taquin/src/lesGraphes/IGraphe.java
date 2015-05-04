package lesGraphes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Outils.Couple;

public interface IGraphe <E> {
	public ArrayList<E> getAdjacent (E u);
	public void ajouterArc(E a, E b);
	public IGraphe<E> supprimerArc (E a, E b);
	
	public Couple<TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf();
	public HashMap<E,Integer> parcoursLarg(E s);
	public String toString();

}


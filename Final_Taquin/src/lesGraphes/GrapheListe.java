package lesGraphes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeMap;

import Outils.Couple;

/** Graphe sous forme de liste de successeur 
 *
 * @param <E> type des elements du graphe
 * 
 * @author Jolan
 */
public class GrapheListe<E> extends Graphe<E> {
	/** Table d'association element -> liste des successeurs*/
	private HashMap<E, ArrayList<E>> graphe;
	
	/** Creer un nouveau GrapheListe
	 * 
	 * @author Jolan
	 */
	public GrapheListe(){
		this.setGraphe(new HashMap<E,	ArrayList<E>>());
	}
	
	/** Ajouter un nouveau sommet au graphe si celui-ci n'y est pas deja
	 * 
	 * @param s le sommet a ajouter
	 * 
	 * @author Jolan
	 */
	public void ajouterSommet(E s){
		if(!graphe.containsKey(s)){
			graphe.put(s, new ArrayList<E>());
		}
		
	}
	
	/** Recupere les sommets voisins d'un element
  	* 
  	* @param u le sommet dont on veut recup les voisins
  	* @return la liste des voisins de u
  	* 
  	* @author Jolan
 	*/
	public ArrayList<E> getAdjacent(E u) {
		return graphe.get(u);
	}

	/** Ajouter un arc au graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 * 
	 * @author Jolan
	 */
	public void ajouterArc(E a, E b) {
		if(!graphe.containsKey(a)) this.ajouterSommet(a);
		ArrayList<E> liste = getAdjacent(a);
		if(!liste.contains(b))
			liste.add(b);
		graphe.put(a, liste);
	}

	/** Supprime un arc du graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 * @return le graphe sans l'arc (a,b)
	 * 
	 * @author Jolan
	 */
	public Graphe<E> supprimerArc(E a, E b) {
		ArrayList<E> liste = getAdjacent(a);
		liste.remove(b);
		return this;
	}
	
	/** Parcours en largeur le graphe a partir d'un sommet source
	 * 
	 * @param s le sommet source
	 * @return  la distance de chaque element avec le sommet source
	 * 
	 * @author Jolan
	 */
	public HashMap<E,Integer> parcoursLarg(E s) {				
		HashMap<E,Integer> dist = new HashMap<E,Integer>();	//dist: tab[1..n] d'entier, distance des sommets du sommet source
		for (E x : getGraphe().keySet()){ /*debut init*/
			peres.put(x, null);
			etats.put(x, "nonAtteint");
			dist.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		etats.put(s, "atteint");
		dist.put(s,0);
		
		LinkedList<E> fifo = new LinkedList<E>(); //fifo : liste des sommets atteint mais non traités
		fifo.add(s); //empiler(s)
		while (!fifo.isEmpty()){
			E u = fifo.poll(); //extraire()
			if (getGraphe().get(u)!=null)
				for (E v : this.getAdjacent(u)){
					if (etats.get(v).equals("nonAtteint")){
						peres.put(v,u);
						dist.put(v, dist.get(u)+1); 
						etats.put(v, "atteint");
						fifo.add(v);
					}
					etats.put(u,"traite");
				}
		}

		return dist;
	}

	/** Parcours en profondeur le graphe a partir d'un sommet source
	 * 
	 * @param s le sommet source
	 * @return un couple de Map: (rencontre du sommet avec la source) (fin traitement du sommet)
	 * 
	 * @author Jolan
	 */
	public Couple<TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf() {
		TreeMap<E,Integer> deb = new TreeMap<E,Integer>();		//deb:  tab[1..n] du temps avant d'atteindre le sommet
		TreeMap<E,Integer> fin = new TreeMap<E,Integer>();		//fin:  tab[1..n] du temps avant le traitement du sommet
		int temps = 0;
		/*debut init*/
		for (E x : getGraphe().keySet()){ 
			peres.put(x, null);
			etats.put(x, "nonAtteint");
			deb.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
			fin.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		/*fini init*/
		
		for (E s : getGraphe().keySet()){
			if (etats.get(s).equals("nonAtteint"))
				temps = Visiter(s, temps, deb, fin);
		}
		return new Couple<TreeMap<E,Integer>,TreeMap<E,Integer>>(deb,fin);
	}
	
	/** Methode recursive auxiliaire au parcours en profondeur
	 * 
	 * @param u l'element a visiter
	 * @param temps le compteur temportel
	 * @param deb la map des temps "debut"
	 * @param fin la map des temps "fin"
	 * 
	 * @return le temps actuel a la fin du sous-programme Visiter
	 * 
	 * @author Jolan
	 */
	public int Visiter(E u, int temps, TreeMap<E, Integer> deb, TreeMap<E, Integer> fin){
		etats.put(u, "atteint");
		temps = temps+1;
		deb.put(u, temps);
		for (E v : getGraphe().get(u)){
			if (etats.get(v).equals("nonAtteint")){
				peres.put(v, u);
				temps = Visiter(v, temps, deb, fin);
			}
		}
		temps = temps +1;
		fin.put(u,temps);
		etats.put(u, "traite");
		return temps;
	}

	/** Creer un affichage pour le graphe
	 * 
	 * @return une chaine de caractere composée de chaque element, et de ses successeurs
	 * 
	 * @author Jolan
	 */
	public String toString(){
		String s ="";
		for (E k: graphe.keySet()){
			s+="sommet:\n"+k+"\nles succ:\n";
			for (E v : graphe.get(k))
				s+=v+"\n";
			s+="\n";
			
		}
		return s;				
	}

	/** Recupere les listes de successeurs des elements
	 *
	 * @return table d'association (element -> liste successeur)
	 * 
	 * @author Jolan
	 */
	public HashMap<E, ArrayList<E>> getGraphe() {
		return graphe;
	}

	/** Fixe la liste de successeurs
	 * 
	 * @param HashMap une table d'association (element -> liste successeur)
	 * 
	 * @author Jolan
	 */
	public void setGraphe(HashMap<E, ArrayList<E>> HashMap) {
		this.graphe = HashMap;
	}


}

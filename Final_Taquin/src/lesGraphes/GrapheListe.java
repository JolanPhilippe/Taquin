package lesGraphes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

public class GrapheListe<E> extends Graphe<E> {
	protected TreeMap<E, ArrayList<E>> graphe;
	
	public GrapheListe(){
		this.graphe= new TreeMap<E, ArrayList<E>>();
	}
	
	public void ajouterSommet(E s){
		graphe.put(s, new ArrayList<E>());
	}
	
	/** Recupere les sommets voisins d'un element
  	* 
  	* @param u le sommet dont on veut recup les voisins
  	* @return la liste des voisins de u
 	*/
	public ArrayList<E> getAdjacent(E u) {
		return graphe.get(u);
	}

	/** Ajouter un arc au graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 */
	public void ajouterArc(E a, E b) {
		ArrayList<E> liste = getAdjacent(a);
		liste.add(b);
	}

	/** Supprime un arc du graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 * @return le graphe sans l'arc (a,b)
	 * 
	 */
	public Graphe<E> supprimerArc(E a, E b) {
		ArrayList<E> liste = getAdjacent(a);
		liste.remove(b);
		return this;
	}
	
	/** Parcours en largeur le graphe a partir d'un sommet source
	 * 
	 * @param s le sommet source
	 * @return un couple de Map: (fst -> Map<> pere) (snd -> Map<> dist)
	 */
	public TreeMap<E,Integer> parcoursLarg(E s) {				
		TreeMap<E,Integer> dist = new TreeMap<E,Integer>();	//dist: tab[1..n] d'entier, distance des sommets du sommet source
		for (E x : graphe.keySet()){ /*debut init*/
			peres.put(x, null);
			etats.put(x, "nonAtteint");
			dist.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		etats.put(s, "atteint");
		dist.put(s, 0); /*fini init*/
		LinkedList<E> fifo = new LinkedList<E>(); //fifo : liste des sommets atteint mais non traités
		fifo.add(s); //empiler(s)
		while (!fifo.isEmpty()){
			E u = fifo.poll(); //extraire()
			for (E v : graphe.get(u)){
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
	 * @return un triplet de Map: (fst -> Map<> pere) (snd -> Map<> deb) (thd -> Map<> fin)
	 */
	public Couple<TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf() {
		TreeMap<E,Integer> deb = new TreeMap<E,Integer>();		//deb:  tab[1..n] du temps avant d'atteindre le sommet
		TreeMap<E,Integer> fin = new TreeMap<E,Integer>();		//fin:  tab[1..n] du temps avant le traitement du sommet
		int temps = 0;
		/*debut init*/
		for (E x : graphe.keySet()){ 
			peres.put(x, null);
			etats.put(x, "nonAtteint");
			deb.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
			fin.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		/*fini init*/
		
		for (E s : graphe.keySet()){
			if (etats.get(s).equals("nonAtteint"))
				temps = Visiter(s, temps, deb, fin);
		}
		return new Couple<TreeMap<E,Integer>,TreeMap<E,Integer>>(deb,fin);
	}
	
	/** Methode recursive auxiliaire au parcours en profondeur
	 * 
	 * @param u l'element a visiter
	 * @param etat la map des etat des sommets
	 * @param temps le compteur temportel
	 * @param deb la map des temps "debut"
	 * @param fin la map des temps "fin"
	 * @param pere la map des peres 
	 * @return le temps actuel a la fin du sous-programme Visiter
	 */
	public int Visiter(E u, int temps, TreeMap<E, Integer> deb, TreeMap<E, Integer> fin){
		etats.put(u, "atteint");
		temps = temps+1;
		deb.put(u, temps);
		for (E v : graphe.get(u)){
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

	public String toString(){
		return graphe.toString();
	}


}

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
		TreeMap<E,E> pere = new TreeMap<E,E>();					//pere: tab[1..n] de sommets
		TreeMap<E,String> etat = new TreeMap <E,String>();		//etat: tab[1..n] de string {"nonAtteint";"atteint";"traite"}
		TreeMap<E,Integer> dist = new TreeMap<E,Integer>();		//dist: tab[1..n] d'entier
		/*debut init*/
		for (E x : graphe.keySet()){ 
			pere.put(x, null);
			etat.put(x, "nonAtteint");
			dist.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		etat.put(s, "atteint");
		dist.put(s, 0);
		/*fini init*/
		LinkedList<E> fifo = new LinkedList<E>(); //fifo : liste des sommets atteint mais non traités
		fifo.add(s); //empiler(s)
		while (!fifo.isEmpty()){
			E u = fifo.poll(); //extraire()
			for (E v : graphe.get(u)){
				if (etat.get(v).equals("nonAtteint")){
					pere.put(v,u);
					dist.put(v, dist.get(u)+1); 
					etat.put(v, "atteint");
					fifo.add(v);
				}
				etat.put(u,"traite");
			}
		}
		return dist;
	}

	/** Parcours en profondeur le graphe a partir d'un sommet source
	 * 
	 * @param s le sommet source
	 * @return un triplet de Map: (fst -> Map<> pere) (snd -> Map<> deb) (thd -> Map<> fin)
	 */
	public Triplet<TreeMap<E,E>,TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf() {
		TreeMap<E,E> pere = new TreeMap<E,E>();					//pere: tab[1..n] de sommets
		TreeMap<E,String> etat = new TreeMap <E,String>();		//etat: tab[1..n] de string {"nonAtteint";"atteint";"traite"}
		TreeMap<E,Integer> deb = new TreeMap<E,Integer>();		//deb:  tab[1..n] du temps avant d'atteindre le sommet
		TreeMap<E,Integer> fin = new TreeMap<E,Integer>();		//fin:  tab[1..n] du temps avant le traitement du sommet
		int temps = 0;
		/*debut init*/
		for (E x : graphe.keySet()){ 
			pere.put(x, null);
			etat.put(x, "nonAtteint");
			deb.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
			fin.put(x, Integer.MAX_VALUE); //MAX_VALUE nous sert d'infini
		}
		/*fini init*/
		for (E s : graphe.keySet()){
			if (etat.get(s).equals("nonAtteint"))
				temps = Visiter(s, etat, temps, deb, fin, pere);
		}
		return new Triplet<TreeMap<E,E>,TreeMap<E,Integer>,TreeMap<E,Integer>>(pere,deb,fin);
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
	public int Visiter(E u, TreeMap<E,String>etat, int temps, TreeMap<E, Integer> deb, TreeMap<E, Integer> fin, TreeMap<E,E> pere){
		etat.put(u, "atteint");
		temps = temps+1;
		deb.put(u, temps);
		for (E v : graphe.get(u)){
			if (etat.get(v).equals("")){
				pere.put(v, u);
				temps = Visiter(v,etat, temps, deb, fin, pere);
			}
		}
		temps = temps +1;
		fin.put(u,temps);
		etat.put(u, "traite");
		return temps;
	}

	public String toString(){
		return graphe.toString();
	}


}

package lesGraphes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Outils.Couple;

/** Interface des graphes
 * 
 * @param <E> le type des elements du graphe
 * 
 * @author Jolan
 *
 */
public interface IGraphe <E> {
	/** Recupere les sommets voisins d'un element
  	* 
  	* @param u le sommet dont on veut recup les voisins
  	* @return la liste des voisins de u
  	* 
  	* @author Jolan
 	*/
	public ArrayList<E> getAdjacent (E u);
	/** Ajouter un nouveau sommet au graphe si celui-ci n'y est pas deja
	 * 
	 * @param u le sommet a ajouter
	 * 
	 * @author Jolan
	 */
	public void ajouterSommet(E u);
	/** Ajouter un arc au graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 * 
	 * @author Jolan
	 */
	public void ajouterArc(E a, E b);
	/** Supprime un arc du graphe
	 * 
	 * @param a sommet depart
	 * @param b sommet arrivé
	 * @return le graphe sans l'arc (a,b)
	 * 
	 * @author Jolan
	 */
	public IGraphe<E> supprimerArc (E a, E b);
	/** Parcours en largeur le graphe a partir d'un sommet source
	 * 
	 * @param s le sommet source
	 * @return  la distance de chaque element avec le sommet source
	 * 
	 * @author Jolan
	 */
	public HashMap<E,Integer> parcoursLarg(E s);
	/** Parcours en profondeur le graphe a partir d'un sommet source
	 * 
	 * @return un couple de Map: (rencontre du sommet avec la source) (fin traitement du sommet)
	 * 
	 * @author Jolan
	 */
	public Couple<TreeMap<E,Integer>,TreeMap<E,Integer>> parcoursProf();
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
	public int Visiter(E u, int temps, TreeMap<E, Integer> deb, TreeMap<E, Integer> fin);
	/** Creer un affichage pour le graphe
	 * 
	 * @return une chaine de caractere composée de chaque element, et de ses successeurs
	 * 
	 * @author Jolan
	 */
	public String toString();

}


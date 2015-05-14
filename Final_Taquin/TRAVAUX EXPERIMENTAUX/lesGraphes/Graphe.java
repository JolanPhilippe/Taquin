package lesGraphes;

import java.util.HashMap;

/** Graphe orienté
 * 
 * @author Jolan
 *
 * @param <E> type des elements du graphe
 */
public abstract class Graphe<E> implements IGraphe<E>{
	/** Map d'association d'un element et de son predecesseur */
	protected HashMap<E,E> peres;
	/** Map d'association d'un element et de son etat de parcours */
	protected HashMap<E,String> etats; 

	/** Creer un nouveau graphe
	 * 
	 * @author Jolan
	 */
	public Graphe(){
		peres = new HashMap<E,E>();
		etats = new HashMap <E,String>();
	}

	/** Recupere les peres des elements du graphe
	 * 
	 * @return table des peres
	 * 
	 * @author Jolan
	 */
	public HashMap<E, E> getPeres() {
		return peres;
	}

	/** Fixe les peres des elements du graphe
	 * 
	 * @param peres une assoction element -> pere deja construite
	 *
	 * @author Jolan
	 */
	public void setPeres(HashMap<E, E> peres) {
		this.peres = peres;
	}

	/** Recupere les etats des elements du graphe
	 * 
	 * @return table des etats
	 * 
	 * @author Jolan
	 */
	public HashMap<E, String> getEtats() {
		return etats;
	}

	/** Fixe les etats des elements du graphe
	 * 
	 * @param etats une assoction element -> etat deja construite
	 * 
	 * @author Jolan
	 */
	public void setEtats(HashMap<E, String> etats) {
		this.etats = etats;
	}

}

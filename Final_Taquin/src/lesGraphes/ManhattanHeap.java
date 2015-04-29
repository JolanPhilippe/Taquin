package lesGraphes;

import java.util.HashMap;

public class ManhattanHeap<E> extends BinaryHeap<E>{
	HashMap<E,Integer> distManhattan;
	

	/** Ajoute un element au tas min
	 * -> Le tas doit conserver ses proprietes de tas min
	 * 
	 * @param e element a ajouter
	 */
	public void add(E e) {
	}

	/** Supprime un element au tas min
	 * -> Le tas doit conserver ses proprietes de tas min
	 * 
	 * @param e element a supprimer
	 */
	public void remove(E e) {
	}

	/** Verifie que les fproprietes du tas min sont respectees
	 * 
	 * @return true si pere(e) > e, false sinon
	 */
	public boolean testValidite() {
		return false;
	}
	
	/** Recupere l'element le plus petit du tas
	 * 
	 * @return la racine du tas min
	 */
	public E getMin(){
		return null;
	}
	
	/** Recupere l'element le plus petit du tas et l'y enleve
	 * 
	 * @return la tete du tas
	 */
	public E extractMin(){
		return null;
	}
	
}

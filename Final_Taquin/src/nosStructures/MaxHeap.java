package nosStructures;


public class MaxHeap<E extends Comparable<E>> extends BinaryHeap<E>{
	
	/** Ajoute un element au tas max
	 * -> Le tas doit conserver ses proprietes de tas max
	 * 
	 * @param e element a ajouter
	 */
	public void add(E e) {
	}

	/** Supprime un element au tas max
	 * -> Le tas doit conserver ses proprietes de tas max
	 * 
	 * @param e element a supprimer
	 */
	public void remove(E e) {
	}

	/** Verifie que les proprietes du tas Max sont respectees
	 * 
	 * @return true si pere(e) > e, false sinon
	 */
	public boolean testValidite() {
		return false;
	}
	
	/** Recupere l'element le plus petit du tas
	 * 
	 * @return la racine du tas Max
	 */
	public E getMax(){
		return null;
	}
	
	/** Recupere l'element le plus petit du tas et l'y enleve
	 * 
	 * @return la tete du tas
	 */
	public E extractMax(){
		return null;
	}
}
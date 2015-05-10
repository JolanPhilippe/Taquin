package nosStructures;

import lesExceptions.ElementInexistantException;

/** Interface de Tas
 * 
 * @param <E> type des elements du tas
 * 
 * @author Jolan
 */
public interface Heap <E> {
	
	/** Donne le nombre d'elements du tas
	 * 
	 * @return un entier correspondant au nombre d'element du tas
	 * 
	 * @author Jolan
	 */
	public int size();
	/** Recupere l'index d'un element
	 * 
	 * @param e l'element dont on veut l'index
	 * @return l'index de l'element
	 * @throws ElementInexistantException si l'element n'est pas dans le tas
	 * 
	 * @author Jolan
	 */
	public int getIndex(E e) throws ElementInexistantException;
	/** Recupere l'element a un indice
	 * 
	 * @param i l'indice dont on veut recuperer l'element
	 * @return l'element a l'indice
	 * @throws ElementInexistantException si l'indice ne correspond a aucun element
	 * 
	 * @author Jolan
	 */
	public E getElem(int i)throws ElementInexistantException;
	/** Recupere le sommet du tas
	 * 
	 * @return l'element sommet du tas
	 * @throws ElementInexistantException si le tas est vide
	 * 
	 * @author Jolan
	 */
	public E getHead()throws ElementInexistantException;
	/** Recupere le pere d'un element 
	 * 
	 * @param e l'element dont on veut recuperer le pere
	 * @return le pere de l'element
	 * @throws ElementInexistantException si l'element n'appartient pas au tas
	 * 
	 * @author Jolan
	 */
	public E pere(E e) throws ElementInexistantException;
	/** Recupere le fils gauche d'un element
	 * 
	 * @param e l'element dont on veut recuperer le fils G
	 * @return le fils G de l'element
	 * @throws ElementInexistantException si l'element n'appartient pas au tas
	 * 
	 * @author Jolan
	 */
	public E filsGauche(E e)throws ElementInexistantException;
	/** Recupere le fils droit d'un element
	 * 
	 * @param e l'element dont on veut recuperer le fils D
	 * @return le fils D de l'element
	 * @throws ElementInexistantException si l'element n'appartient pas au tas
	 * 
	 * @author Jolan
	 */
	public E filsDroit(E e) throws ElementInexistantException;	
	/** Recupere le pere d'un element a un indice
	 * 
	 * @param i l'indice de l'element dont on veut recuperer le pere
	 * @return le pere de l'element a l'indice i
	 * @throws ElementInexistantException si l'indice ne correspont pas a un element du tas
	 * 
	 * @author Jolan
	 */
	public E pere(int i) throws ElementInexistantException;
	/** Recupere le fils gauche d'un element a un indice
	 * 
	 * @param i l'indice de l'element dont on veut recuperer le fils G
	 * @return le fils G de l'element a l'indice i
	 * @throws ElementInexistantException si l'indice ne correspont pas a un element du tas
	 * 
	 * @author Jolan
	 */
	public E filsGauche(int i) throws ElementInexistantException;
	/** Recupere le fils droit d'un element a un indice
	 * 
	 * @param i l'indice de l'element dont on veut recuperer le fils D
	 * @return le fils D de l'element a l'indice i
	 * @throws ElementInexistantException si l'indice ne correspont pas a un element du tas
	 * 
	 * @author Jolan
	 */
	public E filsDroit(int i) throws ElementInexistantException;
	/** Ajoute un element au tas
	 * 
	 * @param e l'element a ajouter
	 * 
	 * @author Jolan
	 */
	public void add(E e);
	/** Supprime un element du tas
	 * 
	 * @param e l'element a supprimer
	 * @throws ElementInexistantException si l'element n'apparient pas au tas
	 * 
	 * @author Jolan
	 */
	public void remove(E e) throws ElementInexistantException;
	/** Recupere l'indice pere d'un indice
	 * 
	 * @param i l'indice dont on veut l'indice pere
	 * @return l'indice pere de i
	 * 
	 * @author Jolan
	 */
	public int getIpere(int i);
}
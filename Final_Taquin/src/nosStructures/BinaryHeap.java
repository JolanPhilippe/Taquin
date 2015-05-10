package nosStructures; 
import java.util.ArrayList;

import lesExceptions.ElementInexistantException;

/** Tas binaire
 * 
 * @author Jolan
 *
 * @param <E> type des elements qui vont constituer notre tas
 */
public class  BinaryHeap<E> extends Structure<E> implements Heap<E>  {
	/** Modelisation d'un tas par un tableau */
	protected ArrayList<E> tas;
	
	/** Construit un tas vide
	 * 
	 * @author Jolan
	 */
	public BinaryHeap() {
		this.tas = new ArrayList<E>();
	}
	
	/** Construit un tas a partir d'un tas deja existant
	 * 
	 * @param tas un tableau representant un tas binaire deja existant
	 * 
	 * @author Jolan
	 */
	public BinaryHeap(ArrayList<E> tas) {
		this.tas = tas;
	}

	/** Donne la taille du tas (le nb d'element dans le tas)
	 * 
	 * @return le nombre d'element dans le tas
	 * 
	 * @author Jolan
	 */
	public int size (){
		return tas.size();
	}
	
	/** Recupere l'indice d'un element
	 * 
	 * @param e l'element dont on veut recuperer l'indice dans le tableau
	 * @return un entier associé a la place de e dans le tas 
	 * 
	 * @throws ElementInexistantException si l'element e n'est pas present dans le tas
	 * 
	 * @author Jolan
	 */
	public int getIndex(E e) throws ElementInexistantException{
		for (int i=0; i<size(); i++){
			if (tas.get(i).equals(e))
				return i;
		}
		throw new ElementInexistantException ("L'element "+ e +" n'existe pas dans le tas" );
	}
	
	/** Recupere l'element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut recuperer l'element
	 * @return l'element a l'indice i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
	 * 
	 * @author Jolan
	 */
	public E getElem(int i)throws ElementInexistantException{
		if (i >= tas.size())
			throw new ElementInexistantException("aucun element a l'indice "+i);
		else
			return tas.get(i);
	}
	
	/** Recupere le premier element du tas
	 * 
	 * @return l'element premier du tas, la "racine"
	 * 
	 * @throws ElementInexistantException si le tas est vide
	 * 
	 * @author Jolan
	 */
	public E getHead()throws ElementInexistantException{
		return getElem(0);
	}
	
	/** Recupere le pere d'un element
	 * 
	 * @param e l'element
	 * @return l'element pere de e
	 * 
	 * @throws ElementInexistantException si l'element e n'est pas present dans le tas
	 * 
	 * @author Jolan
	 */
	public E pere(E e) throws ElementInexistantException{
		int i = getIndex(e);
		return pere(i);
	}
	
	/** Recupere le pere d'un element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut l'element pere
	 * @return l'element pere de i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
	 * 
	 * @author Jolan
	 */
	public E pere(int i) throws ElementInexistantException{
		if (i==0) return getElem(0);
		return getElem((i+1)/2-1);
	}
	 
	/** Recupere l'indice pere d'un indice
	 * 
	 * @param i un indice du tableau
	 * @return l'indice pere associe a i
	 * 
	 * @author Jolan
	 */
	public int getIpere(int i){
		if (i==0) return 0;
		else return (i+1)/2-1;
	}
	
	/** Recupere le fils gauche d'un element
	 * 
	 * @param e l'element
	 * @return l'element fils gauche de e
	 * 
	 * @throws ElementInexistantException si l'element e n'est pas present dans le tas
	 *
	 * @author Jolan
	 */
	public E filsGauche(E e) throws ElementInexistantException{
		int i = getIndex(e);
		return getElem(2*i+1);
	}
	
	/** Recupere le fils droit d'un element
	 * 
	 * @param e l'element
	 * @return l'element fils droit de e
	 * 
	 * @throws ElementInexistantException si l'element e n'est pas present dans le tas
	 * 
	 * @author Jolan
	 */
	public E filsDroit(E e) throws ElementInexistantException{
		int i = getIndex(e);
		return getElem(2*i+2);
	}
	
	/** Recupere le fils gauche d'un element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut l'element fils gauche
	 * @return l'element fils gauche de i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
	 * 
	 * @author Jolan
	 */
	public E filsGauche(int i) throws ElementInexistantException{
		return getElem(2*i+1);
	}
	
	/** Recupere le fils droit d'un element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut l'element fils droit
	 * @return l'element fils droit de i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
	 * 
	 * @author Jolan
	 */
	public E filsDroit(int i) throws ElementInexistantException{
		return getElem(2*i+2);
	}

	/** Creer une chaine de caractere modelisant le tas par "etages"
	 * 
	 * @return une chaine structuree de toutes les valeurs du tas en fonction de leur rang
	 * 
	 * @author Jolan
	 */
	public String toString(){
		String s="";
		int compt=0;
		int pow=0;
		for (E e: tas){
			if(compt==Math.pow(2, pow)){ 
				s+="\n";
				compt=0;
				pow++;
			}
			s+=e;
			compt++;
			if(!(compt==Math.pow(2, pow)))s+="|";
			
		}
		return s;
	}
	
	/** Ajouter un element au tas par la fin
	 * 
	 * @param e l'element a ajouter
	 * 
	 * @author Jolan
	 */
	public void add(E e) {
		tas.add(tas.size(),e);
	}
	
	
	/** Supprime un element du tas
	 * 
	 * @param e l'element a retirer
	 * 
	 * @author Jolan
	 */
	public void remove(E e) {
		tas.remove(e);
	}

	public boolean isEmpty() {
		return tas.isEmpty();
	}

	public void add(E e, int lim) {
		if(this.size()<lim){
			this.add(e);
		}
	}

	public E extract() throws ElementInexistantException {
		E e = this.getElem(0);
		this.remove(e);
		return e;
	}
	
}

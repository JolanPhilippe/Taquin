package lesGraphes; // test du commit v3
import java.util.ArrayList;

public abstract class  BinaryHeap<E> implements Heap<E> {
	protected ArrayList<E> tas;
	
	public BinaryHeap() {
		this.tas = new ArrayList<E>();
	}
	
	/** Construit un tas a partir d'un tas deja existant
	 * 
	 * @param tas
	 */
	public BinaryHeap(ArrayList<E> tas) {
		this.tas = tas;
	}

	/** Donne la taille du tas (le nb d'element dans le tas)
	 * 
	 * @return le nombre d'element dans le tas
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
	 */
	public E pere(E e) throws ElementInexistantException{
		int i = getIndex(e);
		return pere(i);
	}
	
	/** Recupere le fils gauche d'un element
	 * 
	 * @param e l'element
	 * @return l'element fils gauche de e
	 * 
	 * @throws ElementInexistantException si l'element e n'est pas present dans le tas
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
	 */
	public E filsDroit(E e) throws ElementInexistantException{
		int i = getIndex(e);
		return getElem(2*i+2);
	}
	
	/** Recupere le pere d'un element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut l'element pere
	 * @return l'element pere de i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
	 */
	public E pere(int i) throws ElementInexistantException{
		return getElem(i/2);
	}
	
	/** Recupere le fils gauche d'un element a partir d'un indice
	 * 
	 * @param i l'indice dont on veut l'element fils gauche
	 * @return l'element fils gauche de i
	 * 
	 * @throws ElementInexistantException si aucun element n'est present a l'indice i
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
	 */
	public E filsDroit(int i) throws ElementInexistantException{
		return getElem(2*i+2);
	}

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
	
	public abstract void add(E e); 
	public abstract void remove(E e);
	
}

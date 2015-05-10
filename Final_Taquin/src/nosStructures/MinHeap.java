package nosStructures;

import lesExceptions.ElementInexistantException;
import lesExceptions.TasVideException;

public class MinHeap<E extends Comparable<E>> extends BinaryHeap<E>{
	
	public void downHeap(E e) throws ElementInexistantException{
		E min=null;
		E l=filsGauche(e);
		E r=filsDroit(e);
		if(filsGauche(l)!=null){
			if(l.compareTo(e)<0){
				min=l;
			}else{
				min=e;
			}
		}
		if(filsGauche(r)!=null && r.compareTo(min)<0){
			min=r;
		}
		if(min!=e){
			//echanger(e,min);
			downHeap(min);
		}
	}
	
	/** Recupere l'element le plus petit du tas
	 * 
	 * @return la racine du tas min
	 */
	public E getMin() throws ElementInexistantException{
		return this.getHead();
	}
	
	/** Recupere l'element le plus petit du tas et l'y enleve
	 * 
	 * @return la tete du tas
	 */
	public E extractMin() throws ElementInexistantException{
		E min=this.getHead();
		tas.set(getIndex(this.getHead()),getElem(this.size()));
		downHeap(this.getHead());
		return min;
	}
	
	public void decreaseKey(int i,E n) {
		this.tas.set(i,n);
		try {
			while(i>1 && this.pere(n).compareTo(this.getElem(i))>0){
				//echanger(this.getElem(i),this.pere(n));
				i=this.getIndex(this.pere(i));
			}
		} catch (ElementInexistantException e) {}
	}
	
	/** Ajoute un element au tas min
	 * Le tas doit conserver ses proprietes de tas min
	 *
	 * @param e element a ajouter
	 * @throws ElementInexistantException 
	 */
	public void add(E e){
		decreaseKey(this.size(),e);
	}

	/** Supprime un element au tas min
	 * -> Le tas doit conserver ses proprietes de tas min
	 * 
	 * @param e element a supprimer
	 */
	public void remove() throws ElementInexistantException, TasVideException{
		//echanger(this.getElem(this.getIndex(this.getHead())),this.getElem(this.size()));
		this.tas.remove(this.size());
		downHeap(this.getHead());
	}
	
	public boolean parcourir(E e) throws ElementInexistantException{
		if(filsDroit(e)!=null && filsGauche(e)!=null){
			if(e.compareTo(filsDroit(e))<0 && e.compareTo(filsGauche(e))<0){
				return parcourir(filsDroit(e)) && parcourir(filsGauche(e));
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	/** Verifie que les proprietes du tas min sont respectees
	 * 
	 * @return true si pere(e) plus gd que e, false sinon
	 * @throws ElementInexistantException 
	 */
	public boolean testValidite() throws ElementInexistantException {
		return parcourir(this.getHead());
	}

}

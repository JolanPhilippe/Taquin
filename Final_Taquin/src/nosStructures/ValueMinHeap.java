package nosStructures;

import java.util.HashMap;

import lesExceptions.ElementInexistantException;
import lesExceptions.ErrorKeyValueException;

/** Tas min valué par une fonction value (E - Integer)
 * 
 * @param <E> type des elements du tas
 * 
 * @author Jolan
 */
public class ValueMinHeap<E> extends BinaryHeap<E> {
	/** Map qui fait office de fonction pour donner a chaque element du tas un valeur entiere */
	private Value<E> value;
		
	/** Creer un tas min valué
	 * 
	 * @param value la fonction qui associe a chaque element de type E, un entier
	 * 
	 * @author Jolan
	 */
	public ValueMinHeap(Value<E> value) {
		super();
		this.setValue(value);
	}
	
	

	/** Verifie que les fproprietes du tas min sont respectees
	 * 
	 * @return true si pere(e) plus petit que e, false sinon
	 * 
	 * @author Jolan
	 */
	public boolean testValidite() {
		for(E e : tas){
			try {
				if (value.get(pere(e))> value.get(e))
					return false;
			} catch (ElementInexistantException e1) {}
		}
		return true;
	}
	
	/** Recupere l'element le plus gd du tas
	 * 
	 * @return l'element le plus grand par la fonction value (map assoc E - Integer)
	 * 
	 * @author Jolan
	 */
	public int getIMax(){
		int max=0;
		try {
			E maxVal = this.getElem(max);
			for (int i=0;i<tas.size();i++)
				if(value.get(tas.get(i))> value.get(maxVal)){
					max = i;
					maxVal = this.getElem(max);
				}
		} catch (ElementInexistantException e) {
				System.out.println(e.getMessage());
		}
		return max;
	}
	
	
	/** Recupere l'element le plus petit du tas et l'y enleve
	 * 
	 * @return la tete du tas
	 * 
	 * @author Jolan
	 */
	public E extract(){
		E r = tas.get(0);
		E e = tas.get(tas.size()-1);
		tas.remove(tas.size()-1);
		if (tas.size()==0) return r; 
		tas.set(0,e);
		int iE=0;
		int iFils=0;
		try {
			if (value.get(filsDroit(0))<value.get(filsGauche(0))) iFils=2;
			else iFils=1;

			
		} catch (ElementInexistantException e1) {}
		try {
			E fils = getElem(iFils); 
			while(getValue().get(e)>getValue().get(fils)){
				tas.set(iFils, e);
				tas.set(iE, fils);
				iE = tas.indexOf(e);
				if (value.get(filsDroit(0))<value.get(filsGauche(0))) iFils=2*iE+2;
				else iFils=2*iE+1;
				fils = getElem(iFils);
			}
		} catch (ElementInexistantException e0) {}
		return r;
	}
		

	/** Ajoute un element au tas min
	 * Le tas doit conserver ses proprietes de tas min
	 * 
	 * @param e element a ajouter
	 * 
	 * @author Jolan
	 */
	public void add(E e) {
		super.add(e);
		int iE = tas.indexOf(e);
		if(iE!=0){
			int iPere = (iE+1)/2-1;
			try {
				E pere =getElem(iPere);
				while(getValue().get(e)<getValue().get(pere)){
					tas.set(iPere, e);
					tas.set(iE, pere);
					iE = tas.indexOf(e);
					if(iE!=0) iPere = (iE+1)/2-1;
					pere = pere(e);
				}
			} catch (ElementInexistantException e1) {
				e1.printStackTrace();
			}
		}
	}

	/** Recupere la fonction value
	 * 
	 * @return la Map value (E - Integer)
	 * 
	 * @author Jolan
	 */
	public Value<E> getValue() {
		return value;
	}

	/** Fixe la fonction value par une map deja existante
	 * 
	 * @param value2 doit contenir tous les elements du tas
	 * 
	 * @author Jolan
	 */
	public void setValue(Value<E> value2) {
		this.value = value2;
	}
	
	/** Modifie la valeur d'une clé dans le tas
	 * 
	 * @param i l'indice du noeud a modifier
	 * @param e le nouvel element a mettre
	 * @throws ErrorKeyValueException si la clé a une + gde valeur par value que la precedante
	 * 
	 * @author Jolan
	 */
	public void setKey(int i, E e) throws ErrorKeyValueException{
		if(value.get(tas.get(i))<value.get(e)) 
			throw new ErrorKeyValueException("Nouvelle clé plus grande que clé actuelle");
		tas.set(i, e);
		E pere;
		try {
			pere = pere(e);
			int iPere = getIpere(i);
			while(value.get(pere) > value.get(e)){
				tas.set(i, pere);
				tas.set(iPere, e);
				i = getIpere(i);
				iPere = getIpere(i);
				pere = tas.get(iPere);
			}
		} catch (ElementInexistantException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	public void add(E e, int lim) {
		if(this.size()<lim){
			this.add(e);
		}else{
			int i = this.getIMax();
			try {
				E max = this.getElem(i);
				if(value.get(max)>value.get(e)) this.setKey(i, e);
			} catch (ElementInexistantException | ErrorKeyValueException e1) {}
			
		}
	}
}

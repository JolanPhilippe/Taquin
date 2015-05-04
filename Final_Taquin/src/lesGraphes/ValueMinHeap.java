package lesGraphes;

import java.util.HashMap;

import lesExceptions.ElementInexistantException;

public class ValueMinHeap<E> extends BinaryHeap<E>{
	private HashMap<E,Integer> value;
		
	/**
	 * @param value
	 */
	public ValueMinHeap(HashMap<E, Integer> value) {
		super();
		this.setValue(value);
	}
	
	/**
	 * @param value
	 */
	public ValueMinHeap() {
		super();
		this.setValue(new HashMap<E, Integer>());
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
	public int getMin(){
		int min=0;
		for (int i=0;i<tas.size();i++){
			try {
				if(value.get(tas.get(i))> value.get(this.getElem(min)))
					min = i;
			} catch (ElementInexistantException e) {
				System.out.println(e.getMessage());
			}
		}
		return min;
		
	}
	
	/** Recupere l'element le plus petit du tas et l'y enleve
	 * 
	 * @return la tete du tas
	 */
	public E extractMin(){
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
	 * -> Le tas doit conserver ses proprietes de tas min
	 * 
	 * @param e element a ajouter
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

	public HashMap<E,Integer> getValue() {
		return value;
	}

	public void setValue(HashMap<E,Integer> value) {
		this.value = value;
	}
	
	public int getMax(){
		int max=0;
		for (int i=0;i<tas.size();i++){
			try {
				if(value.get(tas.get(i))> value.get(this.getElem(max)))
					max = i;
			} catch (ElementInexistantException e) {
				System.out.println(e.getMessage());
			}
		}
		return max;
	}
	
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
}

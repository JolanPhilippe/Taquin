package lesGraphes;

import lesExceptions.ElementInexistantException;

public interface Heap <E> {
	
	public int size();
	
	public int getIndex(E e) throws ElementInexistantException;
	public E getElem(int i)throws ElementInexistantException;
	public E getHead()throws ElementInexistantException;
	
	public E pere(E e) throws ElementInexistantException;
	public E filsGauche(E e)throws ElementInexistantException;
	public E filsDroit(E e) throws ElementInexistantException;
	
	public E pere(int i) throws ElementInexistantException;
	public E filsGauche(int i) throws ElementInexistantException;
	public E filsDroit(int i) throws ElementInexistantException;

	public void add(E e);
	public void remove(E e) throws ElementInexistantException;
	
}
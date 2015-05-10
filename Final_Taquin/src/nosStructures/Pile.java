package nosStructures;

import java.util.LinkedList;

import lesExceptions.ElementInexistantException;

public class Pile<E> extends Structure<E>{
	LinkedList<E> pile;
	
	public Pile() {
		pile = new LinkedList<E>();
	}

	public int size() {
		return pile.size();
	}

	public boolean isEmpty() {
		return pile.isEmpty();
	}

	public void add(E e) {
		pile.add(0, e);
	}

	public void add(E e, int lim) {
		if (size()<lim)
			add(e);
	}

	public E extract() throws ElementInexistantException {
		return pile.removeFirst();
	}

}

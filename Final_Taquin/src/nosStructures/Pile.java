package nosStructures;

import java.util.LinkedList;


public class Pile<E> implements Structure<E>{
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

	public E extract(){
		return pile.removeFirst();
	}

	public boolean contains(E e) {
		for (E e1 : pile)
			if (e.equals(e1)) return true;
		return false;
	}

}

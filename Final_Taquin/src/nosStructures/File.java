package nosStructures;

import java.util.LinkedList;

import lesExceptions.ElementInexistantException;

public class File<E> implements Structure<E> {
	LinkedList<E> fifo;

	public File(){
		fifo = new LinkedList<E>();
	}
	
	public int size() {
		return fifo.size();
	}

	public boolean isEmpty() {
		return fifo.isEmpty();
	}

	public void add(E e) {
		fifo.add(size(),e);
	}

	public void add(E e, int lim) {
		if (size()<lim)
			add(e);
	}

	public E extract() throws ElementInexistantException {
		return fifo.removeFirst();
	}
	
	public boolean contains(E e) {
		for (E e1 : fifo)
			if (e.equals(e1)) return true;
		return false;
	}
}

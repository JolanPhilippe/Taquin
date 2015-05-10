package nosStructures;

import java.util.LinkedList;

import lesExceptions.ElementInexistantException;

public class File<E> extends Structure <E> {
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

	@Override
	public E extract() throws ElementInexistantException {
		return fifo.removeFirst();
	}
}

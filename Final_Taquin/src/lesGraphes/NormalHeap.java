package lesGraphes;

import java.util.ArrayList;

public class NormalHeap<E> extends BinaryHeap<E> {

	public void remove(E e) {
		tas.remove(e);
	}

	/**
	 * 
	 */
	public NormalHeap() {
		super();
	}

	/**
	 * @param tas
	 */
	public NormalHeap(ArrayList<E> tas) {
		super(tas);
	}


}

package nosStructures;

import Taquin.GrilleTaquin;
import lesExceptions.ElementInexistantException;

public interface Structure <E>{
	public int size();
	public boolean isEmpty();
	public void add(E e);
	public void add(E e, int lim);
	public E extract() throws ElementInexistantException;
	public boolean contains(E e);
}

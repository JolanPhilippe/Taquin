package nosStructures;

import lesExceptions.ElementInexistantException;

public abstract class Structure <E>{
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract void add(E e);
	public abstract void add(E e, int lim);
	public abstract E extract() throws ElementInexistantException;
}

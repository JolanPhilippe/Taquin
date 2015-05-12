package nosStructures;

public interface Structure <E>{
	public int size();
	public boolean isEmpty();
	public void add(E e);
	public void add(E e, int lim);
	public E extract();
	public boolean contains(E e);
}

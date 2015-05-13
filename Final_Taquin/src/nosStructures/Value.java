package nosStructures;
import java.util.HashMap;

public class Value <E>{
	private HashMap<E, Double> value;
	
	
	public Value() {
		value = new HashMap<E,Double>();
	}
	
	public void add(E e, double d){
		if(!value.containsKey(e)) value.put(e,d);
	}

	public double get (E e){
		if (!value.containsKey(e)) return 0;
		return value.get(e);
	}
}

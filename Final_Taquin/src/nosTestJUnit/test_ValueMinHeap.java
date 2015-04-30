package nosTestJUnit;

import java.util.HashMap;

import lesGraphes.ElementInexistantException;
import lesGraphes.ValueMinHeap;

import org.junit.Test;

public class test_ValueMinHeap {
	@Test
	public void testCreer() throws ElementInexistantException{
		HashMap<String,Integer> lesVal = new HashMap<String,Integer>();
		ValueMinHeap<String> t = new ValueMinHeap<String>(lesVal);
		for(int i=0; i<50; i++)
			lesVal.put(""+i,i);
		for(int i=49;i>=0;i=i-2)
			t.add(""+i);
		System.out.println(t+"\n");
		String s = t.extractMin();
		System.out.println(t+"\n");
		System.out.println("->"+s);
	}
}

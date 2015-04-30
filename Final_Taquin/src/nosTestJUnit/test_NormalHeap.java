package nosTestJUnit;

import static org.junit.Assert.*;
import lesExceptions.ElementInexistantException;
import lesGraphes.NormalHeap;

import org.junit.Test;

public class test_NormalHeap {
	
	@Test
	public void testCreer() throws ElementInexistantException{
		NormalHeap<String> tas = new NormalHeap<String>();
		for (int i=0; i<15; i++)
			tas.add(""+i);
		System.out.println(tas);
		
		assertTrue(tas.getElem(3).equals("3"));
		assertTrue(tas.filsDroit(2).equals("6"));
		assertTrue(tas.filsGauche(3).equals("7"));
		assertTrue(tas.getElem(3).equals("3"));
		
		assertTrue(tas.pere(1).equals("0"));
		
		for (int i=0; i<15; i=i+2)
			tas.remove(""+i);
		System.out.println(tas);
	}
	
	
	
	
}

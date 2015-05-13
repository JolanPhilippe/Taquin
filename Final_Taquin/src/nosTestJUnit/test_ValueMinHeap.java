package nosTestJUnit;

import lesExceptions.ElementInexistantException;
import lesExceptions.ErrorKeyValueException;
import nosStructures.Value;
import nosStructures.ValueMinHeap;

import org.junit.Test;

public class test_ValueMinHeap {
	
	@Test
	public void testCreer() throws ElementInexistantException{
		System.out.println("####################\nTEST: creer tas valué des nombres pairs de 0 a 50");
		Value<String> lesVal = new Value<String>();
		ValueMinHeap<String> t = new ValueMinHeap<String>(lesVal);
		for(int i=0; i<=50; i++)
			lesVal.add(""+i,(double) i);
		for(int i=0; i<=50; i=i+2)
			t.add(""+i);
		System.out.println(t+"\n");
	}
	
	@Test
	public void testGetMax() throws ElementInexistantException{
		System.out.println("####################\nTEST: recuperer la valeur max d'un tas valué min");
		Value<String> lesVal = new Value<String>();
		ValueMinHeap<String> t = new ValueMinHeap<String>(lesVal);
		for(int i=0; i<50; i++)
			lesVal.add(""+i,(double) i);
		for(int i=0; i<50; i=i+4)
			t.add(""+i);
		System.out.println(t+"\n");
		System.out.println(t.getElem(t.getIMax()));
	}
	
	@Test
	public void testSetKey() throws ElementInexistantException{
		System.out.println("####################\nTEST: Modifier la clé de l'indice 10 par 3");
		Value<String> lesVal = new Value<String>();
		ValueMinHeap<String> t = new ValueMinHeap<String>(lesVal);
		for(int i=0; i<50; i++)
			lesVal.add(""+i,(double) i);
		for(int i=0; i<50; i=i+2)
			t.add(""+i);
		System.out.println("AVANT\n"+t+"\n");
		try {
			t.setKey(10, "3");
		} catch (ErrorKeyValueException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("APRES\n"+t+"\n");
		
	}
	
	
}

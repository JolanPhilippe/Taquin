package Taquin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class resTaquin {
	
	static GrilleTaquin ref;
	
	public static GrilleTaquin initGame(int col){
		GrilleTaquin gt = new GrilleTaquin(col,col);
		ref = gt.copyOf();
		gt.mixTab();
		return gt;
	}
	/** programme executable
	 * 
	 * @param args entree clavier
	 * 
	 * @author Jolan Anthony Thibault Benoit
	 * @since 1.0
	 */
	public static void main (String[]args){
		Date d = new Date(); System.out.println(d);
		GrilleTaquin gt = initGame(3);
		System.out.println("####\n"+gt+"\n####");
		try {
			ResTaquinH(gt,2);
		} catch (ValInexistanteException e) {
			System.out.println(e.getMessage());
		}
		d = new Date(); System.out.println(d);
	}
	
	/**
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinB (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		LinkedList<GrilleTaquin> ATraite = new LinkedList<GrilleTaquin> ();
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		Marque.add(taquin);
		ATraite.add(taquin);
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.getFirst();
			ATraite.remove(0);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){
						switch (typeRes){
							case 1:	ATraite.add(0,p); break;
							case 2:	ATraite.add(ATraite.size(),p); break;
							default: throw new ValInexistanteException("Ce mode de resolution est impossible");
						}
						Marque.add(p);
						if(!lesPeres.containsKey(p))
							lesPeres.put(p, pos);
					}
				}
		}
		
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = ref;
		while(pere!=null){
			succ.add(0,pere);
			pere = lesPeres.get(pere);
		}
		String sol="";
		for (int i=0; i<succ.size()-1;i++){	
			GrilleTaquin ini=succ.get(i);
			GrilleTaquin nxt=succ.get(i+1);
			sol+=(ini.compZero(nxt));
		}
		System.out.println(sol);
	}
	
	/**
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinH (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
		LinkedList<GrilleTaquin> ATraite = new LinkedList<GrilleTaquin> ();
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		Marque.add(taquin);
		ATraite.add(taquin);
		CharPred.put(taquin, 'z');
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.getFirst();
			char c = CharPred.get(pos);
			ATraite.remove(0);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){
						CharPred.put(p, c);
						switch (typeRes){
							case 1:	ATraite.add(0,p); break;
							case 2:	ATraite.add(ATraite.size(),p); break;
							default: throw new ValInexistanteException("Ce mode de resolution est impossible");
						}
						Marque.add(p);
						if(!lesPeres.containsKey(p))
							lesPeres.put(p, pos);
					}
				}
		}
		
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = ref;
		while(pere!=null){
			succ.add(0,pere);
			pere = lesPeres.get(pere);
		}
		String sol="";
		for (int i=0; i<succ.size()-1;i++){	
			GrilleTaquin ini=succ.get(i);
			GrilleTaquin nxt=succ.get(i+1);
			sol+=(ini.compZero(nxt));
		}
		System.out.println(sol);
	}
	
}

package Jeu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import nosStructures.*;
import Outils.Couple;
import Taquin.GrilleTaquin;
import lesExceptions.ValInexistanteException;

public class Solve {
	/** Sommet rencontré pendant la res */
	static LinkedList<GrilleTaquin> Marque;
	/** Map : une GrilleTaquin -> Le sommet qui a permis d'y acceder */
	static HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
	/** Sommets peres dans le parcours des GrilleTaquin */
	static HashMap<GrilleTaquin,GrilleTaquin> Peres = new HashMap<GrilleTaquin,GrilleTaquin>();
	/** Referencement des valeurs de grille */
	static Value<GrilleTaquin> values = new Value<GrilleTaquin>();
	/** Structure de stockage des structures encore a traiter */
	static Structure<GrilleTaquin> ATraite;
	/** Jeu de taquin auquel on veut arriver*/	
	static GrilleTaquin ref;
	
	public static String SolveTaquin(GrilleTaquin gt,int typeRes,int delai){
		ref = gt.sort();
		try {
			if(typeRes <= 33) return ResTaquinB(gt, typeRes, delai);
		} catch (ValInexistanteException e) {System.out.println(e.getMessage());}
		return "";
	}
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt La grilleTaquin a resoudre
	 * @param typeRes 1: profondeur | 2: largeur | 31: manhattan | 32: prof + manhattan | 33: ma fonction
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 * @param delai 
	 */
	public static String ResTaquinB (GrilleTaquin taquin, int typeRes, int delai) throws ValInexistanteException{
		Date d1= new Date();
		boolean overtime = false;
		GrilleTaquin ref = taquin.sort();
		GrilleTaquin init = taquin;
		Marque = new LinkedList<GrilleTaquin> ();
		//ATraite = new ValueMinHeap<GrilleTaquin> (values);
		switch (typeRes){ //Definition de ATraite en fonction du mode de resolution
			case 1: //pile
				ATraite = new Pile<GrilleTaquin>(); break;
			case 2: //file
				ATraite = new File<GrilleTaquin>(); break;
			case 31: case 32: case 33: //tas (file de priorité)
				ATraite = new ValueMinHeap<GrilleTaquin>(values); break;
			default: 
				throw new ValInexistanteException("Ce mode de resolution est impossible") ;
		}
		Marque.add(taquin);
		ATraite.add(taquin);
		CharPred.put(taquin, 'z');
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.extract();
			char c = CharPred.get(pos);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else{
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){						
						CharPred.put(p, pos.compZero(p));
						switch (typeRes){
							case 31:
								values.add(p,manhattan(p,ref));
								break;
							case 32:	
								values.add(p,pmanhattan(p,ref,init)); 
								break;
							case 33:
								values.add(p,euclide(p,ref)); 
								break;
							default: 
						}
						ATraite.add(p,7);
						Marque.add(p);
						if(!Peres.containsKey(p))
							Peres.put(p, pos);
					}
				}
			}
			Date d2 = new Date();
			long s1 = d1.getTime()/1000;
			long s2 = d2.getTime()/1000;
			if (s2-s1>delai){testContinu = false; overtime=true;}
		}
		String sol="";
		if(overtime){
			System.out.println("OVERTIME");
		}else{
			sol = getChemin(ref);
		}
		return sol;
	}

	public static double pmanhattan(GrilleTaquin p, GrilleTaquin ref1, GrilleTaquin ref2) {
		return manhattan(p, ref1) + manhattan(p, ref2);
	}

	public static double manhattan(GrilleTaquin p, GrilleTaquin ref) {
		int[][] t = p.getTable();
		int dist=0;
		for (int i = 0; i<t.length; i++){
			for (int j = 0; j<t[i].length; j++){
				if (t[j][j]!=-1){
					try {
						Couple<Integer, Integer> c = ref.getCouple(t[j][j]);
						int x = c.getFst();
						int y = c.getSnd();
						dist += Math.abs(i-x) + Math.abs(j-y) ;
					} catch (ValInexistanteException e) {}
				}
			}
		}
		return dist;
	}
	
	public static double euclide(GrilleTaquin p, GrilleTaquin ref) {
		int[][] t = p.getTable();
		int dist=0;
		for (int i = 0; i<t.length; i++)
			for (int j = 0; j<t[i].length; j++){
				try {
					Couple<Integer, Integer> c = ref.getCouple(t[j][j]);
					int x = c.getFst();
					int y = c.getSnd();
					dist += Math.sqrt((i-x)*(i-x) + (j-y)*(j-y)) ;
				} catch (ValInexistanteException e) {}
			}
		return dist;
	}
	
	public static String getChemin(GrilleTaquin finaleGT){
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = finaleGT;
		while(pere!=null){
			succ.add(0,pere);
			pere = Peres.get(pere);
		}
		String sol="";
		for (int i=0; i<succ.size()-1;i++){	
			GrilleTaquin ini=succ.get(i);
			GrilleTaquin nxt=succ.get(i+1);
			sol+=(ini.compZero(nxt));
		}
		return sol;
	}


}

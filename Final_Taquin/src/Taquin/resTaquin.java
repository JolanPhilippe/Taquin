package Taquin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import nosStructures.ValueMinHeap;
import Outils.Couple;
import lesExceptions.ValInexistanteException;
import lesGraphes.GrapheListe;

public class resTaquin {
	
	static GrilleTaquin ref;
	
	/** Resolution avec creation d'un graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinB1 (GrilleTaquin gt, int typeRes) throws ValInexistanteException{
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		LinkedList<GrilleTaquin> ATraite = new LinkedList<GrilleTaquin> ();
		GrapheListe<GrilleTaquin> graphe = new GrapheListe <GrilleTaquin>();
		Marque.add(gt);
		boolean test = true;
		do{
			GrilleTaquin tete = Marque.getFirst();
			graphe.ajouterSommet(tete);
			boolean testContains = false;
			for (GrilleTaquin gtTest:ATraite) if(tete.equals(gtTest)) testContains=true;
			if(!testContains){
				for (GrilleTaquin grille : tete.successeur()){
					graphe.ajouterArc(tete, grille);
					if(!graphe.getGraphe().containsKey(grille)) graphe.ajouterSommet(grille);
					if(!ATraite.contains(grille) && !Marque.contains(grille)) Marque.add(grille);
					if (grille.equals(ref)){ref=grille;test = false; ATraite.add(0,grille);}
				}
				switch (typeRes){
					case 1:	ATraite.add(0,tete); break;
					case 2:	ATraite.add(ATraite.size(),tete); break;
					default: throw new ValInexistanteException("Ce mode de resolution est impossible");
				}
			}
			Marque.remove(0);
		}while (test);
		
		switch (typeRes){
			case 1:graphe.parcoursLarg(gt); break;
			case 2:graphe.parcoursProf(); break;
			default: throw new ValInexistanteException("Ce mode de resolution est impossible");
		}
		
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = graphe.getPeres();
		
		ArrayList<GrilleTaquin> succ = new ArrayList<GrilleTaquin>();
		GrilleTaquin pere = ref;
		while(pere!=null){
			succ.add(0,pere);
			pere = lesPeres.get(pere);
		}
		for(GrilleTaquin x:succ) System.out.println(x);	
	}
	
	/** Resolution sans creation 'un graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinB2 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
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
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur 
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinB3 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		GrilleTaquin ref = Game.ref;
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin>();
		HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
		ArrayList<GrilleTaquin> ATraite = new ArrayList<GrilleTaquin>();
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		Marque.add(taquin);
		ATraite.add(taquin);
		CharPred.put(taquin, 'z');
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.get(0);
			char c = CharPred.get(pos);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){		
						CharPred.put(p, c);
						switch (typeRes){
							case 1:
								ATraite.add(0,p);
								break;
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
	
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: manhattan | 2: manhattan + profondeur | 3: ma fonction
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinBTas (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		GrilleTaquin ref = Game.ref;
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		HashMap<GrilleTaquin,Integer> valeurs = new HashMap<GrilleTaquin,Integer>();
		HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
		ValueMinHeap<GrilleTaquin> ATraite = new ValueMinHeap<GrilleTaquin> (valeurs);
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
		int dist=0;
		Marque.add(taquin);
		ATraite.add(taquin);
		CharPred.put(taquin, 'z');
		boolean testContinu = true;
		while (testContinu){
			GrilleTaquin pos = ATraite.extract();
			char c = CharPred.get(pos);
			if (pos.equals(ref)) 
				{testContinu=false; ref=pos;}
			else
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					boolean testContains=false;for(GrilleTaquin t: Marque) if (t.equals(p)) testContains=true;
					if(!testContains){		
						CharPred.put(p, c);
						switch (typeRes){
							case 1:
								valeurs.put(p,manhattan(p,ref));
								break;
							case 2:	
								valeurs.put(p,manhattan(p,ref)*dist); 
								break;
							case 3:
								valeurs.put(p,maFct(p,ref)); 
								break;
							default: throw new ValInexistanteException("Ce mode de resolution est impossible");
						}
						ATraite.add(p);
						Marque.add(p);
						if(!lesPeres.containsKey(p))
							lesPeres.put(p, pos);
					}
				}
			dist++;
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

	private static int manhattan(GrilleTaquin p, GrilleTaquin ref) {
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
	
	private static int maFct(GrilleTaquin p, GrilleTaquin ref2) {
		int[][] t1 = p.getTable();
		int[][] t2 = p.getTable();
		int c=0;
		for (int i = 0; i<t1.length; i++)
			for (int j = 0; j<t1[i].length; j++)
				if (t1[i][j]!=t2[i][j]) c++;
		return c;
	}
	

}

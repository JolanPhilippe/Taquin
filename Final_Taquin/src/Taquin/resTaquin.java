package Taquin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import nosStructures.*;
import Outils.Couple;
import lesExceptions.ElementInexistantException;
import lesExceptions.ValInexistanteException;
import lesGraphes.GrapheListe;

public class resTaquin {
	
	static File<GrilleTaquin> Marque = new File<GrilleTaquin> ();
	static HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
	static HashMap<GrilleTaquin,GrilleTaquin> Peres = new HashMap<GrilleTaquin,GrilleTaquin>();
	static Value<GrilleTaquin> val = new Value<GrilleTaquin>();
	static Structure<GrilleTaquin> ATraite;
	static GrilleTaquin ref;
	
	public static void ResTaquinB (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		GrilleTaquin ref = taquin.taquinRange();
		GrilleTaquin init = taquin;
		switch (typeRes){ //Definition de ATraite en fonction du mode de resolution
			case 1: //pile
				ATraite = new Pile<GrilleTaquin>(); break;
			case 2: //file
				ATraite = new File<GrilleTaquin>(); break;
			case 31: case 32: case 33: //tas (file de priorité)
				ATraite = new ValueMinHeap<GrilleTaquin>(val); break;
			default: 
				throw new ValInexistanteException("Ce mode de resolution est impossible") ;
		}
		Marque.add(init); ATraite.add(init); CharPred.put(taquin, 'z');
		boolean test = true;
		while(test){
			try {
				GrilleTaquin pos = ATraite.extract();
				char c = CharPred.get(pos);
				for (GrilleTaquin p : pos.successeur(c).keySet()){
					if(!Marque.contains(p)){
						CharPred.put(p,pos.compZero(p));
						Marque.add(p); 
						switch (typeRes){ //si ATraite est un tas : necessaire d'enregister sa valeur
							case 31: 
								val.add(p,manhattan(p,ref)); 
								break;
							case 32: 
								val.add(p,pmanhattan(p,ref,init));
								break;
							case 33: 
								val.add(p, euclide(p,ref));
								break;
							default:
								/*cas ou on n'est pas sur une structure de tas, 
								donc pas besoin de d'ajouter une valeur a val*/
						}
						ATraite.add(p);
						if(!Peres.containsKey(p)) Peres.put(p, pos);
					}
					
					if (p.equals(ref)){ref=p; test = false;}
				}
			} catch (ElementInexistantException e1) {System.out.println(e1);} //cas ou ATraite est vide
			
		}

		String sol = getChemin(ref);
		System.out.println(sol);
	}
	
	
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
		GrilleTaquin init = taquin;
		LinkedList<GrilleTaquin> Marque = new LinkedList<GrilleTaquin> ();
		Value<GrilleTaquin> valeurs = new Value<GrilleTaquin>();
		HashMap<GrilleTaquin,Character> CharPred = new HashMap<GrilleTaquin,Character> ();
		ValueMinHeap<GrilleTaquin> ATraite = new ValueMinHeap<GrilleTaquin> (valeurs);
		HashMap<GrilleTaquin,GrilleTaquin> lesPeres = new HashMap<GrilleTaquin,GrilleTaquin>();
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
						CharPred.put(p, pos.compZero(p));
						switch (typeRes){
							case 1:
								valeurs.add(p,manhattan(p,ref));
								break;
							case 2:	
								valeurs.add(p,pmanhattan(p,ref,init)); 
								break;
							case 3:
								valeurs.add(p,euclide(p,ref)); 
								break;
							default: throw new ValInexistanteException("Ce mode de resolution est impossible");
						}
						ATraite.add(p);
						Marque.add(p);
						if(!Peres.containsKey(p))
							Peres.put(p, pos);
					}
				}
		}
		String sol = getChemin(ref);
		System.out.println(sol);
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
	
	public static double euclide(GrilleTaquin p, GrilleTaquin ref2) {
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

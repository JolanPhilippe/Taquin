package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import nosStructures.*;
import Outils.Couple;
import Taquin.GrilleTaquin;
import lesExceptions.ValInexistanteException;
import lesGraphes.GrapheListe;

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
	
	
/** Resolution avec creation d'un graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
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
	 * 
	 * @author Jolan
	 */
	public static String ResTaquinB2 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
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
		return sol;
	}
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur 
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 */
	public static String ResTaquinB3 (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
		GrilleTaquin ref = Game.ref;
		Marque = new LinkedList<GrilleTaquin>();
		switch (typeRes){ //Definition de ATraite en fonction du mode de resolution
		case 1: //pile
			ATraite = new Pile<GrilleTaquin>(); break;
		case 2: //file
			ATraite = new File<GrilleTaquin>(); break;
		}
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
						CharPred.put(p, c);
						ATraite.add(p);
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
		return sol;
	}
	
	/** Resout taquin avec gestion du dernier coup sans graphe annexe
	 * 
	 * @param gt La grilleTaquin a resoudre
	 * @param typeRes 1: profondeur | 2: largeur | 31: manhattan | 32: prof + manhattan | 33: ma fonction
	 * @throws ValInexistanteException
	 * 
	 * @author Jolan
	 */
	public static String ResTaquinB (GrilleTaquin taquin, int typeRes) throws ValInexistanteException{
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
		}
		String sol = getChemin(ref);
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

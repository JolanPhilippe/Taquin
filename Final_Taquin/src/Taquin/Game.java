package Taquin;
import java.awt.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import lesGraphes.GrapheListe;

public class Game {
	
	static GrilleTaquin ref;
	static Scanner sc = new Scanner (System.in);
	/** Fait faire un choix d'un nombre entier a l'utilisateur entre min et max
	 * 
	 * @param min entier minimal pouvant etre choisi
	 * @param max entier maximal pouvant etre choisi
	 * @return l'entier choisi
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static int choiceInt(int min, int max){
		int n=0;
		do{
			try{
				n = sc.nextInt();
			}catch (InputMismatchException ime){
				n=-1;
			}
			if(n<min || n>max)
				System.out.println("Choix impossible");
		}while (n<min || n>max);
		return n;
	}
	
	/** Fait faire un choix d'un nombre entier a l'utilisateur entre min et infini
	 * 
	 * @param min entier minimal pouvant etre choisi
	 * @param max entier maximal pouvant etre choisi
	 * @return l'entier choisi
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static int choiceInt(int min){
		int n=0;
		do{
			n = sc.nextInt();
			if(n<min)
				System.out.println("Choix impossible, valeur inexistante");
		}while (n<min);
		return n;
	}
	
	public static GrilleTaquin initGame(String fichier){
		GrilleTaquin gt = new GrilleTaquin(fichier);
		ref = gt.copyOf();
		gt.mixTab();
		return gt;
	}
	
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
		GrilleTaquin gt = initGame(3);
		try {
			ResTaquinB(gt,2);
		} catch (ValInexistanteException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param gt
	 * @param typeRes 1: parcours en largeur | 2: parcours en profondeur | 3: tas par Manhattan | 4: tas prof+Manhattan
	 * @throws ValInexistanteException
	 */
	public static void ResTaquinB (GrilleTaquin gt, int typeRes) throws ValInexistanteException{
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
	
	public static void playTaquin(){
		boolean play=true;
		do{
			System.out.println("Choisir taille (>= 1)");
			int taille = choiceInt(1);
			System.out.print((char)Event.ESCAPE + "7");///////ici la modif
			GrilleTaquin grille = initGame(taille);
			boolean win=grille.win();
			int nb_coup=0;
			boolean continuer = true;
			while (!win && continuer){
				String str = sc.next();
				char selected = str.charAt(0);
				try {
					grille.playMove(selected);
					nb_coup++;
					System.out.print((char)Event.ESCAPE + "8");///////////et la !!!!!!!!!!!
					System.out.println(grille.toString());
					win = grille.win();
					if (win) System.out.println("Gagné !!! ("+nb_coup+" coups)");
				} catch (MouvementImpossibleException e) {
					System.out.println(e.getMessage());
				} catch (QuitterException e) {
					continuer = false;
				}	
			}
			System.out.println("Voulez vous rejouer ? [0:Non/1:Oui]");
			int choice = sc.nextInt();
			if (choice==0) play=false;
		}while(play);
	}
	
}

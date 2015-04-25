package Taquin;
import java.awt.Event;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

import lesGraphes.GrapheListe;
import lesGraphes.Triplet;

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
		resTaquin();
	}
	
	public static void resTaquin(){
		GrilleTaquin gt = initGame(2);
		System.out.println("LA GRILLE:\n"+gt);
		System.out.println("LA GRILLE REFERENCE:\n"+ref);
		
		ArrayList<GrilleTaquin> aTraiter = new ArrayList<GrilleTaquin> ();
		ArrayList<GrilleTaquin> traite = new ArrayList<GrilleTaquin> ();
		GrapheListe<GrilleTaquin> graphe = new GrapheListe <GrilleTaquin>();
		aTraiter.add(gt);
		boolean test = true;
		do{
			GrilleTaquin tete = aTraiter.get(0);
			if (!traite.contains(tete)){
				System.out.println(tete);
				graphe.ajouterSommet(tete);
				if (tete.equals(ref))
					test = false;
				else{
					for (GrilleTaquin grille : tete.successeur()){
						aTraiter.add(grille);
						System.out.println("AJOUT AU GRAPHE DE\n"+grille);
						graphe.ajouterSommet(grille);
						System.out.println(graphe);
						graphe.ajouterArc(tete, grille);
						
					}
				}
				traite.add(tete);
			}
			aTraiter.remove(0);
		}while (test);
		System.out.println("GRAPHE\n"+graphe);
		
		/*TreeMap<GrilleTaquin, GrilleTaquin> lesPeres = c.getFst();
		GrilleTaquin pere = lesPeres.get(gt);
		LinkedList<GrilleTaquin> chemin = new LinkedList<GrilleTaquin>();
		System.out.println(lesPeres);
		while (!pere.equals(ref)){
			chemin.add(pere);
			pere = lesPeres.get(pere);
		}*/
		
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

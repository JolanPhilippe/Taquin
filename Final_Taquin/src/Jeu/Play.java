package Jeu;

import java.awt.Event;
import java.util.Scanner;

import Outils.*;
import Taquin.GrilleTaquin;
import lesExceptions.*;

public class Play {

	static Scanner sc = new Scanner (System.in);
	
	public static void playTaquin(){
		boolean play=true;
		do{
			System.out.println("Choisir nb ligne (>= 1)");
			int ligne = Tools.choiceInt(1);
			System.out.println("Choisir nb colonne (>= 1)");
			int col = Tools.choiceInt(1);
			System.out.print((char)Event.ESCAPE + "7");
			GrilleTaquin grille = Game.initGame(ligne, col);
			
			boolean win=grille.win();
			int nb_coup=0;
			boolean continuer = true;
			while (!win && continuer){
				String str = sc.next();
				char selected = str.charAt(0);
				try {
					grille.playMove(selected);
					nb_coup++;
					System.out.print((char)Event.ESCAPE + "8");
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
	
	public static void playTaquin(String fichier){
		boolean play=true;
		do{
			GrilleTaquin grille = Game.initGame(fichier);
			boolean win=grille.win();
			int nb_coup=0;
			boolean continuer = true;
			while (!win && continuer){
				String str = sc.next();
				char selected = str.charAt(0);
				try {
					grille.playMove(selected);
					nb_coup++;
					System.out.print((char)Event.ESCAPE + "8");
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

package Jeu;

import java.awt.Event;
import java.util.Scanner;

import Outils.*;
import Taquin.GrilleTaquin;
import lesExceptions.*;

public class Play {

	static Scanner sc = new Scanner (System.in);
	
	public static void playTaquin(GrilleTaquin grille){
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
	}
	
	public static GrilleTaquin initPlay(){
		System.out.println("Choisir nb ligne (>= 1)");
		int ligne = Tools.choiceInt(1);
		System.out.println("Choisir nb colonne (>= 1)");
		int col = Tools.choiceInt(1);
		System.out.print((char)Event.ESCAPE + "7");
		GrilleTaquin grille = Game.initGame(ligne, col);
		return grille;
	}
	
	public static GrilleTaquin initPlay(String fichier){
		GrilleTaquin grille = Game.initGame(fichier);
		return grille;
	}
}

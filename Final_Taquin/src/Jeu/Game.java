package Jeu;
import java.util.Scanner;

import Taquin.*;

public class Game {
	
	public static GrilleTaquin ref;
	static Scanner sc = new Scanner (System.in);
	
	/** Initialise un jeu de Taquin a partir d'un fichier
	 * 
	 * @param fichier
	 * @return une instance de GrilleTaquin correspondant au fichier
	 * 
	 * @author Benoit
	 */
	public static GrilleTaquin initGame(String fichier){
		GrilleTaquin gt = new GrilleTaquin(fichier);
		ref = gt.sort();
		System.out.println(gt);
		return gt;
	}
	
	/**
	 * 
	 * @param ligne
	 * @param col
	 * @return
	 */
	public static GrilleTaquin initGame(int ligne, int col){
		GrilleTaquin gt = new GrilleTaquin(ligne,col);
		ref = gt.copyOf();
		gt.mixTab();
		System.out.println(gt);
		return gt;
	}
	
}

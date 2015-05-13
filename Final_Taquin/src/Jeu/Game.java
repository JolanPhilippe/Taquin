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
	
	public static GrilleTaquin initGame(int ligne, int col){
		GrilleTaquin gt = new GrilleTaquin(ligne,col);
		ref = gt.copyOf();
		gt.mixTab();
		System.out.println(gt);
		return gt;
	}
	
	
	/** programme executable
	 * 
	 * @param args entree clavier
	 * 
	 * @author Jolan Anthony Thibault Benoit
	 * @since 1.0
	 */
	/*public static void main (String[]args){
		Date d1 = new Date();
		//for(int i = 0;i<100;i++){
		GrilleTaquin gt = initGame("C:\\Users\\Jolan\\Desktop\\taquin.txt");
		//GrilleTaquin gt = initGame(2,2);
		//Date d = new Date(); System.out.println(d);
		//System.out.println("####\n"+gt+"\n####");
		try {
			System.out.println(Solve.ResTaquinB(gt,31));
		} catch (ValInexistanteException e) {
			System.out.println(e.getMessage());
			}
		//d = new Date(); System.out.println(d);
		//}
		Date d2 = new Date();
		long s1 = d1.getTime()/1000;
		long s2 = d2.getTime()/1000;
		System.out.println("S'est resolu en "+(s2-s1)+"s");
	}*/
	
	
	
	
	
}

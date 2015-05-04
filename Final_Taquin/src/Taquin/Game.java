package Taquin;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import lesExceptions.ValInexistanteException;

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
		ref = gt.taquinRange();
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
		GrilleTaquin gt = initGame("C:\\Users\\Jolan\\Desktop\\taquin.txt");
		Date d = new Date(); System.out.println(d);
		System.out.println("####\n"+gt+"\n####");
		try {
			resTaquin.ResTaquinBTas(gt,1);
		} catch (ValInexistanteException e) {
			System.out.println(e.getMessage());
		}
		d = new Date(); System.out.println(d);
	}
	
	
	
	
	
}

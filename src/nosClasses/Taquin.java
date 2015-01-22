package nosClasses;

import java.util.InputMismatchException;
import java.util.Scanner;

import nosExceptions.*;

public class Taquin{
	
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
	
	/** Indique si la grille est correctement positionnée 
	*
	* @param grille la grille de jeu de taquin a tester. Elle doit etre de forme carrée (ie: ligne = colonne)
	* @return vrai si chaque case a la bonne valeur, faux sinon
	*
	* @author Jolan
	* @since 1.0
	*/
	public static boolean win ( int[][] grille ){ //grille au format : [ligne] [colonne]
		int nb_count = 1; //valeur de comparaison
		int nb_ligne= grille.length;
		int nb_colonne= grille[0].length;
		int nb_case= nb_ligne*nb_colonne;
		for (int i = 0; i<nb_ligne; i++){
			for (int j = 0; j<nb_colonne; j++){
				if (grille[i][j] != (nb_count%nb_case)) //modulo necessaire pour gerer le 0
					return false;
				else 
					nb_count++; //la valeur est ok, on incremente notre reference de comparaison
			}
		}
		return true;
	}
	
	/** Melange un tableau a deux dimensions
	*	
	* @param grille une grille dont on veux melanger les valeurs	
	*
	* @author Jolan
	* @since 1.0
	*/
	public static void mixTab(int[][] grille){
		int nb_ligne= grille.length;
		int nb_colonne= grille[0].length;
		for (int k=0; k<3; k++) //On effectue 3x le melange pour avoir une grille la plus aleatoire possible
			for (int i=0; i<nb_ligne;i++)
				for (int j=0; j<nb_colonne;j++){
					//on choisi une ligne et une colonne au hasard dans le tableau...
					int ligne= (int)(Math.random()*nb_ligne); 
					int colonne= (int)(Math.random()*nb_colonne);
					//... et on echange
					int tmp = grille[i][j];
					grille[i][j] = grille[ligne][colonne];
					grille[ligne][colonne] = tmp;
				}	
		if(!containsSolution(grille)) mixTab(grille); //si la grille n'a pas de soluc, on effectue un nouveau melange
		
	}
	
	/** Creer une grille carre
	*
	* @param n la dimension de notre grille de jeu
	* @return un tableau de dimension 2 avec n lignes et n colonnes
	*
	* @author Jolan
	* @since 1.0
	*/
	public static int[][] createGrille(int n, boolean mix){
		int[][] grille =  new int[n][n]; //alloc memoire
		remplirGrille(grille); //on remplie
		if (mix) mixTab(grille); //on melange
		afficherGrille(grille); //affiche
		return grille;
	}
	
	/** Indique si la grille de jeu de Taquin est resolvable 
	* 
	* @param grille une grille de jeu carrée
	* @return vrai si la grille est resolvable, faux sinon
	*
	* @author Thibault
	* @since 1.0
	*/
	public static boolean containsSolution(int [][] grille){
		return pairMvm(grille)-pairVide(grille)==0;
	}
	
	/** Indique la parité du nb de mvm a effectuer pour deplacer la case vide a sa position victoire
	* 
	* @param grille une grille de jeu carrée
	* @return 0 si le nb de mvm est pair, 1 sinon
	*
	* @author Jolan
	* @since 1.0
	*/
	public static int pairVide(int [][] grille){
		try {
			Couple c = getCouple(grille,0);
			int ligne_empty = c.getX();
			int colonne_empty = c.getY();
			return (Math.abs(ligne_empty-grille.length-1) + Math.abs(colonne_empty-grille.length-1))%2;
		} catch (ValInexistanteException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	/** Indique la parité du nb de mvm a effectuer pour arriver a la configuration de victoire
	* 
	* @param grille une grille de jeu carrée
	* @return 0 si le nb de mvm est pair, 1 sinon
	*
	* @author Thibault
	* @since 1.0
	*/
	public static int pairMvm(int [][] grille){
		int nb_ligne= grille.length;
		int nb_colonne= grille[0].length;
		int nb_case= nb_ligne*nb_colonne;
		//debut copie grille
		int [][]copieGrille=new int[nb_ligne][nb_colonne];
		for(int i=0; i< nb_ligne;i++) 
			for(int j=0; j<nb_colonne; j++)
				copieGrille[i][j] = grille[i][j];
		//fin copie grille
		int count=1;
		int nbDeCoup=0;
		for(int i=0; i< nb_ligne;i++){
			for(int j=0; j<nb_colonne; j++){
				if((copieGrille[i][j])!=count%nb_case){
					try{
						Couple c=getCouple(copieGrille,count);
						int ligne=c.getX();
						int colonne=c.getY();
						int tmp = copieGrille[ligne][colonne];
						copieGrille[ligne][colonne] = copieGrille[i][j];
						copieGrille[i][j] = tmp;	
						nbDeCoup++;
					}catch(ValInexistanteException e){
						System.out.println(e.getMessage());
					}
				}
				count++;
			}
		}
		return nbDeCoup%2;
	}
	
	/** Joue un mouvement 
	*
	* @param grille la grille de jeu
	* @param n la valeur cherchée qui doit etre presente dans grille
	* @throws MouvementImpossibleException si le mouvement n'est pas possible
	*
	* @author Jolan
	* @since 1.0
	*/
	public static void playMove(int[][] grille, int n) throws MouvementImpossibleException{
		try{
			Couple empty = getCouple(grille, 0);
			int ligne_empty = empty.getX(); int colonne_empty = empty.getY();
			Couple selected = getCouple(grille, n);
			int ligne_selected = selected.getX(); int colonne_selected = selected.getY();
			if((Math.abs(ligne_empty-ligne_selected)==1 && Math.abs(colonne_empty-colonne_selected)==0)||
					(Math.abs(ligne_empty-ligne_selected)==0 && Math.abs(colonne_empty-colonne_selected)==1)){ 
				//test de proximité entre le 0 et la case choisie
				int tmp = grille[ligne_empty][colonne_empty];
				grille[ligne_empty][colonne_empty] = grille[ligne_selected][colonne_selected];
				grille[ligne_selected][colonne_selected] = tmp;
			}else{
				throw new MouvementImpossibleException("La case contenant "+n+" ne peut pas etre déplacee");
			}
		}catch (ValInexistanteException vie){
			System.out.println(vie.getMessage());
		}
	}
	
	/** Recupere le couple de coordonnées d'une valeur
	*
	* @param tab la grille de jeu
	* @param n la valeur cherchée qui doit etre presente dans grille
	* @return un Couple de coordonnées de la valeur n
	* @throws ValInexistanteException si n n'est pas dans la grille
	*
	* @author Jolan
	* @since 1.0
	*/
	public static Couple getCouple (int [][] tab, int n)throws ValInexistanteException{
		for (int i=0; i<tab.length;i++)
			for (int j=0; j<tab[i].length;j++)
				if (tab[i][j]==n){
					return new Couple(i,j);
				}
		throw new ValInexistanteException("La valeur "+n+" n'existe pas");
	}
	
	/** Affiche la grille de jeu
	 * 
	 * @param grille la grille de jeu a afficher
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static void afficherGrille(int [][] grille){
		for (int i=0; i<grille.length; i++){
			for (int j=0; j<grille[i].length; j++){
				String valeur;
				if (grille[i][j]==0)
					valeur="  ";
				else if(grille[i][j]<10)
					valeur=" "+grille[i][j];
				else
					valeur=""+grille[i][j];
				System.out.print("["+valeur+"]");
			}
			System.out.println();
		}
		System.out.println("99 : quitter");
	}
	
	/** Remplie la grille avec des valeurs entieres dans l'ordre
	 * 
	 * @param grille la grille de jeu a remplir
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static void remplirGrille(int[][] grille){
		int nb_count=1;
		for (int i=0; i<grille.length; i++)
			for (int j=0; j<grille[i].length; j++){
				grille[i][j]=nb_count%(grille.length*grille.length);
				nb_count++;
			}
	}
	
	/** programme executable
	 * 
	 * @param args entree clavier
	 * 
	 * @author Jolan Anthony Thibault Benoit
	 * @since 1.0
	 */
	public static void main (String[]args){
		boolean play=true;
		do{
			System.out.println("Choisir taille (>= 1)");
			int taille = choiceInt(1);
			int[][] grille = createGrille(taille, true);
			boolean win=win(grille);
			int nb_coup=0;
			while (!win){
				nb_coup++;
				int selected = sc.nextInt();
				if(selected==99) win=true;
				else{
					try {
						playMove(grille, selected);
					} catch (MouvementImpossibleException e) {
						System.out.println(e.getMessage());
					}
					afficherGrille(grille);
					win = win(grille);
					if (win) System.out.println("Gagné !!! ("+nb_coup+" coups)");
				}
			}
			System.out.println("Voulez vous rejouer ? [0:Non/1:Oui]");
			int choice = sc.nextInt();
			if (choice==0) play=false;
		}while(play);
	}
} 
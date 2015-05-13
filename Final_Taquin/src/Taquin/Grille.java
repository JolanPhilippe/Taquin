package Taquin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Outils.Couple;
import lesExceptions.ValInexistanteException;

public abstract class Grille implements Comparable<Grille>{
	/** Nb de lignes dans la table */
	private int ligne;
	/** Nb de colonnes sur la table*/
	private int colonne;
	/** Tableau d'entiers [ligne][colonne]*/
	private int[][] table;
	
	/** Creer une copie distincte en memoire de this
	 * 
	 * @return une copie de this
	 * 
	 * @author Jolan
	 */
	public abstract Grille copyOf();
	public abstract boolean win();
	
	/** Cree une grille de jeu a partir d'un fichier .taq
	 * 
	 * @param file contenant les données de jeu
	 * 
	 * @author Jolan
	 */
	public Grille(String file){
		try{
			File fic = new File(file);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(fic);
			this.ligne=sc.nextInt();
			this.colonne=sc.nextInt();
			table=new int[getLigne()][getColonne()];
			for(int i=0; i<getLigne(); i++){
				for(int j=0; j<getColonne(); j++){
					getTable()[i][j] = sc.nextInt();
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("Fichier non trouvé");
		}
	}
	
	/** Creer une Grille d'entier de taille n*m
	 * 
	 * @param ligne nb de ligne dans le tableau
	 * @param colonne nb de colonne dans le tableau
	 * 
	 * @author Jolan
	 */
	public Grille(int ligne, int colonne) {
		this.ligne=ligne;
		this.colonne=colonne;
		this.table=new int[ligne][colonne];
		int nb_cases = ligne * colonne;
		int nb_count=1;
		for (int i=0; i<ligne; i++)
			for (int j=0; j<colonne; j++){
				getTable()[i][j]=nb_count%(nb_cases); //modulo necessaire pour avoir une case 0
				nb_count++;
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
	*/
	public Couple<Integer,Integer> getCouple (int n) throws ValInexistanteException{
		for (int i=0; i<getLigne();i++)
			for (int j=0; j<getColonne();j++)
				if (getTable()[i][j]==n){
					return new Couple<Integer,Integer>(i,j);
				}
		throw new ValInexistanteException("La valeur "+n+" n'existe pas");
	}
	
	/** Retourne un affichage de la grille
	 * 
	 * @author Jolan
	 * @return un string contenant l'affichage de la grille
	 */
	public String toString(){
		String chaine="";
		for (int i=0; i<getTable().length; i++){
			for (int j=0; j<getTable()[i].length; j++){
				String valeur;
				if (getTable()[i][j]==0)
					valeur="   ";
				else if (getTable()[i][j]==-1)
					valeur=" X ";
				else if(getTable()[i][j]<10)
					valeur=" "+getTable()[i][j]+" ";
				else
					valeur=""+getTable()[i][j]+" ";
				chaine+="["+valeur+"]";
			}
			chaine+="\n";
		}
		return chaine+"q : quitter";
	}

	/** Melange un tableau a deux dimensions
	*	
	* @param grille une grille dont on veux melanger les valeurs	
	*
	* @author Jolan
	*/
	public void mixTab(){
		for (int k=0; k<3; k++) //On effectue 3x le melange pour avoir une grille la plus aleatoire possible
			for (int i=0; i<getLigne();i++)
				for (int j=i; j<getColonne();j++){
					//on choisi une ligne et une colonne au hasard dans le tableau...
					int c_ligne= (int)(Math.random()*getLigne()); 
					int c_colonne= (int)(Math.random()*getColonne());
					//... et on echange
					int tmp = getTable()[i][j];
					getTable()[i][j] = getTable()[c_ligne][c_colonne];
					getTable()[c_ligne][c_colonne] = tmp;
				}	
	}
	/** Recupere le nb de colonne de la Grille
	 * 
	 * @return this.colonne
	 * 
	 * @author Jolan
	 */
	public int getColonne() {
		return colonne;
	}
	
	/** Recupere le nb de ligne de la Grille
	 * 
	 * @return this.ligne
	 * 
	 * @author Jolan
	 */
	public int getLigne() {
		return ligne;
	}

	/** Recupere la table d'une grille
	 * 
	 * @return the table
	 * 
	 * @author Jolan
	 */
	public int[][] getTable() {
		return table;
	}
	/** Fixe la table d'une grille
	 * 
	 * @param table the table to set
	 * 
	 * @author Jolan
	 */
	public void setTable(int[][] table) {
		this.table = table;
	}
	
	/** Test l'egalité du contenu de 2 grilles
	 * 
	 * @param gt la grille avec laquelle on compare
	 * @return vrai si elles sont egales, faux sinon
	 * 
	 * @author Jolan
	 */
	public boolean equals(Grille gt){
		if(gt.ligne != this.ligne || gt.colonne != this.colonne) return false;
		for (int i = 0; i<ligne; i++)
			for (int j = 0; j<colonne; j++)
				if (this.table[i][j] != gt.table[i][j]) return false;
		return true;
	}

}

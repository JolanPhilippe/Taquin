package Taquin;
import java.awt.Event;
import java.util.ArrayList;
import java.util.HashMap;

import Outils.Couple;
import lesExceptions.MouvementImpossibleException;
import lesExceptions.QuitterException;
import lesExceptions.ValInexistanteException;

public class GrilleTaquin extends Grille{
	/** Ligne sur laquelle se situe le 0 */
	private int l0;
	/** Colonne sur laquelle se situe le 0 */
	private int c0;
	
	/** Creer une grilleTaquin a partir d'un fichier
	 * 
	 * @param file fichier .taq a partir de laquelle creer la Grille
	 * 
	 * @author Benoit
	 */
	public GrilleTaquin (String file){
		super(file);
		try {
			setL0((int) getCouple(0).getFst());
			setC0((int) getCouple(0).getSnd());
		} catch (ValInexistanteException e) {System.out.println("Fichier taquin non valide");}
	}
	
	/** Creer une grille avec une nombre de lignes et de colonnes definis
	 * 
	 * @param ligne nb de lignes sur la grille de Taquin
	 * @param colonne nb de colonnes sur la grille de Taquin
	 * 
	 * @author Jolan
	 */
	public GrilleTaquin(int ligne, int colonne) {
		super(ligne, colonne);
		this.setL0(ligne-1);
		this.setC0(colonne-1);
	}

	/** Indique si la le jeu créé ainsi comporte une solution
	 * 
	 * @return vrai si il a une solution, faux sinon
	 * 
	 * @author Thibault
	 */
	public boolean containsSolution() {
		return pairiteMvm()==pairVide();
	}

	/** Creer une copie de la grille
	 * 
	 * @return une grilleTaquin semblable a this
	 * 
	 * @author Jolan
	 */
	public GrilleTaquin copyOf(){
		GrilleTaquin gt = new GrilleTaquin (this.getLigne(), this.getColonne());
		for (int i = 0; i < getLigne(); i++)
			for (int j = 0; j<getColonne(); j++)
				gt.getTable()[i][j]=this.getTable()[i][j];
		gt.c0=this.c0;
		gt.l0=this.l0;
		return gt;
	}
	
	/** Indique si la grille est correctement positionnée 
	*
	* @param grille la grille de jeu de taquin
	* @return vrai si chaque case a la bonne valeur, faux sinon
	*
	* @author Jolan
	*/
	public boolean win (){ //grille au format : [ligne] [colonne]
		int nb_count = 1; //valeur de comparaison
		int nb_case= this.getLigne()*this.getColonne();
		for (int i = 0; i<getLigne(); i++){
			for (int j = 0; j<getColonne(); j++){
				if (getTable()[i][j]!=-1)
					if (getTable()[i][j] != (nb_count%nb_case)) //modulo necessaire pour gerer le 0
						return false;
					else 
						nb_count++; //la valeur est ok, on incremente notre reference de comparaison
			}
		}
		return true;
	}

	/** Indique la parité du nb de mvm a effectuer pour arriver a la configuration de victoire
	* 
	* @param grille une grille de jeu carrée
	* @return true si le nb de mvm est pair, false sinon
	*
	* @author Thibault
	*/
	public boolean pairiteMvm(){
		int nb_case= this.getLigne()*this.getColonne();
		GrilleTaquin gt_copy=this.copyOf();
		int count=1;
		int nbDeCoup=0;
		for(int i=0; i< this.getLigne();i++){
			for(int j=0; j<this.getColonne(); j++){
				if((gt_copy.getTable()[i][j])!=-1)
					if((gt_copy.getTable()[i][j])!=count%nb_case){
						try{
							Couple<Integer, Integer> c=gt_copy.getCouple(count);
							int ligne_c=c.getFst();
							int colonne_c=c.getSnd();
							int tmp = gt_copy.getTable()[ligne_c][colonne_c];
							gt_copy.getTable()[ligne_c][colonne_c] =gt_copy.getTable()[i][j];
							gt_copy.getTable()[i][j] = tmp;	
							nbDeCoup++;
						}catch(ValInexistanteException e){
							System.out.println(e.getMessage());
						}
					}
				count++;
			}
		}
		return nbDeCoup%2==0;
	}

	/** Indique la parité du nb de mvm a effectuer pour deplacer la case vide a sa position victoire
	 * 
	 * @return 0 si le nb de mvm est pair, 1 sinon
	 * 
	 * @author Jolan
	 */
	public boolean pairVide(){
		return (Math.abs(getL0()-this.getLigne()-1) + Math.abs(getC0()-this.getColonne()-1))%2==0;
	}
	
	/** Joue un mouvement dans la grille
	 * 
	 * @param c le mouvement a effectuer pour la case vide | n:Nord | s:Sud | o:Ouest | e:Est
	 * @return La grille avec le mouvement effectué
	 * @throws MouvementImpossibleException si le mouvement est impossible ou n'existe pas
	 * @throws QuitterException si le joueur souhaite quitter 
	 * 
	 * @author Jolan
	 */
	public GrilleTaquin playMove(char c) throws MouvementImpossibleException, QuitterException{
		switch (c){
			case 'N': case 'n': 
				if (getL0()==0 || getTable()[getL0()-1][getC0()] == -1)
					throw new MouvementImpossibleException ("Mouvement impossible");
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()-1][getC0()];
					setL0(getL0() - 1);
				}
				break;
			case 'S': case 's': 
				if (getL0()== getTable().length-1 || getTable() [getL0()+1][getC0()] == -1)
					throw new MouvementImpossibleException ("Mouvement impossible");
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()+1][getC0()];
					setL0(getL0() + 1);
				}
				break;
			case 'O': case 'o': case 'W': case 'w':
				if (getC0()==0 || getTable() [getL0()][getC0()-1] == -1)
					throw new MouvementImpossibleException ("Mouvement impossible");
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()][getC0()-1];
					setC0(getC0() - 1);
				}
				break;
			case 'E': case 'e': 
				if (getC0()== getTable().length-1 || getTable() [getL0()][getC0()+1] == -1)
					throw new MouvementImpossibleException ("Mouvement impossible");
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()][getC0()+1];
					setC0(getC0() + 1);
				}
				break;
			case 'q': case 'Q':
				throw new QuitterException ();
			default : 
				throw new MouvementImpossibleException ("Mouvement impossible");
		}
		getTable()[getL0()][getC0()]=0;
		return this;
	}
	
	/** Effectue une suite de deplacement dans this
	 * 
	 * @param s1 un String qui contient une suite de mouvement à effectuer
	 * @param dynamique un booleen indiquant si on veut un affichage, ou non dynamique
	 * @return la chaine des caracteres joués
	 * @throws MouvementImpossibleException
	 * @throws QuitterException
	 * 
	 * @author Anthony
	 */
	public String playMove(String s1, boolean dynamique) throws MouvementImpossibleException, QuitterException{
		String s2 = "";
		char deplacement;
		for (int i=0; i<s1.length(); i++){
			GrilleTaquin avant = this.copyOf();
			deplacement = s1.charAt(i);
			if(dynamique)
				System.out.print((char)Event.ESCAPE + "7");//Commande d'initialisation à l'affichage dynamique
			this.playMove(deplacement);
			if(dynamique)
				System.out.print((char)Event.ESCAPE + "8");//Commande finale de l'affichage dynamique
			System.out.println(this);
			if (!this.equals(avant)){s2=s2+deplacement;}
		}
		return s2;
	}
	
	/** Melange un tableau a deux dimensions
	*
	* @author Jolan
	*/
	public void mixTab(){
		do{
			super.mixTab();
			try {
				Couple <Integer, Integer> c = getCouple(0);
				setL0(c.getFst()); setC0(c.getSnd());
			} catch (ValInexistanteException e) {
				System.out.println(e.getMessage());
			}
		}while(!containsSolution()); //si la grille n'a pas de soluc, on effectue un nouveau melange
		
	}
	
	/** Calcul la liste des successeurs d'une GrilleTaquin
	 * 
	 * @return une liste de Taquin successeurs de this
	 * 
	 * @author Jolan
	 */
	public ArrayList<GrilleTaquin> successeur (){
		ArrayList<GrilleTaquin> lesSucc= new ArrayList<GrilleTaquin> ();
		//test SUD
		try {
			lesSucc.add(this.copyOf().playMove('s'));
		} catch (MouvementImpossibleException | QuitterException e){}
		
		//test NORD
		try {
			lesSucc.add(this.copyOf().playMove('n'));
		} catch (MouvementImpossibleException | QuitterException e){}
		
		//test EST
		try {
			lesSucc.add(this.copyOf().playMove('e'));
		} catch (MouvementImpossibleException | QuitterException e){}
		
		//test OUEST
		try {
			lesSucc.add(this.copyOf().playMove('o'));
		} catch (MouvementImpossibleException | QuitterException e){}
		
		return lesSucc;
	}
	
	/** Calcul la liste des successeurs d'une GrilleTaquin en se souciant des redondances
	 * 
	 * @param c le dernier parametre joué pour arriver a cette configuration de grille
	 * @return une liste de Taquin successeurs de this sans retour arriere
	 * 
	 * @author Jolan
	 */
	public HashMap<GrilleTaquin, Character> successeur (char c){
		HashMap<GrilleTaquin,Character> lesSucc= new HashMap<GrilleTaquin,Character> ();
		//test SUD
		if(!(c=='n'))
			try {
				lesSucc.put(this.copyOf().playMove('s'),'s');
			} catch (MouvementImpossibleException | QuitterException e){}
		
		//test NORD
		if(!(c=='s'))
			try {
				lesSucc.put(this.copyOf().playMove('n'),'n');
			} catch (MouvementImpossibleException | QuitterException e){}
		
		//test EST
		if(!(c=='o'))
			try {
				lesSucc.put(this.copyOf().playMove('e'),'e');
			} catch (MouvementImpossibleException | QuitterException e){}
		
		//test OUEST
		if(!(c=='e'))
			try {
				lesSucc.put(this.copyOf().playMove('o'),'o');
			} catch (MouvementImpossibleException | QuitterException e){}
		
		return lesSucc;
	}
	
	
	/**Cree un affichage pour la grille Taquin
	 *
	 * @return une chaine contenant les valeurs de la table 
	 * 
	 * @author Jolan
	 */
	public String toString(){
		String s = super.toString();
		return s;
	}

	/** Recupere la ligne sur laquelle est située la case 0
	 * 
	 * @return la coordonnée ligne de la case 0
	 * 
	 * @author Jolan
	 */
	public int getL0() {
		return l0;
	}

	/** Fixe la ligne de la case 0
	 * 
	 * @param l0 coordonnee de la ligne 0
	 * 
	 * @author Jolan
	 */
	public void setL0(int l0) {
		this.l0 = l0;
	}

	/** Recupere la colonne sur laquelle est située la case 0
	 * 
	 * @return la coordonnée colonne de la case 0
	 * 
	 * @author Jolan
	 */
	public int getC0() {
		return c0;
	}

	/** Fixe la colonne de la case 0
	 * 
	 * @param c0 coordonnee de la colonne 0
	 * 
	 * @author Jolan
	 */
	public void setC0(int c0) {
		this.c0 = c0;
	}
	
	/** Test l'egalite avec une grille Taquin
	 * 
	 * @param gt la grille a test avec this
	 * @return vrai si elle sont egales, faux sinon
	 * 
	 * @author Jolan
	 */
	public boolean equals (GrilleTaquin gt){
		return super.equals(gt);
	}

	/**
	 * 
	 */
	public int compareTo(Grille arg0) {
		if (this.equals(arg0))
			return 0;
		else
			return -1;
	}
	
	/** Trie une GrilleTaquin
	 * 
	 * @return La grille de taquin correctement rangée
	 * 
	 * @author Benoit
	 */
	public GrilleTaquin sort(){
		int l = this.getLigne();
		int c = this.getColonne();
		int[][] table = this.getTable();
		int[][] tableRange = new int[l][c];
		GrilleTaquin range = new GrilleTaquin(l, c);
		int count = 1;
		for(int i=0; i<l; i++){
			for(int j=0; j<c; j++){
				if(table[i][j] == -1){
					tableRange[i][j] = -1;
				}else{
					tableRange[i][j] = count;
					count++;
				}
			}
		}
		tableRange[l-1][c-1]=0;
		range.setTable(tableRange);
		return range;
	}
	
	/** Compare les coordonnées de la case 0 et retourne le mouvement qui a du etre effectué de this vers gt
	 * 
	 * @param gt Grille de taquin apres mouvement
	 * 
	 * @return Un caractère correspondant au mouvement effectué par l'utilisateur pour déplacer la case vide
	 * 
	 * @author Benoit
	 */
	public char compZero(GrilleTaquin gt){
		if( this.l0 > gt.l0 )
			return 'N';
		if( this.l0 < gt.l0 )
			return 'S';
		if( this.c0 > gt.c0 )
			return 'O';
		else
			return 'E';
	}
	
	
}


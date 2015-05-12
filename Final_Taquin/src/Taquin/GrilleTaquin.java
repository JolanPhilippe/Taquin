package Taquin;
import java.util.ArrayList;
import java.util.HashMap;

import Outils.Couple;
import lesExceptions.MouvementImpossibleException;
import lesExceptions.QuitterException;
import lesExceptions.ValInexistanteException;

public class GrilleTaquin extends Grille{
	
	private int l0;
	private int c0;
	
	public GrilleTaquin (String file){
		super(file);
		try {
			setL0((int) getCouple(0).getFst());
			setC0((int) getCouple(0).getSnd());
		} catch (ValInexistanteException e) {
			e.printStackTrace();
		}
	}
	
	public GrilleTaquin(int ligne, int colonne) {
		super(ligne, colonne);
		this.setL0(ligne-1);
		this.setC0(colonne-1);
	}

	/** Indique si la le jeu créé ainsi comporte une solution
	 * 
	 * @return
	 */
	private boolean containsSolution() {
		return pairiteMvm()==pairVide();
	}

	/** Creer une copie de 
	 * 
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
	* @since 1.0
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
	* @since 1.0
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
	* @param grille une grille de jeu carrée
	* @return 0 si le nb de mvm est pair, 1 sinon
	*
	* @author Jolan
	* @since 1.0
	*/
	public boolean pairVide(){
		return (Math.abs(getL0()-this.getLigne()-1) + Math.abs(getC0()-this.getColonne()-1))%2==0;
	}
	
	/** Joue un mouvement 
	*
	* @param grille la grille de jeu
	* @param n la valeur cherchée qui doit etre presente dans grille
	* @throws MouvementImpossibleException si le mouvement n'est pas possible
	*
	* @author Jolan
	 * @return 
	* @throws QuitterException 
	* @since 1.0
	*/
	public GrilleTaquin playMove(char c) throws MouvementImpossibleException, QuitterException{
		switch (c){
			case 'N': case 'n': 
				if (getL0()==0 || getTable()[getL0()-1][getC0()] == -1)
					throw new MouvementImpossibleException ();
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()-1][getC0()];
					setL0(getL0() - 1);
				}
				break;
			case 'S': case 's': 
				if (getL0()== getTable().length-1 || getTable() [getL0()+1][getC0()] == -1)
					throw new MouvementImpossibleException ();
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()+1][getC0()];
					setL0(getL0() + 1);
				}
				break;
			case 'O': case 'o': case 'W': case 'w':
				if (getC0()==0 || getTable() [getL0()][getC0()-1] == -1)
					throw new MouvementImpossibleException ();
				else{
					getTable()[getL0()][getC0()]=getTable()[getL0()][getC0()-1];
					setC0(getC0() - 1);
				}
				break;
			case 'E': case 'e': 
				if (getC0()== getTable().length-1 || getTable() [getL0()][getC0()+1] == -1)
					throw new MouvementImpossibleException ();
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
	
	/** Melange un tableau a deux dimensions
	*	
	* @param grille une grille dont on veux melanger les valeurs	
	*
	* @author Jolan
	* @since 1.0
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
	
	/**
	 * 
	 * @return
	 * 
	 * @author Jolan
	 * @since 1.0 
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
	
	/**
	 * 
	 * @return
	 * 
	 * @author Jolan
	 * @since 1.0 
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
	
	
	/**
	 * @author Jolan
	 * @since 1.1
	 */
	public String toString(){
		String s = super.toString();
		return s;
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public int getL0() {
		return l0;
	}

	/**
	 * 
	 * @param l0
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public void setL0(int l0) {
		this.l0 = l0;
	}

	/**
	 * 
	 * @return
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public int getC0() {
		return c0;
	}

	/**
	 * 
	 * @param c0
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public void setC0(int c0) {
		this.c0 = c0;
	}
	
	public boolean equals (GrilleTaquin gt){
		return super.equals(gt);
	}

	public int compareTo(Grille arg0) {
		if (this.equals(arg0))
			return 0;
		else
			return -1;
	}
	
	/** Permet de fournir la grille de taquin finale d'une grille de taquin mélangée
	 * 
	 * @param t La grille de taquin à ranger
	 * @return La grille de taquin correctement rangée
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
	
	/**
	 * 
	 * @param t1 Grille de taquin avant mouvement
	 * @param t2 Grille de taquin après mouvement
	 * 
	 * @return Un caractère correspondant au mouvement effectué par l'utilisateur pour déplacer la case vide
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


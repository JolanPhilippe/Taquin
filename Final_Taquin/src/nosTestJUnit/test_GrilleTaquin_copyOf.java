package nosTestJUnit;

import org.junit.Test;

import Taquin.Game;
import Taquin.GrilleTaquin;
import Taquin.MouvementImpossibleException;
import Taquin.QuitterException;

/** Tests pour la methode copyOf de la classe GrilleTaquin
 * 
 * @author Jolan
 *
 */
public class test_GrilleTaquin_copyOf {
	@Test
	public void testCopyOf1(){ 
		//test pour verifier la non-liaison des valeurs
		System.out.println("####################\nTEST 1:");
		GrilleTaquin grille1 = Game.initGame(2);
		GrilleTaquin grille2 = grille1.copyOf();
		System.out.println("\ngrille1:\n"+grille1);
		System.out.println("\ngrille2:\n"+grille2);
		System.out.println("\nChangement sur la grille 2....\n");
		//changement de toutes les valeurs du tab de la grille2
		for(int i=0; i<grille2.getLigne(); i++)
			for(int j=0; j<grille2.getColonne(); j++)
				grille2.getTable()[i][j]++;
		
		System.out.println("\ngrille1:\n"+grille1);
		System.out.println("\ngrille1:\n"+grille2);
		System.out.println("####################\n");
	}
	
	@Test
	public void testCopyOf2() throws MouvementImpossibleException, QuitterException{
		//test pour verifier la non-liaison des variables de coordonnées de la case 0
		System.out.println("####################\nTEST 2:");
		GrilleTaquin grille1 = Game.initGame(2);
		GrilleTaquin grille2 = grille1.copyOf();
		System.out.println("\ngrille1:\n"+grille1);
		System.out.println("\ngrille2:\n"+grille2);
		System.out.println("\nDeplacement sur la grille 2....\n");
		try {
			grille1.playMove('n');
		} catch (MouvementImpossibleException e) {
			grille1.playMove('s'); //si nord impossible, sud l'est forcement
		} catch (QuitterException e) {
			e.printStackTrace();
		}
		
		System.out.println("\ngrille1:\n"+grille1+" l0:"+grille1.getL0()+" c0:"+grille1.getC0());
		System.out.println("\ngrille2:\n"+grille2+" l0:"+grille2.getL0()+" c0:"+grille2.getC0());
		System.out.println("####################\n");
	}
}

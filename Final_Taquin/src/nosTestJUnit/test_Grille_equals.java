package nosTestJUnit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Jeu.Game;
import Taquin.GrilleTaquin;

public class test_Grille_equals {

	@Test
	public void testEquals1() {
		GrilleTaquin grille1 = Game.initGame(2,2);
		GrilleTaquin grille2 = grille1.copyOf();
		for(int i=0; i<grille2.getLigne(); i++)
			for(int j=0; j<grille2.getColonne(); j++)
				grille2.getTable()[i][j]++;
		assertFalse(grille1.equals(grille2));
	}

	@Test
	public void testEquals2() {
		GrilleTaquin grille1 = Game.initGame(2,2);
		GrilleTaquin grille2 = grille1.copyOf();
		assertTrue(grille1.equals(grille2));
	}
}

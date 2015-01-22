package testsUnitaires;

import static org.junit.Assert.*;
import nosClasses.Taquin;

import org.junit.Test;

public class testGrille {
	
	@Test
	/** Test de presence de solution pour les 3 grilles possibles non mélangées
	 */
	public void testContainsSolutionNotMixed() {
		int[][]table3 = Taquin.createGrille(3,false);
		int[][]table4 = Taquin.createGrille(4,false);
		int[][]table5 = Taquin.createGrille(5,false);
		boolean solution = Taquin.containsSolution(table3) && Taquin.containsSolution(table4) && Taquin.containsSolution(table5);
		assertTrue(solution);
	}

	@Test
	/** Test de presence de solution pour les 3 grilles possibles mélangées
	 */
	public void testContainsSolutionMixed() {
		int[][]table3 = Taquin.createGrille(3,true);
		int[][]table4 = Taquin.createGrille(4,true);
		int[][]table5 = Taquin.createGrille(5,true);
		boolean solution = Taquin.containsSolution(table3) && Taquin.containsSolution(table4) && Taquin.containsSolution(table5);
		assertTrue(solution);
	}
	
	@Test
	/** Test si les tables non mélangées sont des tables gagnantes
	 */
	public void testWinTableNotMixed() {
		int[][]table3 = Taquin.createGrille(3,false);
		int[][]table4 = Taquin.createGrille(4,false);
		int[][]table5 = Taquin.createGrille(5,false);
		boolean win = Taquin.win(table3) && Taquin.win(table4) && Taquin.win(table5);
		assertTrue(win);
	}
	
	@Test
	/** Test si les tables mélangées sont des tables non gagnantes
	 */
	public void testWinTableMixed() {
		int[][]table3 = Taquin.createGrille(3,true);
		int[][]table4 = Taquin.createGrille(4,true);
		int[][]table5 = Taquin.createGrille(5,true);
		boolean win = Taquin.win(table3) || Taquin.win(table4) || Taquin.win(table5);
		assertFalse(win);
	}

}

package nosTestJUnit;

import java.util.ArrayList;

import org.junit.Test;

import Taquin.Game;
import Taquin.GrilleTaquin;

public class test_GrilleTaquin_successeur {

	@Test
	public void testSuccesseur1() {
		System.out.println("####################\nTEST 1: grille 1x1");
		GrilleTaquin grille = Game.initGame(1);
		System.out.println("------\n"+grille+"\n------");
		ArrayList<GrilleTaquin> lesSucc = grille.successeur();
		
		for (GrilleTaquin x : lesSucc)
			System.out.println(x.toString());
		System.out.println("####################\n");
	}
	
	@Test
	public void testSuccesseur2() {
		System.out.println("####################\nTEST 2: grille 2x2");
		GrilleTaquin grille = Game.initGame(2);
		System.out.println("------\n"+grille+"\n------");
		ArrayList<GrilleTaquin> lesSucc = grille.successeur();
		
		for (GrilleTaquin x : lesSucc)
			System.out.println(x.toString());
		System.out.println("####################\n");
	}
	
	@Test
	public void testSuccesseur3() {
		System.out.println("####################\nTEST 3: grille 3x3");
		GrilleTaquin grille = Game.initGame(3);
		System.out.println("------\n"+grille+"\n------");
		ArrayList<GrilleTaquin> lesSucc = grille.successeur();
		
		for (GrilleTaquin x : lesSucc)
			System.out.println(x.toString());
		System.out.println("####################\n");
	}

}

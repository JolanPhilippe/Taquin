package nosTestJUnit;

import java.util.TreeMap;
import lesGraphes.GrapheListe;
import org.junit.Test;
import Taquin.GrilleTaquin;

public class test_Graphe {
	
	/*@Test
	public void test(){
		System.out.println("TEST CREATION GRAPHE TYPE CHAR");
		GrapheListe<Character> graphe = new GrapheListe<Character>();
		
		graphe.ajouterSommet('r');
		graphe.ajouterSommet('s');
		graphe.ajouterSommet('t');
		graphe.ajouterSommet('u');
		graphe.ajouterSommet('v');
		graphe.ajouterSommet('w');
		graphe.ajouterSommet('x');
		graphe.ajouterSommet('y');
		
		graphe.ajouterArc('r', 'v'); graphe.ajouterArc('v', 'r');
		graphe.ajouterArc('r', 's'); graphe.ajouterArc('s', 'r');
		graphe.ajouterArc('s', 'w'); graphe.ajouterArc('w', 's');
		graphe.ajouterArc('t', 'w'); graphe.ajouterArc('w', 't');
		graphe.ajouterArc('x', 'w'); graphe.ajouterArc('w', 'x');
		graphe.ajouterArc('t', 'x'); graphe.ajouterArc('x', 't');
		graphe.ajouterArc('t', 'u'); graphe.ajouterArc('u', 't');
		graphe.ajouterArc('x', 'y'); graphe.ajouterArc('y', 'x');
		
		System.out.println(graphe);
	}*/

	@Test
	public void testGrapheTaquin() {
		System.out.println("TEST CREATION GRAPHE TYPE TAQUIN");
		GrapheListe<GrilleTaquin> grapheL = new GrapheListe<GrilleTaquin>();

		GrilleTaquin gt2 = new GrilleTaquin(2, 2);
		GrilleTaquin gt3 = new GrilleTaquin(3, 3);
		GrilleTaquin gt4 = new GrilleTaquin(4, 4);
		grapheL.ajouterSommet(gt2);
		grapheL.ajouterSommet(gt3);
		grapheL.ajouterSommet(gt4);
		System.out.println("GRAPHE:\n"+grapheL);
		
		/*
		grapheL.ajouterArc(gt3,gt2);
		grapheL.ajouterArc(gt2,gt2);	
		
		System.out.println("GRAPHE:\n"+grapheL);*/
		
	}
	/*
	@Test
	public void testPracoursLargeur() {
		System.out.println("TEST PARCOURS LARGEUR");
		GrapheListe<Character> graphe = new GrapheListe<Character>();
		
		graphe.ajouterSommet('r');
		graphe.ajouterSommet('s');
		graphe.ajouterSommet('t');
		graphe.ajouterSommet('u');
		graphe.ajouterSommet('v');
		graphe.ajouterSommet('w');
		graphe.ajouterSommet('x');
		graphe.ajouterSommet('y');
		
		graphe.ajouterArc('r', 'v'); graphe.ajouterArc('v', 'r');
		graphe.ajouterArc('r', 's'); graphe.ajouterArc('s', 'r');
		graphe.ajouterArc('s', 'w'); graphe.ajouterArc('w', 's');
		graphe.ajouterArc('t', 'w'); graphe.ajouterArc('w', 't');
		graphe.ajouterArc('x', 'w'); graphe.ajouterArc('w', 'x');
		graphe.ajouterArc('t', 'x'); graphe.ajouterArc('x', 't');
		graphe.ajouterArc('t', 'u'); graphe.ajouterArc('u', 't');
		graphe.ajouterArc('x', 'y'); graphe.ajouterArc('y', 'x');
		
		System.out.println("PARCOURS LARGEUR:");
		graphe.parcoursLarg('s');
		TreeMap<Character, Character> lesPeres = graphe.getPeres();
		System.out.println(lesPeres+"\n");
	}
	
	@Test
	public void testPracoursProfondeur() {
		System.out.println("TEST PARCOURS PROFONDEUR");
		GrapheListe<Character> graphe = new GrapheListe<Character>();
		
		graphe.ajouterSommet('r');
		graphe.ajouterSommet('s');
		graphe.ajouterSommet('t');
		graphe.ajouterSommet('u');
		graphe.ajouterSommet('v');
		graphe.ajouterSommet('w');
		graphe.ajouterSommet('x');
		graphe.ajouterSommet('y');
		
		graphe.ajouterArc('r', 'v'); graphe.ajouterArc('v', 'r');
		graphe.ajouterArc('r', 's'); graphe.ajouterArc('s', 'r');
		graphe.ajouterArc('s', 'w'); graphe.ajouterArc('w', 's');
		graphe.ajouterArc('t', 'w'); graphe.ajouterArc('w', 't');
		graphe.ajouterArc('x', 'w'); graphe.ajouterArc('w', 'x');
		graphe.ajouterArc('t', 'x'); graphe.ajouterArc('x', 't');
		graphe.ajouterArc('t', 'u'); graphe.ajouterArc('u', 't');
		graphe.ajouterArc('x', 'y'); graphe.ajouterArc('y', 'x');
		
		System.out.println("PARCOURS PROFONDEUR:");
		graphe.parcoursProf();
		TreeMap<Character, Character> lesPeres = graphe.getPeres();
		System.out.println(lesPeres);
	}*/
	
	
}

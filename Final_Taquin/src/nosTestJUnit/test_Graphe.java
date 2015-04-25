package nosTestJUnit;

import java.util.TreeMap;

import lesGraphes.Couple;
import lesGraphes.GrapheListe;
import lesGraphes.Triplet;

import org.junit.Test;

import Taquin.Game;
import Taquin.GrilleTaquin;

public class test_Graphe {
	
	@Test
	public void testGrapheTaquin() {
		GrapheListe<GrilleTaquin> graphe = new GrapheListe<GrilleTaquin>();
		
		GrilleTaquin gt1 = Game.initGame(1);
		GrilleTaquin gt2 = Game.initGame(2);
		GrilleTaquin gt3 = Game.initGame(3);

		graphe.ajouterSommet(gt1);
		graphe.ajouterSommet(gt2);
		graphe.ajouterSommet(gt3);
		
		
		System.out.println(graphe);
		
	}	
	
	@Test
	public void testPracoursLargeur() {
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
		
		System.out.println("PARCOURS LARGEUR\n"+graphe);
		graphe.parcoursLarg('s');
		Couple<TreeMap<Character, Character>, TreeMap<Character, Integer>> c = graphe.parcoursLarg('s');
		TreeMap<Character, Character> lesPeres = c.getFst();
		System.out.println(lesPeres);
	}
	
	@Test
	public void testPracoursProfondeur() {
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
		
		System.out.println("PARCOURS PROFONDEUR\n"+graphe);
		Triplet<TreeMap<Character, Character>, TreeMap<Character, Integer>, TreeMap<Character, Integer>> c = graphe.parcoursProf();
		TreeMap<Character, Character> lesPeres = c.getFst();
		System.out.println(lesPeres);
	}
	
}

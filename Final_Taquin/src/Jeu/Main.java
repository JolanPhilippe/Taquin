package Jeu;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeMap;

import lesExceptions.MouvementImpossibleException;
import lesExceptions.QuitterException;
import Outils.Couple;
import Outils.Tools;
import Taquin.*;

/** Programme principal MAIN
 * 
 * @param arguments
 * @author Jolan & Benoit
 */

public class Main {

	public static void main(String[] args){
		switch(args[0]){
		case "-name":
			System.out.println(name());
			break;
			
		case "-h":
			System.out.println(rules());
			break;
			
		case "-sol":
			if(new GrilleTaquin(args[1]).containsSolution())
				System.out.println("Le fichier "+args[1]+" contient une solution");
			else
				System.out.println("Le fichier "+args[1]+" ne contient pas de soluion");
			break;
			
		case "-joue":
			GrilleTaquin gt1;
			switch (args[1]){
			case "-r":
				gt1 = Play.initPlay();
				break;
			default :
				String f1 = args[1];
				gt1 = Play.initPlay(f1);
			}
			Play.playTaquin(gt1);
			break;
		case "-cal": case "-anime":
			int delai = Integer.parseInt(args[1]);
			String f2 = args[3];
			String mode=args[2];
			GrilleTaquin gt2 = new GrilleTaquin(f2);
			String sol = Solve.SolveTaquin(gt2, mode, delai).getFst();
			sol = Tools.elagage(sol, gt2.getLigne(), gt2.getColonne());
			try {
				if (!sol.equals("OVERTIME")) gt2.playMove(sol, args[0].equals("-anime"), true);
				else System.out.println(sol);
			} catch (MouvementImpossibleException | QuitterException e) {}
			break;
			
		case "-stat":
			LinkedList<String> lesModes = new LinkedList<String>();
			TreeMap<String,String> stats1 = new TreeMap<String,String>();
			int delai2 = Integer.parseInt(args[1]);
			String f3="";
			switch (args[2]){
				case "file":case"pile":case"manhattan":case"pmanhattan":case"choix":
					lesModes.add(args[2]); 
					f3=args[3];
					break;
				default:
					lesModes.add("file");lesModes.add("pile");
					lesModes.add("manhattan");lesModes.add("pmanhattan");
					lesModes.add("choix");
					f3=args[2];
			}
			GrilleTaquin gt3 = new GrilleTaquin(f3);
			for(String modeRes : lesModes){
				Date d1= new Date();
				Couple<String,Integer> sol1 = Solve.SolveTaquin(gt3, modeRes, delai2);
				String solution = sol1.getFst();
				if(!solution.equals("OVERTIME")) {
					solution = Tools.elagage(solution, gt3.getLigne(), gt3.getColonne());
					stats1.put(modeRes+"_tailleSolution", ""+solution.length());
					int nb_coup1=sol1.getSnd();
					stats1.put(modeRes+"_positionsParcourues",""+nb_coup1);
					Date d2 = new Date();
					long s1 = d1.getTime()/1000;
					long s2 = d2.getTime()/1000;
					stats1.put(modeRes+"_tempsExecution", ""+(s2-s1)+"s");
				}else{
					stats1.put(modeRes+"_tailleSolution", "OVERTIME");
					stats1.put(modeRes+"_positionParcourues", "OVERTIME");
					stats1.put(modeRes+"_tempsExecution", "OVERTIME");
				}
			}
			Tools.toHtml(stats1);
			break;
		case "-aleatoire": 
			break;
		default: System.out.println("ERROR COMMAND NOT FOUND\n"+rules());
		}
	}

	private static String rules() {
		String s="";
		s+="\t-name\nafficher les noms et prénoms\n\n";
		s+="\t-sol -fichier.taq -j\n";
		s+="tester si le jeu a une solution a partir de la position initiale donnée dans le fichier fichier.taq\n\n";
		s+="\t-joue fichier.taq\n";
		s+="jouer au jeu de taquin avec la configuration de fichier.taq\n\n";
		s+="\t-cal delai algo fichier.taq\n";
		s+="calculer une solution en utilisant l'algorithme 'algo'. "
				+ "Le programme renvoie une solution (si possible) sous la forme d'une liste de positions (une par ligne). "
				+ "Si le temps d'exécution de votre programme excède la durée 'delai', il doit s'interrompre brutalement.\n\n";
		s+="\t-anime delai algo fichier.taq\n";
		s+="est identique à la commande précédente sauf que la solution est présentée sous la forme d'une animation\n\n";
		s+="\t-stat delai algo fichier.taq\n";
		s+="est identique à la commande précédente sauf que le programme renvoie des statistiques sur l'exécution du programme : "
				+ "taille de la solution, nombre de positions parcourues et temps d'exécution\n\n";
		s+="\t-stat delai fichier.taq\n";
		s+="applique la commande précédente à tous les algorithmes et revoei sous la forme d'un tableau html toutes les "
				+ "statistiques\n\n";
		
		s+="\t-aleatoire n largeur hauteur delai fichier.taq\n";
		s+="applique à tous les algorithmes à 'n' positions initiales générées de façon aléatoire "
				+ "de taille 'largeur' x 'hauteur'. Donnera pour chaque algorithme : le nombre de problème résolut, "
				+ "et les moyennes des tailles des solutions, du nombre de solutions parcourues et du temps d'exécution."
				+ "Les résultats seront présentés sous la forme d'un tableau html\n\n";
		return s;
	}
	
	private static String name(){
		return "CHARPIGNON Thibault\nGALLET Benoit\nNEAU Anthony\nPHILIPPE Jolan";
	}

}

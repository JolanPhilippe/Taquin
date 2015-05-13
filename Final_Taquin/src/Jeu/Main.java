package Jeu;
import Taquin.*;

public class Main {

	public static void main(String[] args){
		switch(args[0]){
		case "-name":
			System.out.println("CHARPIGNON Thibault");
			System.out.println("GALLET Benoit");
			System.out.println("NEAU Anthony");
			System.out.println("PHILIPPE Jolan");
			break;
			
		case "-h":
			System.out.println("-name");
			System.out.println("afficher les noms et pr�noms");
			System.out.println();
			
			System.out.println("-sol -fichier.taq -j");
			System.out.println("tester si le jeu a une solution a partir de la position initiale donn�e dans le fichier fichier.taq");
			System.out.println();
			
			System.out.println("-joue fichier.taq");
			System.out.println("jouer au jeu de taquin avec la configuration de fichier.taq");
			System.out.println();
			
			System.out.println("-cal delai algo fichier.taq");
			System.out.println("calculer une solution en utilisant l'algorithme 'algo'. "
					+ "Le programme renvoie une solution (si possible) sous la forme d'une liste de positions (une par ligne). "
					+ "Si le temps d'ex�cution de votre programme exc�de la dur�e 'delai', il doit s'interrompre brutalement.");
			System.out.println();
			System.out.println("-anime delai algo fichier.taq");
			System.out.println("est identique � la commande pr�c�dente sauf que la solution est pr�sent�e sous la forme d'une animation");
			System.out.println();
			
			System.out.println("-stat delai algo fichier.taq");
			System.out.println("est identique � la commande pr�c�dente sauf que le programme renvoie des statistiques sur l'ex�cution du programme : "
					+ "taille de la solution, nombre de positions parcourues et temps d'ex�cution");
			System.out.println();
			
			System.out.println("-stat delai fichier.taq");
			System.out.println("applique la commande pr�c�dente � tous les algorithmes et revoei sous la forme d'un tableau html toutes les statistiques");
			System.out.println();
			
			System.out.println("-aleatoire n largeur hauteur delai fichier.taq");
			System.out.println("applique � tous les algorithmes � 'n' positions initiales g�n�r�es de fa�on al�atoire "
					+ "de taille 'largeur' x 'hauteur'. Donnera pour chaque algorithme : le nombre de probl�me r�solut, "
					+ "et les moyennes des tailles des solutions, du nombre de solutions parcourues et du temps d'ex�cution."
					+ "Les r�sultats seront pr�sent�s sous la forme d'un tableau html");
			break;
			
		case "-sol":
			String f1 = args[1];
			GrilleTaquin gt1 = new GrilleTaquin(f1);
			if(gt1.containsSolution())
				System.out.println("Le fichier " + f1 + " contient une solution");
			else
				System.out.println("Le fichier " + f1 + " ne contient pas de soluion");
			break;
			
		case "-joue":
			GrilleTaquin gt2;
			switch (args[1]){
			case "-r":
				gt2 = Play.initPlay();
				break;
			default :
				String f = args[1];
				gt2 = Play.initPlay(f);
			}
			Play.playTaquin(gt2);
			break;
		
		case "-cal":
			//int d1 = Integer.parseInt(args[4]);
			// appeller l'algorithme passe dans args[5] a partir du fichier dans args[6]
			// le delai de resolution ne doit pas depasser delai (args[4])
			break;
			
		case "-anime":
			//int d2 = Integer.parseInt(args[4]);
			// appeller l'algorithme passe dans args[5] a partir du fichier dans args[6]
			// le delai de resolution ne doit pas depasser delai (args[4])
			break;
			
		case "-stat":
			
		}
	}
}

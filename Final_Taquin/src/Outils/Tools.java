package Outils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Tools {

	static Scanner sc = new Scanner (System.in);
	
	/** Fait faire un choix d'un nombre entier a l'utilisateur entre min et infini
	 * 
	 * @param min entier minimal pouvant etre choisi
	 * @param max entier maximal pouvant etre choisi
	 * @return l'entier choisi
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static int choiceInt(int min){
		int n=0;
		do{
			n = sc.nextInt();
			if(n<min)
				System.out.println("Choix impossible, valeur inexistante");
		}while (n<min);
		return n;
	}
	
	/**Programme d'�lagage des s�quences de mouvement
	 * 
	 * @param String Suite de mouvement
	 * @param int hauteur
	 * @param int largeur
	 * @return String (chaine de caract�re effectuant les m�mes d�placements mais de fa�on optimale)
	 * @author Anthony
	 */

	public static String elagage(String s1, int h, int l) {
		String s2 = s1;
		int p = 2*h+2*l-5;//Calcul du p�rim�tre d'un taquin -1
		int min = min(h,l);//Prise du minimum entre la hauteur et la largeur
		for(int i=1; i<h; i++){
			s2= s2.replaceAll(multChar("NS",i), "");//Permet de remplacer toutes les combinaisons de la forme "NS" "NNSS" "NNNSSS"...
			s2= s2.replaceAll(multChar("SN",i), "");//Idem pour "SN"
		}
		for(int j=1; j<l; j++){
			s2= s2.replaceAll(multChar("OE",j), "");//Permet de remplacer toutes les combinaisons de la forme "OE" "OOEE" ...
			s2= s2.replaceAll(multChar("EO",j), "");//Idem pour "EO"
		}
		for(int m=1; m<min; m++){
			/*Les combinaisons suivantes correspondent aux suites de mouvements qui effectuent des tours
			 *  et qui appliqu� n fois reviennent � ne rien bouger sur le jeu de taquin, les lignes suivantes
			 *  supprimes donc ces combinaisons*/
			s2= s2.replaceAll(multString(multCharString("OSENOSENOSEN",h,l),p),""); 
			s2= s2.replaceAll(multString(multCharString("ENOSENOSENOS",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("ESONESONESON",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("ONESONESONES",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("NESONESONESO",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("NOSENOSENOSE",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("SENOSENOSENO",h,l),p),"");
			s2= s2.replaceAll(multString(multCharString("SONESONESONE",h,l),p),"");
			/* Les lignes suivantes correspondent � des mouvements de tours qui peuvent et qui sont donc simplifi�s */
			s2= s2.replaceAll(multCharString("ONESONES",h,l),multCharString("NOSE",h,l));
			s2= s2.replaceAll(multCharString("NOSENOSE",h,l),multCharString("ONES",h,l));
			s2= s2.replaceAll(multCharString("NESONESO",h,l),multCharString("ENOS",h,l));
			s2= s2.replaceAll(multCharString("ENOSENOS",h,l),multCharString("NESO",h,l));
			s2= s2.replaceAll(multCharString("ESONESON",h,l),multCharString("SENO",h,l));
			s2= s2.replaceAll(multCharString("SONESONE",h,l),multCharString("OSEN",h,l));
			s2= s2.replaceAll(multCharString("OSENOSEN",h,l),multCharString("SONE",h,l));
			s2= s2.replaceAll(multCharString("SENOSENO",h,l),multCharString("ESON",h,l));
			h--;
			l--;
		}
		if(!s1.equals(s2)) {
			return elagage(s2, h, l);//Rappel r�cursive de la fonction d'�lagage tant que l'�lagage supprime des combinaisons inutiles
		}else{
			return s2;
		}
	}
	
	/**Programme de multiplication de chaine de caract�res 
	 * 
	 * @param s un String contenant un suite de mouvement
	 * @param int p�rim�tre
	 * @return String la m�me chaine multipli�e par le perimetre d'un taquin qu'il soit carr�e ou rectangulaire
	 * @author Anthony
	 */
	
	public static String multString (String s, int x){
		String c = "";
		for (int i = 0; i<x; i++)
			c +=s;
		return c;
	}
	
	/** Multiplication de caract�res
	 * 
	 * @param s suite de mouvement
	 * @param h hauteur
	 * @param l largeur
	 * @return String
	 * @author Anthony
	 */
	
	public static String multCharString (String s, int h, int l){
		String mouv = "";
		char c;
			for (int j=0; j<s.length(); j++){
				c = s.charAt(j);
				if (c=='N'||c=='S'){
					for (int r=1; r<h; r++)
						mouv += c;
				}
				if (c=='O'||c=='E'){
					for (int r=1; r<l; r++)
						mouv += c;
				}
			}
		return mouv;	
	}
	
	/** Multiplication de caract�res
	 * 
	 * @param s chaine de caractere repr�sentant une suite de mouvement
	 * @param x int
	 * @return la chaine avec la multiplication de ses caract�res
	 * @author Anthony
	 */
	
	public static String multChar (String s, int x){
		String mouv = "";
		char c;
		for (int j=0; j<s.length(); j++){
				c = s.charAt(j);
				if (c=='N'||c=='S'){
					for (int r=0; r<x; r++)
						mouv += c;
				}
				if (c=='O'||c=='E'){
					for (int r=0; r<x; r++)
						mouv += c;
				}
		}
		return mouv;	
	}
	
	/** Minimum entre deux entiers
	 * 
	 * @param a entier
	 * @param b entier
	 * @return minimum des deux entiers
	 * @author Anthony
	 */
	
	public static int min(int a, int b){
		if(a<b)
			return a;
		else
			return b;
	}
	

	/** Convertit une map en un fichier HTML
	 * 
	 * @param tab Treemap
	 * @author Jolan
	 */
	
	
	public static void toHtml(TreeMap<String, String> tab){
		String html = "<html lang=\"fr\" xml:lang=\"fr\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
				+ "<head>\n"
				+ "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>\n"
				+ "<title>Statistique</title>\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />"
				+ "</head>\n";
		html+="<body>\n"
				+ "<table id=\"tab\">\n"
				+ "<colgroup>\n"
				+ "<col span=\"1\" id=\"navcol\">\n"
				+ "<col span=\"1\" id=\"numcol\">\n"
				+ "</colgroup>\n"
				+ "<caption>Statistiques</caption>\n";
		for (String s : tab.keySet()){
			html+="\n<tr><td>"+s+"</td><td>"+tab.get(s)+"</td></tr>";
		}
		html+="</table></body></html>";
		FileWriter fichierHtml;
		try {
			fichierHtml = new FileWriter("Stats.html");
			fichierHtml.write(html);
			fichierHtml.close();
		} catch (IOException e) {}

		String css="#navcol{\nwidth:200px;\n}\n"
				+ "#numcol{\nwidth:150px;\n}\n"
				+ "#tab{\nmargin: auto;\n border: #DDEEFF 2px solid;\n border-collapse: separate;"
				+ "\nborder-spacing:2px;\nempty-cells:hide;\n}\n"
				+ "#tab caption{\nbackground-color:#DDEEFF\n}\n"
				+ "#tab th{\ncolor: #996600; \nbackground-color: #FFCC66; \nborder: #FFCC66 1px solid;\n}\n"
				+ "#tab td{ \nborder: #DDEEFF 1px solid; \nmargin-left: 10px;\n}\n"
				+ "#tab tr:nth-child(even){\n background-color: #DDEEFF;"
				+ "\n}\n";
		FileWriter fichierCSS;
		try {
			fichierCSS = new FileWriter("style.css");
			fichierCSS.write(css);
			fichierCSS.close();
		} catch (IOException e) {}	
	}
}

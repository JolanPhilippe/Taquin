package Outils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

public class Tools {

	static Scanner sc = new Scanner(System.in);
	/** Fait faire un choix d'un nombre entier a l'utilisateur entre min et max
	 * 
	 * @param min entier minimal pouvant etre choisi
	 * @param max entier maximal pouvant etre choisi
	 * @return l'entier choisi
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public static int choiceInt(int min, int max){
		int n=0;
		do{
			try{
				n = sc.nextInt();
			}catch (InputMismatchException ime){
				n=-1;
			}
			if(n<min || n>max)
				System.out.println("Choix impossible");
		}while (n<min || n>max);
		return n;
	}
	
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
	
	public static String elagage(String s1) {
		String s2 = s1;
		s2.replaceAll("NS", ""); 
		s2.replaceAll("SN", "");
		s2.replaceAll("EO", "");
		s2.replaceAll("OE", "");
		s2.replaceAll("NOSENOSENOSE", "");
		s2.replaceAll("ONESONESONES", "");
		if(!s1.equals(s2)) return elagage(s2);
		else return s2;
	}

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
	
	public static void main(String[]args){
		TreeMap<String,String> tab = new TreeMap<String,String>();
		tab.put("J", "swag");
		tab.put("M", " pas swag");
		tab.put("A", "swag");
		tab.put("T", "swag");
		tab.put("B", "swag");
		toHtml(tab);
	}
	
}
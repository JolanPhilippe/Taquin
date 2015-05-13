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
		String html = "<html>\n<head>\n<meta http-equiv=\"content-type\" "
				+ "content=\"text/html; charset=utf-8\"/>\n<title>Statistique"
				+ "\n</title>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /></head>";
		html+="\n<body>\n<table id=\"tab\">\n<colgroup>\n<col span=\"1\" id=\"navcol\">"
				+ "\n<col span=\"1\" id=\"numcol\">\n</colgroup>\n<caption>Statistique</caption>";
		for (String s : tab.keySet()){
			html+="\n<tr><th>"+s+"</th><th>"+tab.get(s)+"</th></tr>";
		}
		FileWriter fichierHtml;
		try {
			fichierHtml = new FileWriter("Stats.html");
			fichierHtml.write(html);
			fichierHtml.close();
		} catch (IOException e) {}
		
		String css="test";
		
		FileWriter fichierCSS;
		try {
			fichierCSS = new FileWriter("style.css");
			fichierCSS.write(css);
			fichierCSS.close();
		} catch (IOException e) {}	
	}
	
	
	
}
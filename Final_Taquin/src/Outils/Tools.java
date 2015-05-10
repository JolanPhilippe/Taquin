package Outils;

public class Tools {

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
	
	public static void main(String[]args){
		String s="ENSOEOEOEONSNSAA";
		System.out.println(elagage(s));
	}

}

package lesGraphes;

@SuppressWarnings("serial")
public class ElementInexistantException extends Exception {
	/** Constructeur par defaut de l'Exception
 	*/
	public ElementInexistantException(){
		super();
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public ElementInexistantException(String message){
		super(message);
	} 
}

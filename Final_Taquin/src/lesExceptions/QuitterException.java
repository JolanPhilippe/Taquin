package lesExceptions;

@SuppressWarnings("serial")
/** Exception dans le cas ou il est demandé de quitter le jeu
 */
public class QuitterException extends Exception {

	/** Constructeur par defaut de l'Exception
 	*/
	public QuitterException(){
		super();
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public QuitterException(String message){
		super(message);
	} 

}

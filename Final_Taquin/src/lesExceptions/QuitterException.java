package lesExceptions;

@SuppressWarnings("serial")
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

package lesExceptions;

@SuppressWarnings("serial")
/** Exception dans le cas ou un tas est vide
 */
public class TasVideException extends Exception {
	/** Constructeur par defaut de l'Exception
 	*/
	public TasVideException() {
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public TasVideException(String arg0) {
		super(arg0);
	}

}

package lesExceptions;

@SuppressWarnings("serial")
public class ErrorKeyValueException extends Exception {
	/** Constructeur par defaut de l'Exception
 	*/
	public ErrorKeyValueException() {
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public ErrorKeyValueException(String arg0) {
		super(arg0);
	}

}

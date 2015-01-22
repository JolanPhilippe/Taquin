package nosExceptions;

@SuppressWarnings("serial")
/** Exception dans le cas ou une valeur n'est pas dans l'ensemble demandé (tableau)
 * 
 * @author Jolan
 * @since 1.0
 */
public class ValInexistanteException extends Exception{
	/** Constructeur par defaut de l'Exception
 	*/
	public ValInexistanteException(){
		super();
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public ValInexistanteException(String message){
		super(message);
	} 
}
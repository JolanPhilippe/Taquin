package lesExceptions;

@SuppressWarnings("serial")

/** Exception dans le cas ou un mouvement n'est pas possible
 * 
 * @author Jolan
 * @since 1.0
 */
public class MouvementImpossibleException extends Exception{
	/** Constructeur par defaut de l'Exception
 	*/
	public MouvementImpossibleException(){
		super();
	}
	/** Constructeur de l'Exception avec message d'erreur integré
	 * 
	 * @param arg0 : un message clair sur la nature du probleme
	 */
	public MouvementImpossibleException(String arg0){
		super(arg0);
	} 
}
package nosClasses;

/** Permet le regroupement de deux valeurs 
 * (principalement pour les indices doubles dans un tableau)
 * 
 * @author Jolan
 * @since 1.0
 */
public class Couple{
	/**Valeur 1 */
	private int x;
	/**Valeur 2 */
	private int y;
	
	/** Creer un couple de valeurs entieres
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public Couple(){
		this.x=0;
		this.y=0;
	}
	
	/** Creer un couple de valeurs entieres
	 * 
	 * @param x la valeur a mettre pour x
	 * @param y la valeur a mettre pour y
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public Couple(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/** Recupere la valeur de x
	 * 
	 * @return valeur de x
	 * 
	 * @author Jolan
	 * @since 1.0 
	 */
	public int getX() {
		return x;
	}
	
	/** Definie la valeur de x
	 * 
	 * @param x la valeur a mettre pour x
	 * 
	 * @author Jolan
	 * @since 1.0 
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/** Recupere la valeur de y
	 * 
	 * @return valeur de y
	 * 
	 * @author Jolan
	 * @since 1.0
	 */
	public int getY() {
		return y;
	}
	
	/** Definie la valeur de y
	 * 
	 * @param y la valeur a mettre pour y
	 * 
	 * @author Jolan
	 * @since 1.0 
	 */
	public void setY(int y) {
		this.y = y;
	}
}
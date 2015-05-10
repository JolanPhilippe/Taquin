package Outils;
public class Triplet<A, B, C> {
	/**Valeur 1 */
	private A fst;
	/**Valeur 2 */
	private B snd;
	/**Valeur 3*/
	private C thd;
	
	
	/** Creer un couple de valeurs entieres
	 * 
	 * @author Jolan
	 */
	public Triplet(){
		this.fst=null;
		this.snd=null;
		this.thd=null;
	}
	
	/** Creer un couple de valeurs entieres
	 * 
	 * @param x la valeur a mettre pour fst
	 * @param y la valeur a mettre pour snd
	 * @param z la valeur a mettre pour thd
	 * 
	 * @author Jolan
	 */
	public Triplet (A x, B y, C z){
		this.fst=x;
		this.snd=y;
		this.thd=z;
	}

	/** Recupere la valeur de fst
	 * 
	 * @return valeur de fst
	 * 
	 * @author Jolan
	 */
	public A getFst() {
		return fst;
	}
	
	/** Definie la valeur de fst
	 * 
	 * @param x la valeur a mettre pour fst
	 * 
	 * @author Jolan
	 */
	public void setFst(A x) {
		this.fst = x;
	}
	
	/** Recupere la valeur de snd
	 * 
	 * @return valeur de snd
	 * 
	 * @author Jolan
	 */
	public B getSnd() {
		return snd;
	}
	
	/** Definie la valeur de snd
	 * 
	 * @param y la valeur a mettre pour snd
	 * 
	 * @author Jolan
	 */
	public void setSnd(B y) {
		this.snd = y;
	}
	
	/** Recupere la valeur de thd
	 * 
	 * @return valeur de thd
	 * 
	 * @author Jolan
	 */
	public C getThd() {
		return this.thd;
	}
	
	/** Definie la valeur de thd
	 * 
	 * @param z la valeur a mettre pour thd
	 * 
	 * @author Jolan
	 */
	public void setThd(C z) {
		this.thd = z;
	}
}

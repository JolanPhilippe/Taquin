package lesGraphes;


/** Permet le regroupement de deux valeurs 
 * (principalement pour les indices doubles dans un tableau)
 * 
 */
public class Couple <A,B>{
	/**Valeur 1 */
	private A fst;
	/**Valeur 2 */
	private B snd;
	
	/** Creer un couple de valeurs entieres
	 * 
	 */
	public Couple(){
		this.fst=null;
		this.snd=null;
	}
	
	/** Creer un couple de valeurs entieres
	 * 
	 * @param x la valeur a mettre pour fst
	 * @param y la valeur a mettre pour snd
	 * 
	 */
	public Couple (A x, B y){
		this.fst=x;
		this.snd=y;
	}
	
	/** Recupere la valeur de fst
	 * 
	 * @return valeur de fst
	 * 
	 */
	public A getFst() {
		return fst;
	}
	
	/** Definie la valeur de fst
	 * 
	 * @param x la valeur a mettre pour fst
	 * 
	 */
	public void setFst(A x) {
		this.fst = x;
	}
	
	/** Recupere la valeur de snd
	 * 
	 * @return valeur de snd
	 * 
	 */
	public B getSnd() {
		return snd;
	}
	
	/** Definie la valeur de snd
	 * 
	 * @param y la valeur a mettre pour snd
	 * 
	 */
	public void setSnd(B y) {
		this.snd = y;
	}
}
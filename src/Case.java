public class Case {
	private Creation c;
	private int colonne;
	private int ligne;
	private boolean nord;
	private boolean sud;
	private boolean est;
	private boolean ouest;
	private int id;
	private Case casPrec;
/**
 * Definit une case par ses coordonnees sachant qu'une case a plusieurs caracteristique :
 * ses coordonnees
 * ses portes
 * son identifiant
 * @param lig
 * @param col
 */
	public Case(int lig, int col) {
		this.colonne = col;
		this.ligne = lig;
		this.est = false;
		this.nord = false;
		this.sud = false;
		this.ouest = false;
		this.id = 1;
	}
/**
 * retourne la case precedente (pour le chemin solution)
 * @return
 */
	public Case getCasePrec() {
		return this.casPrec;
	}
/**
 * Definit la case precedente (pour solution)
 * @param cas
 */
	public void setCasePrec(Case cas) {
		this.casPrec = cas;
	}
/**
 * retourne la colonne de la case
 * @return
 */
	public int getCol() {
		return this.colonne;
	}
/**
 * retourne la ligne de la case
 * @return
 */
	public int getLig() {
		return this.ligne;
	}
/**
 * definit la colonne de la case
 * @param col
 */
	public void setCol(int col) {
		this.colonne = col - 1;
	}
/**
 * definit la ligne de la case
 * @param lig
 */
	public void setLig(int lig) {
		this.ligne = lig - 1;
	}
/**
 * retourne l'etat de la porte nord
 * @return
 */
	public boolean getNord() {
		return this.nord;
	}
/**
 * retourne l'etat de la porte sud
 * @return
 */
	public boolean getSud() {
		return this.sud;
	}
/**
 * retourne l'etat de la porte ouest
 * @return
 */
	public boolean getOuest() {
		return this.ouest;
	}
/**
 * retourne l'etat de la porte est
 * @return
 */
	public boolean getEst() {
		return this.est;
	}
/**
 * retourne l'identifiant de la case
 * @return
 */
	public int getId() {
		return this.id;
	}
/**
 * definit l'etat de la porte nord
 * @param n
 */
	public void setNord(boolean n) {
		this.nord = n;
	}
/**
 * definit l'etat de la porte sud
 * @param s
 */
	public void setSud(boolean s) {
		this.sud = s;
	}
/**
 * definit l'etat de la porte ouest
 * @param o
 */
	public void setOuest(boolean o) {
		this.ouest = o;
	}
/**
 * definit l'etat de la porte est
 * @param e
 */
	public void setEst(boolean e) {
		this.est = e;
	}
/**
 * definit l'identifiant de la case
 * @param ide
 */
	public void setId(int ide) {
		this.id = ide;
	}
/**
 * retourne l'identifiant qui changera
 * @param ouverte
 * @return
 */
	public int getIndiceAIndicer(Case ouverte) {
		if (this.getId() <= ouverte.getId()) {
			return this.getId();
		} else {
			return ouverte.getId();
		}
	}
/**
 * Indice l'identifiant qui convient
 * @param ouverte
 */
	public void indicage(Case ouverte) {
		if (this.getId() <= ouverte.getId()) {
			this.setId(ouverte.getId());
		} else {
			ouverte.setId(this.getId());
		}
	}

	public static void main(String[] args) {
		// Creation crea = new Creation("laby_exemple_sujet.txt");
		Case c = new Case(0, 8);
		System.out.println(c.getCol());
	}
}

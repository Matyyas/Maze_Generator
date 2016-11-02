import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Labyrinthe {

	private static char[][] laby;
	private static int longueur;
	private static int largeur;
	private static Case moi;
	private static int compteur;
	private int ligneOr;
	private int colOr;
	private int ligneEp;
	private int colEp;
	private static int ligneEnt;
	private static int colEnt;
	private static int ligneSor;
	private static int colSor;
	private static int colDepMineur;
	private static int ligDepMineur;
	private static Case gardien;
	private static Case nulle = new Case(0, 0);
	private static boolean gardienTue = false;
	private static boolean epeeTrouvee = false;
	private static char[][] chemin = new char[longueur][largeur];
	private static GrilleLayout grille = new GrilleLayout();
	private static GrilleLayoutVue grilleVue = new GrilleLayoutVue();
	private static boolean jeu = true;
	private DeuxiemeFenetre deu;

	private static JFrame fenetre = new JFrame("Jeu");

	ArrayList<Case> listIndicage = new ArrayList<Case>();

	/**
	 * constructeur de labyrinthe de longueur lon et de largeur lar creer la
	 * fenetre graphique pour l'afficher
	 * 
	 * @param lon
	 * @param lar
	 */
	public Labyrinthe(int lon, int lar) {
		creerLab(lon, lar);

		fenetre = new JFrame("Jeu");
		fenetre.setContentPane(grille);
		fenetre.setSize(25 * (largeur + 1), 26 * (longueur + 1));
		fenetre.setVisible(true);
	}

	/**
	 * retourne sa longueur
	 * 
	 * @return
	 */
	public static int getLongueurLaby() {
		return Labyrinthe.longueur;
	}

	/**
	 * retourne sa largeur
	 * 
	 * @return
	 */
	public static int getLargeurLaby() {
		return Labyrinthe.largeur;
	}

	/**
	 * retourne la ligne du mineur
	 * 
	 * @return
	 */
	public static int getLigMineur() {
		return moi.getLig();
	}

	/**
	 * retourne la colonne du mineur
	 * 
	 * @return
	 */
	public static int getColMineur() {
		return moi.getCol();
	}

	/**
	 * retourne le char a la ligne ligne et a la colonne col
	 * 
	 * @param ligne
	 * @param col
	 * @return
	 */
	public static char getChar(int ligne, int col) {
		return Labyrinthe.laby[ligne][col];
	}

	/**
	 * remplace tous les identifiants des cases d'identifiant ancienId par
	 * nouvelId
	 * 
	 * @param liste
	 * @param ancienId
	 * @param nouvelId
	 */
	public static void remplacerId(ArrayList<Case> liste, int ancienId,
			int nouvelId) {
		ArrayList<Case> listInt = liste;
		if (ancienId <= nouvelId) {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i).getId() == ancienId) {
					listInt.get(i).setId(nouvelId);
				} else {
				}
			}
			liste = listInt;
		} else {
			for (int i = 0; i < liste.size(); i++) {
				if (liste.get(i).getId() == nouvelId) {
					listInt.get(i).setId(ancienId);
				} else {
				}
			}
			liste = listInt;
		}

	}

	/**
	 * affiche la solution
	 */
	public static void Soluce() {
		Chemin(moi);
		Case prec = nulle;
		prec.setCol(colSor);
		prec.setLig(ligneSor);
		prec = prec.getCasePrec();
		while (prec != moi) {
			laby[prec.getLig()][prec.getCol()] = '*';
			prec = prec.getCasePrec();
		}

	}

	/**
	 * trouve le plus court chemin vers la sortie
	 * 
	 * @param ca
	 */
	public static void Chemin(Case ca) {
		Case suiv = nulle;
		chemin = laby;
		while (suiv.getLig() != ligneSor && suiv.getCol() != colSor) {
			if (chemin[ca.getLig() - 1][ca.getCol()] == ' ') {
				suiv = new Case(ca.getLig() - 1, ca.getCol());
				if (suiv.getCasePrec() == nulle) {
					suiv.setCasePrec(ca);
					Chemin(suiv);
					System.out.println("caca");
				}
			}
			if (chemin[ca.getLig() + 1][ca.getCol()] == ' ') {
				suiv = new Case(ca.getLig() + 1, ca.getCol());
				if (suiv.getCasePrec() == nulle) {
					suiv.setCasePrec(ca);
					Chemin(suiv);
				}
			}
			if (chemin[ca.getLig()][ca.getCol() - 1] == ' ') {
				suiv = new Case(ca.getLig(), ca.getCol() - 1);
				if (suiv.getCasePrec() == nulle) {
					suiv.setCasePrec(ca);
					Chemin(suiv);
				}
			}
			if (chemin[ca.getLig()][ca.getCol() + 1] == ' ') {
				suiv = new Case(ca.getLig(), ca.getCol() + 1);
				if (suiv.getCasePrec() == nulle) {
					suiv.setCasePrec(ca);
					Chemin(suiv);
				}
			}
		}
	}

	/**
	 * ouvre une porte aleatoirement pour chaque case du labyrinthe definit
	 * comme une case à portes
	 * 
	 * @param tab
	 */
	public void ouverture(Case[] tab) {
		int compt = (this.longueur - 1) * (this.largeur - 1) / 4;
		for (int k = 0; k < compt; k++) {
			this.listIndicage.add(tab[k]);
		}
		for (int i = 0; i < compt; i++) {
			int porte = (int) (Math.random() * 10) % 4;
			if (porte == 0 // identifiant de la porte, ici celle du nord
					&& tab[i].getLig() != 1 // pas de porte pour sortir du
											// labyrinthe
					&& tab[i].getNord() == false // la porte est bien fermée
					&& tab[i - (largeur - 1) / 2].getId() != tab[i].getId()) { // il
																				// n'y
																				// a
																				// aucun
																				// autre
																				// acces
																				// vers
																				// la
																				// cellule
																				// du
																				// dessus
																				// (les
																				// identifiants
																				// des
																				// cellules
																				// sont
																				// différents)

				tab[i].setNord(true); // on ouvre
				tab[i - (largeur - 1) / 2].setSud(true);// on ouvre la porte
														// correspondante de la
														// cellule binome
				remplacerId(listIndicage, tab[i - (largeur - 1) / 2].getId(),
						tab[i].getId());
				laby[tab[i].getLig() - 1][tab[i].getCol()] = ' ';
			} else {
				if (porte == 1 && tab[i].getCol() != largeur - 2
						&& tab[i].getEst() == false
						&& tab[i + 1].getId() != tab[i].getId()) { // Porte est
					tab[i].setEst(true);
					tab[i + 1].setOuest(true);
					remplacerId(listIndicage, tab[i + 1].getId(),
							tab[i].getId());
					laby[tab[i].getLig()][tab[i].getCol() + 1] = ' ';
				} else {
					if (porte == 2
							&& tab[i].getLig() != longueur - 2
							&& tab[i].getSud() == false
							&& tab[i + (largeur - 1) / 2].getId() != tab[i]
									.getId()) { // Porte sud
						tab[i].setSud(true);
						tab[i + (largeur - 1) / 2].setNord(true);
						remplacerId(listIndicage,
								tab[i + (largeur - 1) / 2].getId(),
								tab[i].getId());
						laby[tab[i].getLig() + 1][tab[i].getCol()] = ' ';
					} else {
						if (porte == 3 && tab[i].getCol() != 1
								&& tab[i].getOuest() == false
								&& tab[i - 1].getId() != tab[i].getId()) { // Porte
																			// ouest
							tab[i].setOuest(true);
							tab[i - 1].setEst(true);
							remplacerId(listIndicage, tab[i - 1].getId(),
									tab[i].getId());
							laby[tab[i].getLig()][tab[i].getCol() - 1] = ' ';
						}
						/*
						 * else{ //pas la bonne idée i--; // ici on force
						 * l'ouverture des cellules }
						 */
					}
				}
			}
		}
	}

	/**
	 * creer un labyrinthe dont les cases de colonne et ligne impairs sont des
	 * cases à portes avec un identifiant propre à chacune puis tente d'ouvrir
	 * les portes de chacunes de ces cases puis ouvre des portes des cases qui
	 * ne sont pas encore reliées au chemin menant à la sortie quand toutes ces
	 * cases ont le meme identifiant, on peut aller dans tout le labyrinthe
	 * 
	 * puis place les cases spéciales : entree, sortie en fonction de l'entree
	 * (perimetre de proximité interdit), l'or dans une partie centrale du
	 * labyrinthe, l'epee à son oppose mineur, et gardien.
	 * 
	 * @param lon
	 * @param lar
	 */
	public void creerLab(int lon, int lar) {
		this.largeur = lar;
		this.longueur = lon;
		// Le tableau de base
		int e = 0;
		int l = this.longueur;
		int f = 0;
		int c = this.largeur;
		int compt = 0;
		Case[] tab = new Case[(l - 1) / 2 * (c - 1) / 2];
		this.laby = new char[l][c];
		while (e < l) {
			while (f < c) {
				if ((e % 2) != 0 && (f % 2) != 0) {
					laby[e][f] = ' ';
					tab[compt] = new Case(e, f);
					tab[compt].setId(compt);
					compt++;
				} else {
					laby[e][f] = '#';
				}
				f++;
			}
			f = 0;
			e++;
		}
		// on ouvre les portes
		ouverture(tab);

		// ici on doit reprendre les cases pour que tous les identifiants soient
		// egaux à celui de la derniere case !
		boolean soluble = false;
		int sol = 0;

		while (soluble == false) {
			for (int k = 0; k < compt; k++) {
				if (this.listIndicage.get(k).getId() != compt - 1) {
					int porte = (int) (Math.random() * 10) % 4;
					if (porte == 0
							&& tab[k].getLig() != 1
							&& tab[k].getNord() == false
							&& tab[k - (largeur - 1) / 2].getId() != tab[k]
									.getId()) { // porte nord
						tab[k].setNord(true);
						tab[k - (c - 1) / 2].setSud(true);
						remplacerId(listIndicage,
								tab[k - (largeur - 1) / 2].getId(),
								tab[k].getId());
						laby[tab[k].getLig() - 1][tab[k].getCol()] = ' ';
					} else {
						if (porte == 1 && tab[k].getCol() != c - 2
								&& tab[k].getEst() == false
								&& tab[k + 1].getId() != tab[k].getId()) { // Porte
																			// est
							tab[k].setEst(true);
							tab[k + 1].setOuest(true);
							remplacerId(listIndicage, tab[k + 1].getId(),
									tab[k].getId());
							laby[tab[k].getLig()][tab[k].getCol() + 1] = ' ';
						} else {
							if (porte == 2
									&& tab[k].getLig() != l - 2
									&& tab[k].getSud() == false
									&& tab[k + (largeur - 1) / 2].getId() != tab[k]
											.getId()) { // Porte sud
								tab[k].setSud(true);
								tab[k + (c - 1) / 2].setNord(true);
								remplacerId(listIndicage, tab[k + (largeur - 1)
										/ 2].getId(), tab[k].getId());
								laby[tab[k].getLig() + 1][tab[k].getCol()] = ' ';
							} else {
								if (porte == 3 && tab[k].getCol() != 1
										&& tab[k].getOuest() == false
										&& tab[k - 1].getId() != tab[k].getId()) { // Porte
																					// ouest
									tab[k].setOuest(true);
									tab[k - 1].setEst(true);
									remplacerId(listIndicage,
											tab[k - 1].getId(), tab[k].getId());
									laby[tab[k].getLig()][tab[k].getCol() - 1] = ' ';

								}
							}
						}

					}
					// System.out.println(k);
				}

				sol = 0;
				for (int i = 0; i < compt; i++) {
					// System.out.println("l'indice de la cellule"+i+"est "+this.listIndicage.get(i).getId());
					if (this.listIndicage.get(i).getId() == compt - 1) {
						sol++;
					}
					// System.out.println(sol);
				}
				if (sol == compt) {
					soluble = true;
				} else {
				}
				// System.out.println(k+"="+this.listIndicage.get(k).getId()+soluble);
			}
		}
		// placement des cases spéciales

		// entrée et sortie en fonction avec une marge de 3 cases
		int ent = (int) (Math.random() * 10 % 4);
		int sor = (int) (Math.random() * 10 % 4);
		switch (ent) {
		case 0:
			int col = (int) (Math.random() * 10 % (c - 2) + 1);
			laby[0][col] = 'E';
			laby[1][col] = ' ';
			this.colEnt = col;
			this.ligneEnt = 0;
			this.colDepMineur = col;
			this.ligDepMineur = 1;
			switch (sor) {
			case 0:
				int cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (col - 4 <= cols && cols <= col + 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[0][cols] = 'S';
				laby[1][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = 0;
				break;
			case 1:
				int ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (col >= c - 4 && ligs <= 4) {
					ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				}
				laby[ligs][c - 1] = 'S';
				laby[ligs][c - 2] = ' ';
				this.colSor = c - 1;
				this.ligneSor = ligs;
				break;
			case 2:
				cols = (int) (Math.random() * 10 % (c - 2) + 1);
				laby[l - 1][cols] = 'S';
				laby[l - 2][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = l - 1;
				break;
			case 3:
				ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (col <= 4 && ligs <= 4) {
					ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				}
				laby[ligs][0] = 'S';
				laby[ligs][1] = ' ';
				this.colSor = 0;
				this.ligneSor = ligs;
				break;
			}
			break;
		case 1:
			int lig = (int) (Math.random() * 10 % (l - 2) + 1);
			laby[lig][c - 1] = 'E';
			laby[lig][c - 2] = ' ';
			this.colEnt = c - 1;
			this.ligneEnt = lig;
			this.colDepMineur = c - 2;
			this.ligDepMineur = lig;
			switch (sor) {
			case 0:
				int cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (lig <= 4 && cols >= c - 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[0][cols] = 'S';
				laby[1][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = 0;
				break;
			case 1:
				int ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (lig - 4 <= ligs && ligs <= lig + 4) {
					ligs = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[ligs][c - 1] = 'S';
				laby[ligs][c - 2] = ' ';
				this.colSor = c - 1;
				this.ligneSor = ligs;
				break;
			case 2:
				cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (lig >= l - 4 && cols >= c - 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[l - 1][cols] = 'S';
				laby[l - 2][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = l - 1;
				break;
			case 3:
				ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				laby[ligs][0] = 'S';
				laby[ligs][1] = ' ';
				this.colSor = 0;
				this.ligneSor = ligs;
				break;
			}
			break;
		case 2:
			col = (int) (Math.random() * 10 % (c - 2) + 1); // entree au sud
			laby[l - 1][col] = 'E';
			laby[l - 2][col] = ' ';
			this.colEnt = col;
			this.ligneEnt = l - 1;
			this.colDepMineur = col;
			this.ligDepMineur = l - 2;
			switch (sor) {
			case 0:
				int cols = (int) (Math.random() * 10 % (c - 2) + 1);
				laby[0][cols] = 'S';
				laby[1][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = 0;
				break;
			case 1:
				int ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (col >= c - 4 && ligs >= l - 4) {
					ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				}
				laby[ligs][c - 1] = 'S';
				laby[ligs][c - 2] = ' ';
				this.colSor = c - 1;
				this.ligneSor = ligs;
				break;
			case 2:
				cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (cols <= col + 4 && cols >= col - 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[l - 1][cols] = 'S';
				laby[l - 2][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = l - 1;
				break;
			case 3:
				ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (col <= 4 && ligs >= l - 4) {
					ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				}
				laby[ligs][0] = 'S';
				laby[ligs][1] = ' ';
				this.colSor = 0;
				this.ligneSor = ligs;
				break;
			}
			break;
		case 3:
			lig = (int) (Math.random() * 10 % (l - 2) + 1); // entrée à l'est
			laby[lig][0] = 'E';
			laby[lig][1] = ' ';
			this.colEnt = 0;
			this.ligneEnt = lig;
			this.colDepMineur = 1;
			this.ligDepMineur = lig;
			switch (sor) {
			case 0:
				int cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (cols <= 4 && lig <= 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[0][cols] = 'S';
				laby[1][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = 0;
				break;
			case 1:
				int ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				laby[ligs][c - 1] = 'S';
				laby[ligs][c - 2] = ' ';
				this.colSor = c - 1;
				this.ligneSor = ligs;
				break;
			case 2:
				cols = (int) (Math.random() * 10 % (c - 2) + 1);
				while (lig >= l - 4 && cols <= 4) {
					cols = (int) (Math.random() * 10 % (c - 2) + 1);
				}
				laby[l - 1][cols] = 'S';
				laby[l - 2][cols] = ' ';
				this.colSor = cols;
				this.ligneSor = l - 1;
				break;
			case 3:
				ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				while (ligs <= lig + 4 && ligs >= lig - 4) {
					ligs = (int) (Math.random() * 10 % (l - 2) + 1);
				}
				laby[ligs][0] = 'S';
				laby[ligs][1] = ' ';
				this.colSor = 0;
				this.ligneSor = ligs;
				break;
			}
			break;
		}
		// case de la pépite

		Or();
		// Case de l'arme anti Gardien

		Epee();
		// Positionnement de depart
		Mineur();
		// Positionnement du gardien
		PlacementGardien(new Case(ligneSor, colSor));

		/*
		 * System.out.println("Le Labyrinthe est prêt : " + "" + soluble);
		 * 
		 * e = 0; while (e < l) { while (f < c) { System.out.print(laby[e][f]);
		 * f++; } System.out.println(); f = 0; e++; }
		 */
	}

	/**
	 * definit la case de l'or aleatoirement dans une case vide du labyrinthe
	 * et ce loin des limites
	 */
	public void Or() {
		boolean orPose=false;
		while (orPose==false){
			int r = (int) (Math.random() * 100 % (longueur - 2) + 1);
			int s = (int) (Math.random() * 100 % (largeur - 2) + 1);
			this.ligneOr = r;
			this.colOr = s;
			if(laby[r][s] == ' '){
				laby[r][s] = 'O';
				orPose=true;
			}
		}
	}

	/**
	 * place l'epee comme l'or
	 */
	public void Epee() {
		boolean epeePose=false;
		while (epeePose==false){
			int r = (int) (Math.random() * 100 % (longueur - 2) + 1);
			int s = (int) (Math.random() * 100 % (largeur - 2) + 1);
			this.ligneEp = r;
			this.colEp = s;
			if(laby[r][s] == ' '){
				laby[r][s] = '!';
				epeePose=true;
			}
		}
	}

	/**
	 * place le mineur devant l'entree
	 */
	public static void Mineur() {
		moi = new Case(0, 0);
		if (ligneEnt == 0) {
			moi.setLig(2);
			moi.setCol(colEnt + 1);
			//laby[moi.getLig()][moi.getCol()] = 'M';
		}
		if (ligneEnt == longueur - 1) {
			moi.setLig(longueur - 1);
			moi.setCol(colEnt + 1);
			//laby[moi.getLig()][moi.getCol()] = 'M';
		}
		if (colEnt == 0) {
			moi.setCol(2);
			moi.setLig(ligneEnt + 1);
			//laby[moi.getLig()][moi.getCol()] = 'M';
		}
		if (colEnt == largeur - 1) {
			moi.setCol(largeur - 1);
			moi.setLig(ligneEnt + 1);
			//laby[moi.getLig()][moi.getCol()] = 'M';
		}
	}

	/**
	 * retourne la case depart
	 * 
	 * @return
	 */
	public Case getDepart() { // On veut obtenir la case depart
		Case caseDep = new Case(0, 0);
		caseDep.setCol(this.colEnt + 1);
		caseDep.setLig(this.ligneEnt + 1);
		return caseDep;
	}

	/**
	 * retourne la case du mineur
	 * 
	 * @return
	 */
	public Case getMineur() {
		Case caseMin = new Case(0, 0);
		caseMin.setCol(moi.getCol() + 1);
		caseMin.setLig(moi.getLig() + 1);
		return caseMin;
	}

	/**
	 * retourne la case de l'or
	 * 
	 * @return
	 */
	public Case getOr() { // On veut obtenir la case avec la pepite d'or
		Case caseOr = new Case(0, 0);
		caseOr.setCol(this.colOr + 1);
		caseOr.setLig(this.ligneOr + 1);
		return caseOr;
	}

	/**
	 * retourne la case de l'epee
	 * 
	 * @return
	 */
	public Case getEpee() { // On veut obtenir la case avec l'epee
		Case caseEp = new Case(0, 0);
		caseEp.setCol(this.colEp + 1);
		caseEp.setLig(this.ligneEp + 1);
		return caseEp;
	}

	/**
	 * retourne la case de la sortie
	 * 
	 * @return
	 */
	public Case getSortie() { // On veut obtenir la sortie
		Case caseSor = new Case(0, 0);
		caseSor.setCol(this.colSor + 1);
		caseSor.setLig(this.ligneSor + 1);
		return caseSor;
	}

	/**
	 * retourne la case du dessus si elle est accessible
	 * 
	 * @param cas
	 * @return
	 */
	public static String CaseAuDessus(Case cas) { // aller à la case du dessus
		Case temp = new Case(cas.getLig(), cas.getCol());
		if (laby[cas.getLig() - 1][cas.getCol()] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (laby[cas.getLig() - 1][cas.getCol()] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
		}
		if (laby[cas.getLig() - 1][cas.getCol()] == 'G') {
			return "Attention au Gardien !";
		} else {
			temp = new Case(cas.getLig() - 1, cas.getCol());
			moi = temp;
			compteur++;
			return "continue d'avancer";
		}
	}

	/**
	 * retourne la case du dessous si elle est accessible
	 * 
	 * @param cas
	 * @return
	 */
	public static String CaseAuDessous(Case cas) { // obtenir la case du dessous
		Case temp = new Case(cas.getLig(), cas.getCol());
		if (laby[cas.getLig() + 1][cas.getCol()] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (laby[cas.getLig() + 1][cas.getCol()] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
		}
		if (laby[cas.getLig() + 1][cas.getCol()] == 'G') {
			return "Attention au Gardien !";
		} else {
			temp = new Case(cas.getLig() + 1, cas.getCol());
			moi = temp;
			compteur++;
			return "continue d'avancer";
		}
	}

	/**
	 * retourne la case de droite si elle est accessible
	 * 
	 * @param cas
	 * @return
	 */
	public static String CaseADroite(Case cas) { // obtenir la case de droite
		Case temp = new Case(cas.getLig(), cas.getCol());
		if (laby[cas.getLig()][cas.getCol() + 1] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (laby[cas.getLig()][cas.getCol() + 1] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
		}
		if (laby[cas.getLig()][cas.getCol() + 1] == 'G') {
			return "Attention au Gardien !";
		} else {
			temp = new Case(cas.getLig(), cas.getCol() + 1);
			moi = temp;
			compteur++;
			return "continue d'avancer";
		}
	}

	/**
	 * retourne la case de gauche si elle est accessible
	 * 
	 * @param cas
	 * @return
	 */
	public static String CaseAGauche(Case cas) { // obtenir la case de gauche
		Case temp = new Case(cas.getLig(), cas.getCol());
		if (laby[cas.getLig()][cas.getCol() - 1] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (laby[cas.getLig()][cas.getCol() - 1] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
		}
		if (laby[cas.getLig()][cas.getCol() - 1] == 'G') {
			return "Attention au Gardien !";
		} else {
			temp = new Case(cas.getLig(), cas.getCol() - 1);
			moi = temp;
			compteur++;
			return "continue d'avancer";
		}
	}

	/**
	 * retourne la nouvelle position
	 * 
	 * @param lieu
	 * @return
	 */

	public static String Deplacement(Case lieu) {
		char deplacement = Clavier.deplacer();
		if (deplacement == 'z') {
			return CaseAuDessus(lieu) + " tu es en (" + moi.getLig() + ","
					+ moi.getCol() + ")";
		}
		if (deplacement == 's') {
			return CaseAuDessous(lieu) + " tu es en (" + moi.getLig() + ","
					+ moi.getCol() + ")";
		}
		if (deplacement == 'd') {
			return CaseADroite(lieu) + " tu es en (" + moi.getLig() + ","
					+ moi.getCol() + ")";
		}
		if (deplacement == 'q') {
			return CaseAGauche(lieu) + " tu es en (" + moi.getLig() + ","
					+ moi.getCol() + ")";
		}
		if (deplacement == 'S') {
			Soluce();
			return "Voici le chemin";
		} else {
			return "fait un autre deplacement";
		}

	}

	/**
	 * affiche la position dans le labyrinthe
	 * 
	 * @param mineur
	 */
	public static void Position(Case min) { // revoir ici
		char[][] tab = new char[longueur][largeur];

		int k = 0;
		int l = 0;

		tab[min.getLig()][min.getCol()] = 'M'; // pour voir le deplacement
		for (int e = 0; e < min.getLig(); e++) {
			for (int f = 0; f < largeur; f++) {
				System.out.print(laby[e][f]);

			}
			System.out.println();
		}
		for (int i = 0; i < min.getCol(); i++) {
			System.out.print(laby[min.getLig()][i]);
		}
		System.out.print(tab[min.getLig()][min.getCol()]);

		for (int f = min.getCol() + 1; f < largeur; f++) {
			System.out.print(laby[min.getLig()][f]);
		}
		System.out.println();
		for (int e = min.getLig() + 1; e < longueur; e++) {
			for (int f = 0; f < largeur; f++) {
				System.out.print(laby[e][f]);

			}
			System.out.println();
		}

		fenetre.setContentPane(grille);
		fenetre.setFocusable(true);

		/*
		 * for( k=0;k<longueur;k++){ for ( l=0;l<largeur;l++){
		 * 
		 * 
		 * if(tab[k][l]=='M' ){ JLabel mineur = new JLabel (new
		 * ImageIcon("mineur.png")); contentPane.add(mineur); } else{
		 * if(laby[k][l]=='G' ){ JLabel gardien = new JLabel (new
		 * ImageIcon("chemin.png")); contentPane.add(gardien); } else{ if (
		 * laby[k][l]=='#'){ JLabel mur = new JLabel (new ImageIcon("mur.png"));
		 * contentPane.add(mur); } else{ if(laby[k][l]==' ' ){ JLabel chemin =
		 * new JLabel (new ImageIcon(" ")); contentPane.add(chemin); } else{
		 * if(laby[k][l]=='S' ){ JLabel sortie = new JLabel (new
		 * ImageIcon("sortie.png")); contentPane.add(sortie);
		 * 
		 * } else{ if(laby[k][l]=='O'){ JLabel or = new JLabel (new
		 * ImageIcon("or.png")); contentPane.add(or);
		 * 
		 * } else{ if(laby[k][l]=='E'){ JLabel entrée = new JLabel (new
		 * ImageIcon("entree.png")); contentPane.add(entrée); } } } } } } } }
		 * l=0;
		 * 
		 * }
		 */

	}

	/**
	 * retourne la position avec le chemin solution affiche
	 * 
	 * @param min
	 */

	public static void PositionEtSol(Case min) { // revoir ici
		char[][] tab = new char[longueur][largeur];

		int k = 0;
		int l = 0;

		tab[min.getLig()][min.getCol()] = 'M'; // pour voir le deplacement
		for (int e = 0; e < min.getLig(); e++) {
			for (int f = 0; f < largeur; f++) {
				System.out.print(chemin[e][f]);

			}
			System.out.println();
		}
		for (int i = 0; i < min.getCol(); i++) {
			System.out.print(chemin[min.getLig()][i]);
		}
		System.out.print(tab[min.getLig()][min.getCol()]);

		for (int f = min.getCol() + 1; f < largeur; f++) {
			System.out.print(chemin[min.getLig()][f]);
		}
		System.out.println();
		for (int e = min.getLig() + 1; e < longueur; e++) {
			for (int f = 0; f < largeur; f++) {
				System.out.print(chemin[e][f]);

			}
			System.out.println();
		}

		fenetre.setContentPane(grille);
	}

	// Ici on veut juste les alentours du mineur, comme si il ne pouvait voir
	// que les cases autour de lui
	/**
	 * n'affiche que les environs du mineur cad les cases qui l'entourent et
	 * uniquement elles
	 * 
	 * @param mineur
	 */

	public static void Vue(Case min) {
		char[][] vue = new char[3][3];

		int k = 0;
		int l = 0;

		vue[1][1] = 'M'; // pour voir le deplacement
		vue[1][0] = laby[min.getLig()][min.getCol() - 1];
		vue[1][2] = laby[min.getLig()][min.getCol() + 1];

		for (int f = 0; f < 3; f++) {
			vue[0][f] = laby[min.getLig() - 1][min.getCol() - 1 + f];
			System.out.print(vue[0][f]);
		}
		System.out.println();

		for (int f = 0; f < 3; f++) {
			System.out.print(vue[1][f]);
		}
		System.out.println();

		for (int f = 0; f < 3; f++) {
			vue[2][f] = laby[min.getLig() + 1][min.getCol() - 1 + f];
			System.out.print(vue[2][f]);
		}
		fenetre.setContentPane(grilleVue);
		fenetre.setFocusable(true);
		fenetre.setSize(50, 140);
		fenetre.setVisible(true);

	}

	public static void PlacementGardien(Case sor) {
		if (ligneSor == 0) {
			gardien = new Case(ligneSor + 1, colSor);
			laby[gardien.getLig()][gardien.getCol()] = 'G';
		} else {
			if (ligneSor == longueur - 1) {
				gardien = new Case(ligneSor - 1, colSor);
				laby[gardien.getLig()][gardien.getCol()] = 'G';
			} else {
				if (colSor == 0) {
					gardien = new Case(ligneSor, colSor + 1);
					laby[gardien.getLig()][gardien.getCol()] = 'G';
				} else {
					if (colSor == largeur - 1) {
						gardien = new Case(ligneSor, colSor - 1);
						laby[gardien.getLig()][gardien.getCol()] = 'G';
					}
				}
			}
		}
	}

	/**
	 * fait revenir le mineur au depart si il n'a pas l'epee sinon le gardien
	 * est tue et disparait
	 */
	public static void VS() {
		if (gardien.getLig() == moi.getLig()
				&& gardien.getCol() == moi.getCol()) {
			if (epeeTrouvee == false) {
				moi.setLig(ligDepMineur + 1);
				moi.setCol(colDepMineur + 1);
			} else {
				gardienTue = true;
			}
		}
	}

	/**
	 * oblige le gardien à aller sur une case de passage a chaque deplacement du
	 * mineur mais ne peut aller sur la pepite ou l'epee si il tombe sur le
	 * mineur il y a un VS
	 */
	public static void DepGardien() {
		boolean depGar = false;
		while (depGar == false) {
			int deplacementGardien = (int) (Math.random() * 10) % 4;
			switch (deplacementGardien) {
			case 0:
				Case temp = new Case(gardien.getLig(), gardien.getCol());
				if (laby[gardien.getLig() - 1][gardien.getCol()] == ' ') {
					temp = new Case(gardien.getLig() - 1, gardien.getCol());
					laby[gardien.getLig()][gardien.getCol()] = ' ';
					gardien = temp;
					laby[gardien.getLig()][gardien.getCol()] = 'G';
					depGar = true;
				}
				VS();
				break;
			case 1:
				temp = new Case(gardien.getLig(), gardien.getCol());
				if (laby[gardien.getLig()][gardien.getCol() + 1] == ' ') {
					temp = new Case(gardien.getLig(), gardien.getCol() + 1);
					laby[gardien.getLig()][gardien.getCol()] = ' ';
					gardien = temp;
					laby[gardien.getLig()][gardien.getCol()] = 'G';
					depGar = true;
				}
				VS();
				break;
			case 2:
				temp = new Case(gardien.getLig(), gardien.getCol());
				if (laby[gardien.getLig() + 1][gardien.getCol()] == ' ') {
					temp = new Case(gardien.getLig() + 1, gardien.getCol());
					laby[gardien.getLig()][gardien.getCol()] = ' ';
					gardien = temp;
					laby[gardien.getLig()][gardien.getCol()] = 'G';
					depGar = true;
				}
				VS();
				break;
			case 3:
				temp = new Case(gardien.getLig(), gardien.getCol());
				if (laby[gardien.getLig()][gardien.getCol() - 1] == ' ') {
					temp = new Case(gardien.getLig(), gardien.getCol() - 1);
					laby[gardien.getLig()][gardien.getCol()] = ' ';
					gardien = temp;
					laby[gardien.getLig()][gardien.getCol()] = 'G';
					depGar = true;
				}
				VS();
				break;
			}

		}
	}

	/**
	 * ne sert que pour definir si l'on recommence la partie ou non a la fin
	 * d'une autre
	 * 
	 * @param b
	 */
	public static void setJeu(boolean b) {
		jeu = b;
	}

	public static void main(String[] args) {

		while (jeu = true) {
			epeeTrouvee = false;

			// PremiereFenetre f = new PremiereFenetre();
			// f.setVisible(true);

			// Labyrinthe labyr = f.getLab();

			// System.out.println(labyr.getDepart().getLig()+"et"+labyr.getDepart().getCol());

			char taille = Clavier.Taille();
			int tai = 0;
			int r = 0;

			if (taille == 'p') {
				longueur = 13;
				largeur = 25;
				tai = 1;
			} else if (taille == 'm') {
				longueur = 21;
				largeur = 39;
				tai = 1;
			} else if (taille == 'g') {
				longueur = 27;
				largeur = 49;
				tai = 1;
			}
			if (tai == 1) {
				char choix = Clavier.Choix();
				int chx = 0;
				int s = 0;
				int p = 0;

				Labyrinthe labyr = new Labyrinthe(longueur, largeur);
				System.out.println(labyr.getEpee().getCol()+""+labyr.getEpee().getLig());
				while (s == 0) {
					if (choix == 'n') {
						Position(moi); // redonne la carte avec la position
						chx = 1;
					} else {
						Vue(moi); // donne la vue réelle
						chx = 1;
					}
					if (chx == 1) {
						System.out.println("Quelle direction ?");
						System.out.println(Deplacement(moi));
						if (gardienTue == false) {
							DepGardien();

						} else {
							laby[gardien.getLig()][gardien.getCol()] = ' ';
						}
						if (p != 1 && moi.getCol() == labyr.getOr().getCol()
								&& moi.getLig() == labyr.getOr().getLig()) {
							p = 1; // compteur pour confirmer que le mineur a
									// bien fait son travail : trouver de l'or
							laby[labyr.getOr().getLig()][labyr.getOr().getCol()] = ' ';
							Message or = new Message(
									"L'or est à toi maintenant trouve la sortie");
						}

						if (epeeTrouvee == false
								&& moi.getCol() == labyr.getEpee().getCol()
								&& moi.getLig() == labyr.getEpee().getLig()) {
							epeeTrouvee = true; // compteur pour confirmer que
												// le mineur a trouvé l'epee
							laby[labyr.getEpee().getLig()][labyr.getEpee()
									.getCol()] = ' ';
							Message epee = new Message(
									"L'epee est à toi, le gardien ne sera plus un probleme");
						}
						if (moi.getCol() == labyr.getSortie().getCol()
								&& moi.getLig() == labyr.getSortie().getLig()) { // idem
							if (p == 1) {
								s = 1; // compteur pour la sortie
								System.out.println();
								jeu = false;
								MessageDeFin fin = new MessageDeFin(
										"Félicitation tu es sorti riche et en vie, à toi la gloire et les femmes en "
												+ compteur + " coups !");

							} else { // il peut sortir mais n'a pas son or, donc
										// il doit repartir
								Message sortieNon = new Message(
										"Tu ne peux pas repartir bredouille, retourne chercher de l'or");
								if (labyr.getSortie().getCol() == largeur - 1) {
									moi.setCol(labyr.getSortie().getCol());
								}
								if (labyr.getSortie().getCol() == 0) {
									moi.setCol(labyr.getSortie().getCol() + 2);
								}
								if (labyr.getSortie().getLig() == longueur - 1) {
									moi.setLig(labyr.getSortie().getLig());
								}
								if (labyr.getSortie().getLig() == 0) {
									moi.setLig(labyr.getSortie().getLig() + 2);
								}
							}

						}
						// la boucle tourne tant que le mineur n'est pas sortie
						// riche
						System.out.println("nombre de dépalcements éffectués: "
								+ compteur);

					}
					// System.out.println("case de sortie : ("+labyr.getSortie().getLig()+","+labyr.getSortie().getCol()+")");
					// System.out.println("case du filon : ("+c.getOr().getLig()+","+c.getOr().getCol()+")");
				}
			}

		}
	}
}
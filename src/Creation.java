import java.io.*;

public class Creation {

	private static char[][] labyrinthe;
	private static Texte l;
	private static int compteur = 0;
	private static Case moi;
	private static int s = 0;
	private static int p = 0;
	private static char choix;

	public Creation(String nomFichier) {
		CreerLabyrinthe(nomFichier);
	}

	/**
	 * Lit le fichier pour creer la labyrinthe
	 * 
	 * @param nomFichier
	 */
	public void CreerLabyrinthe(String nomFichier) {
		l = new Texte(nomFichier);
		String ligne;
		labyrinthe = new char[l.getLongueur()][l.getLargeur()];
		int i = 0;
		int j = 0;
		try {
			BufferedReader aLire = new BufferedReader(
					new FileReader(nomFichier));
			do {
				ligne = aLire.readLine();
				if (ligne != null) {

					while (j < l.getLargeur()) {// tant que l'on atteind pas la
												// fin de la ligne j
						labyrinthe[i][j] = ligne.charAt(j);// on met l'élèment i
															// de la ligne j
						j++;// on passe à l'élèment suivant
					}
					i++;
					j = 0;
				}

			}

			while (ligne != null);
			aLire.close();
			/*
			 * for (int e=0;e<l.getLongueur();e++){ // Il faut decocher ici pour
			 * recuperer le laby complet for (int f=0;f<l.getLargeur();f++){
			 * System.out.print(labyrinthe[e][f]); on affiche | et l'élèment en
			 * ligne e et colonne f
			 * 
			 * } System.out.println(); //on va à la ligne }
			 */
		} catch (IOException e) {
			System.out.println("exception dans fichier " + nomFichier + " :"
					+ e);
		}
	}

	/**
	 * retourne la case de depart après avoir parcouru les contours du
	 * labyrinthe mais ne compte pas les coins
	 * 
	 * @return
	 */
	public Case getDepart() { // On veut obtenir la case depart
		char depart = '#';
		Case caseDep = new Case(0, 0);
		// Methode ici qui parcours tout le tableau, il y a mieux après
		/*
		 * int i =0; int j=0; while( depart != 'E'){ while(depart != 'E' &&
		 * j<l.getLargeur()){ depart=labyrinthe[i][j]; j++; colonne=j; } j=0;
		 * i++; ligne = i; } caseDep.setCol(colonne); caseDep.setLig(ligne);
		 */

		// Methode qui parcours que les cases pertinentes et possibles surtout
		int ligne = 0;
		int colonne = 0;
		int i = 1;
		int j = 1;
		while (depart != 'E') {
			while (depart != 'E' && j < l.getLargeur() - 1) {
				depart = labyrinthe[0][j];
				colonne = j;
				ligne = 0;
				j++;
			}
			j = 1;
			while (depart != 'E' && i < l.getLongueur() - 1) {
				depart = labyrinthe[i][l.getLargeur() - 1];
				ligne = i;
				colonne = l.getLargeur() - 1;
				i++;
			}
			i = 1;
			while (depart != 'E' && j < l.getLargeur() - 1) {
				depart = labyrinthe[l.getLongueur() - 1][j];
				colonne = j;
				ligne = l.getLongueur() - 1;
				j++;
			}

			while (depart != 'E' && i < l.getLongueur() - 1) {
				depart = labyrinthe[i][0];
				ligne = i;
				colonne = 0;
				i++;
			}
		}
		caseDep.setCol(colonne + 1);
		caseDep.setLig(ligne + 1);

		return caseDep;
	}

	/**
	 * Parcoure le labyrinthe pour trouver la pepite puis la retourne
	 * 
	 * @return
	 */
	public Case getOr() { // On veut obtenir la case avec la pepite d'or
		char or = '#';
		Case caseOr = new Case(0, 0);
		int ligne = 0;
		int colonne = 0;
		int i = 0;
		int j = 0;
		while (or != 'O') {
			while (or != 'O' && j < l.getLargeur()) {
				or = labyrinthe[i][j];
				j++;
				colonne = j;
			}
			j = 0;
			i++;
			ligne = i;
		}
		caseOr.setCol(colonne);
		caseOr.setLig(ligne);

		return caseOr;
	}

	/**
	 * retourne la case sortie de la meme facon que la case depart
	 * 
	 * @return
	 */
	// idem que pour la case entree
	public Case getSortie() { // On veut obtenir la sortie
		char sor = '#';
		Case caseSor = new Case(0, 0);
		int ligne = 0;
		int colonne = 0;

		// Methode pour tout le tableau comme getEntree
		/*
		 * int i =0; int j=0; while( sor != 'S'){ while(sor != 'S' &&
		 * j<l.getLargeur()){ sor=labyrinthe[i][j]; j++; colonne=j; } j=0; i++;
		 * ligne = i; } caseSor.setCol(colonne); caseSor.setLig(ligne);
		 */

		// Methode plus rapide comme getEntree
		int i = 1;
		int j = 1;
		while (sor != 'S') {
			while (sor != 'S' && j < l.getLargeur() - 1) {
				sor = labyrinthe[0][j];
				colonne = j;
				ligne = 0;
				j++;
			}
			j = 1;
			while (sor != 'S' && i < l.getLongueur() - 1) {
				sor = labyrinthe[i][l.getLargeur() - 1];
				ligne = i;
				colonne = l.getLargeur() - 1;
				i++;
			}
			i = 1;
			while (sor != 'S' && j < l.getLargeur() - 1) {
				sor = labyrinthe[l.getLongueur() - 1][j];
				colonne = j;
				ligne = l.getLongueur() - 1;
				j++;
			}

			while (sor != 'S' && i < l.getLongueur() - 1) {
				sor = labyrinthe[i][0];
				ligne = i;
				colonne = 0;
				i++;
			}
		}
		caseSor.setCol(colonne + 1);
		caseSor.setLig(ligne + 1);

		return caseSor;
	}

	/**
	 * retourne la case du dessus si elle est accessible
	 * 
	 * @param cas
	 * @return
	 */
	public static String CaseAuDessus(Case cas) { // obtenir la case du dessus
		Case temp = new Case(cas.getLig(), cas.getCol());
		if (labyrinthe[cas.getLig() - 1][cas.getCol()] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (labyrinthe[cas.getLig() - 1][cas.getCol()] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
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
		if (labyrinthe[cas.getLig() + 1][cas.getCol()] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (labyrinthe[cas.getLig() + 1][cas.getCol()] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
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
		if (labyrinthe[cas.getLig()][cas.getCol() + 1] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (labyrinthe[cas.getLig()][cas.getCol() + 1] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
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
		if (labyrinthe[cas.getLig()][cas.getCol() - 1] == 'E') {
			return "L'entree est bloque, trouve une autre sortie...";
		}
		if (labyrinthe[cas.getLig()][cas.getCol() - 1] == '#') {
			return "Un mur bloque le chemin, ouvre un peu les yeux et prends une autre direction !";
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
		} else {
			return "fait un autre deplacement";
		}

	}

	/**
	 * affiche la position dans le labyrinthe
	 * 
	 * @param mineur
	 */
	public static void Position(Case mineur) { // revoir ici
		char[][] tab = new char[l.getLongueur()][l.getLargeur()];
		tab[mineur.getLig()][mineur.getCol()] = 'M'; // pour voir le deplacement
		for (int e = 0; e < mineur.getLig(); e++) {
			for (int f = 0; f < l.getLargeur(); f++) {
				System.out.print(labyrinthe[e][f]);

			}
			System.out.println();
		}
		for (int i = 0; i < mineur.getCol(); i++) {
			System.out.print(labyrinthe[mineur.getLig()][i]);
		}
		System.out.print(tab[mineur.getLig()][mineur.getCol()]);

		for (int f = mineur.getCol() + 1; f < l.getLargeur(); f++) {
			System.out.print(labyrinthe[mineur.getLig()][f]);
		}
		System.out.println();
		for (int e = mineur.getLig() + 1; e < l.getLongueur(); e++) {
			for (int f = 0; f < l.getLargeur(); f++) {
				System.out.print(labyrinthe[e][f]);

			}
			System.out.println();
		}
	}

	// Ici on veut juste les alentours du mineur, comme si il ne pouvait voir
	// que les cases autour de lui
	/**
	 * n'affiche que les environs du mineur cad les cases qui l'entourent et
	 * uniquement elles
	 * 
	 * @param mineur
	 */
	public static void Vue(Case mineur) {
		char[][] vue = new char[3][3];

		vue[1][1] = 'M'; // pour voir le deplacement
		vue[1][0] = labyrinthe[mineur.getLig()][mineur.getCol() - 1];
		vue[1][2] = labyrinthe[mineur.getLig()][mineur.getCol() + 1];

		for (int f = 0; f < 3; f++) {
			vue[0][f] = labyrinthe[mineur.getLig() - 1][mineur.getCol() - 1 + f];
			System.out.print(vue[0][f]);
		}
		System.out.println();

		for (int f = 0; f < 3; f++) {
			System.out.print(vue[1][f]);
		}
		System.out.println();

		for (int f = 0; f < 3; f++) {
			vue[2][f] = labyrinthe[mineur.getLig() + 1][mineur.getCol() - 1 + f];
			System.out.print(vue[2][f]);
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Creation c = new Creation("laby_exemple.txt");

		compteur = 0;

		// Positionnement de depart
		moi = new Case(0, 0);
		if (c.getDepart().getLig() == 0) {
			moi.setLig(2);
			moi.setCol(c.getDepart().getCol() + 1);
		}
		if (c.getDepart().getLig() == l.getLongueur() - 1) {
			moi.setLig(l.getLongueur() - 1);
			moi.setCol(c.getDepart().getCol() + 1);
		}
		if (c.getDepart().getCol() == 0) {
			moi.setCol(2);
			moi.setLig(c.getDepart().getLig() + 1);
		}
		if (c.getDepart().getCol() == l.getLargeur() - 1) {
			moi.setCol(l.getLargeur() - 1);
			moi.setLig(c.getDepart().getLig() + 1);
		}

		char choix = Clavier.Choix();
		int chx = 0;

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

				if (p != 1 && moi.getCol() == c.getOr().getCol()
						&& moi.getLig() == c.getOr().getLig()) { // le equals
																	// n'est
																	// peut être
																	// pas
																	// approprié
					p = 1; // compteur pour confirmer que le mineur a bien fait
							// son travail : trouver de l'or
					labyrinthe[c.getOr().getLig()][c.getOr().getCol()] = ' ';
					System.out
							.println("L'or est à toi maintenant trouve la sortie");
				}
				if (moi.getCol() == c.getSortie().getCol()
						&& moi.getLig() == c.getSortie().getLig()) { // idem
					if (p == 1) {
						s = 1; // compteur pour la sortie
						System.out
								.println("Félicitation tu es sorti riche et en vie, à toi la gloire et les femmes !");
					} else { // il peut sortir mais n'a pas son or, donc il doit
								// repartir
						System.out
								.println("Tu ne peux pas repartir bredouille, retourne chercher de l'or");
						if (c.getSortie().getCol() == l.getLargeur() - 1) {
							moi.setCol(c.getSortie().getCol());
						}
						if (c.getSortie().getCol() == 0) {
							moi.setCol(c.getSortie().getCol() + 2);
						}
						if (c.getSortie().getLig() == l.getLongueur() - 1) {
							moi.setLig(c.getSortie().getLig());
						}
						if (c.getSortie().getLig() == 0) {
							moi.setLig(c.getSortie().getLig() + 2);
						}
					}

				}
				// la boucle tourne tant que le mineur n'est pas sortie riche
				System.out.println("nombre de dépalcements éffectués: "
						+ compteur);

			}
			System.out.println("case de sortie : (" + c.getSortie().getLig()
					+ "," + c.getSortie().getCol() + ")");
			// System.out.println("case du filon : ("+c.getOr().getLig()+","+c.getOr().getCol()+")");
		}
	}
}

import java.io.*;

public class Clavier {
	static BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));// debut de la classe clavier du TP3 ou 4

	/**
	 * retourne la ligne lue
	 * 
	 * @return
	 */
	public static String lireString() {
		System.out.flush();
		try {
			return input.readLine();
		} catch (Exception e) {
			return "";// fin de ce qui n'a pas été retouché
		}
	}

	/**
	 * retourne le deplacement voulu sur le clavier zqsd
	 * 
	 * @return
	 */
	public static char deplacer() {// recopie partielle de clavier du TP
		char deplacement;
		try {
			deplacement = lireString().charAt(0);// on ne prends en compte que
													// le premier deplacement
													// ici.
			if (deplacement == 'z'// attention il faut mettre un truc pour quand
									// on ne met rien cad entree direct
					|| deplacement == 's'
					|| deplacement == 'd'
					|| deplacement == 'q' || deplacement == 'S') { // on
																	// autorise
																	// que le
																	// clavier
																	// zqsd en
																	// attendant
																	// de savoir
																	// utiliser
																	// les
																	// fleches
				//System.out.println("vous vous deplacez vers " + deplacement);

				/*
				 * System.out.println("effectuez un autre deplacement");// on
				 * boucle pour pouvoir faire plusieurs deplacement char x; x=
				 * Clavier.deplacer();
				 */
			} else {
				System.out.println("essaye encore");// on peut toujours refaire
													// une saisie pour se
													// deplacer si on s'est
													// trompe
				char x;
				x = Clavier.deplacer();
			}
		} catch (Exception e) {
			deplacement = (char) 0;
		}
		return deplacement;
	}

	/**
	 * retourne le type de jeu
	 * 
	 * @return
	 */
	public static char Choix() {
		char chx;
		try {
			System.out.println("Quel type de jeu ?");
			System.out.println("- Normal clique sur n");
			System.out.println("- Brouillard clique sur b");
			chx = lireString().charAt(0);
			if (chx == 'n' || chx == 'b') {
				return chx;
			} else {
				System.out.println("choix par defaut: normal");
				return chx = 'n';
			}

		} catch (Exception e) {
			return chx = (char) 0;
		}
	}
	
	

	/**
	 * retourne la taille du labyrinthe
	 * 
	 * @return
	 */
	public static char Taille() {
		char taille;
		try {
			System.out.println("Quel taille de jeu ?");
			System.out.println("- Petit clique sur p");
			System.out.println("- Moyen clique sur m");
			System.out.println("- Grand clique sur g");
			taille = lireString().charAt(0);
			if (taille == 'p' || taille == 'm' || taille == 'g') {
				return taille;
			} else {
				System.out.println("choix par defaut: moyen");
				return taille = 'm';
			}

		} catch (Exception e) {
			return taille = (char) 0;
		}
	}

	/**
	 * retourne la solution
	 * 
	 * @return
	 */
	public static String Solution() {
		String sol;
		try {
			sol = lireString();
			if (sol == "solutionSTP") {
				return sol;

			}
		} catch (Exception e) {
			return sol = "e";
		}
		return sol;

	}

	public static void main(String[] args) {
		System.out.println("effectuez un deplacement :");
		char x;
		x = Clavier.deplacer();
	}

}

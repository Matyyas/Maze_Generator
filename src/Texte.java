import java.io.*;

public class Texte {
	private int longueur;
	private int largeur;
	
	public Texte(String nomFichier) {
		LireLabyrinthe(nomFichier);// objet de lecture du fichier
	}
	
	public void LireLabyrinthe( String nomFichier ) { //comment ça marche
		String ligne;
		int j=0;
		try {
			BufferedReader aLire = new BufferedReader ( new FileReader (nomFichier)) ;
			do  {
				ligne = aLire.readLine();
				
				if ( ligne!=null) {
					largeur = ligne.length(); // ici on définit la largeur
					//System.out.println(ligne); // supprimer les "//" pour afficher le fichier texte
					j++; // compteur pour la longueur
				}
			}
			
			while ( ligne!=null) ;
			aLire.close();
			longueur = j; // et ici la longueur
		}
		catch (IOException e) {
			System.out.println("exception dans fichier " + nomFichier + " :" + e);
		}
	}
	public int getLongueur() {
		return  longueur;
	}
	public int getLargeur () {
			return largeur;
		}
	public static void main(String[] args) {
		Texte texte = new Texte("laby_exemple_sujet.txt");
		System.out.println("la longueur est :"+" "+texte.getLongueur());
		System.out.println("La largeur est :"+" "+texte.getLargeur());
	}
}

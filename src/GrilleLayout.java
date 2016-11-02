import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GrilleLayout extends JPanel {

	private BufferedImage mineur;
	private BufferedImage mur;
	private BufferedImage sortie;
	private BufferedImage entree;
	private BufferedImage or;
	private BufferedImage chemin;
	private BufferedImage gardien;
	private BufferedImage epee;

	public GrilleLayout() {
		try {
			mineur = ImageIO.read(new File("mineur.png"));
			mur = ImageIO.read(new File("mur.png"));
			sortie = ImageIO.read(new File("sortie.png"));
			entree = ImageIO.read(new File("entree.png"));
			or = ImageIO.read(new File("or.png"));
			chemin = ImageIO.read(new File("chemin.png"));
			gardien = ImageIO.read(new File("Gardien.jpg"));
			epee = ImageIO.read(new File("epee.jpg"));
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * affiche l'interface graphique du labyrinthe en mode normal
	 */
	@Override
	public void paint(Graphics g) {

		int k;
		int l;
		int x;
		int y;

		for (k = 0; k < Labyrinthe.getLongueurLaby(); k++) {
			for (l = 0; l < Labyrinthe.getLargeurLaby(); l++) {
				x = 25 * l;
				y = 25 * k;

				if (Labyrinthe.getLigMineur() == k
						&& Labyrinthe.getColMineur() == l) {
					g.drawImage(mineur, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == '#') {
					g.drawImage(mur, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == 'S') {
					g.drawImage(sortie, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == 'E') {
					g.drawImage(entree, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == 'O') {
					g.drawImage(or, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == 'G') {
					g.drawImage(gardien, x, y, 25, 25, null);
				} else if (Labyrinthe.getChar(k, l) == '!') {
					g.drawImage(epee, x, y, 25, 25, null);
				}
			}
		}
		l = 0;

	}

	/**
	 * devrait permettre le controle via les fleches directionnelles mais ne
	 * marche pas
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			Labyrinthe.CaseAGauche(new Case(Labyrinthe.getLigMineur(),
					Labyrinthe.getColMineur()));
		}

		if (key == KeyEvent.VK_RIGHT) {
			Labyrinthe.CaseADroite(new Case(Labyrinthe.getLigMineur(),
					Labyrinthe.getColMineur()));
		}

		if (key == KeyEvent.VK_UP) {
			Labyrinthe.CaseAuDessus(new Case(Labyrinthe.getLigMineur(),
					Labyrinthe.getColMineur()));
		}

		if (key == KeyEvent.VK_DOWN) {
			Labyrinthe.CaseAuDessous(new Case(Labyrinthe.getLigMineur(),
					Labyrinthe.getColMineur()));
		}
	}

	/**
	 * devrait permettre le controle via les fleches directionnelles mais ne
	 * marche pas
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {

		}

		if (key == KeyEvent.VK_RIGHT) {

		}

		if (key == KeyEvent.VK_UP) {

		}

		if (key == KeyEvent.VK_DOWN) {

		}
	}

}

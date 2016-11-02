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

public class GrilleLayoutVue extends JPanel {

	private BufferedImage mineur;
	private BufferedImage mur;
	private BufferedImage sortie;
	private BufferedImage entree;
	private BufferedImage or;
	private BufferedImage chemin;
	private BufferedImage gardien;
	private BufferedImage epee;
	private int k;
	private int l;
	private int x;
	private int y;

	public GrilleLayoutVue() {
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
	 * affiche le labyrinthe dans l'interface graphique en mode sans carte
	 */
	@Override
	public void paint(Graphics g) {

		for (k = 0; k < 3; k++) {
			for (l = 0; l < 3; l++) {
				x = 32 * l;
				y = 32 * k;

				if (k == 1 && l == 1) {
					g.drawImage(mineur, x, y, 32, 32, null);
				} else if (k == 0) {
					setVue(g, Labyrinthe.getLigMineur() - 1,
							Labyrinthe.getColMineur() + l - 1);
				} else if (k == 2) {
					setVue(g, Labyrinthe.getLigMineur() + 1,
							Labyrinthe.getColMineur() - 1 + l);
				} else if (k == 1 && l == 0) {
					setVue(g, Labyrinthe.getLigMineur(),
							Labyrinthe.getColMineur() - 1);
				} else if (k == 1 && l == 2) {
					setVue(g, Labyrinthe.getLigMineur(),
							Labyrinthe.getColMineur() + 1);
				}
			}

		}
		l = 0;
	}

	/**
	 * definit l'image a afficher en fonction de la case
	 * 
	 * @param g
	 * @param lo
	 * @param la
	 */
	public void setVue(Graphics g, int lo, int la) {
		if (Labyrinthe.getChar(lo, la) == '#') {
			g.drawImage(mur, x, y, 32, 32, null);
		} else if (Labyrinthe.getChar(lo, la) == 'S') {
			g.drawImage(sortie, x, y, 32, 32, null);
		} else if (Labyrinthe.getChar(lo, la) == 'E') {
			g.drawImage(entree, x, y, 32, 32, null);
		} else if (Labyrinthe.getChar(lo, la) == 'O') {
			g.drawImage(or, x, y, 32, 32, null);
		} else if (Labyrinthe.getChar(lo, la) == 'G') {
			g.drawImage(gardien, x, y, 32, 32, null);
		} else if (Labyrinthe.getChar(lo, la) == '!') {
			g.drawImage(epee, x, y, 32, 32, null);
		}
	}
}

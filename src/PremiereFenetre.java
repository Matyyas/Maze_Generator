import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PremiereFenetre extends JFrame implements ActionListener {

	private JRadioButton normal = new JRadioButton("Avec carte");
	private JRadioButton brouillard = new JRadioButton("Sans carte");
	private JButton monBtn = new JButton("OK");
	private Labyrinthe labyrin;
	private boolean choixFait = false;
	private char choix;
	Container contentPane = this.getContentPane();

	public PremiereFenetre() {
		super("Bonjour et bienvenue sur le Labyrinthe IPIPIP !");
		this.setSize(400, 170);

		// Texte de bienvenue
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(new JLabel(" "));
		contentPane.add(new JLabel(
				"                Faites un choix :               "));
		contentPane.add(new JLabel(" "));
		// Emplacement pour écrire si besoin
		// contentPane.add(new JTextField());

		Container conteneurPrinc = this.getContentPane();
		conteneurPrinc
				.setLayout(new BoxLayout(conteneurPrinc, BoxLayout.Y_AXIS));
		ButtonGroup taille = new ButtonGroup();
		normal.addActionListener(this);
		brouillard.addActionListener(this);
		monBtn.addActionListener(new OKListener());
		normal.setToolTipText("Vous vous déplacez avec la carte.");
		brouillard.setToolTipText("Vous n'avez pas de carte.");
		taille.add(normal);
		taille.add(brouillard);
		conteneurPrinc.add(normal);
		conteneurPrinc.add(brouillard);
		Container conteneurOK = this.getContentPane();
		conteneurOK.setLayout(new BoxLayout(conteneurPrinc, BoxLayout.Y_AXIS));
		conteneurOK.add(monBtn);

		// Positionnement de la fenetre
		this.setLocationRelativeTo(null);
	}

	/**
	 * retourne le choix de mode de jeu : avec ou sans carte
	 * 
	 * @return
	 */
	public char getChoix() {
		return this.choix;
	}

	/**
	 * definit le choix de mode de jeu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == normal) {

			System.out.println("normal");
			choixFait = true;
			choix = 'n';

		} else if (source == brouillard) {
			System.out.println("sans carte");
			choixFait = true;
			choix = 'b';
		}

	}

	public Labyrinthe getLab() {
		return labyrin;
	}

	public class OKListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (choixFait == true) {
				DeuxiemeFenetre d = new DeuxiemeFenetre();
				d.setVisible(true);
			}

		}
	}

	public static void main(String[] args) {
		PremiereFenetre f = new PremiereFenetre();

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

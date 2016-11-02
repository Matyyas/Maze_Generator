import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeuxiemeFenetre extends JFrame implements ActionListener {

	private JRadioButton petit = new JRadioButton("Petit");
	private JRadioButton moyen = new JRadioButton("Moyen");
	private JRadioButton grand = new JRadioButton("Grand");
	private JRadioButton autre = new JRadioButton("Autre");
	private JPanel longueur = new JPanel();
	private JPanel largeur = new JPanel();
	private SpinnerNumberModel modelLar = new SpinnerNumberModel(21, 11, 41, 2);
	private SpinnerNumberModel modelLong = new SpinnerNumberModel(21, 11, 23, 2);
	private JSpinner spinnerLar = new JSpinner(modelLar);
	private JSpinner spinnerLong = new JSpinner(modelLong);
	private JButton monBtn = new JButton("OK");
	private Labyrinthe labyrin;
	private int lon;
	private int lar;
	private boolean choixFait = false;
	Container contentPane = this.getContentPane();

	public DeuxiemeFenetre() {
		super("Bonjour et bienvenue sur le Labyrinthe IPIPIP !");
		this.setSize(400, 250);

		// Texte de bienvenue
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(new JLabel(" "));
		contentPane.add(new JLabel("Faites un choix :"));
		contentPane.add(new JLabel(" "));
		// Emplacement pour écrire si besoin
		// contentPane.add(new JTextField());

		Container conteneurPrinc = this.getContentPane();
		conteneurPrinc
				.setLayout(new BoxLayout(conteneurPrinc, BoxLayout.Y_AXIS));
		ButtonGroup taille = new ButtonGroup();

		petit.addActionListener(this);
		moyen.addActionListener(this);
		grand.addActionListener(this);
		autre.addActionListener(this);
		monBtn.addActionListener(new OKListener());

		petit.setToolTipText("13x25");
		moyen.setToolTipText("17x29");
		grand.setToolTipText("23x39");
		taille.add(petit);
		taille.add(moyen);
		taille.add(grand);
		taille.add(autre);
		conteneurPrinc.add(petit);
		conteneurPrinc.add(moyen);
		conteneurPrinc.add(grand);
		conteneurPrinc.add(autre);

		// Choix de la longueur
		Container conteneurUn = this.getContentPane();
		conteneurUn.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		longueur.setLayout(new BoxLayout(longueur, BoxLayout.X_AXIS));
		longueur.add(new JLabel("Longueur :                    "));
		longueur.add(spinnerLong);
		conteneurUn.add(longueur);
		spinnerLong.setEnabled(false);

		// Choix de la largeur
		Container conteneurDeux = this.getContentPane();
		conteneurDeux.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		largeur.setLayout(new BoxLayout(largeur, BoxLayout.X_AXIS));
		largeur.add(new JLabel("Largeur :                       "));
		largeur.add(spinnerLar);
		conteneurDeux.add(largeur);
		spinnerLar.setEnabled(false);

		Container conteneurOK = this.getContentPane();
		conteneurOK.add(monBtn);

		// Positionnement de la fenetre
		this.setLocationRelativeTo(null);

	}

	/**
	 * definit la taille du labyrinthe en fonction du choix
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == petit) {

			System.out.println("petit");
			spinnerLar.setEnabled(false);
			spinnerLong.setEnabled(false);
			lon = 13;
			lar = 25;
			choixFait = true;

		} else if (source == moyen) {
			System.out.println("moyen");
			spinnerLar.setEnabled(false);
			spinnerLong.setEnabled(false);
			lon = 17;
			lar = 29;
			choixFait = true;
		} else if (source == grand) {
			System.out.println("grand");
			spinnerLar.setEnabled(false);
			spinnerLong.setEnabled(false);
			lon = 23;
			lar = 39;
			choixFait = true;
		} else if (source == autre) {
			spinnerLar.setEnabled(true);
			spinnerLong.setEnabled(true);
			lon = (int) modelLong.getNumber();
			lar = (int) modelLar.getNumber();
			choixFait = true;
		}

	}

	/*
	 * public Labyrinthe getLab() { return labyrin; }
	 */

	public class OKListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (choixFait == true) {
				labyrin = new Labyrinthe(lon, lar);

			}

		}
	}

	public static void main(String[] args) {
		PremiereFenetre f = new PremiereFenetre();

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

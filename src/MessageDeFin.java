import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageDeFin extends Message {

	private JButton Button1, Button2;

	public MessageDeFin(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
		int option = JOptionPane.showConfirmDialog(this, "Rejouer ?",
				"Que voulez vous faire ?", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Labyrinthe.setJeu(true);
		} else {
			System.exit(0);

		}
	}

	public static void main(String[] args) {
		MessageDeFin f = new MessageDeFin("test");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

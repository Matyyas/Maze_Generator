import javax.swing.*;

public class Message extends JFrame {
	public Message(String msg) {
		super("Message");
		JOptionPane.showMessageDialog(this, msg, "Message",
				JOptionPane.PLAIN_MESSAGE);
	}

}
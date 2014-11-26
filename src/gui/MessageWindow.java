package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MessageWindow {
	public MessageWindow(String message) {
		   final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, message, "Information", JOptionPane.INFORMATION_MESSAGE);     
    }
}

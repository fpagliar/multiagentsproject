package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	private DataPanel dataPanel;
	
	public MainWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(1, 1, 1200, 1000);
		setResizable(false);
		center();

		mainPanel = new MainPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setSize(1000, 1000);

		dataPanel = new DataPanel();
		dataPanel.setBackground(Color.DARK_GRAY);
		dataPanel.setSize(200, 1000);

		add(mainPanel);
		add(dataPanel);
	}

	public void center() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
	}

	public void repaint() {
		mainPanel.repaint();
	}

}

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	private DataPanel dataPanel;
//	private JProgressBar progressBar;	
	public volatile boolean paused = false;
	private JButton pauseButton;
	
	private static MainWindow window = new MainWindow();
	
	public static MainWindow getInstance() {
		return window;
	}
	
	public MainWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(1, 1, 1400, 1000);
		setResizable(false);
		center();

		mainPanel = new MainPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setSize(1000, 1000);

		dataPanel = new DataPanel();
		dataPanel.setBackground(Color.DARK_GRAY);
		dataPanel.setSize(400, 1000);
		
//		progressBar = new JProgressBar(0, QLearner.TOTAL_EPOCHS);
//		progressBar.setValue(0);
//		progressBar.setSize(1000, 30);
//		progressBar.setVisible(true);
//		progressBar.setStringPainted(true);
		
		pauseButton = new JButton("Pause");
		pauseButton.setVisible(true);
		pauseButton.setSize(100, 30);
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				paused = !paused;
				if(paused)
					pauseButton.setText("Continue");
				else
					pauseButton.setText("Pause");
			}
		});
		
		add(pauseButton);
//		add(progressBar);
		add(mainPanel);
		add(dataPanel);
	}

	public void center() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);
	}

	public void putString(final String value) {
		dataPanel.putString(value);
	}

	public void clearStrings() {
		dataPanel.clear();
	}
	
//	public void setProgress(final int newValue) {
//		progressBar.setValue(newValue);
//	}
	
	public void setTitle(final String value) {
		dataPanel.setTitle(value);
	}
}

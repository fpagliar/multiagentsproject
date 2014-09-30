package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Set<Point> notEmpty = new HashSet<Point>();
	private GUIBoard board = GUIBoard.getInstance();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Set<Point> used = new HashSet<>();
		for (final Paintable p : board.getObjects()) {
			used.addAll(p.paint(g));
		}

		for (final Point p : notEmpty) {
			if (!used.contains(p)) {
				board.getEmptyCell().paint(g, p);
			}
		}
		notEmpty = used;
	}

}

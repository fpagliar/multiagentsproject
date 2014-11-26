package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Set<Point> notEmpty = new HashSet<Point>();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Set<Point> used = new HashSet<>();
		List<Paintable> list = GUIBoard.getInstance().getObjects();
		for (final Paintable p : list) {
				used.addAll(p.paint(g));
		}

		for (final Point p : notEmpty) {
			if (!used.contains(p)) {
				GUIBoard.getInstance().getEmptyCell().paint(g, p);
			}
		}
		notEmpty = used;
	}
}

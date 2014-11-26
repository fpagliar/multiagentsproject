package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int STRING_HEIGHT = 20;

	private static Set<String> values = new TreeSet<String>();
	private static String title = "";

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawString(title, 1000 + 90, 15);

		int pos = 1;
		for (final String string : values) {
			if (pos % 2 == 0)
				g.setColor(Color.GRAY);
			else
				g.setColor(Color.WHITE);
			g.drawString(string, 1000 + 10, pos * STRING_HEIGHT + 20);
			pos++;
		}
	}

	public void putString(final String value) {
		final Set<String> newList = new TreeSet<>();
		newList.addAll(values);
		newList.add(value);
		values = newList;
	}

	public void setTitle(final String value) {
		title = value;
	}

	public void clear() {
		values = new TreeSet<>();
	}
}

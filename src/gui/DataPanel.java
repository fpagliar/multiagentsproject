package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int STRING_HEIGHT = 20;

	private static List<String> values = new ArrayList<String>();
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
		final List<String> newList = new ArrayList<String>();
		newList.addAll(DataPanel.values);
		newList.add(value);
		DataPanel.values = newList;
	}

	public void setTitle(final String value) {
		DataPanel.title = value;
	}

	public void clear() {
		DataPanel.values = new ArrayList<String>();
	}
}

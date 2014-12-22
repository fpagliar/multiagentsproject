package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.BlackBoard;
import model.Tactic;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int STRING_HEIGHT = 20;

	private static List<String> values = new ArrayList<String>();
	private static String title = "";
	private static BlackBoard blackboard = BlackBoard.getInstance();

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
		pos = 25;
		g.setColor(Color.RED);
		g.drawString("BLACKBOARD - FAILED", 1000 + 140, pos * STRING_HEIGHT + 20);
		pos++;
		for (final Tactic tactic : blackboard.getTacticsFailed()) {
			if (pos % 2 == 0)
				g.setColor(Color.GRAY);
			else
				g.setColor(Color.WHITE);
			g.drawString(tactic.toString(), 1000 + 10, pos * STRING_HEIGHT + 20);
			pos++;
		}
		pos += 1;
		g.setColor(Color.RED);
		g.drawString("BLACKBOARD - SUCCESS", 1000 + 140, pos * STRING_HEIGHT + 20);
		pos++;
		for (final Tactic tactic : blackboard.getSuccessfulTactics()) {
			if (pos % 2 == 0)
				g.setColor(Color.GRAY);
			else
				g.setColor(Color.WHITE);
			g.drawString(tactic.toString(), 1000 + 10, pos * STRING_HEIGHT + 20);
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

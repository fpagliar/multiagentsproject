package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GUIEmptyCell {
	
	private GUIEmptyCell() {
	}

	public static GUIEmptyCell getInstance() {
		return new GUIEmptyCell();
	}

	public void paint(Graphics g, Point p) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(p.x, p.y, 1, 1);
	}

}

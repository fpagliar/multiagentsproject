package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

import agents.Wall;

public class GUIWall implements Paintable {

	private final Wall wall;

	public GUIWall(final Wall wall) {
		this.wall = wall;
	}

	public Wall getWall() {
		return wall;
	}

	@Override
	public Set<Point> paint(Graphics g) {
		g.setColor(Color.BLACK);
		Rectangle position = wall.getPosition();
		g.fillRect(position.x, position.y, position.width, position.height);
		return wall.occupiedPoints();
	}

	@Override
	public Object getBackendModel() {
		return wall;
	}

}

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

import agents.Creature;
import agents.RectangularObject;

public abstract class GUICreature<T extends Creature> extends GUIImageObject {
	protected final T creature;

	protected GUICreature(final T creature) {
		this.creature = creature;
	}

	public T getCreature() {
		return creature;
	}

	@Override
	protected Set<Point> occupiedPoints() {
		return creature.occupiedPoints();
	}

	@Override
	protected String descriptor() {
		return creature.toString();
	}

	@Override
	protected Rectangle getPosition() {
		return creature.getPosition();
	}
	
	@Override
	public RectangularObject getBackendModel() {
		return creature;
	}
	
	@Override
	public Set<Point> paint(Graphics g) {
		Set<Point> points = super.paint(g);
		final int radius = creature.getReach();
		final Rectangle position = creature.getPosition();
		int centerX = position.x + position.width / 2;
		int centerY = position.y + position.height/2;
		g.setColor(Color.CYAN);
		for(int x = centerX - radius; x < centerX + radius ; x++){
			int y = (int) Math.pow(radius * radius - (x - centerX) * (x - centerX), 0.5);
			g.drawRect(x, centerY + y, 1, 1);
			g.drawRect(x, centerY - y, 1, 1);			
		}
		
		return points;
	}

}

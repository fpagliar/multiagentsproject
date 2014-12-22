package agents.starAgent;

import java.awt.Point;
import java.awt.Rectangle;

import model.Board;
import utils.RandomGenerator;
import agents.ruleBased.LocusMovementAction;
import agents.ruleBased.RoolBasedCreature;

public class StarCreature extends RoolBasedCreature {
		
	private static final int HEALTH = 2;

	protected StarCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}

	public static StarCreature newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 1000);
			int y = (int) (RandomGenerator.getNext() * 1000);
			final Rectangle pos = new Rectangle(x, y, 20, 20);
			if (Board.getInstance().isFree(pos) && !Board.getInstance().inShootingZone(pos)) 
				return new StarCreature(HEALTH, new Rectangle(x, y, 20, 20), 3);
		}
	}

	public static StarCreature newInstance(final Point p) {
		return new StarCreature(HEALTH, new Rectangle(p.x, p.y, 20, 20), 3);
	}
	
	public static StarCreature newInstance(final Rectangle rect) {
		return new StarCreature(HEALTH, rect, 3);
	}
	
	@Override
	public String toString() {
		return "StarCreature: " + super.toString();
	}

	@Override
	public String labelString() {
		return "* " + getId() + ": " + getHealth()  + " -> " + LocusMovementAction.calculateCuadrant(this);
	}
	
}

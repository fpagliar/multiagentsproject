package agents;

import java.awt.Point;
import java.awt.Rectangle;

import model.Board;
import utils.RandomGenerator;
import agents.ruleBased.LocusMovementAction;
import agents.ruleBased.RoolBasedCreature;

public class AmpersandCreature extends RoolBasedCreature {

	private static final int HEALTH = 3;
	private static final int SPEED = 3;
	
	private AmpersandCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}
	
	public static AmpersandCreature newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 900) + 90;
			int y = (int) (RandomGenerator.getNext() * 900) + 90;
			if (x > 450 && x < 550)
				x = 550;
			if (y > 450 && y < 550)
				y = 550;
			final Rectangle pos = new Rectangle(x, y, 32, 32);
			if(Board.getInstance().isFree(pos) && !Board.getInstance().inShootingZone(pos))
				return new AmpersandCreature(HEALTH, pos, SPEED);
		}
	}

	public static AmpersandCreature newInstance(final Point p) {
		return new AmpersandCreature(HEALTH, new Rectangle(p.x, p.y, 32, 32), SPEED);
	}
	
	@Override
	public String toString() {
		return "HashtagCreature: " + super.toString();
	}
		
	@Override
	public String labelString() {
		return "& " + getId() + ": " + getHealth() + " -> " + LocusMovementAction.calculateCuadrant(this);
	}

}

package agents;

import java.awt.Point;
import java.awt.Rectangle;

public class StarCreature extends Creature {

	private StarCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}

	public static StarCreature newInstance() {
		int x = (int)(Math.random() * 1000);
		int y = (int)(Math.random() * 1000);
		return new StarCreature(1, new Rectangle(x, y, 20, 20), 3);
//		return new StarCreature(1, new Rectangle(0, 0, 20, 20), 3);
	}

	public static StarCreature newInstance(final Point p) {
		return new StarCreature(1, new Rectangle(p.x, p.y, 20, 20), 3);
	}
	
	@Override
	public String toString() {
		return "StarCreature: " + super.toString();
	}
}

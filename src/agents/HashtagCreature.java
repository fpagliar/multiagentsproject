package agents;

import java.awt.Point;
import java.awt.Rectangle;

public class HashtagCreature extends Creature {

	private HashtagCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}
	
	public static HashtagCreature newInstance() {
		int x = (int)(Math.random() * 1000);
		int y = (int)(Math.random() * 1000);
		return new HashtagCreature(3, new Rectangle(x, y, 40, 40), 1);
//		return new HashtagCreature(3, new Rectangle(20, 20, 40, 40), 1);
	}

	public static HashtagCreature newInstance(final Point p) {
		return new HashtagCreature(3, new Rectangle(p.x, p.y, 40, 40), 1);
	}
	
	@Override
	public String toString() {
		return "HashtagCreature: " + super.toString();
	}

}

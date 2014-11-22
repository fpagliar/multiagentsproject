package agents;

import java.awt.Point;
import java.awt.Rectangle;

import model.Board;
import utils.RandomGenerator;

public class StarCreature extends Creature {
	
	protected StarCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}

	public static StarCreature newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 1000);
			int y = (int) (RandomGenerator.getNext() * 1000);
			if (Board.getInstance().isFree(new Rectangle(x, y, 20, 20))) {
				System.out.println("CREATING IN -> x:" + x + " y:" + y);
				return new StarCreature(1, new Rectangle(x, y, 20, 20), 3);
			} else
				System.out.println("AA");
		}
//		return new StarCreature(1, new Rectangle(0, 0, 20, 20), 3);
	}

	public static StarCreature newInstance(final Point p) {
		return new StarCreature(1, new Rectangle(p.x, p.y, 20, 20), 3);
	}
	
	public static StarCreature newInstance(final Rectangle rect) {
		return new StarCreature(1, rect, 3);
	}
	
	@Override
	public String toString() {
		return "StarCreature: " + super.toString();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String labelString() {
		return "Star Creature: " + getId();
	}
	
}

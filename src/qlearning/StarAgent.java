package qlearning;

import java.awt.Point;
import java.awt.Rectangle;

import model.Board;
import utils.RandomGenerator;
import agents.StarCreature;

public class StarAgent extends StarCreature {

	public enum AgentAction {
		ROTATE, FOWARD, STAY;
	}

	private Direction direction;

	public StarAgent(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
		direction = Direction.UP;
	}

	public static StarAgent newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 300 + 200);
			int y = (int) (RandomGenerator.getNext() * 300 + 200);
			if (Board.getInstance().isFree(new Rectangle(x, y, 20, 20)))
				return new StarAgent(1, new Rectangle(x, y, 20, 20), 3);
		}
	}

	public static StarAgent newInstance(final Point p) {
		return new StarAgent(1, new Rectangle(p.x, p.y, 20, 20), 3);
	}

	public static StarAgent newInstance(final Rectangle rect) {
		return new StarAgent(1, rect, 3);
	}

	public void perform(final AgentAction action) {
		if (action == AgentAction.ROTATE) {
			if (direction == Direction.UP) {
				direction = Direction.LEFT;
			} else if (direction == Direction.LEFT) {
				direction = Direction.DOWN;
			} else if (direction == Direction.DOWN) {
				direction = Direction.RIGHT;
			} else if (direction == Direction.RIGHT) {
				direction = Direction.UP;
			} else {
				throw new AssertionError();
			}

		} else if (action == AgentAction.FOWARD) {
			super.move(direction, getMaxSpeed());
		} else if (action == AgentAction.STAY) {
			return; // DO NOTHING
		} else {
			throw new AssertionError();
		}
	}

	public boolean canMove() {
		return canMove(direction, getMaxSpeed());
	}

	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public void move(Direction direction, int speed) {
		throw new IllegalStateException("USE PERFORM");
	}

}

package agents.ruleBased;

import java.awt.Rectangle;

import utils.RandomGenerator;
import model.Action;
import model.Board;
import agents.Creature;
import agents.RectangularObject.Direction;

public class LocusMovementAction implements Action {

	private CircularDirection actualDirection;

	public enum CircularDirection {
		CLOCKWISE, ANTICLOCKWISE, STAY
	}

	public enum Cuadrant {
		BOTTOM_LEFT, BOTTOM_RIGHT, UPPER_LEFT, UPPER_RIGHT, BOTTOM_CENTER, UPPER_CENTER, CENTER_LEFT, CENTER_RIGHT;
	}

	public static LocusMovementAction createAction() {
		return new LocusMovementAction();
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		recalculateDirection();
		if (actualDirection == CircularDirection.STAY)
			return;
		final Cuadrant cuadrant = calculateCuadrant(creature);
		switch (cuadrant) {
		case CENTER_LEFT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if (creature.canMove(Direction.UP, 1))
					creature.move(Direction.UP, 1);
				return;
			} else {
				if (creature.canMove(Direction.DOWN, 1))
					creature.move(Direction.DOWN, 1);
				return;
			}
		case CENTER_RIGHT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if (creature.canMove(Direction.DOWN, 1))
					creature.move(Direction.DOWN, 1);
				return;
			} else {
				if (creature.canMove(Direction.UP, 1))
					creature.move(Direction.UP, 1);
				return;
			}
		case BOTTOM_CENTER:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if (creature.canMove(Direction.LEFT, 1))
					creature.move(Direction.LEFT, 1);
				return;
			} else {
				if (creature.canMove(Direction.RIGHT, 1))
					creature.move(Direction.RIGHT, 1);
				return;
			}
		case UPPER_CENTER:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if (creature.canMove(Direction.RIGHT, 1))
					creature.move(Direction.RIGHT, 1);
				return;
			} else {
				if (creature.canMove(Direction.LEFT, 1))
					creature.move(Direction.LEFT, 1);
				return;
			}
		case BOTTOM_LEFT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if(move(creature, board, Direction.LEFT))
					return;
				move(creature, board, Direction.UP);
				return;
			} else {
				if(move(creature, board, Direction.DOWN))
					return;
				move(creature, board, Direction.RIGHT);
				return;
			}
		case UPPER_LEFT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if(move(creature, board, Direction.RIGHT))
					return;
				move(creature, board, Direction.UP);
				return;
			} else {
				if(move(creature, board, Direction.LEFT))
					return;
				move(creature, board, Direction.DOWN);
				return;
			}
		case UPPER_RIGHT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if(move(creature, board, Direction.RIGHT))
					return;
				move(creature, board, Direction.DOWN);
				return;
			} else {
				if(move(creature, board, Direction.LEFT))
					return;
				move(creature, board, Direction.UP);
				return;
			}
		case BOTTOM_RIGHT:
			if (actualDirection == CircularDirection.CLOCKWISE) {
				if(move(creature, board, Direction.DOWN))
					return;
				move(creature, board, Direction.LEFT);
				return;
			} else {
				if(move(creature, board, Direction.RIGHT))
					return;
				move(creature, board, Direction.UP);
				return;
			}
		default:
			break;
		}
	}
	
	private boolean move(final Creature creature, final Board board, final Direction direction) {
		final Rectangle newPos = creature.getMovePosition(direction, 5);
		final Rectangle actualPos = creature.getPosition();
		board.inShootingZone(actualPos);
		board.inShootingZone(newPos);
		if (board.inCircleOfFire(newPos) &&
				!board.inShootingZone(newPos) && creature.canMove(direction, 5)) {
			creature.move(direction, 1);
			return true;
		}
		return false;
	}

	private void recalculateDirection() {
		if (actualDirection == null || RandomGenerator.getNext() > 0.99) {
			if (RandomGenerator.getNext() > .8)
				actualDirection = CircularDirection.STAY;
			else{
				if (RandomGenerator.getNext() > .5)
					actualDirection = CircularDirection.CLOCKWISE;
				else
					actualDirection = CircularDirection.ANTICLOCKWISE;					
			}
			// int rand = (int) (RandomGenerator.getNext() *
			// CircularDirection.values().length);
			// actualDirection = CircularDirection.values()[rand];
		}
	}

	public static Cuadrant calculateCuadrant(final Creature creature) {
		final int centerX = creature.center().x;
		final int centerY = creature.center().y;
		if(centerX == 500)
			if(centerY < 500)
				return Cuadrant.UPPER_CENTER;
			else
				return Cuadrant.BOTTOM_CENTER;
		if(centerY == 500)
			if(centerX > 500)
				return Cuadrant.CENTER_RIGHT;
			else
				return Cuadrant.CENTER_LEFT;
		if (centerY > 500) {
			if (centerX > 500)
				return Cuadrant.BOTTOM_RIGHT;
			else
				return Cuadrant.BOTTOM_LEFT;
		} else {
			if (centerX > 500)
				return Cuadrant.UPPER_RIGHT;
			else
				return Cuadrant.UPPER_LEFT;
		}
	}
}

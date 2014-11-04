package agents.hashtagCreature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Action;
import model.Board;
import agents.Creature;
import agents.RectangularObject.Direction;

public class ExtremeDodgeObstaclesAction implements Action {

	private static ExtremeMovementAction movementAction = ExtremeMovementAction.getAction();

	// This is the memory
	private Direction movement;
	private Direction target;

	public static ExtremeDodgeObstaclesAction createAction() {
		return new ExtremeDodgeObstaclesAction();
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		if (target == null)
			setTarget(creature, board);

		if (creature.canMove(target, creature.getMaxSpeed())) {
			movement = target;
		}

		if (movement == null || !creature.canMove(movement, creature.getMaxSpeed())) {
			final List<Direction> dirs = Arrays.asList(Direction.values());
			final List<Direction> possible = new ArrayList<>();
			for (Direction dir : dirs) {
				if (creature.canMove(dir, creature.getMaxSpeed()) && dir.getPoint().x != target.getPoint().x
						&& dir.getPoint().y != target.getPoint().y)
					possible.add(dir);
			}
			if (possible.size() == 0) {
				// System.out.println("NO POSSIBLE MOVES :(");
				throw new IllegalStateException("NO POSSIBLE MOVES :(");
				// return;
			}
			movement = possible.get((int) (Math.random() * possible.size()));
		}

		// FIXME: it should dodge, not dummy go other place.
		// Move to a random possible direction
		creature.move(movement, creature.getMaxSpeed());
		return;
	}

	private void setTarget(final Creature agent, final Board board) {
		final List<Direction> dirs = new ArrayList<>(movementAction.getDirections(agent, board));
		target = dirs.get((int) (Math.random() * dirs.size()));
	}

}
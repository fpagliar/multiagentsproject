package agents.hashtagCreature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Action;
import model.Board;
import agents.Creature;
import agents.RectangularObject.Direction;

public class DodgeObstaclesAction implements Action {

	private static DodgeObstaclesAction action = new DodgeObstaclesAction();
	private BasicMovementAction movementAction = BasicMovementAction.getAction();

	public static DodgeObstaclesAction getAction() {
		return action;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		final List<Direction> dirs = Arrays.asList(Direction.values());
		final List<Direction> possible = new ArrayList<>();
		for (Direction dir : dirs) {
			if (creature.canMove(dir, creature.getMaxSpeed()))
				possible.add(dir);
		}
		if (possible.size() == 0) {
			System.out.println("NO POSSIBLE MOVES :(");
			return;
		}
		// FIXME: it should dodge, not dummy go other place.
		// Move to a random possible direction
		creature.move(possible.get((int) (Math.random() * possible.size())), creature.getMaxSpeed());
		return;
	}

}

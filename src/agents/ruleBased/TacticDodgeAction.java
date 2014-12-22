package agents.ruleBased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Action;
import model.Board;
import utils.RandomGenerator;
import agents.Creature;
import agents.RectangularObject.Direction;

public class TacticDodgeAction implements Action {
	
	// This is the memory
	private Direction movement;
	private Direction target;
	
	public TacticDodgeAction(final Direction target) {
		this.target = target;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		if (movement == null || !creature.canMove(movement, creature.getMaxSpeed())) {
			final List<Direction> dirs = Arrays.asList(Direction.values());
			final List<Direction> possible = new ArrayList<>();
			for (Direction dir : dirs) {
				if (creature.canMove(dir, creature.getMaxSpeed()) && dir.getPoint().x != target.getPoint().x
						&& dir.getPoint().y != target.getPoint().y)
					possible.add(dir);
			}
			//Trapped
			if (possible.size() == 0) {
				 return;
			}
			movement = possible.get((int) (RandomGenerator.getNext() * possible.size()));
		}

		creature.move(movement, creature.getMaxSpeed());
		return;
	}
}

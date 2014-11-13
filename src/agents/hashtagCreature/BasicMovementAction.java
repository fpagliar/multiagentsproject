package agents.hashtagCreature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.RandomGenerator;
import model.Action;
import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.RectangularObject.Direction;

public class BasicMovementAction implements Action {

	private static BasicMovementAction action = new BasicMovementAction();

	public static BasicMovementAction getAction() {
		return action;
	}

	public Set<Direction> getDirections(final Creature creature, final Board board) {
		final Cannon cannon = board.getCannon(creature);
		int diffX = creature.getPosition().x - cannon.getPosition().x;
		int diffY = creature.getPosition().y - cannon.getPosition().y;
		int deltaX = (diffX < 0) ? 1 : -1;
		int deltaY = (diffY < 0) ? 1 : -1;
		final Set<Direction> ans = new HashSet<>();
		if (Math.abs(diffX) > (creature.getPosition().width/2)) {
			ans.add(Direction.from(deltaX, 0));
		}
		if (Math.abs(diffY) > (creature.getPosition().height/2)) {
			ans.add(Direction.from(0, deltaY));
		}
		return ans;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		final List<Direction> dirs = new ArrayList<Direction>();
		for(Direction d : getDirections(creature, board))
			if(creature.canMove(d, creature.getMaxSpeed()))
				dirs.add(d);
		
		if (dirs.size() == 0)
			throw new IllegalStateException("WHERE THE FUCK AM I?");
		
		// Move to a random possible direction
		creature.move(dirs.get((int) (RandomGenerator.getNext() * dirs.size())), creature.getMaxSpeed());
		return;
	}

}

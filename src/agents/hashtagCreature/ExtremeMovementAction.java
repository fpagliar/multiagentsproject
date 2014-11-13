
package agents.hashtagCreature;

import java.util.HashSet;
import java.util.Set;

import model.Action;
import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.RectangularObject.Direction;

public class ExtremeMovementAction implements Action {


	private static ExtremeMovementAction action = new ExtremeMovementAction();

	public static ExtremeMovementAction getAction() {
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
		return;
	}
}

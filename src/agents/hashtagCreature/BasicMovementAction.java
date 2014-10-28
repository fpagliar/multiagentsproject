package agents.hashtagCreature;

import agents.Cannon;
import agents.Creature;
import agents.RectangularObject.Direction;
import model.Action;
import model.Board;

public class BasicMovementAction implements Action {

	private static BasicMovementAction action = new BasicMovementAction();

	public static BasicMovementAction getAction() {
		return action;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		final Cannon cannon = board.getCannon(creature);
		int deltaX = (creature.getPosition().x < cannon.getPosition().x)? 1 : -1;
		int deltaY = (creature.getPosition().y < cannon.getPosition().y)? 1 : -1;
		if ((deltaX != 0) && ((deltaY == 0) || Math.random() > 0.5)) {
			creature.move(Direction.from(deltaX, 0), creature.getMaxSpeed());
		} else if(deltaY != 0){
			creature.move(Direction.from(0, deltaY), creature.getMaxSpeed());			
		} else {
			throw new IllegalStateException("WHERE THE FUCK AM I?");
		}
		return;
	}

}

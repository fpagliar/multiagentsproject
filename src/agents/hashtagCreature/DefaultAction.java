package agents.hashtagCreature;

import agents.Creature;
import model.Action;
import model.Board;

public class DefaultAction implements Action {

	private static final DefaultAction action = new DefaultAction();

	public static DefaultAction getAction() {
		return action;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		// Do nothing
		return;
	}
}

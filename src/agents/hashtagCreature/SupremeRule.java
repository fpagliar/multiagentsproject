package agents.hashtagCreature;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;

public class SupremeRule implements HeuristicRule {

	private static SupremeRule rool = new SupremeRule();

	public static SupremeRule getRule() {
		return rool;
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		return board.touchingCannon(agent);
	}

	@Override
	public Action getAction() {
		//STAY
		return DefaultAction.getAction();
	}

}
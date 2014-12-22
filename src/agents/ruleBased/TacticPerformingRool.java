package agents.ruleBased;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;

public class TacticPerformingRool implements HeuristicRule {

	private TacticPerformingAction action = new TacticPerformingAction();

	public static TacticPerformingRool createRule() {
		return new TacticPerformingRool();
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		final RoolBasedCreature creature = (RoolBasedCreature) agent;
		return creature.getTactic() != null;
	}

	@Override
	public Action getAction() {
		return action;
	}

}

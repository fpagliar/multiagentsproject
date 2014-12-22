package agents.ruleBased;

import java.util.HashSet;
import java.util.Set;

import utils.RandomGenerator;
import model.Action;
import model.BlackBoard;
import model.Board;
import model.HeuristicRule;
import model.Tactic;
import model.Tactic.Strategy;
import agents.Creature;

public class TacticFormingRule implements HeuristicRule {

	private final static int MIN_VAL = 7;
	private final static int MIN_SIZE = 2;

	private TacticFormingAction action;

	public static TacticFormingRule createRule() {
		return new TacticFormingRule();
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		if (board.inShootingZone(agent))
			return true;
		if (!board.inCircleOfFire(agent)) {
			action = null;
			return false;
		}

		final Set<Creature> group = new HashSet<>();
		for(final Creature c : board.getCreaturesInSight(agent)){
			if(board.inCircleOfFire(c))
				group.add(c);
		}
		group.add(agent);
		final Strategy strategy = Strategy.values()[(int)(RandomGenerator.getNext() * Strategy.values().length)];
		final Tactic tactic = new Tactic(group, strategy);
		if (tactic.getValue() >= MIN_VAL && tactic.getCreatures().size() >= MIN_SIZE
				&& !BlackBoard.getInstance().failedContains(tactic)) {
			action = new TacticFormingAction(tactic);
			return true;
		} else {
			action = null;
			return false;
		}
	}

	@Override
	public Action getAction() {
		return action;
	}

}

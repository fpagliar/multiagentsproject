package agents.ruleBased;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;

public class BasicMovementRule implements HeuristicRule {
	
	private static BasicMovementRule rool = new BasicMovementRule();

	public static BasicMovementRule getRule() {
		return rool;
	}
	
	@Override
	public boolean applies(final Creature agent, final Board board) {
		return ! board.inCircleOfFire(agent);
	}

	@Override
	public Action getAction() {
		return BasicMovementAction.getAction();
	}

}

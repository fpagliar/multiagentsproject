package agents.hashtagCreature;

import java.util.Set;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.RectangularObject.Direction;

public class DodgeObstaclesRule implements HeuristicRule {

	private static DodgeObstaclesRule rool = new DodgeObstaclesRule();
	private BasicMovementAction movementAction = BasicMovementAction.getAction();

	public static DodgeObstaclesRule getRule() {
		return rool;
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		final Set<Direction> dirs = movementAction.getDirections(agent, board);
		boolean blockedMoves = false;
		for (Direction dir : dirs) {
			if (!agent.canMove(dir, agent.getMaxSpeed()))
				blockedMoves = true;
		}
		return blockedMoves;
	}

	@Override
	public Action getAction() {
		return DodgeObstaclesAction.getAction();
	}

}

package agents.hashtagCreature;

import java.util.Set;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.RectangularObject.Direction;


public class ExtremeDodgeObstaclesRule implements HeuristicRule {

	// private static DodgeObstaclesRule rool = new DodgeObstaclesRule();
	private ExtremeMovementAction movementAction = ExtremeMovementAction.getAction();
	private ExtremeDodgeObstaclesAction action = ExtremeDodgeObstaclesAction.createAction();
	
	// Not a singleton, because the action has memory, so it is an instance per rule
	public static ExtremeDodgeObstaclesRule createRule() {
		return new ExtremeDodgeObstaclesRule();
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		if(!board.inCircleOfFire(agent))
			return false;
		final Set<Direction> dirs = movementAction.getDirections(agent, board);
		boolean canMove = false;
		for (Direction dir : dirs) {
			canMove = canMove || agent.canMove(dir, agent.getMaxSpeed());
		}
		return ! canMove;
	}

	@Override
	public Action getAction() {
		return action;
	}

}
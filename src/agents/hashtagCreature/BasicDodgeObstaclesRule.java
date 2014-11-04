package agents.hashtagCreature;

import java.util.Set;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.RectangularObject.Direction;

public class BasicDodgeObstaclesRule implements HeuristicRule {

	// private static DodgeObstaclesRule rool = new DodgeObstaclesRule();
	private BasicMovementAction movementAction = BasicMovementAction.getAction();
	// It should be called when a new target is set
	private DodgeObstaclesAction action = DodgeObstaclesAction.createAction(this);
	
	private Direction target = null;
	
	
	// Not a singleton, because the action has memory, so it is an instance per rule
	public static BasicDodgeObstaclesRule createRule() {
		return new BasicDodgeObstaclesRule();
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		if(board.inCircleOfFire(agent))
			return false;
		// If I have no target, I should check if I have to dodge
		if (target == null) {
			final Set<Direction> dirs = movementAction.getDirections(agent, board);
			boolean canMove = false;
			for (final Direction dir : dirs) {
				canMove = canMove || agent.canMove(dir, agent.getMaxSpeed());
			}
			// If it applies, it will set the target!
			return !canMove;
		} else {
			// I already have a target set
			return true;
		}		
//		boolean blockedMoves = false;
//		for (Direction dir : dirs) {
//			if (!agent.canMove(dir, agent.getMaxSpeed()))
//				blockedMoves = true;
//		}
//		return blockedMoves;
	}

	/**
	 * Setting the target or nulling it out
	 */
	public void setTarget(final Direction d) {
		target = d;
	}
	
	@Override
	public Action getAction() {
		return action;
	}

}

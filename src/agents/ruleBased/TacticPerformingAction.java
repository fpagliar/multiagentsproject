package agents.ruleBased;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import utils.RandomGenerator;
import model.Action;
import model.Board;
import model.Tactic;
import agents.Creature;
import agents.RectangularObject.Direction;

public class TacticPerformingAction implements Action {

	private static BasicMovementAction action = new BasicMovementAction();
	private TacticDodgeAction dodgeAction;

	public TacticPerformingAction() {
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		final RoolBasedCreature agent = (RoolBasedCreature) creature;
		final Tactic tactic = agent.getTactic();
		switch (tactic.getStrategy()) {
		case FREE_FOR_ALL:
			freeForAllAction(agent, board);
			return;
		case TANKING:
			// If it's killing me
			final Creature currentTarget = board.getCannon(creature).getTarget();
			if (currentTarget != null && currentTarget.equals(creature)) {
				freeForAllAction(creature, board);
			} else {
				tankingAction(agent, board, tactic);
			}
			return;
		case HALF_SWITCHER:
			halfSwitcherAction(agent, board, tactic);
			return;
		}
	}

	private void halfSwitcherAction(final Creature creature, final Board board, final Tactic tactic) {
		final double distance = board.distanceToTarget(creature);
		if(distance < board.getCannon(creature).getReach() / 2)
			freeForAllAction(creature, board);
		else
			tankingAction(creature, board, tactic);
	}
	
	private void freeForAllAction(final Creature creature, final Board board) {
		final Set<Direction> dirs = action.getDirections(creature, board);
		for (final Direction dir : dirs) {
			if (creature.canMove(dir, creature.getMaxSpeed())) {
				creature.move(dir, creature.getMaxSpeed());
				dodgeAction = null;
				return;
			}
		}
		if (dodgeAction == null) {
			final Direction dir = new ArrayList<Direction>(dirs).get((int) (RandomGenerator.getNext() * dirs.size()));
			dodgeAction = new TacticDodgeAction(dir);
		}
		dodgeAction.perform(creature, board);
	}

	private void tankingAction(final Creature creature, final Board board, final Tactic tactic) {
		final Set<Creature> healthier = new HashSet<>();
		for (final Creature c : tactic.getCreatures())
			if (c.getHealth() > creature.getHealth())
				healthier.add(c);
		// I'm the healthiest
		if (healthier.size() == 0) {
			freeForAllAction(creature, board);
			return;
		}
 		double minDistance = 0;
 		boolean touchingCannon = false;
		for (final Creature c : healthier) {
			double distance = board.distanceToTarget(c);
			if (distance > minDistance)
				minDistance = distance;
			touchingCannon = touchingCannon || board.touchingCannon(c);
		}

		final Set<Direction> dirs = action.getDirections(creature, board);
		for (final Direction dir : dirs) {
			if (creature.canMove(dir, creature.getMaxSpeed())) {
				double slowMovingDistance = board.distanceToTarget(creature.getMovePosition(dir, 1));
				double fastMovingDistance = board
						.distanceToTarget(creature.getMovePosition(dir, creature.getMaxSpeed()));
				if (touchingCannon || fastMovingDistance > minDistance + 5)
					creature.move(dir, creature.getMaxSpeed());
				else if (slowMovingDistance > minDistance + 5)
					creature.move(dir, 1);
				dodgeAction = null;
				return;
			}
		}
		if (dodgeAction == null) {
			final Direction dir = new ArrayList<Direction>(dirs).get((int) (RandomGenerator.getNext() * dirs.size()));
			dodgeAction = new TacticDodgeAction(dir);
		}
		dodgeAction.perform(creature, board);		
	}

}

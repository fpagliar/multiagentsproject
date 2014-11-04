package agents.hashtagCreature;

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
//		final List<Cannon> cannons = board.getCannons();
//		for (final Cannon cannon : cannons) {
//			double distance = Cannon.distance(new Rectangle(cannon.center().x, cannon.center().y, 1, 1), agent.getPosition());
//			// Add max speed, as it can reach that point on the next turn
//			if (distance < cannon.getReach() + agent.getMaxSpeed()) {
//				return false;
//			}
//		}
//		return true;
	}

	@Override
	public Action getAction() {
		return BasicMovementAction.getAction();
	}

}

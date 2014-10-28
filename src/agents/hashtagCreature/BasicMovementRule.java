package agents.hashtagCreature;

import java.util.List;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Cannon;
import agents.Creature;

public class BasicMovementRule implements HeuristicRule {
	
	private static BasicMovementRule rool = new BasicMovementRule();

	public static BasicMovementRule getRule() {
		return rool;
	}
	
	@Override
	public boolean applies(final Creature agent, final Board board) {
		final List<Cannon> cannons = board.getCannons();
		for (final Cannon cannon : cannons) {
			double distance = Cannon.distance(cannon.getPosition(), agent.getPosition());
			if (distance < cannon.getReach()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Action getAction() {
		// TODO Auto-generated method stub
		return null;
	}

}

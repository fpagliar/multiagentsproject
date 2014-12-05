package agents.hashtagCreature;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.ruleBased.BasicMovementAction;

public class SuicideRool implements HeuristicRule {
	
	private static final SuicideRool rool = new SuicideRool();

	public static SuicideRool getRule() {
		return rool;
	}
	
	@Override
	public boolean applies(final Creature agent, final Board board) {
		if(board.inShootingZone(agent))
			return true;
		if(!board.inCircleOfFire(agent))
			return false;
		for (final Creature c : board.getCreatures())
			if (!c.equals(agent) && c instanceof HashtagCreature)
				if (board.inCircleOfFire(c) || board.inShootingZone(c))
					return true;
		return false;
	}

	@Override
	public Action getAction() {
		return BasicMovementAction.getAction();
	}

}

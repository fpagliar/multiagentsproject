package agents.starAgent;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.hashtagCreature.BasicMovementAction;
import agents.hashtagCreature.HashtagCreature;

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
		int hashTags = 0;
		int stars = 0;
		for (final Creature c : board.getCreatures()){
			if(c instanceof StarCreature && (board.inShootingZone(c) || board.inCircleOfFire(c)))
				if(board.inShootingZone(c))
					return true;
				else
					stars++;
			if (c instanceof HashtagCreature && board.inShootingZone(c))
					hashTags++;
		}
		return hashTags > 1 && stars > 1;
	}

	@Override
	public Action getAction() {
		return BasicMovementAction.getAction();
	}

}
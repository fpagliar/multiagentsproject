package agents.hashtagCreature;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;
import agents.starAgent.GoSlowFollowHashtagsAction;

public class NoMansLandRool implements HeuristicRule {
	
	private static final NoMansLandRool rool = new NoMansLandRool();

	public static NoMansLandRool getRule() {
		return rool;
	}
	
	@Override
	public boolean applies(final Creature agent, final Board board) {
		if(!board.inShootingZone(agent))
			return false;
		double minHashtagDistance = 1000;
		Creature closestHashtag = null;
		for(final Creature c : board.getCreatures())
			if(c instanceof HashtagCreature)
				if(board.distanceToTarget(c) < minHashtagDistance){
					minHashtagDistance = board.distanceToTarget(c);
					closestHashtag = c;
				}
		double myDistance = board.distanceToTarget(agent);
		if (myDistance < minHashtagDistance) {
			return false;
		}
		double secondHashtagDistance = 1000;
		for(final Creature c : board.getCreatures())
			if(c instanceof HashtagCreature && !c.equals(closestHashtag))
				if(board.distanceToTarget(c) < secondHashtagDistance){
					secondHashtagDistance = board.distanceToTarget(c);
				}
		return secondHashtagDistance < myDistance;
	}

	protected static double secondHashtagDistance(final Board board) {
		double minHashtagDistance = 1000;
		Creature closestHashtag = null;
		for (final Creature c : board.getCreatures())
			if (c instanceof HashtagCreature)
				if (board.distanceToTarget(c) < minHashtagDistance) {
					minHashtagDistance = board.distanceToTarget(c);
					closestHashtag = c;
				}
		double secondHashtagDistance = 1000;
		for (final Creature c : board.getCreatures())
			if (c instanceof HashtagCreature && !c.equals(closestHashtag))
				if (board.distanceToTarget(c) < secondHashtagDistance) {
					secondHashtagDistance = board.distanceToTarget(c);
				}
		return secondHashtagDistance;
	}	
	
	@Override
	public Action getAction() {
		return GoSlowFollowHashtagsAction.getAction();
	}

}

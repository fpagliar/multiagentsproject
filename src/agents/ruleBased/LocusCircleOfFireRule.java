package agents.ruleBased;

import model.Action;
import model.Board;
import model.HeuristicRule;
import agents.Creature;

public class LocusCircleOfFireRule implements HeuristicRule {

//	private static LocusCircleOfFireRule rool = new LocusCircleOfFireRule();
//
//	public static LocusCircleOfFireRule getRule() {
//		return rool;
//	}
	
	
	private LocusMovementAction action = LocusMovementAction.createAction();

	public static LocusCircleOfFireRule createRule() {
		return new LocusCircleOfFireRule();
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		if (!board.inCircleOfFire(agent))
			return false;
		
//		boolean surrounded;
//		if(agent.center().x < 500)
//			surrounded = checkForBottom();
//		else
//			surrounded = checkForTop();
//		if (creatureLeft && creatureRight)
//			return false;
		return true;
	}
	
	@Override
	public Action getAction() {
		return action;
	}

}

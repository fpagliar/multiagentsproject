package agents.ruleBased;

import agents.Creature;
import model.Action;
import model.Board;
import model.HeuristicRule;

public class DefaultRule implements HeuristicRule {

	private static DefaultRule rool = new DefaultRule();

	public static DefaultRule getRule() {
		return rool;
	}

	@Override
	public boolean applies(final Creature agent, final Board board) {
		return true;
	}

	@Override
	public Action getAction() {
		return DefaultAction.getAction();
	}

}

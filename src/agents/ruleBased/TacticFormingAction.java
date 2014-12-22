package agents.ruleBased;

import model.Action;
import model.Board;
import model.Tactic;
import agents.Creature;

public class TacticFormingAction implements Action {

	private final Tactic tactic;

	public TacticFormingAction(final Tactic tactic) {
		this.tactic = tactic;
	}

	@Override
	public void perform(final Creature creature, final Board board) {
		for(final Creature c : tactic.getCreatures()){
			final RoolBasedCreature roolBC = (RoolBasedCreature) c;
			roolBC.setTactic(tactic);
		}
	}
}

package agents.ruleBased;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Board;
import model.HeuristicRule;
import utils.RandomGenerator;
import agents.Creature;

public class RoolBasedCreature extends Creature {

	private List<Set<HeuristicRule>> rules;
	
	public RoolBasedCreature(final int health, final Rectangle size, final int speed,
			final List<Set<HeuristicRule>> rules) {
		super(health, size, speed);
		this.rules = rules;
	}
		
	public void action() {
		final List<HeuristicRule> validRulz = new ArrayList<>();
		for (final Set<HeuristicRule> level : rules) {
			for (final HeuristicRule rool : level) {
				if (rool.applies(this, Board.getInstance())) {
					validRulz.add(rool);
				}
			}
			// If at least one rule of this level applies
			if (validRulz.size() > 0) {
				// Choose a random rule from the ones that apply to the situation.
				final HeuristicRule rool = validRulz.get((int) (RandomGenerator.getNext() * validRulz.size()));
				rool.getAction().perform(this, Board.getInstance());
				return; // After applying the rule, abort, one rule per turn.
			}
		}
		// No rule applies, do nothing?
		throw new IllegalStateException("NOONE RULZ");
	}

	@Override
	public String labelString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}

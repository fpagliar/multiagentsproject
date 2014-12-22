package agents.ruleBased;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.BlackBoard;
import model.Board;
import model.HeuristicRule;
import model.Tactic;
import utils.RandomGenerator;
import agents.Creature;

public class RoolBasedCreature extends Creature {

	private List<Set<HeuristicRule>> rules;
	private Tactic tactic;

	public RoolBasedCreature(final int health, final Rectangle size, final int speed,
			final List<Set<HeuristicRule>> rules) {
		super(health, size, speed);
		this.rules = rules;
	}

	public RoolBasedCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
		setDefaultRules();
	}

	private void setDefaultRules() {
		rules = new ArrayList<>();

		// LEVEL 0 RULZ
		final Set<HeuristicRule> level0Rulz = new HashSet<>();
		level0Rulz.add(SupremeRule.getRule());
		rules.add(level0Rulz);

		// LEVEL 1 RULZ
		final Set<HeuristicRule> level1Rulz = new HashSet<>();
		level1Rulz.add(TacticPerformingRool.createRule());
		// level1Rulz.add(ExtremeDodgeObstaclesRule.createRule());
		rules.add(level1Rulz);

		// LEVEL 2 RULZ
		final Set<HeuristicRule> level2Rulz = new HashSet<>();
		level2Rulz.add(BasicDodgeObstaclesRule.createRule());
		level2Rulz.add(TacticFormingRule.createRule());
		rules.add(level2Rulz);

		// // LEVEL 3 RULZ
		final Set<HeuristicRule> level3Rulz = new HashSet<>();
		level3Rulz.add(LocusCircleOfFireRule.createRule());
		rules.add(level3Rulz);
		// final Set<HeuristicRule> level3Rulz = new HashSet<>();
		// level3Rulz.add(NoMansLandRool.getRule());
		// rules.add(level3Rulz);

		// LEVEL 4 RULZ
		final Set<HeuristicRule> level4Rulz = new HashSet<>();
		level4Rulz.add(BasicMovementRule.getRule());
		// level4Rulz.add(SuicideRool.getRule());
		rules.add(level4Rulz);

		// LEVEL 5 RULZ
		final Set<HeuristicRule> level5Rulz = new HashSet<>();
		level5Rulz.add(DefaultRule.getRule());
		rules.add(level5Rulz);
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
				// Choose a random rule from the ones that apply to the
				// situation.
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
		return getId();
	}

	public void setTactic(final Tactic tactic) {
		this.tactic = tactic;
	}

	public Tactic getTactic() {
		return tactic;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof RoolBasedCreature))
			return false;
		final RoolBasedCreature other = (RoolBasedCreature) obj;
		return getId() == other.getId();
	}

	@Override
	public boolean receiveShot() {
		boolean ans = super.receiveShot();
		if (isDead() && !tactic.isWinner()) {
			boolean allDead = true;
			for (final Creature c : tactic.getCreatures())
				allDead = allDead && c.isDead();
			if (allDead)
				BlackBoard.getInstance().writeFailed(tactic);
		}
		return ans;
	}

}

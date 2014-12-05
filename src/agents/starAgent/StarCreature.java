package agents.starAgent;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Board;
import model.HeuristicRule;
import utils.RandomGenerator;
import agents.hashtagCreature.ExtremeDodgeObstaclesRule;
import agents.ruleBased.BasicDodgeObstaclesRule;
import agents.ruleBased.BasicMovementRule;
import agents.ruleBased.DefaultRule;
import agents.ruleBased.RoolBasedCreature;
import agents.ruleBased.SupremeRule;

public class StarCreature extends RoolBasedCreature {
	
	private static List<Set<HeuristicRule>> getRools() {
		final List<Set<HeuristicRule>> rules = new ArrayList<>();
		
		// LEVEL 0 RULZ
		final Set<HeuristicRule> level0Rulz = new HashSet<>();
		level0Rulz.add(SupremeRule.getRule());
		rules.add(level0Rulz);

		// LEVEL 1 RULZ
		final Set<HeuristicRule> level1Rulz = new HashSet<>();
		level1Rulz.add(ExtremeDodgeObstaclesRule.createRule());
		rules.add(level1Rulz);

		// LEVEL 2 RULZ
		final Set<HeuristicRule> level2Rulz = new HashSet<>();
		level2Rulz.add(BasicDodgeObstaclesRule.createRule());
		rules.add(level2Rulz);

		// LEVEL 3 RULZ
		final Set<HeuristicRule> level3Rulz = new HashSet<>();
		level3Rulz.add(NoMansLandRool.getRule());
		rules.add(level3Rulz);

		// LEVEL 4 RULZ
		final Set<HeuristicRule> level4Rulz = new HashSet<>();
		level4Rulz.add(BasicMovementRule.getRule());
		level4Rulz.add(SuicideRool.getRule());
		rules.add(level4Rulz);

		// LEVEL 5 RULZ
		final Set<HeuristicRule> level5Rulz = new HashSet<>();
		level5Rulz.add(DefaultRule.getRule());
		rules.add(level5Rulz);		
		
		return rules;
	}
	
	private static final int HEALTH = 1;

	protected StarCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed, getRools());
	}

	public static StarCreature newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 1000);
			int y = (int) (RandomGenerator.getNext() * 1000);
			if (Board.getInstance().isFree(new Rectangle(x, y, 20, 20))) 
				return new StarCreature(HEALTH, new Rectangle(x, y, 20, 20), 3);
		}
	}

	public static StarCreature newInstance(final Point p) {
		return new StarCreature(HEALTH, new Rectangle(p.x, p.y, 20, 20), 3);
	}
	
	public static StarCreature newInstance(final Rectangle rect) {
		return new StarCreature(HEALTH, rect, 3);
	}
	
	@Override
	public String toString() {
		return "StarCreature: " + super.toString();
	}

	@Override
	public String labelString() {
		return "* " + getId() + ": " + getHealth();
	}
	
}

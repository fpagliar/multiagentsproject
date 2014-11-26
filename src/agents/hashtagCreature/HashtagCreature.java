package agents.hashtagCreature;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Board;
import model.HeuristicRule;
import model.RoolBasedCreature;
import utils.RandomGenerator;

public class HashtagCreature extends RoolBasedCreature {
	
	// LOADS THE RULZ
	private static List<Set<HeuristicRule>> getRools() {
		final List<Set<HeuristicRule>> rules = new ArrayList<>();
		
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
		level3Rulz.add(BasicMovementRule.getRule());
		level3Rulz.add(SuicideRool.getRule());
		rules.add(level3Rulz);

		// LEVEL 4 RULZ
		final Set<HeuristicRule> level4Rulz = new HashSet<>();
		level4Rulz.add(DefaultRule.getRule());
		rules.add(level4Rulz);		
		
		return rules;
	}
	
	private static final int HEALTH = 5;
	
	private HashtagCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed, getRools());
	}
	
	public static HashtagCreature newInstance() {
		while (true) {
			int x = (int) (RandomGenerator.getNext() * 900) + 90;
			int y = (int) (RandomGenerator.getNext() * 900) + 90;
			if (x > 450 && x < 550)
				x = 550;
			if (y > 450 && y < 550)
				y = 550;
			if(Board.getInstance().isFree(new Rectangle(x, y, 40, 40)))
				return new HashtagCreature(HEALTH, new Rectangle(x, y, 40, 40), 1);
		}
	}

	public static HashtagCreature newInstance(final Point p) {
		return new HashtagCreature(HEALTH, new Rectangle(p.x, p.y, 40, 40), 1);
	}
	
	@Override
	public String toString() {
		return "HashtagCreature: " + super.toString();
	}
		
	@Override
	public String labelString() {
		return "# Creature: " + getId();
	}

}

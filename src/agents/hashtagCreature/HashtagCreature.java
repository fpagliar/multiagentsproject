package agents.hashtagCreature;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.Board;
import model.HeuristicRule;
import agents.Creature;

public class HashtagCreature extends Creature {

	private static List<Set<HeuristicRule>> rules = new ArrayList<>();
	
	static {
		//LOAD THE RULZ
	}
	
	private static final int HEALTH = 4;
	
	private HashtagCreature(final int health, final Rectangle size, final int speed) {
		super(health, size, speed);
	}
	
	public static HashtagCreature newInstance() {
		int x = (int)(Math.random() * 500) + 250;
		int y = (int)(Math.random() * 500) + 250;
		if(x > 450 && x < 550)
			x = 550;
		if(y > 450 && y < 550)
			y = 550;
		return new HashtagCreature(HEALTH, new Rectangle(x, y, 40, 40), 1);
//		return new HashtagCreature(3, new Rectangle(20, 20, 40, 40), 1);
	}

	public static HashtagCreature newInstance(final Point p) {
		return new HashtagCreature(HEALTH, new Rectangle(p.x, p.y, 40, 40), 1);
	}
	
	@Override
	public String toString() {
		return "HashtagCreature: " + super.toString();
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
				final HeuristicRule rool = validRulz.get((int) (Math.random() * validRulz.size()));
				rool.getAction().perform(this, Board.getInstance());
				return; // After applying the rule, abort, one rule per turn.
			}
		}
		// No rule applies, do nothing?
		return;
	}
}

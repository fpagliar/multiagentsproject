package model;

import agents.Creature;

public interface HeuristicRule {
	
	/**
	 * True if the rule applies to the specific agent for this current board; otherwise, false.
	 */
	boolean applies(final Creature agent, final Board board);
	
	/**
	 * Gets the action related to this rule.
	 */
	Action getAction();

}

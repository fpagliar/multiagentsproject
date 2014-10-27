package model;

import agents.Creature;

public interface Action {
	
	/**
	 * Represents an action to be performed by the agent. That particular action may be influenced by the board status.
	 */
	void perform(final Creature creature, final Board board);

}

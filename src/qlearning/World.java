package qlearning;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import qlearning.StarAgent.AgentAction;
import agents.Creature;
import agents.hashtagCreature.HashtagCreature;

public class World {

	private final StarAgent creature;
	
	public World(final StarAgent creature) {
		this.creature = creature;
	}

	public BoardState getInitialState() {
		return new BoardState(creature);
	}
	
	public List<AgentAction> getActions() {
		final List<AgentAction> possible = new ArrayList<>();
		possible.add(AgentAction.STAY);
		possible.add(AgentAction.ROTATE);
		if(creature.canMove())
			possible.add(AgentAction.FOWARD);
		return possible;
	}
	
	public BoardState getNextState(final AgentAction action) {
		creature.perform(action);
//		creature.move(action, creature.getMaxSpeed());
		return new BoardState(creature);
	}

	public boolean endState() {
		if(creature.isDead() || Board.getInstance().isGameOver())
			return true;
		int hashtag = 0;
		for(final Creature c : Board.getInstance().getCreatures())
			if(c instanceof HashtagCreature)
				hashtag++;
		if(hashtag == 1){
			for(final Creature c : Board.getInstance().getCreatures())
				if(c instanceof HashtagCreature)
					if(!Board.getInstance().inShootingZone(c))
						return true;
		}
		return Board.getInstance().getCreatures().size() <= 1;
	}
}

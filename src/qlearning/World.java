package qlearning;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import agents.Creature;
import agents.RectangularObject.Direction;

public class World implements RLWorld {

	private final Creature creature;
	
	public World(final Creature creature) {
		this.creature = creature;
	}

	@Override
	public BoardState getInitialState() {
		return new BoardState(creature);
	}
	
	@Override
	public List<Direction> getActions() {
		final List<Direction> possible = new ArrayList<>();
		for(final Direction dir : Direction.values())
			if(creature.canMove(dir, creature.getMaxSpeed()))
				possible.add(dir);
		return possible;
	}
	
	@Override
	public BoardState getNextState(final Direction action) {
		creature.move(action, creature.getMaxSpeed());
		return new BoardState(creature);
	}

	@Override
	public boolean endState() {
		if(creature.isDead() || Board.getInstance().isGameOver())
			return true;
		return Board.getInstance().getCreatures().size() <= 1;
	}
}

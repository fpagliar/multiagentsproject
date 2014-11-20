package qlearning;

import java.util.List;

import agents.RectangularObject.Direction;

public interface RLWorld {
	
	BoardState getInitialState();
	
	List<Direction> getActions();
	
	BoardState getNextState(final Direction action);

	boolean endState();
}

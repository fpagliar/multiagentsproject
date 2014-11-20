package qlearning;

import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.StarCreature;
import agents.hashtagCreature.HashtagCreature;


public class BoardState {

	private final int starAgentsInside;
	private final int hashtagAgentsInside;
	private final int agentsInCircleOfFire;
	private final double distanceToTarget;
	private final QState state;

	//TODO: replace the state?
	/*
	 * - Facing direction
	 * - numb #
	 * - numb *
	 * - zona 
	 * - veo o no al canion?
	 * poner una direccion en la que mira y accion girar / moverse para adelante / quedarse quieto
	 */
	// Refuerzo: GANAR 250, PERDER -100
	
	public static enum QState {
		OUTSIDE_DL, OUTSIDE_DR, OUTSIDE_DC, 
		OUTSIDE_UL, OUTSIDE_UR, OUTSIDE_UC, 
		OUTSIDE_CL, OUTSIDE_CR, NO_MANS_LAND,
		CIRCLE_OF_FIRE;
	};
	
	public BoardState(final Creature agent) {
		final Board board = Board.getInstance();
		distanceToTarget = board.distanceToTarget(agent);
		if(distanceToTarget < 0)
			System.out.println("FDFA");
		int hashtagAgents = 0;
		int starAgents = 0;
		int circleOfFireAgents = 0;
		for (final Creature creature : board.getCreatures()) {
			if (board.inCircleOfFire(creature)) {
				circleOfFireAgents++;
			} else if (board.inShootingZone(creature)) {
				if (creature instanceof HashtagCreature) {
					hashtagAgents++;
				} else if (creature instanceof StarCreature) {
					starAgents++;
				}
			}
		}
		starAgentsInside = starAgents;
		hashtagAgentsInside = hashtagAgents;
		agentsInCircleOfFire = circleOfFireAgents;
		this.state = getState(agent);
	}

	public QState getState() {
		return state;
	}
	
	public Double getReward(final BoardState nextState) {
		final Double STATE_REWARD = 1000.0;
		if(state == QState.OUTSIDE_UL && (nextState.state == QState.OUTSIDE_CL || nextState.state == QState.OUTSIDE_UC))
			return STATE_REWARD;

		if(state == QState.OUTSIDE_DL && (nextState.state == QState.OUTSIDE_CL || nextState.state == QState.OUTSIDE_DC))
			return STATE_REWARD;

		if(state == QState.OUTSIDE_DR && (nextState.state == QState.OUTSIDE_DC || nextState.state == QState.OUTSIDE_CR))
			return STATE_REWARD;

		if(state == QState.OUTSIDE_UR && (nextState.state == QState.OUTSIDE_UC || nextState.state == QState.OUTSIDE_CR))
			return STATE_REWARD;

		if(nextState.state == QState.CIRCLE_OF_FIRE)
			return 10000.0; // 10K
		
		if (state == QState.CIRCLE_OF_FIRE && nextState.state == QState.NO_MANS_LAND) {
			if (nextState.hashtagAgentsInside > 0)
				return 1000000.0; // 1M
			else 
				return -10000.0;
		}

//		Double reward = 0.0;
		if(nextState.distanceToTarget < distanceToTarget)
			return 10.0;
		else
			return -100.0;
//		reward += (distanceToTarget - nextState.distanceToTarget) * 10;
//		return reward;
	}
	
	private QState getState(final Creature agent) {
		if (Board.getInstance().inShootingZone(agent))
			return QState.NO_MANS_LAND;
		if (Board.getInstance().inCircleOfFire(agent))
			return QState.CIRCLE_OF_FIRE;
		final Cannon cannon = Board.getInstance().getCannon(agent);
		int diffX = agent.center().x - cannon.center().x;
		int diffY = agent.center().y - cannon.center().y;
				
		final int CORRIDOR = 50;
		if(Math.abs(diffX) < CORRIDOR && diffY < 0)
			return QState.OUTSIDE_DC;
		if(Math.abs(diffX) < CORRIDOR && diffY > 0)
			return QState.OUTSIDE_UC;
		if(diffX < 0 && Math.abs(diffY) < CORRIDOR)
			return QState.OUTSIDE_CL;
		if(diffX > 0 && Math.abs(diffY) < CORRIDOR)
			return QState.OUTSIDE_CR;

		if(diffX < 0 && diffY < 0)
			return QState.OUTSIDE_DL;
		if(diffX < 0 && diffY > 0)
			return QState.OUTSIDE_UL;
		if(diffX > 0 && diffY < 0)
			return QState.OUTSIDE_DR;
		if(diffX > 0 && diffY > 0)
			return QState.OUTSIDE_UR;
		throw new IllegalStateException("WTF Men");
	}
	
}

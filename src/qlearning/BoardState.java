package qlearning;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.hashtagCreature.HashtagCreature;

public class BoardState {

//	private final int starAgentsInside;
	private final int hashtagAgentsInside;
	// private final int agentsInCircleOfFire;
	// private final double distanceToTarget;
	private final QState state;
//	private final Direction facingDirection;
	private final boolean canSeeCannon;
	private final StarAgent agent;
	
	// TODO: replace the state?
	/*
	 * - Facing direction - numb # - numb * - zona - veo o no al canion? poner
	 * una direccion en la que mira y accion girar / moverse para adelante /
	 * quedarse quieto
	 */
	// Refuerzo: GANAR 250, PERDER -100

	public static enum QState {
		OUTSIDE, CIRCLE_OF_FIRE, NO_MANS_LAND
	};

	public BoardState(final StarAgent agent) {
		final Board board = Board.getInstance();
		int hashtagAgents = 0;
//		int starAgents = 0;
//		int circleOfFireAgents = 0;
		for (final Creature creature : board.getCreatures()) {
			if (board.inCircleOfFire(creature)) {
//				circleOfFireAgents++;
			} else if (board.inShootingZone(creature)) {
				if (creature instanceof HashtagCreature) {
					hashtagAgents++;
//				} else if (creature instanceof StarCreature) {
//					starAgents++;
				}
			}
		}
//		starAgentsInside = starAgents;
		hashtagAgentsInside = hashtagAgents;
		// agentsInCircleOfFire = circleOfFireAgents;
		this.state = getState(agent);
//		this.facingDirection = agent.getDirection();
		this.canSeeCannon = !canSeeCannon(agent);
		this.agent = agent;
	}

	public Double getReward(final BoardState nextState) {
		if (agent.isDead())
			return -100.0;
//		if (Board.getInstance().getCreatures().size() == 1)
//			return -1000.0;
		if (Board.getInstance().isGameOver())
			return 250.0;
//		if (canSeeCannon && !nextState.canSeeCannon)
//			return -50.0;
//		if (!nextState.canSeeCannon)
//			return -1.0;
//		if (state == QState.OUTSIDE && nextState.state == QState.CIRCLE_OF_FIRE)
//			return 10.0;
//		if (state == QState.CIRCLE_OF_FIRE && nextState.state == QState.NO_MANS_LAND && nextState.hashtagAgentsInside > 0)
//			return 50.0;
		return 0.0;
	}

	private boolean canSeeCannon(final StarAgent agent) {
		final Cannon cannon = Board.getInstance().getCannon(agent);
		int diffX = agent.center().x - cannon.center().x;
		int diffY = agent.center().y - cannon.center().y;
		if (diffX > 0 && agent.getDirection().getPoint().x > 0)
			return true;
		if (diffX < 0 && agent.getDirection().getPoint().x < 0)
			return true;
		if (diffY > 0 && agent.getDirection().getPoint().y > 0)
			return true;
		if (diffY < 0 && agent.getDirection().getPoint().y < 0)
			return true;
		return false;
	}

	private QState getState(final Creature agent) {
		if (Board.getInstance().inShootingZone(agent))
			return QState.NO_MANS_LAND;
		if (Board.getInstance().inCircleOfFire(agent))
			return QState.CIRCLE_OF_FIRE;
		return QState.OUTSIDE;
	}
	
	public QState getState() {
		return state;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if(obj == null)
			return false;
		final BoardState other = (BoardState) obj;
		return other.state == this.state && 
//				other.facingDirection == this.facingDirection && 
				other.canSeeCannon == this.canSeeCannon &&
				other.hashtagAgentsInside == this.hashtagAgentsInside;
//				other.starAgentsInside == this.starAgentsInside;
	}
	
	@Override
	public int hashCode() {
		return (state.ordinal() + 27) * (hashtagAgentsInside + 7);
//		return (state.ordinal() + 27) * (starAgentsInside + 13) * (hashtagAgentsInside + 7);
//				* (facingDirection.ordinal() + 23);
	}
		
//	private BoardState(final int starAgentsInside, final int hashtagAgentsInside, final QState state,
//			final boolean canSeeCannon, final StarAgent agent) {
	private BoardState(final int hashtagAgentsInside, final QState state,
			final boolean canSeeCannon, final StarAgent agent) {
//		this.starAgentsInside = starAgentsInside;
		this.hashtagAgentsInside = hashtagAgentsInside;
		this.state = state;
//		this.facingDirection = facingDirection;
		this.canSeeCannon = canSeeCannon;
		this.agent = agent;
	}
	
	public static List<BoardState> createAll(final StarAgent agent) {
		final List<BoardState> list = new ArrayList<>();
		for (int i = 0; i < 3; i++)
			// for (int j = 0; j < 3; j++)
			for (final QState state : QState.values()) {
				// for (final Direction dir : Direction.values()) {
				// list.add(new BoardState(i, j, state, true, agent));
				// list.add(new BoardState(i, j, state, false, agent));
				list.add(new BoardState(i, state, true, agent));
				list.add(new BoardState(i, state, false, agent));
			}
		return list;
	}
	
	@Override
	public String toString() {
		return state.toString() + " - " + canSeeCannon + " inside: "
				+ hashtagAgentsInside;
//				+ (starAgentsInside + hashtagAgentsInside);
//		return state.toString() + " - " + facingDirection.toString() + ":" + canSeeCannon + " inside: "
//				+ (starAgentsInside + hashtagAgentsInside);
	}
	
	public String getTitle(){
		return agent.getDirection().toString() + " -> " + this.toString();
	}

}

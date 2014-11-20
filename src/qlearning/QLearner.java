package qlearning;

import gui.GUIBoard;
import gui.GUIStarCreature;
import gui.MainWindow;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Board;
import qlearning.BoardState.QState;
import utils.Pair;
import utils.RandomGenerator;
import agents.Cannon;
import agents.Creature;
import agents.RectangularObject.Direction;
import agents.StarCreature;
import agents.hashtagCreature.HashtagCreature;

public class QLearner {
	// path finding
	private final double alpha = 0.1;
	private final double gamma = 0.9;

	private StarCreature creature;
	private boolean visual;
	private MainWindow window;
	private final Point initialPos;
	
	// states A,B,C,D,E,F
	// e.g. from A we can go to B or D
	// from C we can only go to C
	// C is goal state, reward 100 when B->C or F->C
	//
	// _______
	// |A|B|C|
	// |_____|
	// |D|E|F|
	// |_____|
	//

	// Q(s,a)= Q(s,a) + alpha * (R(s,a) + gamma * Max(next state, all actions) -
	// Q(s,a))
	// < STATE, < ACTION, Q > >
	private final Map<QState, Map<Direction, Double>> Q = new HashMap<>();

	public QLearner() {
		visual = true;
		creature = StarCreature.newInstance(new Point(200, 200));
		initialPos = new Point(creature.getPosition().x, creature.getPosition().y);
		for (final QState state : QState.values()) {
			final Map<Direction, Double> map = new HashMap<Direction, Double>();
			for (final Direction dir : Direction.values())
				map.put(dir, 0.0);
			Q.put(state, map);
		}
		init();
	}

	public void init() {
		GUIBoard.createNewBoard();
		GUIBoard.getInstance().register(new GUIStarCreature(creature));
		creature.setPosition(initialPos);
	}

	public static void main(String[] args) {
		long BEGIN = System.currentTimeMillis();

		QLearner learner = new QLearner();

		learner.run();
		// learner.printResult();
		// learner.showPolicy();

		long END = System.currentTimeMillis();
		System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
	}

	void run() {
		/*
		 * 1. Set parameter , and environment reward matrix R 2. Initialize
		 * matrix Q as zero matrix 3. For each episode: Select random initial
		 * state Do while not reach goal state o Select one among all possible
		 * actions for the current state o Using this possible action, consider
		 * to go to the next state o Get maximum Q value of this next state
		 * based on all possible actions o Compute o Set the next state as the
		 * current state
		 */
		if (visual) {
			window = MainWindow.getInstance();
			window.setVisible(true);
		}

		// For each episode
		//TODO: AGREGAR EPSILON = 1 QUE VA DECAYENDO LINEALMENTE POR CADA ITERACION DENTRO DE UNA MISMA EPOCA
		for (int i = 0; i < 100000; i++) { // train episodes // epochs
			if (visual) {
				window.clearStrings();
			}
			init();
			final World world = new World(creature);
			BoardState actualState = world.getInitialState();
			GUIBoard.getInstance().clearTemps();
			int ticks = 0;
//			System.out.println("EPOCH");
			while (!world.endState()) { // goal state
//				System.out.println("ACTION");
				if (visual && i % 100 == 0) {
					updateStrings();
					ticks++;
					if (ticks % 10 == 0) {
						ticks = 0;
						GUIBoard.getInstance().clearTemps();
					}
					try {
						window.repaint();
						Thread.sleep(50, 0);
					} catch (InterruptedException e) { }
				}
				
				// Select one among all possible actions for the current state
				final List<Direction> actions = world.getActions();

				for (final Direction dir : Direction.values()) {
					int times = (int) Q(actualState, dir);
					while (times > 0) {
						actions.add(dir);
						actions.add(dir);
						actions.add(dir);
						actions.add(dir);
						actions.add(dir);
						times /= 10;
					}
				}

				// Selection strategy is random in this example
				//TODO: add epsilon
				final Direction action = actions.get((int) (RandomGenerator.getNext() * actions.size()));

				// Action outcome is set to deterministic in this example
				// Transition probability is 1
				final BoardState nextState = world.getNextState(action);

				// Using this possible action, consider to go to the next state
				double q = Q(actualState, action);
				double maxQ = maxQ(nextState);
				// int reward = R(actualState, action);
				double reward = actualState.getReward(nextState);

				double value = q + alpha * (reward + gamma * maxQ - q);
//				System.out.println(value);
				setQ(actualState, action, value);

				// Set the next state as the current state
				actualState = nextState;
				for (final Cannon c : Board.getInstance().getCannons()) {
					Board.getInstance().shoot(c);
				}
				for (final Creature c : Board.getInstance().getCreatures()) {
					if(c instanceof HashtagCreature)
						c.action();
				}
			}
		}
		showResult();
	}
	
	private void updateStrings() {
		for (final QState state : Q.keySet()) {
			for (final Direction direction : Q.get(state).keySet()) {
				window.putString(new Pair<QState, Direction>(state, direction), Q.get(state).get(direction).toString());
			}
		}
	}	
	
	// Deterministico vs Estocastico
	private void showResult() {
		init();
		final World world = new World(creature);
		BoardState actualState = world.getInitialState();
		int ticks = 0;
		while (!world.endState()) { // goal state
			if (visual) {
				if (ticks % 100 == 0) {
					ticks = 0;
					GUIBoard.getInstance().clearTemps();
				} else {
					ticks++;
				}
				try {
					window.repaint();
					Thread.sleep(50, 0);
				} catch (InterruptedException e) {	}
			}
			
			Direction action = null;
			Double max = Double.MIN_VALUE;
//			if(Q.get(actualState) != null)
			for (final Direction d : Q.get(actualState.getState()).keySet()) {
				if (Q.get(actualState.getState()).get(d) > max) {
					max = Q.get(actualState.getState()).get(d);
					action = d;
				}
			}

			// Action outcome is set to deterministic in this example
			// Transition probability is 1
			final BoardState nextState = world.getNextState(action);

			// Set the next state as the current state
			actualState = nextState;
			for (final Cannon c : Board.getInstance().getCannons()) {
				Board.getInstance().shoot(c);
			}
			for (final Creature c : Board.getInstance().getCreatures()) {
				if (c instanceof HashtagCreature)
					c.action();
			}
		}
	}

	private Double maxQ(final BoardState state) {
		Double maxValue = Double.MIN_VALUE;
		for (final Double value : Q.get(state.getState()).values()) {
			if (value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	double Q(final BoardState state, final Direction action) {
			return Q.get(state.getState()).get(action);			
	}

	void setQ(final BoardState state, final Direction action, double value) {
		Q.get(state.getState()).put(action, value);
	}
}

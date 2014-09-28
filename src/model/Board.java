package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import agents.Cannon;
import agents.Creature;
import agents.RectangularObject;

public class Board {

	private static Board instance = new Board();

	private List<Creature> creatures = new ArrayList<Creature>();
	private List<Cannon> cannons = new ArrayList<Cannon>();

	public Board() {

	}

	public List<Creature> getCreatures() {
		return new ArrayList<Creature>(creatures);
	}

	public List<Cannon> getCannons() {
		return new ArrayList<Cannon>(cannons);
	}

	public void register(final Creature creature) {
		creatures.add(creature);
	}

	public void register(final Cannon cannon) {
		cannons.add(cannon);
	}

	public boolean isFree(final Point p) {
		boolean ans = true;
		for (final Creature c : creatures) {
			if (c.occupies(p)) {
				ans = false;
			}
		}
		return ans;
	}
	
	public boolean canMove(final Rectangle newPos, final RectangularObject actual) {
		for (final Creature c : creatures) {
			if ((!c.equals(actual)) && c.occupies(newPos)) {
				return false;
			}
		}
		for (final Cannon c : cannons) {
			if ((!c.equals(actual)) && c.occupies(newPos)) {
				return false;
			}
		}
		return true;
	}

	public static Board getInstance() {
		return instance;
	}

}

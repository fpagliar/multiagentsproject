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

//	private List<Creature> creatures = new ArrayList<Creature>();
//	private List<Cannon> cannons = new ArrayList<Cannon>();
//	private List<Wall> walls = new ArrayList<Wall>();
	private List<RectangularObject> objects = new ArrayList<RectangularObject>();

	public Board() {

	}

	public List<Creature> getCreatures() {
		List<Creature> ans = new ArrayList<Creature>();
		for(RectangularObject object : objects)
			if(object instanceof Creature)
				ans.add((Creature)object);
		return ans;
	}

	public List<Cannon> getCannons() {
		List<Cannon> ans = new ArrayList<Cannon>();
		for(RectangularObject object : objects)
			if(object instanceof Cannon)
				ans.add((Cannon)object);
		return ans;
	}
	
	public void register(RectangularObject object){
		objects.add(object);
	}

//	public void register(final Creature creature) {
//		creatures.add(creature);
//	}
//
//	public void register(final Cannon cannon) {
//		cannons.add(cannon);
//	}
//	
//	public void register(final Wall wall) {
//		walls.add(wall);
//	}

	public boolean isFree(final Point p) {
		boolean ans = true;
		for (final RectangularObject object : objects) {
			if (object.occupies(p)) {
				ans = false;
			}
		}
		return ans;
	}
	
	public boolean canMove(final Rectangle newPos, final RectangularObject actual) {
		for (final RectangularObject object : objects) {
			if ((!object.equals(actual)) && object.occupies(newPos)) {
				return false;
			}
		}
		return true;
	}

	public static Board getInstance() {
		return instance;
	}

}

package model;

import gui.GUIBoard;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import agents.Cannon;
import agents.Creature;
import agents.RectangularObject;
import agents.StraightLine;

public class Board {

	private static Board instance = new Board();
	private List<RectangularObject> objects = new ArrayList<RectangularObject>();

	public Board() {

	}

	public List<Creature> getCreatures() {
		List<Creature> ans = new ArrayList<Creature>();
		for (RectangularObject object : objects)
			if (object instanceof Creature)
				ans.add((Creature) object);
		return ans;
	}

	public List<Cannon> getCannons() {
		List<Cannon> ans = new ArrayList<Cannon>();
		for (RectangularObject object : objects)
			if (object instanceof Cannon)
				ans.add((Cannon) object);
		return ans;
	}

	public void register(RectangularObject object) {
		objects.add(object);
	}

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
				if (object instanceof Cannon) {
					System.out.println("MOVED TO CANNON");
					System.exit(0);
				}
				return false;
			}
		}
		return true;
	}

	public void shoot(final Cannon shooter) {
		final StraightLine line = shooter.shoot();
		//Not shooting
		if(line == null)
			return;
		System.out.println("SHOOTING: " + line);
		GUIBoard.getInstance().registerTemp(line);
		Point p;
		do {
			p = line.getNextPoint();
			for (final RectangularObject object : objects) {
				if ((!object.equals(shooter)) && object.occupies(p)) {
					if(object.receiveShot()){
						System.out.println("Removing killed: " + object);
						objects.remove(object);
						GUIBoard.getInstance().remove(object);
						shooter.killed();
					}
					return;
				}
			}
		} while (p.x > 0 && p.x < 1000 && p.y > 0 && p.y < 1000);
		GUIBoard.getInstance().registerTemp(line);
		return;
	}

	public static Board getInstance() {
		return instance;
	}

}

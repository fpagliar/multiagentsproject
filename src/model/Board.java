package model;

import gui.GUIBoard;
import gui.MainWindow;

import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
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
					while (true) {
					}
					// System.exit(0);
				}
				return false;
			}
		}
		return true;
	}

	public void shoot(final Cannon shooter) {
		final Line2D line = shooter.shoot();
		// Not shooting
		if (line == null)
			return;
		StraightLine l = new StraightLine(line.getP1(), line.getP2());
		RectangularObject min = null;
		double minDistance = Integer.MAX_VALUE;
		for (final RectangularObject object : objects) {
			double d = Cannon.distance(shooter.getPosition(), object.getPosition());
			if (!object.equals(shooter) && line.intersects(object.getPosition()) && d < minDistance) {
				minDistance = d;
				min = object;
			}
		}
		if (min == null) {
			throw new IllegalStateException();
		}
		if (min.receiveShot()) {
			System.out.println("Removing killed: " + min);
			objects.remove(min);
			GUIBoard.getInstance().remove(min);
			shooter.killed();
		}
		GUIBoard.getInstance().registerTemp(l);
		return;
	}

	public boolean canSee(final Line2D line, final Creature target, final Cannon shooter) {
		if(!line.intersects(target.getPosition())){
			throw new IllegalArgumentException();
		}
		for (final RectangularObject object : objects) {
			if(!object.equals(shooter) && !object.equals(target) && line.intersects(object.getPosition())){
				return false;
			}
		}
		return true;
	}
	
	public Cannon getCannon(final Creature creature){
		double distance = Double.MAX_VALUE;
		Cannon min = null;
		for(final Cannon cannon : getCannons()){
			double d = Cannon.distance(cannon.getPosition(), creature.getPosition());
			if (distance > d) {
				distance = d;
				min = cannon;
			}
		}
		if(min == null)
			throw new IllegalStateException("No cannons");
		return min;
	}

	public static Board getInstance() {
		return instance;
	}

}

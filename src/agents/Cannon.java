package agents;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import model.Board;

public class Cannon extends RectangularObject {

	private final int fireRate;
	private final float angle;
	private final int reach;
	private int cooldown;
	private Creature target;
	private Line2D shootingLine;

	// TODO: add shooting callback?
	public Cannon(final int fireRate, final Rectangle position) {
		super(position, 0);
		this.fireRate = fireRate;
		this.angle = 0;
		this.reach = 100;
	}

	// public void action(long timestamp) {
	// if (timestamp % fireRate == 0) {
	// shoot();
	// }
	// }

	public Line2D shoot() {
		cooldown--;
		// Watching line may have changed, due to movement in the target, but it can still be seen, 
		// so we should adjust the shootingLine.
		if (target == null || ((shootingLine = canSee(target)) == null))
			fixTarget();
		// No targets?
		if (target == null)
			return null;
		// Can't shoot cd
		if (cooldown > 0)
			return null;
		cooldown = fireRate;

		return shootingLine;
	}

	private void fixTarget() {
//		System.out.println("FIXING TARGET");
		double distance = Double.MAX_VALUE;
		Line2D targetLine = null;
		Creature target = null;
		Line2D line;
		for (final Creature c : Board.getInstance().getCreatures()) {
			Point center = center();
			double d = distance(new Rectangle(center.x, center.y, 1, 1), c.getPosition());
			if (d < distance && (line = canSee(c)) != null) {
				distance = d;
				target = c;
				targetLine = line;
			}
		}
		if (distance < reach) {
			this.target = target;
			this.shootingLine = targetLine;
		} else {
			this.target = null;
			this.shootingLine = null;
		}
	}

	public Line2D canSee(final Creature creature) {
		List<Point> targets = new ArrayList<>();
		final Rectangle pos = getPosition();
		final Point center = new Point(pos.x + pos.width / 2, pos.y + pos.height / 2);
		final Rectangle targetPos = creature.getPosition();
		targets.add(new Point(targetPos.x + targetPos.width / 2, targetPos.y + targetPos.height / 2)); // TARGET CENTER
		targets.add(new Point(targetPos.x, targetPos.y + targetPos.height)); // TARGET TOP LEFT
		targets.add(new Point(targetPos.x + targetPos.width, targetPos.y + targetPos.height)); // TARGET TOP RIGHT
		targets.add(new Point(targetPos.x, targetPos.y)); // TARGET BOTTOM LEFT
		targets.add(new Point(targetPos.x + targetPos.width, targetPos.y)); // TARGET BOTTOM RIGHT

		for (Point p : targets) {
			Line2D l = new Line2D.Float((float) center.x, (float) center.y, (float) p.x, (float) p.y);
			l.intersects(creature.getPosition());
			if (Board.getInstance().canSee(l, creature, this))
				return l;
		}
		return null;
	}

	public void killed() {
		target = null;
		shootingLine = null;
	}

	public int getReach() {
		return reach;
	}

	public static Cannon getInstance() {
		return new Cannon(5, new Rectangle(500 - 25, 500 - 25, 50, 50));
	}

	public static double distance(final Rectangle first, final Rectangle second) {
		if (first.intersects(second))
			return 0;
		double deltaX;
		if (first.x < second.x)
			deltaX = second.x - (first.x + first.width);
		else
			deltaX = first.x - (second.x + second.width);
		double deltaY;
		if (first.y < second.y)
			deltaY = second.y - (first.y + first.height);
		else
			deltaY = first.y - (second.y + second.height);
		return Math.pow(deltaX * deltaX + deltaY * deltaY, 0.5);
	}

	@Override
	public boolean receiveShot() {
		return false;
	}

	@Override
	public String toString() {
		return "CANNON: " + super.toString();
	}
}

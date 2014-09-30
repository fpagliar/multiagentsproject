package agents;

import java.awt.Point;
import java.awt.Rectangle;

import model.Board;

public class Cannon extends RectangularObject {

	private final int fireRate;
	private final float angle;
	private final int reach;
	private int cooldown;
	private Creature target;

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

	public StraightLine shoot() {
		cooldown--;
		if (target == null)
			fixTarget();
		// No targets?
		if (target == null)
			return null;
		//Can't shoot cd
		if(cooldown > 0)
			return null;
		cooldown = fireRate;
		final Rectangle pos = getPosition();
		final Point center = new Point(pos.x + pos.width / 2, pos.y + pos.height / 2);
		final Rectangle targetPos = target.getPosition();
		final Point targetPoint = new Point(targetPos.x + targetPos.width / 2, targetPos.y + targetPos.height / 2);
		return new StraightLine(center, targetPoint);
	}

	private void fixTarget() {
		double distance = Double.MAX_VALUE;
		Creature target = null;
		for (Creature c : Board.getInstance().getCreatures()) {
			Point center = center();
			double d = distance(new Rectangle(center.x, center.y, 1, 1), c.getPosition());
			// double d = distance(getPosition(), c.getPosition());
			if (d < distance) {
				distance = d;
				target = c;
			}
		}
		if (distance < reach) {
			this.target = target;
//			System.out.println("NEW TARGET distance:" + distance + " target:" + target + " cannon:" + toString());
		}
	}

	public void killed() {
		target = null;
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
}

package agents;

import gui.Paintable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class StraightLine implements Paintable {
	private final Point start;
	private int x;
	private final double m;
	private final boolean vertical;
	private final boolean up;
	private final boolean right;

	public StraightLine(final Point2D point2d, final Point2D point2d2) {
		this.start = new Point((int)point2d.getX(), (int)point2d.getY());
		final double deltaX = point2d2.getX() - point2d.getX();
		final double deltaY = point2d2.getY() - point2d.getY();
		if (deltaX == 0) {
			vertical = true;
			m = 0;
			up = deltaY > 0;
			right = true;
		} else {
			vertical = false;
			up = deltaY > 0;
			right = deltaX > 0;
			m = deltaY / deltaX;
		}
		this.x = 0;
	}

	public Point getNextPoint() {
		if (vertical) {
			Point p = new Point(start.x, start.y + ((up) ? x : -x));
			x++;
			return p;
		} else {
			Point p = new Point(start.x + ((right) ? x : -x), start.y + (int) (((right) ? x : -x) * m));
			x++;
			return p;
		}
	}

	@Override
	public Set<Point> paint(Graphics g) {
		Set<Point> ans = new HashSet<>();
		int prevX = x;
		x = 0;
		Point p;
		do {
			p = getNextPoint();
			ans.add(p);
			g.setColor(Color.RED);
			g.drawRect(p.x, p.y, 1, 1);
		} while (p.x > 0 && p.x < 1000 && p.y > 0 && p.y < 1000);
		x = prevX;
		return ans;
	}

	@Override
	public RectangularObject getBackendModel() {
		throw new IllegalAccessError("WTF ARE YOU TRYING TO DO");
	}

	@Override
	public String toString() {
		return "Straight line, start: (" + start.x + ", " + start.y + ") m: " + m;
	}
}

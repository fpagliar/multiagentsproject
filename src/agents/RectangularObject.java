package agents;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import model.Board;

public abstract class RectangularObject {
	public enum Direction {
		LEFT(1, 0), RIGHT(-1, 0), UP(0, 1), DOWN(0, -1);
		private final Point dir;

		private Direction(final int x, final int y) {
			dir = new Point(x, y);
		}

		public Point getPoint() {
			return dir;
		}
		
		public static Direction from(final int x, final int y) {
			return (x == 1) ? LEFT : (x == -1) ? RIGHT : (y == 1) ? UP : (y == -1) ? DOWN : null;
		}
	}

	private static int ID_COUNT = 0;
	private final int id;
	private Rectangle position;
	private int maxSpeed;

	protected RectangularObject(final Rectangle size, final int maxSpeed) {
		this.position = size;
		this.maxSpeed = maxSpeed;
		this.id = ID_COUNT++;
	}

	public Rectangle getPosition() {
		return new Rectangle(position);
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getId() {
		return id;
	}

	public boolean occupies(final Point point) {
		return (point.x >= position.x && point.x <= (position.x + position.width))
				&& (point.y >= position.y && point.y <= (position.y + position.height));
	}

	public boolean occupies(final Rectangle rect) {
		return this.position.contains(rect) || rect.contains(position) || position.intersects(rect);
	}

	public Set<Point> occupiedPoints() {
		Set<Point> ans = new HashSet<>();
		for (int i = position.x - 1; i < position.x + position.width; i++)
			for (int j = position.y - 1; j < position.y + position.height; j++)
				ans.add(new Point(i, j));
		return ans;
	}

	public boolean move(final Direction direction, final int speed) {
		if (maxSpeed == 0)
			return false;
		final Point movement = direction.getPoint();
		int actualSpeed = 0;
		boolean changes = true;
		while (actualSpeed <= speed && actualSpeed <= maxSpeed && changes) {
			actualSpeed++;
			final Rectangle newPos = new Rectangle(position.x + direction.dir.x * actualSpeed, position.y
					+ direction.dir.y * actualSpeed, position.width, position.height);
			if (!Board.getInstance().canMove(newPos, this)) {
				changes = false;
			}
		}
		if (!changes && actualSpeed == 1) {
			return false;
		}
		position.setLocation(new Point(position.x + movement.x * (actualSpeed - 1), position.y + movement.y
				* (actualSpeed - 1)));
		// if (!Board.getInstance().canMove(position, this)) {
		// System.out.println("CHECKPOINT");
		// Board.getInstance().canMove(position, this);
		// }
		return true;
	}

	public Point center() {
		return new Point(position.x + position.width / 2, position.y + position.height / 2);
	}

	/**
	 * @return true if it died with the shot
	 */
	public abstract boolean receiveShot();

	@Override
	public String toString() {
		return "Rectangular Object id:" + id + " position(" + position.x + ", " + position.y + ") dimensions: ("
				+ position.width + ", " + position.height + ")";
	}
}

package agents;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import model.Board;

public abstract class RectangularObject {
	public enum Direction {
		RIGHT(1, 0, "DERECHA"), LEFT(-1, 0, "IZQUIERDA"), DOWN(0, 1, "ABAJO"), UP(0, -1, "ARRIBA");
		public final Point dir;
		private final String name;

		private Direction(final int x, final int y, final String name) {
			dir = new Point(x, y);
			this.name = name;
		}

		public Point getPoint() {
			return dir;
		}
		
		public static Direction from(final int x, final int y) {
			return (x == 1) ? RIGHT : (x == -1) ? LEFT : (y == 1) ? DOWN : (y == -1) ? UP : null;
		}
		
		@Override
		public String toString() {
			return name;
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
	
	public void setPosition(final Point newPos) {
		position.x = newPos.x;
		position.y = newPos.y;
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
	
	public int getMovementSpeed(final Direction direction, final int speed) {
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
			return 0;
		}
		return actualSpeed;
	}

	public boolean canMove(final Direction direction, final int speed) {
		if (maxSpeed == 0)
			return false;
		return getMovementSpeed(direction, speed) > 0;
	}

	public void move(final Direction direction, final int speed) {
		if (!canMove(direction, speed)) {
			throw new IllegalArgumentException("CAN'T MOVE THERE");
		}
		final Point movement = direction.getPoint();
		final int actualSpeed = getMovementSpeed(direction, speed);
		
		position.setLocation(new Point(position.x + movement.x * (actualSpeed - 1), position.y + movement.y
				* (actualSpeed - 1)));
	}
	
	public Rectangle getMovePosition(final Direction direction, final int speed) {
//		if (!canMove(direction, speed)) {
////			throw new IllegalArgumentException("CAN'T MOVE THERE");
////			System.out.println("WATAFA");
//		}
		final Point movement = direction.getPoint();
//		final int actualSpeed = getMovementSpeed(direction, speed);

		Rectangle ans = getPosition();
		ans.setLocation(new Point(position.x + movement.x * (speed - 1), position.y + movement.y
				* (speed - 1)));
		return ans;
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
	
	
	public double distance(final Rectangle target) {
		return distance(new Rectangle(center().x, center().y, 1, 1), target);
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

	
	public abstract String labelString();

}

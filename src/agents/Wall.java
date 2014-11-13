package agents;

import java.awt.Rectangle;

public class Wall extends RectangularObject {

	protected Wall(final Rectangle size) {
		super(size, 0);
	}

	public static Wall getInstance() {
		return new Wall(new Rectangle(450, 620, 50, 5));
//		return new Wall(new Rectangle(490, 570, 50, 5));
//		int x = (int) (Math.random() * 1000);
//		int y = (int) (Math.random() * 1000);
//		return new Wall(new Rectangle(x, y, 20, 20));
	}

	@Override
	public boolean receiveShot() {
		return false;
	}
	
	@Override
	public String toString() {
		return "WALL " + super.toString();
	}

	@Override
	public String labelString() {
		return "Wall: " + getId();
	}
}

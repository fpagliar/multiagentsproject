package agents;

import java.awt.Rectangle;

public class Wall extends RectangularObject {

	protected Wall(final Rectangle size) {
		super(size, 0);
	}

	public static Wall getInstance() {
		int x = (int) (Math.random() * 1000);
		int y = (int) (Math.random() * 1000);
		return new Wall(new Rectangle(x, y, 20, 20));
	}

	@Override
	public boolean receiveShot() {
		return false;
	}
}

package agents;

import java.awt.Rectangle;

public class Cannon extends RectangularObject {

	private final int fireRate;
//	private final float angle;
//	private final int alcance;

	//TODO: add shooting callback?
	public Cannon(final int fireRate, final Rectangle position) {
		super(position, 0);
		this.fireRate = fireRate;
//		this.angle = 0;
//		this.alcance = 10;
	}
	
	public void action(long timestamp) {
		if (timestamp % fireRate == 0) {
			shoot();
		}
	}

	private void shoot() {
		// Do the shooting here
	}
	
	public static Cannon getInstance() {
		return new Cannon(10, new Rectangle(500, 500, 50, 50));
	}
}

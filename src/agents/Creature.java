package agents;

import java.awt.Rectangle;

public abstract class Creature extends RectangularObject {

	private int health;
	private int sight = 100;
	
	protected Creature(final int health, final Rectangle size, final int maxSpeed) {
		super(size, maxSpeed);
		this.health = health;
	}
	
	public boolean receiveShot() {
		health--;
		return health <= 0;
	}

	public boolean isDead() {
		return health <= 0;
	}
	
	public abstract void action();
	
	public int getHealth() {
		return health;
	}
	
	public boolean canSee(final Creature other) {
		return distance(other.getPosition()) < sight;
	}

}

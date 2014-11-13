package gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

import agents.Creature;
import agents.RectangularObject;

public abstract class GUICreature<T extends Creature> extends GUIImageObject {
	protected final T creature;

	protected GUICreature(final T creature) {
		this.creature = creature;
	}

	public T getCreature() {
		return creature;
	}

	@Override
	protected Set<Point> occupiedPoints() {
		return creature.occupiedPoints();
	}

	@Override
	protected String descriptor() {
		return creature.toString();
	}

	@Override
	protected Rectangle getPosition() {
		return creature.getPosition();
	}
	
	@Override
	public RectangularObject getBackendModel() {
		return creature;
	}
}

package gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import agents.Cannon;
import agents.RectangularObject;
import agents.Wall;
import agents.hashtagCreature.HashtagCreature;

public class GUIBoard {

	private static GUIBoard instance = new GUIBoard();

	private final List<Paintable> objects = new ArrayList<Paintable>();
	private final GUIEmptyCell emptyCell = GUIEmptyCell.getInstance();
	private final List<Paintable> temps = new ArrayList<Paintable>();

	public GUIBoard() {
	}

	public List<Paintable> getObjects() {
		List<Paintable> ans = new ArrayList<>(objects);
		ans.addAll(temps);
		// if(temps.size() != 0)
		// System.out.println("TEMPS:" + temps.size());
		return ans;
	}

	public GUIEmptyCell getEmptyCell() {
		return emptyCell;
	}

	public void register(final GUICannon cannon) {
		objects.add(cannon);
		Board.getInstance().register(cannon.getCannon());
	}

	public void register(final GUICreature<?> creature) {
		objects.add(creature);
		Board.getInstance().register(creature.getCreature());
	}

	public void register(final GUIWall wall) {
		objects.add(wall);
		Board.getInstance().register(wall.getWall());
	}

	public void register(final Paintable object) {
		objects.add(object);
	}

	public void registerTemp(final Paintable object) {
		temps.add(object);
	}

	public void clearTemps() {
		temps.removeAll(temps);
	}

	public void remove(final RectangularObject object) {
		for (Paintable p : objects) {
			if (p.getBackendModel().equals(object)) {
				objects.remove(p);
				return;
			}
		}
	}

	public static GUIBoard getInstance() {
		return instance;
	}

	public static GUIBoard createNewBoard() {
		Board.restart();
		instance = new GUIBoard();
		instance.register(new GUICannon(Cannon.getInstance()));
		instance.register(new GUIWall(Wall.createHorizontalInstance(600, 280, 300)));
		instance.register(new GUIWall(Wall.createHorizontalInstance(400, 820, 400)));
//		instance.register(new GUIWall(Wall.createHorizontalInstance(550, 620)));
//		instance.register(new GUIWall(Wall.createHorizontalInstance(430, 620)));
		instance.register(new GUIWall(Wall.createVerticalInstance(720, 400, 180)));
		instance.register(new GUIWall(Wall.createVerticalInstance(270, 200, 350)));
//		instance.register(new GUIWall(Wall.getInstance(470, 570)));
		instance.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(440, 250))));
		instance.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(490, 300))));
		return instance;
	}

}

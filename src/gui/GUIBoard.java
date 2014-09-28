package gui;

import java.util.ArrayList;
import java.util.List;

import model.Board;

public class GUIBoard {

	private static GUIBoard instance = new GUIBoard();

	private final List<Paintable> objects = new ArrayList<Paintable>();
	private final GUIEmptyCell emptyCell = GUIEmptyCell.getInstance();

	public GUIBoard() {
	}

	public List<Paintable> getObjects() {
		return objects;
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
	
	public void register(final GUIImageObject object) {
		objects.add(object);
	}

	public static GUIBoard getInstance() {
		return instance;
	}

}

package gui;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import agents.RectangularObject;

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
//		if(temps.size() != 0)
//			System.out.println("TEMPS:" + temps.size());
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
		for(Paintable p : objects){
			if(p.getBackendModel().equals(object)){
				objects.remove(p);
				return;
			}				
		}
	}

	public static GUIBoard getInstance() {
		return instance;
	}

}

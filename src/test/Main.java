package test;

import gui.GUIBoard;
import gui.GUICannon;
import gui.GUIHashtagCreature;
import gui.GUIStarCreature;
import gui.MainWindow;
import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.HashtagCreature;
import agents.RectangularObject.Direction;
import agents.StarCreature;

public class Main {

	public static void main(String[] args) {
		final GUIBoard guiBoard = GUIBoard.getInstance();
		guiBoard.register(new GUIStarCreature(StarCreature.newInstance()));
		guiBoard.register(new GUIStarCreature(StarCreature.newInstance()));
		guiBoard.register(new GUIStarCreature(StarCreature.newInstance()));
		guiBoard.register(new GUIStarCreature(StarCreature.newInstance()));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance()));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance()));
		guiBoard.register(new GUICannon(Cannon.getInstance()));
		final MainWindow window = new MainWindow();

		window.setVisible(true);

		final Board board = Board.getInstance();
		while (true) {
			for (final Creature c : board.getCreatures()) {
				if (Math.random() > 0.5) {
					if (!moveHorizontal(c)) {
						moveVertical(c);
					}
				} else {
					if (!moveVertical(c)) {
						moveHorizontal(c);
					}
				}
			}
			try {
				window.repaint();
				Thread.sleep(10, 0);
			} catch (InterruptedException e) {
			}
		}
	}

	private static boolean moveHorizontal(final Creature c) {
		boolean ans;
		if (c.getPosition().x > 500) {
			ans = c.move(Direction.RIGHT, (Math.min(c.getMaxSpeed(),  c.getPosition().x - 500)));
		} else if (c.getPosition().x < 500) {
			ans = c.move(Direction.LEFT, (Math.min(c.getMaxSpeed(), 500 - c.getPosition().x)));
		} else {
			ans = true;
		}
		return ans;
	}

	private static boolean moveVertical(final Creature c) {
		boolean ans;
		if (c.getPosition().y > 500) {
			ans = c.move(Direction.DOWN, (Math.min(c.getMaxSpeed(),  c.getPosition().y - 500)));
		} else if (c.getPosition().y < 500) {
			ans = c.move(Direction.UP, (Math.min(c.getMaxSpeed(),  500 - c.getPosition().y)));
		} else {
			ans = true;
		}
		return ans;
	}
}

package test;

import java.awt.Point;

import gui.GUIBoard;
import gui.GUICannon;
import gui.GUIHashtagCreature;
import gui.GUIStarCreature;
import gui.GUIWall;
import gui.MainWindow;
import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.RectangularObject.Direction;
import agents.hashtagCreature.HashtagCreature;
import agents.StarCreature;
import agents.Wall;

public class Main {

	private static final int TOTAL_WALLS = 0;
	private static final int STAR_CREATURES = 20;
	private static final int HASHTAG_CREATURES = 10;
	private static final int KICKS = 50;

	public static void main(String[] args) {
		final GUIBoard guiBoard = GUIBoard.getInstance();
		guiBoard.register(new GUICannon(Cannon.getInstance()));
		guiBoard.register(new GUIWall(Wall.getInstance()));
//		c = ;
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(490, 600))));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(490, 700))));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(490, 300))));
		
//		for (int i = 0; i < TOTAL_WALLS; i++) {
//			guiBoard.register(new GUIWall(Wall.getInstance()));
//		}
//		for (int i = 0; i < HASHTAG_CREATURES; i++) {
//			boolean validPos = false;
//			HashtagCreature c = HashtagCreature.newInstance();
//			while (!validPos) {
//				c = HashtagCreature.newInstance();
//				if (Board.getInstance().canMove(c.getPosition(), c)) {
//					validPos = true;
//				}
//			}
//			guiBoard.register(new GUIHashtagCreature(c));
//		}
//		for (int i = 0; i < STAR_CREATURES; i++) {
//			boolean validPos = false;
//			StarCreature c = StarCreature.newInstance();
//			while (!validPos) {
//				c = StarCreature.newInstance();
//				if (Board.getInstance().canMove(c.getPosition(), c)) {
//					validPos = true;
//				}
//			}
//			guiBoard.register(new GUIStarCreature(c));
//		}
		final MainWindow window = MainWindow.getInstance();
		window.setVisible(true);

		final Board board = Board.getInstance();
		int ticks = 0;
		while (true) {
			if (ticks % 10 == 0) {
				ticks = 0;
				guiBoard.clearTemps();
			} else {
				ticks++;
			}
			for (final Creature c : board.getCreatures()) {
				c.action();
//				System.out.println(c);
//				if (Math.random() > 0.5) {
//					if (!moveHorizontal(c)) {
//						if (!moveVertical(c)) {
//							for (int i = 0; i < KICKS; i++) {
//								int index = (int) (Math.random() * Direction.values().length);
//								c.move(Direction.values()[index], c.getMaxSpeed());
//							}
//						}
//					}
//				} else {
//					if (!moveVertical(c)) {
//						if (!moveHorizontal(c)) {
//							for (int i = 0; i < KICKS; i++) {
//								int index = (int) (Math.random() * Direction.values().length);
//								c.move(Direction.values()[index], c.getMaxSpeed());
//							}
//						}
//					}
//				}
			}
			for (final Cannon c : board.getCannons()) {
				board.shoot(c);
			}
			try {
				window.repaint();
				Thread.sleep(100, 0);
			} catch (InterruptedException e) {
			}
		}
	}
//
//	private static boolean moveHorizontal(final Creature c) {
//		boolean ans;
//		if (c.getPosition().x > 500) {
//			ans = c.move(Direction.RIGHT, (Math.min(c.getMaxSpeed(), c.getPosition().x - 500)));
//		} else if (c.getPosition().x < 500) {
//			ans = c.move(Direction.LEFT, (Math.min(c.getMaxSpeed(), 500 - c.getPosition().x)));
//		} else {
//			// ans = true;
//			ans = false;
//		}
//		return ans;
//	}
//
//	private static boolean moveVertical(final Creature c) {
//		boolean ans;
//		if (c.getPosition().y > 500) {
//			ans = c.move(Direction.DOWN, (Math.min(c.getMaxSpeed(), c.getPosition().y - 500)));
//		} else if (c.getPosition().y < 500) {
//			ans = c.move(Direction.UP, (Math.min(c.getMaxSpeed(), 500 - c.getPosition().y)));
//		} else {
//			// ans = true;
//			ans = false;
//		}
//		return ans;
//	}
}

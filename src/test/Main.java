package test;

import gui.GUIBoard;
import gui.GUICannon;
import gui.GUIHashtagCreature;
import gui.GUIWall;
import gui.MainWindow;

import java.awt.Point;

import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.Wall;
import agents.hashtagCreature.HashtagCreature;

public class Main {

	public static void main(String[] args) {
		final GUIBoard guiBoard = GUIBoard.getInstance();
		guiBoard.register(new GUICannon(Cannon.getInstance()));
		guiBoard.register(new GUIWall(Wall.getInstance(430, 620)));
		guiBoard.register(new GUIWall(Wall.getInstance(470, 570)));
		Math.random();
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(300, 250))));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(440, 250))));
		HashtagCreature creature = HashtagCreature.newInstance(new Point(490, 700));
		guiBoard.register(new GUIHashtagCreature(creature));
		guiBoard.register(new GUIHashtagCreature(HashtagCreature.newInstance(new Point(490, 300))));
		
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
			}
			for (final Cannon c : board.getCannons()) {
				board.shoot(c);
			}
			try {
				window.repaint();
				Thread.sleep(50, 0);
			} catch (InterruptedException e) {
			}
		}
	}

}

package test;

import java.awt.Point;

import gui.GUIBoard;
import gui.GUIHashtagCreature;
import gui.MainWindow;
import model.Board;
import agents.Cannon;
import agents.Creature;
import agents.hashtagCreature.HashtagCreature;

public class Main {
	
	private static final int RESPAWN_TIME = 100;

	public static void main(String[] args) {
		final GUIBoard guiBoard = GUIBoard.createNewBoard();
		final MainWindow window = MainWindow.getInstance();
		window.setVisible(true);

		int ticks = 0;
		long epoch = 0;
		long startTime = System.currentTimeMillis();
		while (true) {
			window.setTitle((System.currentTimeMillis() - startTime) / 1000 / 60 + " minutes "
					+ (System.currentTimeMillis() - startTime) / 1000 % 60 + " seconds");
			window.repaint();
			if(window.paused)
				continue;
			epoch++;
			if (ticks % 10 == 0) {
				ticks = 0;
				guiBoard.clearTemps();
			} else {
				ticks++;
			}
			if (epoch % RESPAWN_TIME == 0) {
				GUIBoard.getInstance().register(
						new GUIHashtagCreature(HashtagCreature.newInstance()));
			}
			for (final Creature c : Board.getInstance().getCreatures()) {
				c.action();
			}
			for (final Cannon c : Board.getInstance().getCannons()) {
				Board.getInstance().shoot(c);
			}
			try {
				window.repaint();
				Thread.sleep(50, 0);
			} catch (InterruptedException e) {
			}
		}
	}

}

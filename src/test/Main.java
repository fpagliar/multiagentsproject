package test;

import gui.GUIAmpersandCreature;
import gui.GUIBoard;
import gui.GUIHashtagCreature;
import gui.GUIStarCreature;
import gui.MainWindow;
import gui.MessageWindow;

import java.util.ArrayList;
import java.util.List;

import model.BlackBoard;
import model.Board;
import agents.AmpersandCreature;
import agents.Cannon;
import agents.Creature;
import agents.hashtagCreature.HashtagCreature;
import agents.ruleBased.RoolBasedCreature;
import agents.starAgent.StarCreature;

public class Main {
	
	private static final int HASHTAG_RESPAWN_TIME = 400;
	private static final int STAR_RESPAWN_TIME = 200;
	private static final int AMPERSAND_RESPAWN_TIME = 300;

	public static void main(String[] args) {
		final GUIBoard guiBoard = GUIBoard.createNewBoard();
		GUIBoard.getInstance().register(
				new GUIStarCreature(StarCreature.newInstance()));
		GUIBoard.getInstance().register(
				new GUIStarCreature(StarCreature.newInstance()));
		final MainWindow window = MainWindow.getInstance();
		window.setVisible(true);

		int ticks = 0;
		long epoch = 0;
		long startTime = System.currentTimeMillis();
		long lastGoalTime = System.currentTimeMillis();
		while (true) {
			window.setTitle((System.currentTimeMillis() - startTime) / 1000 / 60 + " minutes "
					+ (System.currentTimeMillis() - startTime) / 1000 % 60 + " seconds");
			if(window.paused)
				continue;
			final List<Creature> outside = new ArrayList<>();
			final List<Creature> circleOfFire = new ArrayList<>();
			final List<Creature> noMansLand = new ArrayList<>();
			final List<Creature> touching = new ArrayList<>();
			for (final Creature c : Board.getInstance().getCreatures()) {
				if(Board.getInstance().inCircleOfFire(c))
					circleOfFire.add(c);
				else if(Board.getInstance().touchingCannon(c))
					touching.add(c);
				else if(Board.getInstance().inShootingZone(c))
					noMansLand.add(c);
				else
					outside.add(c);
			}
			try {
				window.repaint();
				if(noMansLand.size() > 0 && window.slowMotion)
					Thread.sleep(200, 0);
				else
					Thread.sleep(20, 0);
			} catch (InterruptedException e) {
			}
			epoch++;
			if (ticks % 10 == 0) {
				ticks = 0;
				guiBoard.clearTemps();
			} else {
				ticks++;
			}
			if (epoch % HASHTAG_RESPAWN_TIME == 0) {
				GUIBoard.getInstance().register(
						new GUIHashtagCreature(HashtagCreature.newInstance()));
			}
			if (epoch % STAR_RESPAWN_TIME == 0) {
				GUIBoard.getInstance().register(
						new GUIStarCreature(StarCreature.newInstance()));
			}
			if (epoch % AMPERSAND_RESPAWN_TIME == 0) {
				GUIBoard.getInstance().register(
						new GUIAmpersandCreature(AmpersandCreature.newInstance()));
			}
			for (final Creature c : Board.getInstance().getCreatures()) {
				c.action();
			}
			window.clearStrings();
			putStrings(outside, circleOfFire, noMansLand, touching);
			
			for (final Cannon c : Board.getInstance().getCannons()) {
				Board.getInstance().shoot(c);
			}
			int creaturesTouching = 0;
			for (final Creature c : touching)
				creaturesTouching++;
			if (creaturesTouching > 1 && (System.currentTimeMillis() - lastGoalTime) > 5000) {
				lastGoalTime = System.currentTimeMillis();
				final RoolBasedCreature winner = (RoolBasedCreature) touching.get(0);
				BlackBoard.getInstance().writeSuccess(winner.getTactic());
				new MessageWindow("THE GOAL WAS REACHED");
			}
		}
	}
	
	private static void putStrings(final List<Creature> outside, final List<Creature> circleOfFire,
			final List<Creature> noMansLand, final List<Creature> touching) {
		MainWindow.getInstance().putString("OUTSIDE");
		for(final Creature c : outside)
			MainWindow.getInstance().putString(c.labelString());
		
		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("CIRCLE OF FIRE");
		for(final Creature c : circleOfFire)
			MainWindow.getInstance().putString(c.labelString());
		
		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("NO MANS LAND");
		for(final Creature c : noMansLand)
			MainWindow.getInstance().putString(c.labelString());

		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("");
		MainWindow.getInstance().putString("TOUCHING THE CANNON");
		for(final Creature c : touching)
			MainWindow.getInstance().putString(c.labelString());
	}

}

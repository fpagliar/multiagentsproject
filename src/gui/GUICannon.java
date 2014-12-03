package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.Set;

import javax.imageio.ImageIO;

import agents.Cannon;
import agents.RectangularObject;

public class GUICannon extends GUIImageObject {

	private final Cannon cannon;
	private static final String IMAGE_PATH = "resources/cannon2.png";
	private static final Image image = innerGetImage();
	private static final int IMAGE_WIDTH = 50;
	private static final int IMAGE_HEIGHT = 50;

	private static Image innerGetImage() {
		try {
			File img = new File(IMAGE_PATH);
			return ImageIO.read(img);
			// FOR RUNNABLE JAR
//			return ImageIO.read(GUICannon.class.getResource(IMAGE_PATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GUICannon(final Cannon cannon) {
		this.cannon = cannon;
	}

	public Cannon getCannon() {
		return cannon;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	protected Rectangle getPosition() {
		return cannon.getPosition();
	}

	@Override
	protected int getImageWidth() {
		return IMAGE_WIDTH;
	}

	@Override
	protected int getImageHeight() {
		return IMAGE_HEIGHT;
	}

	@Override
	protected String descriptor() {
		return cannon.toString();
	}

	@Override
	protected Set<Point> occupiedPoints() {
		return cannon.occupiedPoints();
	}

	@Override
	public RectangularObject getBackendModel() {
		return cannon;
	}

	@Override
	public Set<Point> paint(Graphics g) {
		Set<Point> points = super.paint(g);
		final int radius = cannon.getReach();
		final Rectangle position = cannon.getPosition();
		int centerX = position.x + position.width / 2;
		int centerY = position.y + position.height/2;
		g.setColor(Color.RED);
		for(int x = centerX - radius; x < centerX + radius ; x++){
			int y = (int) Math.pow(radius * radius - (x - centerX) * (x - centerX), 0.5);
			g.drawRect(x, centerY + y, 1, 1);
			g.drawRect(x, centerY - y, 1, 1);			
		}
		
		return points;
	}
}

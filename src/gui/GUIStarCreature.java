package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import agents.StarCreature;

public class GUIStarCreature extends GUICreature<StarCreature> {

	private static final String IMAGE_PATH = "resources/star.png";
	private static final Image image = innerGetImage();
	private static final int IMAGE_WIDTH = 20;
	private static final int IMAGE_HEIGHT = 20;

	private static Image innerGetImage() {
		try {
			File img = new File(IMAGE_PATH);
			return ImageIO.read(img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public GUIStarCreature(final agents.StarCreature creature) {
		super(creature);
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	protected int getImageWidth() {
		return IMAGE_WIDTH;
	}

	@Override
	protected int getImageHeight() {
		return IMAGE_HEIGHT;
	}

}

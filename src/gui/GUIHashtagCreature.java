package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import agents.hashtagCreature.HashtagCreature;

public class GUIHashtagCreature extends GUICreature<HashtagCreature> {

	private static final String IMAGE_PATH = "resources/hashtag.png";
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

	public GUIHashtagCreature(final HashtagCreature creature) {
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

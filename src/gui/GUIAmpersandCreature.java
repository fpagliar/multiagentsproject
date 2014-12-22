package gui;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import agents.AmpersandCreature;

public class GUIAmpersandCreature extends GUICreature<AmpersandCreature> {

		private static final String IMAGE_PATH = "resources/third.png";
		private static final Image image = innerGetImage();
		private static final int IMAGE_WIDTH = 32;
		private static final int IMAGE_HEIGHT = 32;

		private static Image innerGetImage() {
			try {
				File img = new File(IMAGE_PATH);
				return ImageIO.read(img);
				// FOR RUNNABLE JAR
//				return ImageIO.read(GUICannon.class.getResource(IMAGE_PATH));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public GUIAmpersandCreature(final AmpersandCreature creature) {
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

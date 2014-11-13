package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

public abstract class GUIImageObject implements Paintable {

	public abstract Image getImage();

	protected abstract Rectangle getPosition();

	protected abstract int getImageWidth();

	protected abstract int getImageHeight();
	
	protected abstract String descriptor();

	protected abstract Set<Point> occupiedPoints();

	@Override
	public Set<Point> paint(Graphics g) {
		final Rectangle pos = getPosition();
		if (pos.width % getImageWidth() != 0) {
			throw new RuntimeException("INVALID WIDTH FOR ELEMENT:" + descriptor() + " it doesn't match the image width");
		}
		if (pos.height % getImageHeight() != 0) {
			throw new RuntimeException("INVALID WIDTH FOR ELEMENT:" + descriptor() + " it doesn't match the image width");
		}
		for (int x = pos.x; x < pos.x + pos.width; x += getImageWidth())
			for (int y = pos.y; y < pos.y + pos.height; y += getImageHeight()) {
				g.drawImage(getImage(), x, y, null);
			}
		
		g.setColor(Color.WHITE);
		g.drawString(getBackendModel().labelString(), pos.x + pos.width, pos.y + pos.height/2);
		
		return occupiedPoints();
	}

}

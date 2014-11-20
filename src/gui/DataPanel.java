package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int STRING_HEIGHT = 20;
	
	private static final Map<Object, String> values = new HashMap<Object, String>();
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		int pos = 1;
		for(final Object key : values.keySet()){
			g.drawString(key.toString() + " : " + values.get(key), 1000 + 10, pos * STRING_HEIGHT + 5);
			pos++;
		}
	}
	
	public void putString(final Object key, final String value) {
		values.put(key, value);
	}

	public void removeString(final Object key) {
		values.remove(key);
	}
	
	public void clear() {
		values.clear();
	}
}

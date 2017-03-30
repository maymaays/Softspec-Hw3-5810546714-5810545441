package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
		// TODO: Implement this method.
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
		// TODO: Implement this method.
	}

	@Override
	public void move(int dX, int dY) {
		x += dX;
		y += dY;
		for (GObject g : gObjects) {
			g.move(dX, dY);
		}
		// TODO: Implement this method.
	}

	public void recalculateRegion() {
		int minx = 0, maxx = 0, miny = 0, maxy = 0;
		for (int i = 0; i < gObjects.size(); i++) {
			if (i == 0) {
				minx = gObjects.get(i).x;
				miny = gObjects.get(i).y;
				maxx = gObjects.get(i).x + gObjects.get(i).width;
				maxy = gObjects.get(i).y + gObjects.get(i).height;
			} else {
				if (minx > gObjects.get(i).x) {
					minx = gObjects.get(i).x;
				}
				if (miny > gObjects.get(i).y) {
					miny = gObjects.get(i).y;
				}
				if (maxx < gObjects.get(i).x + gObjects.get(i).width) {
					maxx = gObjects.get(i).x + gObjects.get(i).width;
				}
				if (maxy < gObjects.get(i).y + gObjects.get(i).height) {
					maxy = gObjects.get(i).y + gObjects.get(i).height;
				}
			}
			x = minx;
			y = miny;
			width = maxx - minx;
			height = maxy - miny;
		}
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject x : gObjects) {
			x.paintObject(g);
		}
		// TODO: Implement this method.
	}

	@Override
	public void paintLabel(Graphics g) {
		g.drawString("Grouped", x, y + height + 10);
		// TODO: Implement this method.
	}

}

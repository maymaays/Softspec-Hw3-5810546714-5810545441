package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter;
	private List<GObject> gObjects;
	private GObject target;

	private int gridSize = 10;

	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}

	public void addGObject(GObject gObject) {
		gObjects.add(gObject);
		repaint();
		// TODO: Implement this method.
	}

	public void groupAll() {
		CompositeGObject cp = new CompositeGObject();
		for (GObject g : gObjects) {
			cp.add(g);
		}
		cp.recalculateRegion();
		gObjects.clear();
		gObjects.add(cp);
		repaint();

		// TODO: Implement this method.
	}

	public void deleteSelected() {
		gObjects.remove(target);
		target = null;
		repaint();
		// TODO: Implement this method.
	}

	public void clear() {
		gObjects.clear();
		target = null;
		repaint();
		// TODO: Implement this method.
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here
		int positionX, positionY;

		private void deselectAll() {
			// TODO: Implement this method.
			for (GObject g : gObjects) {
				g.deselected();
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			{
				positionX = e.getX();
				positionY = e.getY();
				deselectAll();
				target = null;
				for (GObject g : gObjects) {
					if (g.pointerHit(positionX, positionY)) {
						g.selected();
						target = g;
						repaint();
						break;
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int x, y;
			if (target == null) 
				return;
			x = e.getX();
			y = e.getY();
			target.move(x - positionX, y - positionY);
			positionX = x;
			positionY = y;
			repaint();
		}
	}

}
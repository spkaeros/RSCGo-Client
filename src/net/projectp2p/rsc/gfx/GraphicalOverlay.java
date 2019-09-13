package net.projectp2p.rsc.gfx;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import net.projectp2p.rsc.mudclient;

public class GraphicalOverlay {

	private List<GraphicalComponent> components = new ArrayList<>();

	private mudclient<?> mc;

	public boolean visible = false;

	public boolean menu = false;

	public GraphicalOverlay(mudclient<?> mc) {
		this.mc = mc;
	}

	public boolean add(GraphicalComponent... comp) {
		for (GraphicalComponent c : comp) {
			c.mc = mc;
			components.add(c);
		}
		return true;
	}

	private boolean doAction(int x, int y, int button, GraphicalComponent comp) {
		if (comp.getBoundarys() != null
				&& comp.getBoundarys().contains(new Point(x, y))) {
			for (GraphicalComponent c : comp.getComponents()) {
				if (c.getBoundarys().contains(new Point(x, y))) {
					c.hovering = true;
				} else {
					c.hovering = false;
				}
				if (c.hoveringCallback != null)
					c.hoveringCallback.hovering();
				if (c.action != null && button != 0
						&& c.getBoundarys().contains(new Point(x, y))) {
					c.action.action(x, y, button);
					return true;
				}
			}
			comp.hovering = true;
			if (comp.hoveringCallback != null)
				comp.hoveringCallback.hovering();
			if (comp.action != null && button != 0) {
				comp.action.action(x, y, button);
				return true;
			}
			return false;
		} else {
			comp.hovering = false;
			for (GraphicalComponent c : comp.getComponents()) {
				if (!c.getBoundarys().contains(new Point(x, y))) {
					c.hovering = false;
				}
			}
		}
		return false;
	}

	public List<GraphicalComponent> getComponents() {
		return components;
	}

	public boolean isMenu() {
		return menu;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean onAction(int x, int y, int button) {
		for (GraphicalComponent comp : components) {
			if (!comp.visible && !comp.isDragging())
				continue;
			if (!comp.isDragging() && doAction(x, y, button, comp))
				return true;
		}
		return false;
	}

	public boolean onComponent(int x, int y, GraphicalComponent comp) {
		if (comp.getBoundarys() != null
				&& comp.getBoundarys().contains(new Point(x, y))) {
			return true;
		}
		return false;
	}

	public void onRender() {
		if (visible) {
			for (GraphicalComponent comp : components) {
				comp.onRender();
			}
		}
	}

	public void onResize(int width, int height) {
		for (GraphicalComponent comp : components) {
			comp.onResize(width, height);
		}
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
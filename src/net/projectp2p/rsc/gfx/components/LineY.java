package net.projectp2p.rsc.gfx.components;

import net.projectp2p.rsc.gfx.GraphicalComponent;

public class LineY extends GraphicalComponent {

	private int x, y, endx = 0;

	public LineY(int x, int y, int endx) {
		this.x = x;
		this.y = y;
		this.endx = endx;
	}

	@Override
	public void render() {
		if (!visible)
			return;
		mc.surface.drawLineY(x, y, endx, this.convertToJag(0, 0, 0));
	}
}
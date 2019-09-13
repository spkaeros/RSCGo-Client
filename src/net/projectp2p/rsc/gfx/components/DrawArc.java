package net.projectp2p.rsc.gfx.components;

import net.projectp2p.rsc.gfx.GraphicalComponent;

public class DrawArc extends GraphicalComponent {

	@Override
	public void render() {
		mc.surface.rounded_rectangle(100, 100, 100, 100, 50000, 100);
	}

}

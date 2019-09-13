package net.projectp2p.rsc.gfx.components;

import net.projectp2p.rsc.gfx.GraphicalComponent;

public class BlackScreen extends GraphicalComponent {

	@Override
	public void render() {
		mc.surface.blackScreen();
	}

}

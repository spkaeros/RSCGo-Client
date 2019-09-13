package net.projectp2p.rsc.gfx.uis;

import net.projectp2p.rsc.mudclient;
import net.projectp2p.rsc.gfx.GraphicalOverlay;
import net.projectp2p.rsc.gfx.components.DrawArc;

public class ChatUI extends GraphicalOverlay {

	public ChatUI(mudclient<?> mc) {
		super(mc);
		add(new DrawArc());
	}

}

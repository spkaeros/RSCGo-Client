package net.projectp2p.rsc.gfx.uis.various;

import java.util.ArrayList;
import java.util.List;

import net.projectp2p.rsc.mudclient;
import net.projectp2p.rsc.gfx.GraphicalOverlay;
import net.projectp2p.rsc.gfx.uis.BankUI;
import net.projectp2p.rsc.gfx.uis.ChatUI;

public class GameUIs {
	public static void reload() {
		overlay.clear();
		overlay.add(new BankUI(mudclient.getInstance()));
		overlay.add(new ChatUI(mudclient.getInstance()));
	}

	public static List<GraphicalOverlay> overlay = new ArrayList<>();

	static {
		reload();
	}

}

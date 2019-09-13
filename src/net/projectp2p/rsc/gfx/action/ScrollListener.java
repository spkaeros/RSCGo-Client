package net.projectp2p.rsc.gfx.action;

public interface ScrollListener {

	public void onScrollUpdate(int index);

	// 0 down - 1 up
	public void scrolling(int type);

}

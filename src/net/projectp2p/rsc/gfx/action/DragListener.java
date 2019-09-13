package net.projectp2p.rsc.gfx.action;

public interface DragListener {

	public boolean onDragging(int startX, int startY);

	public void stopDragging();

}
package net.projectp2p.rsc.various;

import java.awt.Container;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract interface ImplementationDelegate extends MouseListener,
		MouseMotionListener, MouseWheelListener, KeyListener {

	public abstract Container getContainerImpl();

}
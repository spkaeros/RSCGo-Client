package net.projectp2p.rsc.io;

public class IsaacContainer {
	public Isaac encoder;
	public Isaac decoder;
	public IsaacContainer(long c, long s) {
		encoder = new Isaac(new long[] { c, s });
		decoder = new Isaac(new long[] { c + 50, s + 50 });
	}
}
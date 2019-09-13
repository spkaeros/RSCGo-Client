package net.projectp2p.rsc.audio;

import java.io.InputStream;

import sun.audio.AudioPlayer;

@SuppressWarnings("restriction")
public class SoundInputStream extends InputStream {
	byte dataArray[];
	int length;
	int offset;

	public SoundInputStream() {
		AudioPlayer.player.start(this);
	}

	public void stop() {
		AudioPlayer.player.stop(this);
	}

	public void loadData(byte abyte0[], int i, int j) {
		dataArray = abyte0;
		offset = i;
		length = i + j;
	}

	@Override
	public int read() {
		byte abyte0[] = new byte[1];
		read(abyte0, 0, 1);
		return abyte0[0];
	}

	@Override
	public int read(byte abyte0[], int i, int j) {
		for(int k = 0; k < j; k++)
			if(offset < length)
				abyte0[i + k] = dataArray[offset++];
			else
				abyte0[i + k] = -1;
		return j;
	}
}
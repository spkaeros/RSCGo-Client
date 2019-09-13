package net.projectp2p.rsc.cache;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.projectp2p.rsc.bzip.CBZip2OutputStream;

public class Utils {
	public static byte[] bz2Compress(byte[] b) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		CBZip2OutputStream bzo = new CBZip2OutputStream(bos, 1);
		bzo.write(b);
		bzo.close();
		bos.close();
		return bos.toByteArray();
	}

	public static byte[] gzDecompress(byte[] b) throws IOException {
		GZIPInputStream gzi = new GZIPInputStream(new ByteArrayInputStream(b));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while((len = gzi.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		gzi.close();
		return out.toByteArray();
	}

	public static byte[] gzCompress(byte[] b) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gzo = new GZIPOutputStream(bos);
		gzo.write(b);
		gzo.close();
		bos.close();
		return bos.toByteArray();
	}

	public static int getHash(String s) {
		int identifier = 0;
		s = s.toUpperCase();
		for(int j = 0; j < s.length(); j++)
			identifier = (identifier * 61 + s.charAt(j)) - 32;
		return identifier;
	}

	public static byte[] readFile(File f) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(f, "r");
		byte[] data = new byte[(int) raf.length()];
		raf.readFully(data);
		raf.close();
		return data;
	}

	public static void writeFile(File f, byte[] data) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		raf.write(data);
		raf.close();
	}
}
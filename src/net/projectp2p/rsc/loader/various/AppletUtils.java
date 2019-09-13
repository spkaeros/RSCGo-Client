package net.projectp2p.rsc.loader.various;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class AppletUtils {

	public static final void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	public static boolean doDirChecks() {
		if (!CACHE.exists()) {
			return CACHE.mkdir();
		}
		return true;
	}

	public static void drawPercentage(Graphics g, int percentage, String message) {
		Font font = new Font("Helvetica", 1, 13);
		FontMetrics fontmetrics = g.getFontMetrics(font);
		Font font1 = new Font("Helvetica", 0, 13);
		FontMetrics fontmetrics1 = g.getFontMetrics(font1);
		Font font2 = new Font("TimesRoman", 0, 15);
		FontMetrics fontmetrics2 = g.getFontMetrics(font2);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(198, 198, 198));
		g.setFont(font);
		g.drawString("RuneScape has been updated!",
				width / 2 - fontmetrics.stringWidth(message) / 2 - 40,
				height / 2 - 167 + 125);
		String messages = "Please wait - Fetching new files...";
		g.drawString(messages, width / 2 - fontmetrics.stringWidth(messages)
				/ 2, height / 2 - 167 + 140);
		g.setFont(font1);
		String message1 = "This may take a few minutes, but only";
		g.drawString(message1, width / 2 - fontmetrics1.stringWidth(message1)
				/ 2, height / 2 - 167 + 165);
		String message2 = "needs to be done when the game is updated.";
		g.drawString(message2, width / 2 - fontmetrics1.stringWidth(message2)
				/ 2, height / 2 - 167 + 180);
		Color color = new Color(132, 132, 132);
		g.setColor(color);
		g.drawRect(width / 2 - 152, height / 2 - 167 + 190, 303, 23);
		g.fillRect(width / 2 - 150, height / 2 - 167 + 192, percentage * 3, 20);
		g.setColor(Color.BLACK);
		g.fillRect(width / 2 - 150 + percentage * 3, height / 2 - 167 + 192,
				300 - percentage * 3, 20);
		String s2 = message
				+ " "
				+ (percentage == 0 ? "" : new StringBuilder("- ")
						.append(percentage).append("%").toString());
		g.setFont(font2);
		g.setColor(new Color(198, 198, 198));
		g.drawString(s2, width / 2 - fontmetrics2.stringWidth(s2) / 2,
				height / 2 - 167 + 207);
	}

	public static void extractFolder(String zipFile, String extractFolder) {
		try {
			int BUFFER = 2048;
			File file = new File(zipFile);

			ZipFile zip = new ZipFile(file);
			String newPath = extractFolder;

			new File(newPath).mkdir();
			Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
				// grab a zip file entry
				ZipEntry entry = zipFileEntries.nextElement();
				String currentEntry = entry.getName();

				File destFile = new File(newPath, currentEntry);
				// destFile = new File(newPath, destFile.getName());
				File destinationParent = destFile.getParentFile();

				// create the parent directory structure if needed
				destinationParent.mkdirs();

				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(
							zip.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,
							BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}

			}
			zip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
	}
	public static int width = 512;

	public static int height = 334;

	public static final File CACHE = new File(System.getProperty("user.home")
			+ "/.proelium-beta");

	public static final File CACHEFILE = new File(CACHE + "/" + "cache-r4.zip");

	public static String DISPLAY_MESSAGE = "Loading...";

	public static String MD5_CLIENT = null;

	public static int percentage = 0;

	public static Image update_image = null;

	public static boolean isApplet = false;
}
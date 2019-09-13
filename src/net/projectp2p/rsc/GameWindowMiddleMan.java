package net.projectp2p.rsc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.security.SecureRandom;

import net.projectp2p.rsc.bzip.DataEncryption;
import net.projectp2p.rsc.cache.ChatMessage;
import net.projectp2p.rsc.io.StreamClass;
import net.projectp2p.rsc.various.ImplementationDelegate;
import net.projectp2p.rsc.various.Utility;

public class GameWindowMiddleMan<Delegate_T extends ImplementationDelegate>
		extends GameManager<Delegate_T> {
	public static int clientVersion = 222;

	public static int maxPacketReadCount;

	public int blockChatMessages;

	public int blockDuelRequests;

	public int blockPrivateMessages;

	public int blockTradeRequests;

	public int friendsCount;

	public long friendsListLongs[];

	public int friendsListOnlineStatus[];

	public int ignoreListCount;

	public long ignoreListLongs[];

	long lastPing;

	byte packetData[];

	String password;

	public int port;

	int reconnectTries;

	public String server;

	public int socketTimeout;

	public StreamClass streamClass;

	public long unusedSessionID;

	public int userGroup;

	public static String version = "42";

	String username;

	public GameWindowMiddleMan(Delegate_T c) {
		super(c);
		server = "127.0.0.1";// 37.187.117.185
		port = 43591;
		username = "";
		password = "";
		packetData = new byte[5000];
		friendsListLongs = new long[200];
		friendsListOnlineStatus = new int[200];
		ignoreListLongs = new long[100];
	}

	protected final void addToFriendsList(String s) {
		streamClass.createPacket(213);
		streamClass.addLong(Utility.stringLength12ToLong(s));
		streamClass.formatPacket();
		long l = Utility.stringLength12ToLong(s);
		for (int i = 0; i < friendsCount; i++) {
			if (friendsListLongs[i] == l) {
				return;
			}
		}

		if (friendsCount >= 100) {
			return;
		} else {
			friendsListLongs[friendsCount] = l;
			friendsListOnlineStatus[friendsCount] = 0;
			friendsCount++;
			return;
		}
	}

	protected final void addToIgnoreList(String s) {
		long l = Utility.stringLength12ToLong(s);
		streamClass.createPacket(254);
		streamClass.addLong(l);
		streamClass.formatPacket();
		for (int i = 0; i < ignoreListCount; i++) {
			if (ignoreListLongs[i] == l) {
				return;
			}
		}

		if (ignoreListCount >= 100) {
			return;
		} else {
			ignoreListLongs[ignoreListCount++] = l;
			return;
		}
	}

	protected void cantLogout() {
	}

	private final void checkIncomingPacket(int command, int length) {
//		System.out.println("pid=" + command+",\tlen="+length);
		if (command == 48) {
			String s = new String(packetData, 1, length - 1);
			handleServerMessage(s);
			return;
		}
		if (command == 222) {
			sendLogoutPacket();
			return;
		}
		if (command == 136) {
			cantLogout();
			return;
		}
		if (command == 249) {
			friendsCount = Utility.getUnsignedByte(packetData[1]);
			for (int k = 0; k < friendsCount; k++) {
				friendsListLongs[k] = Utility
						.getUnsigned8Bytes(packetData, 2 + k * 9);
				friendsListOnlineStatus[k] = Utility
						.getUnsignedByte(packetData[10 + k * 9]);
			}

			reOrderFriendsListByOnlineStatus();
			return;
		}
		if (command == 25) {
			long friend = Utility.getUnsigned8Bytes(packetData, 1);
			int status = packetData[9] & 0xff;
			for (int i2 = 0; i2 < friendsCount; i2++) {
				if (friendsListLongs[i2] == friend) {
					if (friendsListOnlineStatus[i2] == 0 && status != 0) {
						handleServerMessage("@pri@"
								+ Utility.longToString(friend)
								+ " has logged in");
					}
					if (friendsListOnlineStatus[i2] != 0 && status == 0) {
						handleServerMessage("@pri@"
								+ Utility.longToString(friend)
								+ " has logged out");
					}
					friendsListOnlineStatus[i2] = status;
					length = 0;
					reOrderFriendsListByOnlineStatus();
					return;
				}
			}

			friendsListLongs[friendsCount] = friend;
			friendsListOnlineStatus[friendsCount] = status;
			friendsCount++;
			reOrderFriendsListByOnlineStatus();
			return;
		}
		if (command == 2) {
			ignoreListCount = Utility.getUnsignedByte(packetData[1]);
			for (int i1 = 0; i1 < ignoreListCount; i1++) {
				ignoreListLongs[i1] = Utility.getUnsigned8Bytes(packetData,
						2 + i1 * 8);
			}

			return;
		}
		if (command == 158) {
			blockChatMessages = packetData[1];
			blockPrivateMessages = packetData[2];
			blockTradeRequests = packetData[3];
			blockDuelRequests = packetData[4];
			return;
		}
		if (command == 170) {
			long user = Utility.getUnsigned8Bytes(packetData, 1);
			String s1 = ChatMessage.byteToString(packetData, 9, length - 9);
			handleServerMessage("@pri@" + Utility.longToString(user)
					+ ": tells you " + s1);
			return;
		} else {
			handleIncomingPacket(command, length, packetData);
			return;
		}
	}

	protected void emptyMethod() {
	}

	protected final void gameBoxPrint(String s, String s1) {
		Graphics g = getGraphics();
		Font font = new Font("Helvetica", 1, 15);
		char c = '\u0200';
		char c1 = '\u0158';
		g.setColor(Color.black);
		g.fillRect(c / 2 - 140, c1 / 2 - 25, 280, 50);
		g.setColor(Color.white);
		g.drawRect(c / 2 - 140, c1 / 2 - 25, 280, 50);
		drawString(g, s, font, c / 2, c1 / 2 - 10);
		drawString(g, s1, font, c / 2, c1 / 2 + 10);
	}

	protected byte[] getBytes(int[] i) {
		byte[] buf = new byte[i.length * 4];
		int off = 0;
		for (int n = 0; n < i.length; n++) {
			buf[off++] = (byte) (i[n] >> 24);
			buf[off++] = (byte) (i[n] >> 16);
			buf[off++] = (byte) (i[n] >> 8);
			buf[off++] = (byte) (i[n]);
		}
		return buf;
	}

	protected int getUID() {
		return 0;
	}

	protected void handleIncomingPacket(int command, int length, byte abyte0[]) {
	}

	protected void handleServerMessage(String s) {
	}

	private static final SecureRandom RANDOM = new SecureRandom();

	protected static long getSecureSeed() {
		long val = 0L;

		do {
			val = RANDOM.nextLong();
		} while (val <= 0);
		
		return val;
	}

	protected final void login(String user, String pass, boolean reconnecting) {
		if (socketTimeout > 0) {
			loginScreenPrint("Please wait...", "Connecting to server");
			try {
				Thread.sleep(2000L);
			} catch (Exception _ex) {
			}
			loginScreenPrint("Sorry! The server is currently full.",
					"Please try again later");
			return;
		}
		try {
			username = user;
			user = Utility.addCharacters(user, username.length());
			password = pass;
			pass = Utility.addCharacters(pass, password.length());
			if (user.trim().length() == 0) {
				loginScreenPrint("You must enter both a username",
						"and a password - Please try again");
				return;
			}
			if (reconnecting)
				gameBoxPrint("Connection lost! Please wait...",
						"Attempting to re-establish");
			else
				loginScreenPrint("Please wait...", "Connecting to server");

			if(streamClass != null) {
				streamClass.closeStream();
			}
			streamClass = new StreamClass(makeSocket(server, port), this);
			streamClass.maxPacketReadCount = maxPacketReadCount;
			long l = Utility.stringLength12ToLong(user);
			streamClass.createPacket(32);
			streamClass.addByte((int) (l >> 16 & 31L));
			streamClass.formatAndSendPacket();
			long sessionID = streamClass.readLong();
			if (sessionID == 0L) {
				loginScreenPrint("Login server offline.",
						"Please try again in a few mins");
				return;
			}
			System.out.print("Session ID: " + sessionID);
			DataEncryption dataEncryption = new DataEncryption(new byte[117]);
			dataEncryption.addByte(reconnecting ? 1 : 0);
			dataEncryption.add4ByteInt(Integer.parseInt(version));
			long clientSeed = getSecureSeed();
			dataEncryption.addLong(clientSeed);
			dataEncryption.addLong(sessionID);
			dataEncryption.addString(user);
			dataEncryption.addString(pass);
			byte[] data = DataEncryption.encrypt(dataEncryption.packet);
			streamClass.createPacket(0); // player login
			streamClass.addBytes(data, 0, data.length);
			streamClass.formatAndSendPacket();
			streamClass.isaac = new net.projectp2p.rsc.io.IsaacContainer(clientSeed, sessionID);
			int loginResponse = streamClass.readInputStream();
			System.out.println(" - Login Response:" + loginResponse);
			if (loginResponse == 25) {
				userGroup = 2; // admin
				reconnectTries = 0;
				resetVars();
				return;
			}
			if (loginResponse == 24) {
				userGroup = 1; // mod
				reconnectTries = 0;
				resetVars();
				return;
			}
			if (loginResponse == 0) {
				userGroup = 0;
				reconnectTries = 0;
				resetVars();
				return;
			}
			if (loginResponse == 1) {
				reconnectTries = 0;
				emptyMethod();
				return;
			}
			if (reconnecting) {
				user = "";
				pass = "";
				resetIntVars();
				return;
			}
			if (loginResponse == -1) {
				loginScreenPrint("Error unable to login.", "Server timed out");
				return;
			}
			if (loginResponse == 3) {
				loginScreenPrint("Invalid username or password.",
						"Try again, or create a new account");
				return;
			}
			if (loginResponse == 4) {
				loginScreenPrint("That username is already logged in.",
						"Wait 60 seconds then retry");
				return;
			}
			if (loginResponse == 5) {
				loginScreenPrint("The client has been updated.",
						"Please reload this page");
				return;
			}
			if (loginResponse == 6) {
				loginScreenPrint("You may only use 1 character at once.",
						"Your account is already in use");
				return;
			}
			if (loginResponse == 7) {
				loginScreenPrint("Login attempts exceeded!",
						"Please try again in 5 minutes");
				return;
			}
			if (loginResponse == 8) {
				loginScreenPrint("Error unable to login.",
						"Server rejected session");
				return;
			}
			if (loginResponse == 9) {
				loginScreenPrint("Error unable to login.",
						"Loginserver rejected session");
				return;
			}
			if (loginResponse == 10) {
				loginScreenPrint("That username is already in use.",
						"Wait 60 seconds then retry");
				return;
			}
			if (loginResponse == 11) {
				loginScreenPrint("Account temporarily disabled.", "");
				return;
			}
			if (loginResponse == 12) {
				loginScreenPrint("Account permanently disabled.", "");
				return;
			}
			if (loginResponse == 14) {
				loginScreenPrint("Sorry! This world is currently full.",
						"Please try a different world");
				socketTimeout = 1500;
				return;
			}
			if (loginResponse == 15) {
				loginScreenPrint("You need a members account",
						"to login to this world");
				return;
			}
			if (loginResponse == 16) {
				loginScreenPrint("Error - no reply from loginserver.",
						"Please try again");
				return;
			}
			if (loginResponse == 17) {
				loginScreenPrint("Error - failed to decode profile.",
						"Contact customer support");
				return;
			}
			if (loginResponse == 20) {
				loginScreenPrint("Error - loginserver mismatch",
						"Please try a different world");
				return;
			} else {
				loginScreenPrint("Error unable to login.",
						"Unrecognised response code");
				return;
			}
		} catch (Exception exception) {
			System.out.println(String.valueOf(exception));
		}
		if (reconnectTries > 0) {
			try {
				Thread.sleep(2500L);
			} catch (Exception _ex) {
			}
			reconnectTries--;
			login(username, password, reconnecting);
		}
		if (reconnecting) {
			username = "";
			password = "";
			resetIntVars();
		} else {
			loginScreenPrint("Sorry! Unable to connect.",
					"Check internet settings or try another world");
		}
	}

	protected void loginScreenPrint(String s, String s1) {
	}

	protected void lostConnection() {
		System.out.println("Lost connection");
		reconnectTries = 10;
		login(username, password, true);
	}

	protected final void removeFromFriends(long l) {
		streamClass.createPacket(99);
		streamClass.addLong(l);
		streamClass.formatPacket();
		for (int i = 0; i < friendsCount; i++) {
			if (friendsListLongs[i] != l) {
				continue;
			}
			friendsCount--;
			for (int j = i; j < friendsCount; j++) {
				friendsListLongs[j] = friendsListLongs[j + 1];
				friendsListOnlineStatus[j] = friendsListOnlineStatus[j + 1];
			}

			break;
		}

		handleServerMessage("@pri@" + Utility.longToString(l)
				+ " has been removed from your friends list");
	}

	protected final void removeFromIgnoreList(long l) {
		streamClass.createPacket(173);
		streamClass.addLong(l);
		streamClass.formatPacket();
		for (int i = 0; i < ignoreListCount; i++) {
			if (ignoreListLongs[i] == l) {
				ignoreListCount--;
				for (int j = i; j < ignoreListCount; j++) {
					ignoreListLongs[j] = ignoreListLongs[j + 1];
				}

				return;
			}
		}

	}

	private final void reOrderFriendsListByOnlineStatus() {
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < friendsCount - 1; i++) {
				if (friendsListOnlineStatus[i] < friendsListOnlineStatus[i + 1]) {
					int j = friendsListOnlineStatus[i];
					friendsListOnlineStatus[i] = friendsListOnlineStatus[i + 1];
					friendsListOnlineStatus[i + 1] = j;
					long l = friendsListLongs[i];
					friendsListLongs[i] = friendsListLongs[i + 1];
					friendsListLongs[i + 1] = l;
					flag = true;
				}
			}

		}
	}

	protected void resetIntVars() {
	}

	protected void resetVars() {
	}

	protected final void sendChatMessage(byte abyte0[], int i) {
		streamClass.createPacket(174);
		streamClass.addBytes(abyte0, 0, i);
		streamClass.formatPacket();
	}

	protected final void sendCommand(String s) {
		streamClass.createPacket(120);
		streamClass.addString(s);
		streamClass.formatPacket();
	}

	protected final void sendLogoutPacket() {
		if (streamClass != null) {
			try {
				streamClass.createPacket(82);
				streamClass.formatAndSendPacket();
			} catch (IOException _ex) {
			}
		}
		username = "";
		password = "";
		resetIntVars();
	}

	protected final void sendPingPacketReadPacketData() {
		long l = System.currentTimeMillis();
		if (streamClass.containsData()) {
			lastPing = l;
		}
		if (l - lastPing > 5000L) {
			lastPing = l;
			streamClass.createPacket(5);
			streamClass.formatPacket();
			mudclient.PING_SENT = System.nanoTime();
		}
		try {
			streamClass.sendPacket(20);
		} catch (IOException _ex) {
			lostConnection();
			return;
		}
		int packetLength = streamClass.readPacket(packetData);
		if (packetLength > 0) {
			int id = packetData[0] & 0xFF;
//			if(streamClass.isaac != null) {
//				byte b = streamClass.isaac.decoder.nextBytes(1)[0];
//				int aux = b < 0 ? b+256 : b;
//				id ^= aux;
//				id &= 0xFF;
//			}
			checkIncomingPacket(id, packetLength);
			// checkIncomingPacket(streamClass.decryptIncomingCommand(newData[0],
			// offset), newData);
		}
	}

	protected final void sendPrivateMessage(long user, byte message[],
			int messageLength) {
		streamClass.createPacket(192);
		streamClass.addLong(user);
		streamClass.addBytes(message, 0, messageLength);
		streamClass.formatPacket();
	}

	protected final void sendUpdatedPrivacyInfo(int chatMessages,
			int privateMessages, int tradeRequests, int duelRequests) {
		streamClass.createPacket(191);
		streamClass.addByte(chatMessages);
		streamClass.addByte(privateMessages);
		streamClass.addByte(tradeRequests);
		streamClass.addByte(duelRequests);
		streamClass.formatPacket();
	}
}

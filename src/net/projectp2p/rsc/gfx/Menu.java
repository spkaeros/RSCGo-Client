package net.projectp2p.rsc.gfx;

public class Menu {

	public static boolean aBoolean220 = true;

	public static int anInt221;

	public static int anInt225;

	public static int blueModifier = 176;

	public static int greeModifier = 114;

	public static int redModifier = 114;

	public boolean aBooleanArray184[];

	public boolean aBooleanArray185[];

	int anInt207;

	int anInt208;

	int anInt209;

	int anInt210;

	int anInt211;

	int anInt212;

	int anInt213;

	int anInt214;

	int anInt215;

	int anInt216;

	int anInt217;

	int anInt218;

	public int anIntArray187[];

	public int anIntArray189[];

	public int anIntArray190[];

	int currentFocusHandle;

	protected Surface gameImage;

	int handleMaxTextLength[];

	int lastMouseButton;

	String menuListText[][];

	public int menuListTextCount[];

	public boolean menuObjectCanAcceptActions[];

	boolean menuObjectColorMask[];

	int menuObjectCount;

	public boolean menuObjectHasAction[];

	int menuObjectHeight[];

	String menuObjectText[];

	int menuObjectTextType[];

	int menuObjectType[];

	int menuObjectWidth[];

	int menuObjectX[];

	int menuObjectY[];

	int mouseButton;

	int mouseClicksConsecutive;
	int mouseX;
	int mouseY;
	public boolean redStringColor;
	int unusedConstructorInt;

	public Menu(Surface gi, int i) {
		currentFocusHandle = -1;
		redStringColor = true;
		gameImage = gi;
		unusedConstructorInt = i;
		menuObjectCanAcceptActions = new boolean[i];
		aBooleanArray184 = new boolean[i];
		aBooleanArray185 = new boolean[i];
		menuObjectHasAction = new boolean[i];
		menuObjectColorMask = new boolean[i];
		anIntArray187 = new int[i];
		menuListTextCount = new int[i];
		anIntArray189 = new int[i];
		anIntArray190 = new int[i];
		menuObjectX = new int[i];
		menuObjectY = new int[i];
		menuObjectType = new int[i];
		menuObjectWidth = new int[i];
		menuObjectHeight = new int[i];
		handleMaxTextLength = new int[i];
		menuObjectTextType = new int[i];
		menuObjectText = new String[i];
		menuListText = new String[i][];
		anInt207 = convertRGBToLongWithModifier(114, 114, 176);
		anInt208 = convertRGBToLongWithModifier(14, 14, 62);
		anInt209 = convertRGBToLongWithModifier(200, 208, 232);
		anInt210 = convertRGBToLongWithModifier(96, 129, 184);
		anInt211 = convertRGBToLongWithModifier(53, 95, 115);
		anInt212 = convertRGBToLongWithModifier(117, 142, 171);
		anInt213 = convertRGBToLongWithModifier(98, 122, 158);
		anInt214 = convertRGBToLongWithModifier(86, 100, 136);
		anInt215 = convertRGBToLongWithModifier(135, 146, 179);
		anInt216 = convertRGBToLongWithModifier(97, 112, 151);
		anInt217 = convertRGBToLongWithModifier(88, 102, 136);
		anInt218 = convertRGBToLongWithModifier(84, 93, 120);
	}

	public void addString(int i, String s, boolean flag) {
		int j = menuListTextCount[i]++;
		if (j >= handleMaxTextLength[i]) {
			j--;
			menuListTextCount[i]--;
			for (int k = 0; k < j; k++)
				menuListText[i][k] = menuListText[i][k + 1];

		}
		menuListText[i][j] = s;
		if (flag)
			anIntArray187[i] = 0xf423f;
	}

	public int convertRGBToLongWithModifier(int red, int green, int blue) {
		return Surface.convertRGBToLong((redModifier * red) / 114,
				(greeModifier * green) / 114, (blueModifier * blue) / 176);
	}

	public int drawBox(int i, int j, int k, int l) {
		menuObjectType[menuObjectCount] = 2;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectX[menuObjectCount] = i - k / 2;
		menuObjectY[menuObjectCount] = j - l / 2;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		return menuObjectCount++;
	}

	public void drawMenu() {
		for (int menuObject = 0; menuObject < menuObjectCount; menuObject++)
			if (menuObjectCanAcceptActions[menuObject])
				if (menuObjectType[menuObject] == 0)
					drawTextAddHeight(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectText[menuObject],
							menuObjectTextType[menuObject]);
				else if (menuObjectType[menuObject] == 1)
					drawTextAddHeight(
							menuObject,
							menuObjectX[menuObject]
									- gameImage.stringWidth(
											menuObjectText[menuObject],
											menuObjectTextType[menuObject]) / 2,
							menuObjectY[menuObject],
							menuObjectText[menuObject],
							menuObjectTextType[menuObject]);
				else if (menuObjectType[menuObject] == 2)
					method146(menuObjectX[menuObject], menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject]);
				else if (menuObjectType[menuObject] == 3)
					method149(menuObjectX[menuObject], menuObjectY[menuObject],
							menuObjectWidth[menuObject]);
				else if (menuObjectType[menuObject] == 4)
					method150(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject],
							menuObjectTextType[menuObject],
							menuListText[menuObject],
							menuListTextCount[menuObject],
							anIntArray187[menuObject]);
				else if (menuObjectType[menuObject] == 5
						|| menuObjectType[menuObject] == 6)
					method145(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject],
							menuObjectText[menuObject],
							menuObjectTextType[menuObject]);
				else if (menuObjectType[menuObject] == 7)
					method152(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectTextType[menuObject],
							menuListText[menuObject]);
				else if (menuObjectType[menuObject] == 8)
					method153(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectTextType[menuObject],
							menuListText[menuObject]);
				else if (menuObjectType[menuObject] == 9)
					scrollableMenu(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject],
							menuObjectTextType[menuObject],
							menuListText[menuObject],
							menuListTextCount[menuObject],
							anIntArray187[menuObject]);
				else if (menuObjectType[menuObject] == 11)
					method147(menuObjectX[menuObject], menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject]);
				else if (menuObjectType[menuObject] == 12)
					method148(menuObjectX[menuObject], menuObjectY[menuObject],
							menuObjectTextType[menuObject]);
				else if (menuObjectType[menuObject] == 14)
					method142(menuObject, menuObjectX[menuObject],
							menuObjectY[menuObject],
							menuObjectWidth[menuObject],
							menuObjectHeight[menuObject]);

		lastMouseButton = 0;
	}

	public void drawMenuListText(int menuHandle, int index, String text) {
		menuListText[menuHandle][index] = text;
		if (index + 1 > menuListTextCount[menuHandle])
			menuListTextCount[menuHandle] = index + 1;
	}

	public int drawText(int x, int y, String s, int type, boolean flag) {
		menuObjectType[menuObjectCount] = 1;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectTextType[menuObjectCount] = type;
		menuObjectColorMask[menuObjectCount] = flag;
		menuObjectX[menuObjectCount] = x;
		menuObjectY[menuObjectCount] = y;
		menuObjectText[menuObjectCount] = s;
		return menuObjectCount++;
	}

	protected void drawTextAddHeight(int menuObject, int x, int y, String text,
			int type) {
		int i1 = y + gameImage.stringHeight(type) / 3;
		drawTextWithMask(menuObject, x, i1, text, type);
	}

	protected void drawTextWithMask(int menuObject, int x, int y, String text,
			int type) {
		int color;
		if (menuObjectColorMask[menuObject])
			color = 0xffffff;
		else
			color = 0;
		gameImage.drawString(text, x, y, type, color);
	}

	public int getMenuIndex(int i) {
		return anIntArray187[i];
	}

	public String getText(int i) {
		if (menuObjectText[i] == null)
			return "null";
		else
			return menuObjectText[i];
	}

	public boolean hasActivated(int i) {
		if (menuObjectCanAcceptActions[i] && menuObjectHasAction[i]) {
			menuObjectHasAction[i] = false;
			return true;
		} else {
			return false;
		}
	}

	public void keyDown(int key, char keyChar) {
		if (key == 0)
			return;
		if ((this.currentFocusHandle != -1)
				&& (this.menuObjectText[this.currentFocusHandle] != null)
				&& (this.menuObjectCanAcceptActions[this.currentFocusHandle])) {
			int textLength = this.menuObjectText[this.currentFocusHandle]
					.length();
			if ((key == 8) && (textLength > 0))
				this.menuObjectText[this.currentFocusHandle] = this.menuObjectText[this.currentFocusHandle]
						.substring(0, textLength - 1);
			if (((key == 10) || (key == 13)) && (textLength > 0))
				this.menuObjectHasAction[this.currentFocusHandle] = true;
			String validCharSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
			if (textLength < this.handleMaxTextLength[this.currentFocusHandle]) {
				for (int k = 0; k < validCharSet.length(); k++)
					if (keyChar == validCharSet.charAt(k)) {
						int tmp153_150 = this.currentFocusHandle;
						String[] tmp153_146 = this.menuObjectText;
						tmp153_146[tmp153_150] = (tmp153_146[tmp153_150] + keyChar);
					}
			}
			if (key == 9)
				do
					this.currentFocusHandle = ((this.currentFocusHandle + 1) % this.menuObjectCount);
				while ((this.menuObjectType[this.currentFocusHandle] != 5)
						&& (this.menuObjectType[this.currentFocusHandle] != 6));
		}
	}

	public int makeButton(int i, int j, int k, int l) {
		menuObjectType[menuObjectCount] = 10;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectX[menuObjectCount] = i - k / 2;
		menuObjectY[menuObjectCount] = j - l / 2;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		return menuObjectCount++;
	}

	public int makeTextBox(int i, int j, int k, int l, int i1, int j1,
			boolean flag, boolean flag1) {
		menuObjectType[menuObjectCount] = 6;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		aBooleanArray185[menuObjectCount] = flag;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectTextType[menuObjectCount] = i1;
		menuObjectColorMask[menuObjectCount] = flag1;
		menuObjectX[menuObjectCount] = i;
		menuObjectY[menuObjectCount] = j;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		handleMaxTextLength[menuObjectCount] = j1;
		menuObjectText[menuObjectCount] = "";
		return menuObjectCount++;
	}

	protected void method142(int i, int j, int k, int l, int i1) {
		gameImage.drawBox(j, k, l, i1, 0xffffff);
		gameImage.drawLineX(j, k, l, anInt215);
		gameImage.drawLineY(j, k, i1, anInt215);
		gameImage.drawLineX(j, (k + i1) - 1, l, anInt218);
		gameImage.drawLineY((j + l) - 1, k, i1, anInt218);
		if (anIntArray189[i] == 1) {
			for (int j1 = 0; j1 < i1; j1++) {
				gameImage.drawLineX(j + j1, k + j1, 1, 0);
				gameImage.drawLineX((j + l) - 1 - j1, k + j1, 1, 0);
			}

		}
	}

	protected void method145(int i, int j, int k, int l, int i1, String s,
			int j1) {
		if (aBooleanArray185[i]) {
			int k1 = s.length();
			s = "";
			for (int i2 = 0; i2 < k1; i2++)
				s = s + "X";

		}
		if (menuObjectType[i] == 5) {
			if (lastMouseButton == 1 && mouseX >= j && mouseY >= k - i1 / 2
					&& mouseX <= j + l && mouseY <= k + i1 / 2)
				currentFocusHandle = i;
		} else if (menuObjectType[i] == 6) {
			if (lastMouseButton == 1 && mouseX >= j - l / 2
					&& mouseY >= k - i1 / 2 && mouseX <= j + l / 2
					&& mouseY <= k + i1 / 2)
				currentFocusHandle = i;
			j -= gameImage.stringWidth(s, j1) / 2;
		}
		if (currentFocusHandle == i)
			s = s + "*";
		int l1 = k + gameImage.stringHeight(j1) / 3;
		drawTextWithMask(i, j, l1, s, j1);
	}

	public void method146(int i, int j, int k, int l) {
		gameImage.setBounds(i, j, i + k, j + l);
		gameImage.drawGradient(i, j, k, l, anInt218, anInt215);
		if (aBoolean220) {
			for (int i1 = i - (j & 0x3f); i1 < i + k; i1 += 128) {
				for (int j1 = j - (j & 0x1f); j1 < j + l; j1 += 128)
					gameImage.fade(i1, j1, 6 + anInt221, 128);

			}

		}
		gameImage.drawLineX(i, j, k, anInt215);
		gameImage.drawLineX(i + 1, j + 1, k - 2, anInt215);
		gameImage.drawLineX(i + 2, j + 2, k - 4, anInt216);
		gameImage.drawLineY(i, j, l, anInt215);
		gameImage.drawLineY(i + 1, j + 1, l - 2, anInt215);
		gameImage.drawLineY(i + 2, j + 2, l - 4, anInt216);
		gameImage.drawLineX(i, (j + l) - 1, k, anInt218);
		gameImage.drawLineX(i + 1, (j + l) - 2, k - 2, anInt218);
		gameImage.drawLineX(i + 2, (j + l) - 3, k - 4, anInt217);
		gameImage.drawLineY((i + k) - 1, j, l, anInt218);
		gameImage.drawLineY((i + k) - 2, j + 1, l - 2, anInt218);
		gameImage.drawLineY((i + k) - 3, j + 2, l - 4, anInt217);
		gameImage.resetDimensions();
	}

	public void method147(int i, int j, int k, int l) {
		gameImage.drawBox(i, j, k, l, 0);
		gameImage.drawBoxEdge(i, j, k, l, anInt212);
		gameImage.drawBoxEdge(i + 1, j + 1, k - 2, l - 2, anInt213);
		gameImage.drawBoxEdge(i + 2, j + 2, k - 4, l - 4, anInt214);
		gameImage.drawSprite(i, j, 2 + anInt221);
		gameImage.drawSprite((i + k) - 7, j, 3 + anInt221);
		gameImage.drawSprite(i, (j + l) - 7, 4 + anInt221);
		gameImage.drawSprite((i + k) - 7, (j + l) - 7, 5 + anInt221);
	}

	protected void method148(int i, int j, int k) {
		gameImage.drawSprite(i, j, k);
	}

	protected void method149(int i, int j, int k) {
		gameImage.drawLineX(i, j, k, 0);
	}

	protected void method150(int i, int j, int k, int l, int i1, int j1,
			String as[], int k1, int l1) {
		int i2 = i1 / gameImage.stringHeight(j1);
		if (l1 > k1 - i2)
			l1 = k1 - i2;
		if (l1 < 0)
			l1 = 0;
		anIntArray187[i] = l1;
		if (i2 < k1) {
			int j2 = (j + l) - 12;
			int l2 = ((i1 - 27) * i2) / k1;
			if (l2 < 6)
				l2 = 6;
			int j3 = ((i1 - 27 - l2) * l1) / (k1 - i2);
			if (mouseButton == 1 && mouseX >= j2 && mouseX <= j2 + 12) {
				if (mouseY > k && mouseY < k + 12 && l1 > 0)
					l1--;
				if (mouseY > (k + i1) - 12 && mouseY < k + i1 && l1 < k1 - i2)
					l1++;
				anIntArray187[i] = l1;
			}
			if (mouseButton == 1
					&& (mouseX >= j2 && mouseX <= j2 + 12 || mouseX >= j2 - 12
							&& mouseX <= j2 + 24 && aBooleanArray184[i])) {
				if (mouseY > k + 12 && mouseY < (k + i1) - 12) {
					aBooleanArray184[i] = true;
					int l3 = mouseY - k - 12 - l2 / 2;
					l1 = (l3 * k1) / (i1 - 24);
					if (l1 > k1 - i2)
						l1 = k1 - i2;
					if (l1 < 0)
						l1 = 0;
					anIntArray187[i] = l1;
				}
			} else {
				aBooleanArray184[i] = false;
			}
			j3 = ((i1 - 27 - l2) * l1) / (k1 - i2);
			method151(j, k, l, i1, j3, l2);
		}
		int k2 = i1 - i2 * gameImage.stringHeight(j1);
		int i3 = k + (gameImage.stringHeight(j1) * 5) / 6 + k2 / 2;
		for (int k3 = l1; k3 < k1; k3++) {
			drawTextWithMask(i, j + 2, i3, as[k3], j1);
			i3 += gameImage.stringHeight(j1) - anInt225;
			if (i3 >= k + i1)
				return;
		}

	}

	protected void method151(int i, int j, int k, int l, int i1, int j1) {
		int k1 = (i + k) - 12;
		gameImage.drawBoxEdge(k1, j, 12, l, 0);
		gameImage.drawSprite(k1 + 1, j + 1, anInt221);
		gameImage.drawSprite(k1 + 1, (j + l) - 12, 1 + anInt221);
		gameImage.drawLineX(k1, j + 13, 12, 0);
		gameImage.drawLineX(k1, (j + l) - 13, 12, 0);
		gameImage.drawGradient(k1 + 1, j + 14, 11, l - 27, anInt207, anInt208);
		gameImage.drawBox(k1 + 3, i1 + j + 14, 7, j1, anInt210);
		gameImage.drawLineY(k1 + 2, i1 + j + 14, j1, anInt209);
		gameImage.drawLineY(k1 + 2 + 8, i1 + j + 14, j1, anInt211);
	}

	protected void method152(int i, int j, int k, int l, String as[]) {
		int i1 = 0;
		int j1 = as.length;
		for (int k1 = 0; k1 < j1; k1++) {
			i1 += gameImage.stringWidth(as[k1], l);
			if (k1 < j1 - 1)
				i1 += gameImage.stringWidth("  ", l);
		}

		int l1 = j - i1 / 2;
		int i2 = k + gameImage.stringHeight(l) / 3;
		for (int j2 = 0; j2 < j1; j2++) {
			int k2;
			if (menuObjectColorMask[i])
				k2 = 0xffffff;
			else
				k2 = 0;
			if (mouseX >= l1 && mouseX <= l1 + gameImage.stringWidth(as[j2], l)
					&& mouseY <= i2 && mouseY > i2 - gameImage.stringHeight(l)) {
				if (menuObjectColorMask[i])
					k2 = 0x808080;
				else
					k2 = 0xffffff;
				if (lastMouseButton == 1) {
					anIntArray189[i] = j2;
					menuObjectHasAction[i] = true;
				}
			}
			if (anIntArray189[i] == j2)
				if (menuObjectColorMask[i])
					k2 = 0xff0000;
				else
					k2 = 0xc00000;
			gameImage.drawString(as[j2], l1, i2, l, k2);
			l1 += gameImage.stringWidth(as[j2] + "  ", l);
		}

	}

	protected void method153(int i, int j, int k, int l, String as[]) {
		int i1 = as.length;
		int j1 = k - (gameImage.stringHeight(l) * (i1 - 1)) / 2;
		for (int k1 = 0; k1 < i1; k1++) {
			int l1;
			if (menuObjectColorMask[i])
				l1 = 0xffffff;
			else
				l1 = 0;
			int i2 = gameImage.stringWidth(as[k1], l);
			if (mouseX >= j - i2 / 2 && mouseX <= j + i2 / 2
					&& mouseY - 2 <= j1
					&& mouseY - 2 > j1 - gameImage.stringHeight(l)) {
				if (menuObjectColorMask[i])
					l1 = 0x808080;
				else
					l1 = 0xffffff;
				if (lastMouseButton == 1) {
					anIntArray189[i] = k1;
					menuObjectHasAction[i] = true;
				}
			}
			if (anIntArray189[i] == k1)
				if (menuObjectColorMask[i])
					l1 = 0xff0000;
				else
					l1 = 0xc00000;
			gameImage.drawString(as[k1], j - i2 / 2, j1, l, l1);
			j1 += gameImage.stringHeight(l);
		}

	}

	public int method157(int i, int j, int k, int l) {
		menuObjectType[menuObjectCount] = 11;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectX[menuObjectCount] = i - k / 2;
		menuObjectY[menuObjectCount] = j - l / 2;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		return menuObjectCount++;
	}

	public int method158(int i, int j, int k) {
		int l = gameImage.imageWidth[k];
		int i1 = gameImage.imageHeight[k];
		menuObjectType[menuObjectCount] = 12;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectX[menuObjectCount] = i - l / 2;
		menuObjectY[menuObjectCount] = j - i1 / 2;
		menuObjectWidth[menuObjectCount] = l;
		menuObjectHeight[menuObjectCount] = i1;
		menuObjectTextType[menuObjectCount] = k;
		return menuObjectCount++;
	}

	public int method159(int i, int j, int k, int l, int i1, int j1,
			boolean flag) {
		menuObjectType[menuObjectCount] = 4;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectX[menuObjectCount] = i;
		menuObjectY[menuObjectCount] = j;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		menuObjectColorMask[menuObjectCount] = flag;
		menuObjectTextType[menuObjectCount] = i1;
		handleMaxTextLength[menuObjectCount] = j1;
		menuListTextCount[menuObjectCount] = 0;
		anIntArray187[menuObjectCount] = 0;
		menuListText[menuObjectCount] = new String[j1];
		return menuObjectCount++;
	}

	public int method160(int i, int j, int k, int l, int i1, int j1,
			boolean flag, boolean flag1) {
		menuObjectType[menuObjectCount] = 5;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		aBooleanArray185[menuObjectCount] = flag;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectTextType[menuObjectCount] = i1;
		menuObjectColorMask[menuObjectCount] = flag1;
		menuObjectX[menuObjectCount] = i;
		menuObjectY[menuObjectCount] = j;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		handleMaxTextLength[menuObjectCount] = j1;
		menuObjectText[menuObjectCount] = "";
		return menuObjectCount++;
	}

	public int method162(int i, int j, int k, int l, int i1, int j1,
			boolean flag) {
		menuObjectType[menuObjectCount] = 9;
		menuObjectCanAcceptActions[menuObjectCount] = true;
		menuObjectHasAction[menuObjectCount] = false;
		menuObjectTextType[menuObjectCount] = i1;
		menuObjectColorMask[menuObjectCount] = flag;
		menuObjectX[menuObjectCount] = i;
		menuObjectY[menuObjectCount] = j;
		menuObjectWidth[menuObjectCount] = k;
		menuObjectHeight[menuObjectCount] = l;
		handleMaxTextLength[menuObjectCount] = j1;
		menuListText[menuObjectCount] = new String[j1];
		menuListTextCount[menuObjectCount] = 0;
		anIntArray187[menuObjectCount] = 0;
		anIntArray189[menuObjectCount] = -1;
		anIntArray190[menuObjectCount] = -1;
		return menuObjectCount++;
	}

	public void method165(int i) {
		anIntArray187[i] = 0;
		anIntArray190[i] = -1;
	}

	public void method165(int i, int base) {
		anIntArray187[i] = base;
		anIntArray190[i] = -1;
	}

	public void method170(int i) {
		menuObjectCanAcceptActions[i] = true;
	}

	public void method171(int i) {
		menuObjectCanAcceptActions[i] = false;
	}

	public void resetListTextCount(int menuHandle) {
		menuListTextCount[menuHandle] = 0;
	}

	public void resize(int handle, int x, int y, int w, int h) {
		this.menuObjectX[handle] = x;
		this.menuObjectY[handle] = y;
		this.menuObjectWidth[handle] = w;
		this.menuObjectHeight[handle] = h;
	}

	public void scroll(int handle, int i) {
		int limit = menuListTextCount[handle]
				- (menuObjectHeight[handle] / gameImage
						.stringHeight(menuObjectTextType[handle]));
		int diff = Math.abs(limit - anIntArray187[handle]);
		if (i > 0)
			if (diff < i)
				anIntArray187[handle] += diff;
			else
				anIntArray187[handle] += i;
		else if (i < 0 && anIntArray187[handle] > 0)
			if (anIntArray187[handle] < -i)
				anIntArray187[handle] -= anIntArray187[handle];
			else
				anIntArray187[handle] += i;
		return;
	}

	protected void scrollableMenu(int i, int j, int k, int l, int i1, int j1,
			String as[], int k1, int l1) {
		int i2 = i1 / gameImage.stringHeight(j1);
		if (i2 < k1) {
			int j2 = (j + l) - 12;
			int l2 = ((i1 - 27) * i2) / k1;
			if (l2 < 6)
				l2 = 6;
			int j3 = ((i1 - 27 - l2) * l1) / (k1 - i2);
			if (mouseButton == 1 && mouseX >= j2 && mouseX <= j2 + 12) {
				if (mouseY > k && mouseY < k + 12 && l1 > 0)
					l1--;
				if (mouseY > (k + i1) - 12 && mouseY < k + i1 && l1 < k1 - i2)
					l1++;
				anIntArray187[i] = l1;
			}
			if (mouseButton == 1
					&& (mouseX >= j2 && mouseX <= j2 + 12 || mouseX >= j2 - 12
							&& mouseX <= j2 + 24 && aBooleanArray184[i])) {
				if (mouseY > k + 12 && mouseY < (k + i1) - 12) {
					aBooleanArray184[i] = true;
					int l3 = mouseY - k - 12 - l2 / 2;
					l1 = (l3 * k1) / (i1 - 24);
					if (l1 < 0)
						l1 = 0;
					if (l1 > k1 - i2)
						l1 = k1 - i2;
					anIntArray187[i] = l1;
				}
			} else {
				aBooleanArray184[i] = false;
			}
			j3 = ((i1 - 27 - l2) * l1) / (k1 - i2);
			method151(j, k, l, i1, j3, l2);
		} else {
			l1 = 0;
			anIntArray187[i] = 0;
		}
		anIntArray190[i] = -1;
		int k2 = i1 - i2 * gameImage.stringHeight(j1);
		int i3 = k + (gameImage.stringHeight(j1) * 5) / 6 + k2 / 2;
		for (int k3 = l1; k3 < k1; k3++) {
			int i4;
			if (menuObjectColorMask[i])
				i4 = 0xffffff;
			else
				i4 = 0;
			if (mouseX >= j + 2
					&& mouseX <= j + 2 + gameImage.stringWidth(as[k3], j1)
					&& mouseY - 2 <= i3
					&& mouseY - 2 > i3 - gameImage.stringHeight(j1)) {
				if (menuObjectColorMask[i])
					i4 = 0x808080;
				else
					i4 = 0xffffff;
				anIntArray190[i] = k3;
				if (lastMouseButton == 1) {
					anIntArray189[i] = k3;
					menuObjectHasAction[i] = true;
				}
			}
			if (anIntArray189[i] == k3 && redStringColor)
				i4 = 0xff0000;
			gameImage.drawString(as[k3], j + 2, i3, j1, i4);
			i3 += gameImage.stringHeight(j1);
			if (i3 >= k + i1)
				return;
		}

	}

	public int selectedListIndex(int i) {
		int j = anIntArray190[i];
		return j;
	}

	public void setFocus(int i) {
		currentFocusHandle = i;
	}

	public boolean updateActions(int x, int y, int lastMouseDownButton,
			int mouseDownButton) {
		mouseX = x;
		mouseY = y;
		boolean returnVal = false;
		mouseButton = mouseDownButton;
		if (lastMouseDownButton != 0)
			lastMouseButton = lastMouseDownButton;
		if (lastMouseDownButton == 1) {
			for (int menuObject = 0; menuObject < menuObjectCount; menuObject++) {
				if (menuObjectCanAcceptActions[menuObject]
						&& menuObjectType[menuObject] == 10
						&& mouseX >= menuObjectX[menuObject]
						&& mouseY >= menuObjectY[menuObject]
						&& mouseX <= menuObjectX[menuObject]
								+ menuObjectWidth[menuObject]
						&& mouseY <= menuObjectY[menuObject]
								+ menuObjectHeight[menuObject]) {
					menuObjectHasAction[menuObject] = true; // if it's a button
															// and clicked
					returnVal = true;
				}
				if (menuObjectCanAcceptActions[menuObject]
						&& menuObjectType[menuObject] == 14
						&& mouseX >= menuObjectX[menuObject]
						&& mouseY >= menuObjectY[menuObject]
						&& mouseX <= menuObjectX[menuObject]
								+ menuObjectWidth[menuObject]
						&& mouseY <= menuObjectY[menuObject]
								+ menuObjectHeight[menuObject])
					anIntArray189[menuObject] = 1 - anIntArray189[menuObject]; // no
																				// idea
																				// what
																				// this
																				// is,
																				// there
																				// is
																				// no
																				// object
																				// of
																				// type
																				// 14
			}

		}
		if (mouseDownButton == 1)
			mouseClicksConsecutive++;
		else
			mouseClicksConsecutive = 0;
		if (lastMouseDownButton == 1 || mouseClicksConsecutive > 20) {
			for (int j1 = 0; j1 < menuObjectCount; j1++)
				if (menuObjectCanAcceptActions[j1] && menuObjectType[j1] == 15
						&& mouseX >= menuObjectX[j1]
						&& mouseY >= menuObjectY[j1]
						&& mouseX <= menuObjectX[j1] + menuObjectWidth[j1]
						&& mouseY <= menuObjectY[j1] + menuObjectHeight[j1])
					menuObjectHasAction[j1] = true;

			mouseClicksConsecutive -= 5;
		}
		return returnVal;
	}

	public void updateText(int i, String s) {
		menuObjectText[i] = s;
	}

}

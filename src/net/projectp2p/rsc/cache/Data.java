package net.projectp2p.rsc.cache;

import net.projectp2p.rsc.various.Utility;

public class Data {

	public static int animationCharacterColor[];

	public static String animationFileName[];

	public static int animationGenderModels[];

	public static int animationHasA[];

	public static int animationHasF[];

	public static String animationName[];

	public static int animationNumber[];
	public static int anInt139;
	public static int anInt144;
	public static int anInt58;
	public static int anIntArray115[];
	public static int anIntArray116[];
	public static int anIntArray117[];
	public static int roofHeight[];
	public static int roofTexture[];
	public static String aStringArray83[] = new String[5000];
	public static String aStringArray86[] = new String[5000];
	public static String dataFileNames[];
	public static String doorCommand1[];
	public static String doorCommand2[];
	public static String doorDescription[];
	public static int doorModelVar1[];
	public static int doorModelVar2[];
	public static int doorModelVar3[];
	public static String doorName[];
	public static int doorType[];
	public static int doorUnkownVar[];
	public static int integerAnimationCount;
	static byte integerData[];
	static int integerDataOffset;
	public static int integerDoorCount;
	public static int integerItemCount;
	public static int integerNpcCount;
	public static int integerObjectCount;
	public static int integerPrayerCount;
	public static int integerSpellCount;
	public static int itemBasePrice[];
	public static String itemCommand[];
	public static String itemDescription[];
	public static int itemInventoryPicture[];
	public static int itemInventoryPictureCount;
	public static int itemMembers[];
	public static String itemName[];
	public static int itemPictureMask[];
	public static int itemSpecial[];
	public static int itemStackable[];
	public static int itemUnusedArray[];
	public static int itemWieldable[];
	public static int modelCount;
	public static String modelNames[] = new String[5000];
	public static int NPC_COMBAT_MODELS[];
	public static int NPC_WALK_MODELS[];
	public static int npcAnimationCount[][];
	public static int npcAttack[];
	public static int npcAttackable[];
	public static int npcBottomColor[];
	public static int npcHovering[];
	public static int npcCameraArray2[];
	public static int npcCombatSprite[];
	public static String npcCommand[];
	public static int npcDefense[];
	public static String npcDescription[];
	public static int npcHairColor[];
	public static int npcHits[];
	public static String npcName[];
	public static int npcSkinColor[];
	public static int npcStrength[];
	public static int npcTopColor[];
	public static String objectCommand1[];
	public static String objectCommand2[];
	public static String objectDescription[];
	public static int objectGroundItemVar[];
	public static int objectHeight[];
	public static int objectModelIndex[];
	public static String objectName[];
	public static int objectType[];
	public static int objectWidth[];
	public static String prayerDescription[];
	public static int prayerDrainRate[];
	public static String prayerName[];
	public static int prayerRequiredLevel[];
	public static String spellDescription[];
	public static int spellDifferentRuneCount[];
	public static String spellName[];
	public static int spellProjectileCount;
	public static int spellRequiredLevel[];
	public static int spellRequiredRuneCount[][];
	public static int spellRequiredRuneID[][];
	public static int spellType[];
	static byte stringData[];
	static int stringDataOffset;
	public static int textureFileCount;

	public static int convertToModelInteger(String s) {
		if (s.equalsIgnoreCase("na"))
			return 0;
		for (int i = 0; i < modelCount; i++)
			if (modelNames[i].equalsIgnoreCase(s))
				return i;

		modelNames[modelCount++] = s;
		return modelCount - 1;
	}

	public static int load2BytesInteger() {
		int i = Utility.getUnsignedShort(integerData, integerDataOffset);
		integerDataOffset += 2;
		return i;
	}

	public static int load4BytesInteger() {
		int i = Utility.getUnsigned4Bytes(integerData, integerDataOffset);
		integerDataOffset += 4;
		if (i > 0x5f5e0ff)
			i = 0x5f5e0ff - i;
		return i;
	}

	public static int loadByteIncrement() {
		int i = integerData[integerDataOffset] & 0xff;
		integerDataOffset++;
		return i;
	}

	public static void loadData(byte abyte0[], boolean flag) {
		stringData = Utility.loadDataFile("string.dat", 0, abyte0);
		stringDataOffset = 0;
		integerData = Utility.loadDataFile("integer.dat", 0, abyte0);
		integerDataOffset = 0;
		integerItemCount = load2BytesInteger();
		itemName = new String[integerItemCount];
		itemDescription = new String[integerItemCount];
		itemCommand = new String[integerItemCount];
		itemInventoryPicture = new int[integerItemCount];
		itemBasePrice = new int[integerItemCount];
		itemStackable = new int[integerItemCount];
		itemUnusedArray = new int[integerItemCount];
		itemWieldable = new int[integerItemCount];
		itemPictureMask = new int[integerItemCount];
		itemSpecial = new int[integerItemCount];
		itemMembers = new int[integerItemCount];
		for (int i = 0; i < integerItemCount; i++)
			itemName[i] = loadString();

		for (int j = 0; j < integerItemCount; j++)
			itemDescription[j] = loadString();

		for (int k = 0; k < integerItemCount; k++)
			itemCommand[k] = loadString();

		for (int l = 0; l < integerItemCount; l++) {
			itemInventoryPicture[l] = load2BytesInteger();
			if (itemInventoryPicture[l] + 1 > itemInventoryPictureCount)
				itemInventoryPictureCount = itemInventoryPicture[l] + 1;
		}

		for (int i1 = 0; i1 < integerItemCount; i1++)
			itemBasePrice[i1] = load4BytesInteger();

		for (int j1 = 0; j1 < integerItemCount; j1++)
			itemStackable[j1] = loadByteIncrement();

		for (int k1 = 0; k1 < integerItemCount; k1++)
			itemUnusedArray[k1] = loadByteIncrement();

		for (int l1 = 0; l1 < integerItemCount; l1++)
			itemWieldable[l1] = load2BytesInteger();

		for (int i2 = 0; i2 < integerItemCount; i2++)
			itemPictureMask[i2] = load4BytesInteger();

		for (int j2 = 0; j2 < integerItemCount; j2++)
			itemSpecial[j2] = loadByteIncrement();

		for (int k2 = 0; k2 < integerItemCount; k2++)
			itemMembers[k2] = loadByteIncrement();
		for (int l2 = 0; l2 < integerItemCount; l2++)
			if (!flag && itemMembers[l2] == 1) {
				itemName[l2] = "Members object";
				itemDescription[l2] = "You need to be a member to use this object";
				itemBasePrice[l2] = 0;
				itemCommand[l2] = "";
				itemUnusedArray[0] = 0;
				itemWieldable[l2] = 0;
				itemSpecial[l2] = 1;
			}

		integerNpcCount = load2BytesInteger();
		npcName = new String[integerNpcCount];
		npcDescription = new String[integerNpcCount];
		npcCommand = new String[integerNpcCount];
		npcAttack = new int[integerNpcCount];
		npcStrength = new int[integerNpcCount];
		npcHits = new int[integerNpcCount];
		npcDefense = new int[integerNpcCount];
		npcAttackable = new int[integerNpcCount];
		npcAnimationCount = new int[integerNpcCount][12];
		npcHairColor = new int[integerNpcCount];
		npcTopColor = new int[integerNpcCount];
		npcBottomColor = new int[integerNpcCount];
		npcSkinColor = new int[integerNpcCount];
		npcHovering = new int[integerNpcCount];
		npcCameraArray2 = new int[integerNpcCount];
		NPC_WALK_MODELS = new int[integerNpcCount];
		NPC_COMBAT_MODELS = new int[integerNpcCount];
		npcCombatSprite = new int[integerNpcCount];
		for (int i3 = 0; i3 < integerNpcCount; i3++)
			npcName[i3] = loadString();

		for (int j3 = 0; j3 < integerNpcCount; j3++)
			npcDescription[j3] = loadString();

		for (int k3 = 0; k3 < integerNpcCount; k3++)
			npcAttack[k3] = loadByteIncrement();

		for (int l3 = 0; l3 < integerNpcCount; l3++)
			npcStrength[l3] = loadByteIncrement();

		for (int i4 = 0; i4 < integerNpcCount; i4++)
			npcHits[i4] = loadByteIncrement();

		for (int j4 = 0; j4 < integerNpcCount; j4++)
			npcDefense[j4] = loadByteIncrement();

		for (int k4 = 0; k4 < integerNpcCount; k4++)
			npcAttackable[k4] = loadByteIncrement();

		for (int l4 = 0; l4 < integerNpcCount; l4++) {
			for (int i5 = 0; i5 < 12; i5++) {
				npcAnimationCount[l4][i5] = loadByteIncrement();
				if (npcAnimationCount[l4][i5] == 255)
					npcAnimationCount[l4][i5] = -1;
			}

		}

		for (int j5 = 0; j5 < integerNpcCount; j5++)
			npcHairColor[j5] = load4BytesInteger();

		for (int k5 = 0; k5 < integerNpcCount; k5++)
			npcTopColor[k5] = load4BytesInteger();

		for (int l5 = 0; l5 < integerNpcCount; l5++)
			npcBottomColor[l5] = load4BytesInteger();

		for (int i6 = 0; i6 < integerNpcCount; i6++)
			npcSkinColor[i6] = load4BytesInteger();

		for (int j6 = 0; j6 < integerNpcCount; j6++)
			npcHovering[j6] = load2BytesInteger();

		for (int k6 = 0; k6 < integerNpcCount; k6++)
			npcCameraArray2[k6] = load2BytesInteger();

		for (int l6 = 0; l6 < integerNpcCount; l6++)
			NPC_WALK_MODELS[l6] = loadByteIncrement();

		for (int i7 = 0; i7 < integerNpcCount; i7++)
			NPC_COMBAT_MODELS[i7] = loadByteIncrement();

		for (int j7 = 0; j7 < integerNpcCount; j7++)
			npcCombatSprite[j7] = loadByteIncrement();

		for (int k7 = 0; k7 < integerNpcCount; k7++)
			npcCommand[k7] = loadString();

		textureFileCount = load2BytesInteger();
		dataFileNames = new String[textureFileCount];
		animationFileName = new String[textureFileCount];
		for (int l7 = 0; l7 < textureFileCount; l7++)
			dataFileNames[l7] = loadString();

		for (int i8 = 0; i8 < textureFileCount; i8++)
			animationFileName[i8] = loadString();

		integerAnimationCount = load2BytesInteger();
		animationName = new String[integerAnimationCount];
		animationCharacterColor = new int[integerAnimationCount];
		animationGenderModels = new int[integerAnimationCount];
		animationHasA = new int[integerAnimationCount];
		animationHasF = new int[integerAnimationCount];
		animationNumber = new int[integerAnimationCount];
		for (int j8 = 0; j8 < integerAnimationCount; j8++)
			animationName[j8] = loadString();

		for (int k8 = 0; k8 < integerAnimationCount; k8++)
			animationCharacterColor[k8] = load4BytesInteger();

		for (int l8 = 0; l8 < integerAnimationCount; l8++)
			animationGenderModels[l8] = loadByteIncrement();

		for (int i9 = 0; i9 < integerAnimationCount; i9++)
			animationHasA[i9] = loadByteIncrement();

		for (int j9 = 0; j9 < integerAnimationCount; j9++)
			animationHasF[j9] = loadByteIncrement();

		for (int k9 = 0; k9 < integerAnimationCount; k9++)
			animationNumber[k9] = loadByteIncrement();

		integerObjectCount = load2BytesInteger();
		objectName = new String[integerObjectCount];
		objectDescription = new String[integerObjectCount];
		objectCommand1 = new String[integerObjectCount];
		objectCommand2 = new String[integerObjectCount];
		objectModelIndex = new int[integerObjectCount];
		objectWidth = new int[integerObjectCount];
		objectHeight = new int[integerObjectCount];
		objectType = new int[integerObjectCount];
		objectGroundItemVar = new int[integerObjectCount];
		for (int l9 = 0; l9 < integerObjectCount; l9++)
			objectName[l9] = loadString();

		for (int i10 = 0; i10 < integerObjectCount; i10++)
			objectDescription[i10] = loadString();

		for (int j10 = 0; j10 < integerObjectCount; j10++)
			objectCommand1[j10] = loadString();

		for (int k10 = 0; k10 < integerObjectCount; k10++)
			objectCommand2[k10] = loadString();

		for (int l10 = 0; l10 < integerObjectCount; l10++)
			objectModelIndex[l10] = convertToModelInteger(loadString());

		for (int i11 = 0; i11 < integerObjectCount; i11++)
			objectWidth[i11] = loadByteIncrement();

		for (int j11 = 0; j11 < integerObjectCount; j11++)
			objectHeight[j11] = loadByteIncrement();

		for (int k11 = 0; k11 < integerObjectCount; k11++)
			objectType[k11] = loadByteIncrement();

		for (int l11 = 0; l11 < integerObjectCount; l11++)
			objectGroundItemVar[l11] = loadByteIncrement();

		integerDoorCount = load2BytesInteger();
		doorName = new String[integerDoorCount];
		doorDescription = new String[integerDoorCount];
		doorCommand1 = new String[integerDoorCount];
		doorCommand2 = new String[integerDoorCount];
		doorModelVar1 = new int[integerDoorCount];
		doorModelVar2 = new int[integerDoorCount];
		doorModelVar3 = new int[integerDoorCount];
		doorType = new int[integerDoorCount];
		doorUnkownVar = new int[integerDoorCount];
		for (int i12 = 0; i12 < integerDoorCount; i12++)
			doorName[i12] = loadString();

		for (int j12 = 0; j12 < integerDoorCount; j12++)
			doorDescription[j12] = loadString();

		for (int k12 = 0; k12 < integerDoorCount; k12++)
			doorCommand1[k12] = loadString();

		for (int l12 = 0; l12 < integerDoorCount; l12++)
			doorCommand2[l12] = loadString();

		for (int i13 = 0; i13 < integerDoorCount; i13++)
			doorModelVar1[i13] = load2BytesInteger();

		for (int j13 = 0; j13 < integerDoorCount; j13++)
			doorModelVar2[j13] = load4BytesInteger();

		for (int k13 = 0; k13 < integerDoorCount; k13++)
			doorModelVar3[k13] = load4BytesInteger();

		for (int l13 = 0; l13 < integerDoorCount; l13++)
			doorType[l13] = loadByteIncrement();

		for (int i14 = 0; i14 < integerDoorCount; i14++)
			doorUnkownVar[i14] = loadByteIncrement();

		anInt139 = load2BytesInteger();
		roofHeight = new int[anInt139];
		roofTexture = new int[anInt139];
		for (int j14 = 0; j14 < anInt139; j14++)
			roofHeight[j14] = loadByteIncrement();

		for (int k14 = 0; k14 < anInt139; k14++)
			roofTexture[k14] = loadByteIncrement();

		anInt58 = load2BytesInteger();
		anIntArray115 = new int[anInt58];
		anIntArray116 = new int[anInt58];
		anIntArray117 = new int[anInt58];
		for (int l14 = 0; l14 < anInt58; l14++)
			anIntArray115[l14] = load4BytesInteger();

		for (int i15 = 0; i15 < anInt58; i15++)
			anIntArray116[i15] = loadByteIncrement();

		for (int j15 = 0; j15 < anInt58; j15++)
			anIntArray117[j15] = loadByteIncrement();

		spellProjectileCount = load2BytesInteger();
		integerSpellCount = load2BytesInteger();
		spellName = new String[integerSpellCount];
		spellDescription = new String[integerSpellCount];
		spellRequiredLevel = new int[integerSpellCount];
		spellDifferentRuneCount = new int[integerSpellCount];
		spellType = new int[integerSpellCount];
		spellRequiredRuneID = new int[integerSpellCount][];
		spellRequiredRuneCount = new int[integerSpellCount][];
		for (int k15 = 0; k15 < integerSpellCount; k15++)
			spellName[k15] = loadString();

		for (int l15 = 0; l15 < integerSpellCount; l15++)
			spellDescription[l15] = loadString();

		for (int i16 = 0; i16 < integerSpellCount; i16++)
			spellRequiredLevel[i16] = loadByteIncrement();

		for (int j16 = 0; j16 < integerSpellCount; j16++)
			spellDifferentRuneCount[j16] = loadByteIncrement();

		for (int k16 = 0; k16 < integerSpellCount; k16++)
			spellType[k16] = loadByteIncrement();

		for (int l16 = 0; l16 < integerSpellCount; l16++) {
			int i17 = loadByteIncrement();
			spellRequiredRuneID[l16] = new int[i17];
			for (int k17 = 0; k17 < i17; k17++)
				spellRequiredRuneID[l16][k17] = load2BytesInteger();

		}

		for (int j17 = 0; j17 < integerSpellCount; j17++) {
			int l17 = loadByteIncrement();
			spellRequiredRuneCount[j17] = new int[l17];
			for (int j18 = 0; j18 < l17; j18++)
				spellRequiredRuneCount[j17][j18] = loadByteIncrement();

		}

		integerPrayerCount = load2BytesInteger();
		prayerName = new String[integerPrayerCount];
		prayerDescription = new String[integerPrayerCount];
		prayerRequiredLevel = new int[integerPrayerCount];
		prayerDrainRate = new int[integerPrayerCount];
		for (int i1 = 0; i1 < integerPrayerCount; i1++)
			prayerName[i1] = loadString();

		for (int i2 = 0; i2 < integerPrayerCount; i2++)
			prayerDescription[i2] = loadString();

		for (int i3 = 0; i3 < integerPrayerCount; i3++)
			prayerRequiredLevel[i3] = loadByteIncrement();

		for (int i4 = 0; i4 < integerPrayerCount; i4++)
			prayerDrainRate[i4] = loadByteIncrement();

		stringData = null;
		integerData = null;

		// int id = 192;
		// System.out.println("itemname[" + id + "] = " + itemName[id] + ";");
		// System.out.println("itemPictureMask[" + id + "] = " +
		// itemPictureMask[id] + ";");
		// System.out.println("itemInventoryPicture[" + id + "] = " +
		// itemInventoryPicture[id] + ";");
	}

	public static String loadString() {
		String s;
		for (s = ""; stringData[stringDataOffset] != 0; s = s
				+ (char) stringData[stringDataOffset++])
			;
		stringDataOffset++;
		return s;
	}

}

package net.projectp2p.rsc.io;

import java.io.IOException;

public class PacketConstruction {

	private static final int HEADER_LENGTH = 3;

    public void closeStream() {
    }

	public int readBytes(byte[] dst, int len) throws IOException {
        readInputStream(len, 0, dst);
        return len;
	}

    public void formatPacket() {
		int j = offset - startOffset - 3;
		data[startOffset] = (byte) (j >> 8);
		data[startOffset + 1] = (byte) (j & 0xFF);
		if(maxWriteLen <= 10000) {
            int k = data[startOffset + 2] & 0xFF;
            commandCounter[k]++;
            commandBandwidth[k] += offset - startOffset;
        }
        startOffset = offset;
    }

    public void addByte(int i) {
        data[offset++] = (byte)i;
    }

    public void addLong(long l) {
        addInt((int)(l >> 32));
        addInt((int)(l & -1L));
    }

    public void addInt(int i) {
        data[offset++] = (byte)(i >> 24);
        data[offset++] = (byte)(i >> 16);
        data[offset++] = (byte)(i >> 8);
        data[offset++] = (byte)i;
    }

    public boolean containsData() {
        return startOffset > 0;
    }

    public void addShort(int i) {
        data[offset++] = (byte)(i >> 8);
        data[offset++] = (byte)i;
    }

    public int readByteB() throws IOException {
        return readInputStream();
    }

    public int readPacket(byte abyte0[]) {
        try {
            readDeadlineCounter++;
            if (maxPacketReadCount > 0 && readDeadlineCounter > maxPacketReadCount) {
            	error = true;
                    errorText = "time-out";
                    maxPacketReadCount += maxPacketReadCount;
                    return 0;
            }
            if (length == 0 && inputStreamAvailable() >= 2) {
            		length = (readInputStream() << 8) | readInputStream();
            }
            if (length > 0 && inputStreamAvailable() >= length) {
                    readBytes(abyte0, length);
                    int oldLen = length;
                    length = 0;
                    readDeadlineCounter = 0;
                    return oldLen;
            }
        }
        catch(IOException ioexception) {
            error = true;
            errorText = ioexception.getMessage();
        }
        return 0;
    }

    public void readInputStream(int i, int j, byte abyte0[]) throws IOException {
    }

    public int inputStreamAvailable() throws IOException {
        return 0;
    }

    public void formatAndSendPacket() throws IOException {
        formatPacket();
        sendPacket(0);
    }

    public long readLong() throws IOException {
        long l = readShort();
        long l1 = readShort();
        long l2 = readShort();
        long l3 = readShort();
        return (l << 48) + (l1 << 32) + (l2 << 16) + l3;
    }

    public int readShort() throws IOException {
        int i = readByteB();
        int j = readByteB();
        return i * 256 + j;
    }

    public void addBytes(byte abyte0[], int i, int j) {
        for(int k = 0; k < j; k++)
            data[offset++] = abyte0[i + k];

    }

    public void sendPacket(int i) throws IOException {
        if(error) {
            startOffset = 0;
            offset = 3;
            error = false;
            throw new IOException(errorText);
        }
        writeDeadlineCounter++;
        if(writeDeadlineCounter < i)
            return;
        if(startOffset > 0) {
            writeDeadlineCounter = 0;
            writeToOutputBuffer(data, 0, startOffset);
        }
        startOffset = 0;
        offset = 3;
    }

	public void addString(String s) {
//        s.getBytes(0, s.length(), data, offset);
		System.arraycopy(s.getBytes(), 0, data, offset, s.length());
        offset += s.length();
    }

    public void writeToOutputBuffer(byte abyte0[], int i, int j) throws IOException {
    }

    public void createPacket(int i) {
    // Flush when 4/5ths of the buffer is used?
        if(startOffset > (maxWriteLen * 4) / 5)
            try {
                sendPacket(0);
            }
            catch(IOException ioexception) {
                error = true;
                errorText = ioexception.getMessage();
            }
        if(data == null)
            data = new byte[maxWriteLen];
        if(isaac != null) {
//        	byte b = (byte) (isaac.encoder.nextBytes(1)[0] & 0xFF);
//        	int aux = b < 0 ? b+256 : b;
//        	data[startOffset + 2] = (byte) (i ^ aux);
            data[startOffset + 2] = (byte)i;
        } else 
            data[startOffset + 2] = (byte)i;
        data[startOffset + 3] = 0;
        offset = startOffset + 3;
    }

    public int readInputStream() throws IOException {
        return 0;
    }

    public PacketConstruction() {
        offset = 3;
        anInt532 = 61;
        anInt533 = 59;
        anInt534 = 42;
        anInt535 = 43;
        anInt536 = 44;
        anInt537 = 45;
        anInt538 = 46;
        anInt539 = 47;
        anInt540 = 92;
        anInt541 = 32;
        anInt542 = 124;
        anInt543 = 34;
        errorText = "";
        maxWriteLen = 5000;
        error = false;
    }

	public IsaacContainer isaac;
    public int length;
    public int readDeadlineCounter;
    public int maxPacketReadCount;
    public int startOffset;
    public int offset;
    public byte data[];
    public static int anIntArray531[] = {
        0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 
        1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff, 
        0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 
        0x3fffffff, 0x7fffffff, -1
    };
    public int anInt532 = 61;
    public int anInt533 = 59;
    public int anInt534 = 42;
    public int anInt535 = 43;
    public int anInt536 = 44;
    public int anInt537 = 45;
    public int anInt538 = 46;
    public int anInt539 = 47;
    public int anInt540 = 92;
    public int anInt541 = 32;
    public int anInt542 = 124;
    public int anInt543 = 34;
    public static char aCharArray546[];
    public static int commandCounter[] = new int[256];
    public String errorText;
    public int maxWriteLen;
    public int writeDeadlineCounter;
    public static int commandBandwidth[] = new int[256];
    public boolean error;
    public static int anInt553;

    static  {
        aCharArray546 = new char[256];
        for(int i = 0; i < 256; i++)
            aCharArray546[i] = (char)i;

        aCharArray546[61] = '=';
        aCharArray546[59] = ';';
        aCharArray546[42] = '*';
        aCharArray546[43] = '+';
        aCharArray546[44] = ',';
        aCharArray546[45] = '-';
        aCharArray546[46] = '.';
        aCharArray546[47] = '/';
        aCharArray546[92] = '\\';
        aCharArray546[124] = '|';
        aCharArray546[33] = '!';
        aCharArray546[34] = '"';
    }
}

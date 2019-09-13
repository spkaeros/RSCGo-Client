package net.projectp2p.rsc.io;

public class Isaac {
	private long[] results = new long[256];
	private long[] state = new long[256];
	long[] gold = new long[] { 0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L,
							   0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L, 0x9e3779b97f4a7c13L
							 };
	private int count = 0;
	private long a = 0;
	private long b = 0;
	private long c = 0;

	private void generateNextSet() {
		c++;
		b += c;

		for(int i = 0; i < 256; i++) {
			long x = state[i];
			switch(i % 4) {
			case 0:
				a = ~(a ^ a << 21);
				break;
			case 1:
				a ^= a >>> 5;
				break;
			case 2:
				a ^= a << 12;
				break;
			case 3:
				a ^= a >>> 33;
				break;
			}

			a += state[(i+128) & 0xFF];
			long y = state[(int) (x & 1020) >> 2] + (a ^ b);
			state[i] = y;
			b = (a ^ state[(int) (y >> 8 & 1020) >> 2]) + x;
			results[i] = b;
		}
	}

	private void mix1(int i, long v) {
		gold[i] -= gold[(i+4)%8];
		gold[(i+5)%8] ^= v;
		
		gold[(i+7)%8] += gold[i];
	}

	private void mix() {
		mix1(0, gold[7]>>>9);
		mix1(1, gold[0]<<9);
		mix1(2, gold[1]>>>23);
		mix1(3, gold[2]<<15);
		mix1(4, gold[3]>>>14);
		mix1(5, gold[4]<<20);
		mix1(6, gold[5]>>>17);
		mix1(7, gold[6]<<14);
	}

	private void messify(long[] ia2) {
		for(int i = 0; i < 256; i += 8) {
			for(int i1 = 0; i1 < 8; i1++)
				gold[i1] += ia2[i+i1];
			mix();
			for(int i1 = 0; i1 < 8; i1++)
				state[i+i1] = gold[i1];
		}
	}

	public void randInit() {
		for(int i = 0; i < 4; i++) {
			mix();
		}
		
		messify(results);
		messify(state);

		generateNextSet();
		count = 0;
	}

	public long next() {
		long number = results[count++];
		if(count == 256) {
			generateNextSet();
			count = 0;
		}
		return number;
	}

	public Isaac(long[] seed) {
		int length = seed.length >= 256 ? 256 : seed.length;
		for(int i = 0; i < length; i++)
			results[i] = seed[i];
        for (int j = length; j < 256; j++) {
            long k = results[j - length];
            results[j] = (0x6c078965L * (k ^ k >>> 30) + j & 0xffffffffL);
        }
		randInit();
	}

	private int index = 0;
	private byte[] remainder;

	public byte[] nextBytes(int n) {
		byte[] buf = new byte[n];
		index = 0;
		if(remainder != null && remainder.length > 0) {
			for(int i = 0; i < remainder.length && index < n; i++)
				buf[index++] = remainder[i];
			if(index >= n) {
				int i1 = remainder.length-index;
				byte[] newRem = new byte[i1];
				for(int i = 0; i < i1; i++) {
					newRem[i] = remainder[index+i];
				}
				remainder = newRem;
				return buf;
			}
		}

		remainder = new byte[8];

		java.util.List<Byte> l = new java.util.ArrayList<>();

		while(index < n) {
			long nextInt = next();
			int l1 = 0;
			for(int i = 0; i < 8; i++) {
				if(index >= n) {
					l.add((byte) (nextInt >>> (8*(7-i))));
					continue;
				}
				buf[index++] = (byte) (nextInt >>> (8*(7-i)));
			}
		}
		if(l.size() > 0) {
			remainder = new byte[l.size()];
			int l1 = 0;
			for(Byte b : l)
				remainder[l1++] = b;
//			remainder = l.toArray(remainder);
		}
		return buf;
	}

	public static void main(String[] args) {
		Isaac isaac = new Isaac(new long[] { 0 });
//		Isaac isaac = new Isaac(new long[256]);
		byte[] buf = isaac.nextBytes(10);
		for(byte b : buf) {
			int aux;
			if(b < 0) aux = 256+b;
			else aux=b;
			System.out.print(aux + ", ");
		}
		System.out.println();
		buf = isaac.nextBytes(10);
		for(byte b : buf) {
			int aux;
			if(b < 0) aux = 256+b;
			else aux=b;
			System.out.print(aux + ", ");
		}
		System.out.println();
	}
}
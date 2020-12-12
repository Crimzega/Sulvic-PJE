package com.sulvic.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class EndianInputStream extends InputStream{
	
	private Endian endianness;
	private InputStream streamIn;
	
	public EndianInputStream(InputStream stream){ this(Endian.BIG, stream); }
	
	public EndianInputStream(Endian endian, InputStream stream){
		endianness = endian;
		streamIn = stream;
	}
	
	public final boolean readBoolean() throws IOException{
		int result = read();
		if(result < 0) throw new EOFException();
		return result != 0;
	}
	
	public final byte readByte() throws IOException{
		int result = read();
		if(result < 0) throw new EOFException();
		return (byte)result;
	}
	
	public final double readDouble() throws IOException{ return endianness.readDouble(this); }
	
	public final float readFloat() throws IOException{ return endianness.readFloat(this); }
	
	public final int readInt() throws IOException{ return endianness.readInt(this); }
	
	public final long readLong() throws IOException{ return endianness.readLong(this); }
	
	public final short readShort() throws IOException{ return endianness.readShort(this); }
	
	public final String readUTF() throws IOException{ return HelperUTF.readUTF(this); }
	
	public final int skipBytes(int amount) throws IOException{
		int total = 0, curr = 0;
		while(total < amount && (curr = (int)streamIn.skip(amount - total)) > 0) total += curr;
		return total;
	}
	
	@Override
	public void close() throws IOException{ streamIn.close(); }
	
	@Override
	public int read() throws IOException{ return streamIn.read(); }
	
	@Override
	public int read(byte[] bytes) throws IOException{ return streamIn.read(bytes); }
	
	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException{ return streamIn.read(bytes, offset, length); }
	
}

package com.sulvic.io;

import java.io.IOException;
import java.io.OutputStream;

public class EndianOutputStream extends OutputStream{
	
	private OutputStream streamOut;
	private Endian endianness;
	
	public EndianOutputStream(OutputStream stream){ this(Endian.BIG, stream); }
	
	public EndianOutputStream(Endian endian, OutputStream stream){
		streamOut = stream;
		endianness = endian;
	}
	
	public final void writeBoolean(boolean value) throws IOException{ streamOut.write(value? 1: 0); }
	
	public final void writeByte(byte value) throws IOException{ streamOut.write(value); }
	
	public final void writeChar(char value) throws IOException{
		int code = (int)value;
		streamOut.write((code >>> 8) & 0xFF);
		streamOut.write((code >>> 0) & 0xFF);
	}
	
	public final void writeChars(String value) throws IOException{
		for(int i = 0; i < value.length(); i++){
			int code = (int)value.charAt(i);
			streamOut.write((code >>> 8) & 0xFF);
			streamOut.write((code >>> 0) & 0xFF);
		}
	}
	
	public final void writeDouble(double value) throws IOException{ streamOut.write(endianness.writeDouble(value)); }
	
	public final void writeFloat(float value) throws IOException{ streamOut.write(endianness.writeFloat(value)); }
	
	public final void writeInt(int value) throws IOException{ streamOut.write(endianness.writeInt(value)); }
	
	public final void writeLong(long value) throws IOException{ streamOut.write(endianness.writeLong(value)); }
	
	public final void writeShort(short value) throws IOException{ streamOut.write(endianness.writeShort(value)); }
	
	public final void writeUTF(String value) throws IOException{ HelperUTF.writeUTF(value, this); }
	
	@Override
	public void close() throws IOException{ streamOut.close(); }
	
	@Override
	public void flush() throws IOException{ streamOut.flush(); }
	
	@Override
	public void write(int bits) throws IOException{ streamOut.write(bits); }
	
	@Override
	public void write(byte[] bytes) throws IOException{ streamOut.write(bytes); }
	
	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException{ streamOut.write(bytes, offset, length); }
	
}

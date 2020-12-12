package com.sulvic.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public enum Endian{
	
	BIG,
	LITTLE;
	
	private byte[] orientBytes(byte[] bytes){
		byte[] result = bytes;
		if(this == LITTLE){
			int size = bytes.length;
			byte[] temp = new byte[size];
			for(int i = 0; i < size; i++) temp[i] = bytes[size - i - 1];
			result = temp;
		}
		return result;
	}
	
	private byte[] getBytes(long value, int size){
		byte[] result = new byte[size];
		for(int i = 0; i < size; i++) result[size - (i + 1)] = (byte)(value >> (i * 8));
		return result;
	}
	
	private byte[] getBytes(InputStream stream, int size) throws IOException{
		byte[] result = new byte[size];
		int[] temp = new int[size];
		for(int i = 0; i < size; i++) temp[i] = stream.read();
		int check = temp[0];
		for(int i = 1; i < size; i++) check |= temp[i];
		if(check < 0) throw new EOFException();
		for(int i = 0; i < size; i++) result[i] = (byte)temp[i];
		return orientBytes(result);
	}
	
	private long getValue(byte[] bytes){
		long result = 0;
		int size = bytes.length;
		for(int i = 0; i < size; i++){
			long temp = (long)(i == 0? bytes[i]: bytes[i] & 0xFF);
			int shift = (size - i - 1) * 8;
			result += temp << shift;
		}
		return result;
	}
	
	public byte[] writeDouble(double value){ return writeLong(Double.doubleToLongBits(value)); }
	
	public byte[] writeFloat(float value){ return writeInt(Float.floatToIntBits(value)); }
	
	public byte[] writeInt(int value){ return orientBytes(getBytes((long)value, 4)); }
	
	public byte[] writeLong(long value){ return orientBytes(getBytes(value, 8)); }
	
	public byte[] writeShort(short value){ return orientBytes(getBytes(value, 2)); }
	
	public double readDouble(InputStream stream) throws IOException{ return Double.longBitsToDouble(readLong(stream)); }
	
	public float readFloat(InputStream stream) throws IOException{ return Float.intBitsToFloat(readInt(stream)); }
	
	public int readInt(InputStream stream) throws IOException{ return (int)getValue(getBytes(stream, 4)); }
	
	public long readLong(InputStream stream) throws IOException{ return getValue(getBytes(stream, 8)); }
	
	public short readShort(InputStream stream) throws IOException{ return (short)getValue(getBytes(stream, 2)); }
	
}

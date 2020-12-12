package com.sulvic.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

public class HelperUTF{
	
	public static String readUTF(InputStream stream) throws IOException{
		int utf = ((stream.read() << 8) + (stream.read() << 0)) & 0xFFFF;
		byte[] bytes = new byte[utf];
		char[] chars = new char[utf];
		stream.read(bytes, 0, utf);
		int ch, ch1, ch2;
		int count = 0, charsCount = 0;
		while(count < utf){
			ch = (int)bytes[count] & 0xFF;
			if(ch > 0x7F) break;
			count++;
			chars[charsCount] = (char)ch;
		}
		while(count < utf){
			ch = (int)bytes[count] & 0xFF;
			switch(ch >> 4){
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
					count++;
					chars[charsCount++] = (char)ch;
				break;
				case 12:
				case 13:
					count += 2;
					if(count > utf) throw new UTFDataFormatException("Malformed input: Partial character at end");
					ch1 = (int)bytes[count - 1];
					if((ch1 & 0xC0) != 0x80) throw new UTFDataFormatException("Malfomed input around byte" + count);
					chars[charsCount++] = (char)(((ch & 0x1F) << 6) | (ch1 & 0x3F));
				break;
				case 14:
					count += 3;
					if(count > utf) throw new UTFDataFormatException("Malformed input: Partial character at end");
					ch1 = (int)bytes[count - 2];
					ch2 = (int)bytes[count - 1];
					if(((ch1 & 0xC0) != 0x80) || ((ch2 & 0xC0) != 0x80)) throw new UTFDataFormatException("Malformed input around byte " + (count - 1));
					chars[charsCount++] = (char)(((ch & 0x0F) << 12) | ((ch1 & 0x3F) << 6) | ((ch2 & 0x3F) << 0));
				break;
				default:
					throw new UTFDataFormatException("Malformed input around byte" + count);
			}
		}
		return new String(chars, 0, charsCount);
	}
	
	public static int writeUTF(String value, OutputStream stream) throws IOException{
		int size = value.length();
		int utf = 0;
		int code, count = 0;
		for(int i = 0; i < size; i++){
			code = value.charAt(i);
			utf += ((code >= 0x0001) && (code <= 0x007F))? 1: (code > 0x07FF)? 3: 2;
		}
		if(utf > 0xFFFF) throw new UTFDataFormatException("Encoded string too long: " + utf + " bytes");
		byte[] bytes = new byte[utf + 2];
		bytes[count++] = (byte)((utf >>> 8) & 0xFF);
		bytes[count++] = (byte)((utf >>> 0) & 0xFF);
		int i = 0;
		for(i = 0; i < size; i++){
			code = value.charAt(i);
			if(!((code >= 0x0001) && (code <= 0x007F))) break;
			bytes[count++] = (byte)code;
		}
		for(; i < size; i++){
			code = value.charAt(i);
			if((code >= 0x0001) && (code <= 0x007F)) bytes[count++] = (byte)code;
			else if(code > 0x07FF){
				bytes[count++] = (byte)(0xE0 | ((code >> 12) & 0x0F));
				bytes[count++] = (byte)(0x80 | ((code >> 6) & 0x3F));
				bytes[count++] = (byte)(0x80 | ((code >> 0) & 0x3F));
			}
			else{
				bytes[count++] = (byte)(0xC0 | ((code >> 6) & 0x1F));
				bytes[count++] = (byte)(0x80 | ((code >> 0) & 0x3F));
			}
		}
		stream.write(bytes, 0, utf + 2);
		return utf + 2;
	}
	
}

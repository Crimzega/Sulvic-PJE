package com.sulvic.util;

public class SulvicUtils{
	
	private static float getColorFromInt(int value){ return (float)wrapColor(value) / 0xFF; }
	
	private static int wrapColor(int value){ return value & 0xFF; }
	
	private static int shiftFromRGB(int value, int offset){ return wrapColor(value) >> offset; }
	
	private static int shiftToRGB(int value, int offset){ return wrapColor(value) << offset; }
	
	private static int getColorFromFloat(float value){ return wrapColor((int)(value * 0xFF)); }
	
	public static <T extends Enum<?>> T addEnum(Class<T> enumClass, String name, Object... data){ return EnumHelper.addEnum(enumClass, name, data); }
	
	public static boolean classMatches(Class<?> mainClass, Class<?>... classes){
		for(Class<?> aClass: classes) if(mainClass.equals(aClass)) return true;
		return false;
	}
	
	public static boolean stringExists(String str){ return !str.isEmpty() || !str.equals("") || str.length() > 0; }
	
	public static float getBlueAsFloat(int rgb){ return getColorFromInt(rgb); }
	
	public static float getGreenAsFloat(int rgb){ return getColorFromInt(shiftFromRGB(rgb, 8)); }
	
	public static float getRedAsFloat(int rgb){ return getColorFromInt(shiftFromRGB(rgb, 16)); }
	
	public static int getBlueAsInt(float value){ return getColorFromFloat(value); }
	
	public static int getGreenAsInt(float value){ return shiftToRGB(getColorFromFloat(value), 8); }
	
	public static int getRedAsInt(float value){ return shiftToRGB(getColorFromFloat(value), 16); }
	
	public static int getRGBFromFloats(float red, float green, float blue){ return getRedAsInt(red) + getGreenAsInt(green) + getBlueAsInt(blue); }
	
	public static int getRGBFromInts(int red, int green, int blue){ return shiftToRGB(red, 16) + shiftToRGB(green, 8) + wrapColor(blue); }
	
}

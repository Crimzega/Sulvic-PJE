package com.sulvic.util;

import static com.sulvic.util.SulvicMath.clampInt;
import static com.sulvic.util.SulvicMath.rangedInt;

import java.util.Collection;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class SulvicArrays{
	
	public static <T> T[] addContentToArray(T[] array, T... extras){
		T[] result = (T[])new Object[array.length + extras.length];
		for(int i = 0; i < array.length; i++) result[i] = array[i];
		for(int i = 0; i < extras.length; i++) result[array.length + i] = extras[i];
		return result;
	}
	
	public static <T> T[] getArrayFromCollection(Collection<T> collection) throws NullPointerException{
		T[] array = (T[])new Object[collection.size()];
		Iterator<T> iterator = collection.iterator();
		int i = 0;
		while(iterator.hasNext()){
			array[i] = iterator.next();
			i++;
		}
		return array;
		
	}
	
	public static <T> T getRandomValue(T[] array){ return array[rangedInt(0, array.length - 1)]; }
	
	public static <T> T getClampedValue(T[] array, int value){ return array[clampInt(value, 0, array.length - 1)]; }
	
}

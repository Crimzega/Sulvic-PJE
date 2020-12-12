package com.sulvic.lib;

public interface DoubleKeySet<K, K1>{
	
	boolean hasKeys(Object keys);
	
	boolean hasKeys(Object key, Object key1);
	
	K getMainKey();
	
	K1 getSecondKey();
	
}

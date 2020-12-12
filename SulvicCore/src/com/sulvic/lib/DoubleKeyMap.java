package com.sulvic.lib;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public interface DoubleKeyMap<K, K2, V>{
	
	default boolean remove(Object key, Object key2, Object oldValue){
		Object currValue = get(key, key2);
		if(Objects.equals(currValue, oldValue) || (currValue != null && !containsKeys(key, key2))) return false;
		remove(key, key2);
		return true;
	}
	
	default boolean replace(K key, K2 key2, V oldValue, V newValue){
		Object currValue = get(key, key2);
		if(!Objects.equals(currValue, oldValue) || (currValue == null && !containsKeys(key, key2))) return false;
		put(key, key2, newValue);
		return true;
	}
	
	default V getOrDefault(Object key, Object key2, V defaultValue){
		V value;
		return ((value = get(key, key2)) != null || containsKeys(key, key2))? value: defaultValue;
	}
	
	default V putIfAbsent(K key, K2 key2, V value){
		V oldValue = get(key, key2);
		if(oldValue == null) oldValue = put(key, key2, value);
		return oldValue;
	}
	
	default V replace(K key, K2 key2, V value){
		V currValue;
		if((currValue = get(key, key2)) != null || containsKeys(key, key2)) currValue = put(key, key2, value);
		return currValue;
	}
	
	default void forEach(BiConsumer<? super KeySet<K, K2>, ? super V> action){
		Objects.requireNonNull(action);
		for(DoubleKeyMap.Entry<K, K2, V> entry: entrySet()){
			KeySet<K, K2> keys;
			V value;
			try{
				keys = entry.getKeys();
				value = entry.getValue();
			}
			catch(IllegalStateException ise){
				throw new ConcurrentModificationException(ise);
			}
			action.accept(keys, value);
		}
	}
	
	default void replaceAll(BiFunction<? super KeySet<K, K2>, ? super V, ? extends V> function){
		Objects.requireNonNull(function);
		for(DoubleKeyMap.Entry<K, K2, V> entry: entrySet()){
			KeySet<K, K2> keys;
			V value;
			try{
				keys = entry.getKeys();
				value = entry.getValue();
			}
			catch(IllegalStateException ise){
				throw new ConcurrentModificationException(ise);
			}
			value = function.apply(keys, value);
			try{
				entry.setValue(value);
			}
			catch(IllegalStateException ise){
				throw new ConcurrentModificationException(ise);
			}
		}
	}
	
	boolean containsKeySet(Object keySet);
	
	boolean containsKeys(Object key, Object key2);
	
	boolean containsValue(Object value);
	
	boolean equals(Object o);
	
	boolean isEmpty();
	
	Collection<V> values();
	
	int hashCode();
	
	int size();
	
	V get(Object key, Object key2);
	
	V put(K key, K2 key2, V value);
	
	V remove(Object key, Object key2);
	
	Set<KeySet<K, K2>> keysSet();
	
	Set<Entry<K, K2, V>> entrySet();
	
	void clear();
	
	void putAll(DoubleKeyMap<? extends K, ? extends K2, ? extends V> map);
	
	public static interface Entry<K, K2, V>{
		
		boolean equals(Object o);
		
		boolean hasKeys(Object keys);
		
		boolean hasKeys(Object key, Object key2);
		
		boolean hasValue(Object value);
		
		KeySet<K, K2> getKeys();
		
		K getMainKey();
		
		K2 getSecondKey();
		
		int hashCode();
		
		V getValue();
		
		V setValue(V value);
		
	}
	
	public static interface KeySet<K, K2>{
		
		boolean hasKeys(Object key, Object key2);
		
		boolean equals(Object obj);
		
		int hashCode();
		
		K getMainKey();
		
		K2 getSecondKey();
		
	}
	
}

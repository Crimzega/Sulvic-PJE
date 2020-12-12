package com.sulvic.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings({"null", "unchecked"})
public class ArrayDoubleKeyMap<K, K2, V> implements DoubleKeyMap<K, K2, V>{
	
	ArrayDoubleKeyMap.Entry<K, K2, V>[] mapEntries;
	
	public ArrayDoubleKeyMap(){ mapEntries = new ArrayDoubleKeyMap.Entry[0]; }
	
	public void clear(){ mapEntries = new ArrayDoubleKeyMap.Entry[0]; }
	
	public boolean containsKeySet(Object keySet){
		for(ArrayDoubleKeyMap.Entry<K, K2, V> map: mapEntries) if(map.getKeys().equals(keySet)) return true;
		return false;
	}
	
	public boolean containsKeys(Object key, Object key2){
		for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries) if(entry.hasKeys(key, key2)) return true;
		return false;
	}
	
	public boolean containsValue(Object value){
		for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries) if(entry.hasValue(value)) return true;
		return false;
	}
	
	public Set<DoubleKeyMap.Entry<K, K2, V>> entrySet(){
		Set<DoubleKeyMap.Entry<K, K2, V>> set = new HashSet<DoubleKeyMap.Entry<K, K2, V>>();
		for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries) set.add(entry);
		return set;
	}
	
	public boolean equals(Object obj){
		if(obj == this) return true;
		if(!(obj instanceof ArrayDoubleKeyMap)) return false;
		ArrayDoubleKeyMap<K, K2, V> map = (ArrayDoubleKeyMap<K, K2, V>)obj;
		if(map.size() != size()) return false;
		try{
			Iterator<DoubleKeyMap.Entry<K, K2, V>> iterator = entrySet().iterator();
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = (DoubleKeyMap.Entry<K, K2, V>)iterator.next();
				K key = entry.getMainKey();
				K2 key2 = entry.getSecondKey();
				V value = entry.getValue();
				if(value == null) if(!(map.get(key, key2) == null && map.containsKeys(key, key2))) return false;
				else{
					if(!value.equals(map.get(key, key2))) return false;
				}
			}
		}
		catch(ClassCastException cce){
			return false;
		}
		catch(NullPointerException npe){
			return false;
		}
		return true;
	}
	
	public V get(Object key, Object key2){
		V result = null;
		if(containsKeys(key, key2)) for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries){
			if(entry.hasKeys(key, key2)){
				result = entry.getValue();
				break;
			}
		}
		return result;
	}
	
	public int hashCode(){
		int hash = 0;
		Iterator<DoubleKeyMap.Entry<K, K2, V>> iterator = entrySet().iterator();
		while(iterator.hasNext()) hash += iterator.next().hashCode();
		return hash;
	}
	
	public boolean isEmpty(){ return mapEntries.length > 0; }
	
	public Set<DoubleKeyMap.KeySet<K, K2>> keysSet(){
		Set<DoubleKeyMap.KeySet<K, K2>> set = new HashSet<DoubleKeyMap.KeySet<K, K2>>();
		for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries) set.add(entry.getKeys());
		return set;
	}
	
	public V put(K key, K2 key2, V value){
		V oldValue = null;
		if(containsKeys(key, key2)) for(ArrayDoubleKeyMap.Entry<K, K2, V> entry: mapEntries){
			if(entry.hasKeys(key, key2)){
				oldValue = entry.setValue(value);
				break;
			}
		}
		else{
			ArrayDoubleKeyMap.Entry<K, K2, V>[] temp = new ArrayDoubleKeyMap.Entry[mapEntries.length + 1];
			for(int i = 0; i < mapEntries.length; i++) temp[i] = mapEntries[i];
			temp[mapEntries.length] = new ArrayDoubleKeyMap.Entry<K, K2, V>(key, key2, value);
			mapEntries = temp;
		}
		return oldValue;
	}
	
	public V remove(Object key, Object key2){
		V oldValue = null;
		if(containsKeys(key, key2)){
			ArrayDoubleKeyMap.Entry<K, K2, V>[] temp = new ArrayDoubleKeyMap.Entry[mapEntries.length - 1];
			int offset = 0;
			for(int i = 0; i < mapEntries.length; i++){
				ArrayDoubleKeyMap.Entry<K, K2, V> entry = mapEntries[i + offset];
				if(entry.hasKeys(key, key2)){
					oldValue = entry.getValue();
					offset++;
					continue;
				}
				temp[i] = entry;
			}
		}
		return oldValue;
		
	}
	
	public void putAll(DoubleKeyMap<? extends K, ? extends K2, ? extends V> map){
		for(DoubleKeyMap.Entry<? extends K, ? extends K2, ? extends V> entry: map.entrySet()) put(entry.getMainKey(), entry.getSecondKey(), entry.getValue());
	}
	
	public int size(){ return mapEntries.length; }
	
	public Collection<V> values(){
		Collection<V> collection = new ArrayList<V>();
		for(Entry<K, K2, V> entry: mapEntries) collection.add(entry.getValue());
		return collection;
	}
	
	public static class Entry<K, K2, V> implements DoubleKeyMap.Entry<K, K2, V>{
		
		KeySet<K, K2> entryKeys;
		V entryValue;
		
		public Entry(K key, K2 key2, V value){
			entryKeys = key != null && key2 != null? new KeySet<K, K2>(key, key2): (KeySet<K, K2>)null;
			entryValue = value;
		}
		
		public boolean hasKeys(Object keys){ return entryKeys.equals(keys); }
		
		public boolean equals(Object obj){
			if(obj == this) return true;
			if(!(obj instanceof DoubleKeyMap.Entry)) return false;
			DoubleKeyMap.Entry<K, K2, V> entry = (DoubleKeyMap.Entry<K, K2, V>)obj;
			return hasKeys(entry.getKeys()) && entryValue.equals(entry.getValue());
		}
		
		public boolean hasKeys(Object key, Object key2){ return entryKeys.hasKeys(key, key2); }
		
		public boolean hasValue(Object value){ return entryValue.equals(value); }
		
		public DoubleKeyMap.KeySet<K, K2> getKeys(){ return entryKeys; }
		
		public K getMainKey(){ return entryKeys.mainKey; }
		
		public K2 getSecondKey(){ return entryKeys.secondKey; }
		
		public int hashCode(){ return entryKeys.hashCode() + Objects.hashCode(entryValue); }
		
		public V getValue(){ return entryValue; }
		
		public V setValue(V value){
			V oldValue = entryValue;
			entryValue = value;
			return oldValue;
		}
		
	}
	
	public static class KeySet<K, K2> implements DoubleKeyMap.KeySet<K, K2>{
		
		K mainKey;
		K2 secondKey;
		
		public KeySet(K key, K2 key2){
			mainKey = key;
			secondKey = key2;
		}
		
		public boolean equals(Object obj){
			if(obj == this) return true;
			if(!(obj instanceof DoubleKeyMap.KeySet)) return false;
			DoubleKeyMap.KeySet<K, K2> keys = (DoubleKeyMap.KeySet<K, K2>)obj;
			return hasKeys(keys.getMainKey(), keys.getSecondKey());
		}
		
		public boolean hasKeys(Object key, Object key2){ return mainKey.equals(key) && secondKey.equals(key2); }
		
		public K getMainKey(){ return mainKey; }
		
		public K2 getSecondKey(){ return secondKey; }
		
		public int hashCode(){ return Objects.hashCode(mainKey) + Objects.hashCode(secondKey); }
		
	}
	
}

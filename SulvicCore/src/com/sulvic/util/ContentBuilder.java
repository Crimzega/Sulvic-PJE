package com.sulvic.util;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

import com.sulvic.lib.DoubleKeyMap;
import com.sulvic.lib.ArrayDoubleKeyMap;

@SuppressWarnings({"unchecked"})
public class ContentBuilder{
	
	public static <E> ArrayList<E> newArrayList(){ return new ArrayList<E>(); }
	
	public static <E> ArrayList<E> newArrayList(E... array){
		ArrayList<E> list = newArrayList();
		for(E value: array) list.add(value);
		return list;
	}
	
	public static <E> LinkedList<E> newLinkedList(){ return new LinkedList<E>(); }
	
	public static <E> LinkedList<E> newLinkedList(E... array){
		LinkedList<E> list = newLinkedList();
		for(E value: array) list.add(value);
		return list;
	}
	
	public static <E> HashSet<E> newHashSet(){ return new HashSet<E>(); }
	
	public static <E> HashSet<E> newHashSet(E... array){
		HashSet<E> set = newHashSet();
		for(E value: array) set.add(value);
		return set;
	}
	
	public static <E> LinkedHashSet<E> newLinkedHashSet(){ return new LinkedHashSet<E>(); }
	
	public static <E> LinkedHashSet<E> newLinkedHashSet(E... array){
		LinkedHashSet<E> set = newLinkedHashSet();
		for(E value: array) set.add(value);
		return set;
	}
	
	public static <E> Vector<E> newVector(){ return new Vector<E>(); }
	
	public static <E> Vector<E> newVector(E... array){
		Vector<E> vector = newVector();
		for(E value: array) vector.add(value);
		return vector;
	}
	
	public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> keyClass){ return new EnumMap<K, V>(keyClass); }
	
	public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> keyClass, Map.Entry<K, V>... entries){
		EnumMap<K, V> map = newEnumMap(keyClass);
		for(Map.Entry<K, V> entry: entries) map.put(entry.getKey(), entry.getValue());
		return map;
	}
	
	public static <K, V> HashMap<K, V> newHashMap(){ return new HashMap<K, V>(); }
	
	public static <K, V> HashMap<K, V> newHashMap(Map.Entry<K, V>... entries){
		HashMap<K, V> map = newHashMap();
		for(Map.Entry<K, V> entry: entries) map.put(entry.getKey(), entry.getValue());
		return map;
	}
	
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(){ return new LinkedHashMap<K, V>(); }
	
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map.Entry<K, V>... entries){
		LinkedHashMap<K, V> map = newLinkedHashMap();
		for(Map.Entry<K, V> entry: entries) map.put(entry.getKey(), entry.getValue());
		return map;
	}
	
	public static <K, K2, V> ArrayDoubleKeyMap<K, K2, V> newHashDoubleKeyMap(){ return new ArrayDoubleKeyMap<K, K2, V>(); }
	
	public static <K, K2, V> ArrayDoubleKeyMap<K, K2, V> newHashDoubleKeyMap(DoubleKeyMap.Entry<K, K2, V>... entries){
		ArrayDoubleKeyMap<K, K2, V> map = newHashDoubleKeyMap();
		for(DoubleKeyMap.Entry<K, K2, V> entry: entries) map.put(entry.getMainKey(), entry.getSecondKey(), entry.getValue());
		return map;
	}
	
}

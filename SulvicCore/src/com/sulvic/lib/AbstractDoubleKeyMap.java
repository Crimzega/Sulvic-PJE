package com.sulvic.lib;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings({"unchecked"})
public abstract class AbstractDoubleKeyMap<K, K2, V> implements DoubleKeyMap<K, K2, V>{
	
	transient Set<KeySet<K, K2>> keysSet;
	transient Collection<V> values;
	
	public boolean containsKeySet(Object keySet){
		Iterator<Entry<K, K2, V>> iterator = entrySet().iterator();
		if(keySet == null){
			while(iterator.hasNext()){
				Entry<K, K2, V> entry = iterator.next();
				if(entry.getKeys() == null) return true;
			}
		}
		else{
			KeySet<K, K2> set = (KeySet<K, K2>)keySet;
			K key = set.getMainKey();
			K2 key2 = set.getSecondKey();
			if(key == null){
				while(iterator.hasNext()){
					Entry<K, K2, V> entry = iterator.next();
					if(entry.getMainKey() == null && entry.getSecondKey().equals(key2)) return true;
				}
			}
			else if(key2 == null){
				while(iterator.hasNext()){
					Entry<K, K2, V> entry = iterator.next();
					if(entry.getMainKey().equals(key) && entry.getSecondKey() == null) return true;
				}
			}
			else{
				while(iterator.hasNext()){
					Entry<K, K2, V> entry = iterator.next();
					if(entry.getMainKey().equals(key) && entry.getSecondKey().equals(key2)) return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsKeys(Object key, Object key2){
		Iterator<Entry<K, K2, V>> iterator = entrySet().iterator();
		if(key == null && key2 == null){
			while(iterator.hasNext()){
				Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey() == null) return true;
			}
		}
		else if(key == null){
			while(iterator.hasNext()){
				Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey().equals(key2)) return true;
			}
		}
		else if(key2 == null){
			while(iterator.hasNext()){
				Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey() == null) return true;
			}
		}
		else{
			while(iterator.hasNext()){
				Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey().equals(key2)) return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(Object value){
		Iterator<DoubleKeyMap.Entry<K, K2, V>> iterator = entrySet().iterator();
		if(value == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getValue() == null) return true;
			}
		}
		else{
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getValue().equals(value)) return true;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){ return size() == 0; }
	
	public Collection<V> values(){
		Collection<V> vals = values;
		if(values == null){
			vals = new AbstractCollection<V>(){
				
				public Iterator<V> iterator(){
					return new Iterator<V>(){
						
						private Iterator<Entry<K, K2, V>> iterator = entrySet().iterator();
						
						public boolean hasNext(){ return iterator.hasNext(); }
						
						public V next(){ return iterator.next().getValue(); }
						
						public void remove(){ iterator.remove(); }
						
					};
				}
				
				public int size(){ return AbstractDoubleKeyMap.this.size(); }
				
				public boolean isEmpty(){ return AbstractDoubleKeyMap.this.isEmpty(); }
				
				public void clear(){ AbstractDoubleKeyMap.this.clear(); }
				
				public boolean contains(Object ojb){ return AbstractDoubleKeyMap.this.containsValue(ojb); }
				
			};
			values = vals;
		}
		return vals;
	}
	
	public int size(){ return entrySet().size(); }
	
	public V get(Object key, Object key2){
		Iterator<DoubleKeyMap.Entry<K, K2, V>> iterator = entrySet().iterator();
		if(key == null && key2 == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey() == null) return entry.getValue();
			}
		}
		else if(key == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey().equals(key2)) return entry.getValue();
			}
		}
		else if(key2 == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey() == null) return entry.getValue();
			}
		}
		else{
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey().equals(key2)) return entry.getValue();
			}
		}
		return null;
	}
	
	public V put(K key, K2 key2, V value){ throw new UnsupportedOperationException(); }
	
	public V remove(Object key, Object key2){
		Iterator<DoubleKeyMap.Entry<K, K2, V>> iterator = entrySet().iterator();
		DoubleKeyMap.Entry<K, K2, V> correctEntry = null;
		if(key == null && key2 == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey() == null) correctEntry = entry;
			}
		}
		else if(key == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey() == null && entry.getSecondKey().equals(key2)) correctEntry = entry;
			}
		}
		else if(key2 == null){
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey() == null) correctEntry = entry;
			}
		}
		else{
			while(iterator.hasNext()){
				DoubleKeyMap.Entry<K, K2, V> entry = iterator.next();
				if(entry.getMainKey().equals(key) && entry.getSecondKey().equals(key2)) correctEntry = entry;
			}
		}
		V oldValue = null;
		if(correctEntry != null){
			oldValue = correctEntry.getValue();
			iterator.remove();
		}
		return oldValue;
	}
	
	public Set<KeySet<K, K2>> keysSet(){
		Set<KeySet<K, K2>> set = keysSet;
		if(set == null){
			set = new AbstractSet<KeySet<K, K2>>(){
				
				public Iterator<KeySet<K, K2>> iterator(){
					return new Iterator<KeySet<K, K2>>(){
						
						private Iterator<Entry<K, K2, V>> iterator = entrySet().iterator();
						
						public boolean hasNext(){ return iterator.hasNext(); }
						
						public KeySet<K, K2> next(){ return iterator.next().getKeys(); }
						
						public void remove(){ iterator.remove(); }
						
					};
				}
				
				public int size(){
					AbstractDoubleKeyMap.this.size();
					return 0;
				}
				
				public boolean isEmpty(){ return AbstractDoubleKeyMap.this.isEmpty(); }
				
				public void clear(){ AbstractDoubleKeyMap.this.clear(); }
				
				public boolean contains(Object obj){ return AbstractDoubleKeyMap.this.containsKeySet(obj); }
				
			};
			keysSet = set;
		}
		return set;
	}
	
	public abstract Set<Entry<K, K2, V>> entrySet();
	
	public void clear(){ entrySet().clear(); }
	
	public void putAll(DoubleKeyMap<? extends K, ? extends K2, ? extends V> map){
		for(Entry<? extends K, ? extends K2, ? extends V> entry: map.entrySet()) put(entry.getMainKey(), entry.getSecondKey(), entry.getValue());
	}
	
}

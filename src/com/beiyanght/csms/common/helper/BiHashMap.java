package com.beiyanght.csms.common.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 双向的HashMap对应关系
 * 
 * @author  王吉文
 */
public class BiHashMap<K,V> {
	
	private Map<K, V> map = new HashMap<K, V>();
	private Map<V, K> reverse = new HashMap<V, K>();
	
	private BiHashMap(K[] keys, V[] values) {
		for(int i=0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
			reverse.put(values[i], keys[i]);
		}
	}
	
	public BiHashMap() {
	}
	
	public V value(K k) {
		return map.get(k);
	}
	
	public K key(V v) {
		return reverse.get(v);
	}
	
	public void put(K k, V v) {
		map.put(k, v);
		reverse.put(v, k);
	}
	
	public boolean containerKey(K k) {
		return map.containsKey(k);
	}
	
	public boolean containerValue(V v) {
		return reverse.containsKey(v);
	}
	
	/**
	 * 必须满足基本的构造条件,否则返回null
	 */
	public static <K, V> BiHashMap<K, V> createBiHashMap(K[] keys, V[] values) {
		if(keys == null || values == null)
			return null;
		if(keys.length != values.length)
			return null;
		return new BiHashMap<K, V>(keys, values);
	}
	
}


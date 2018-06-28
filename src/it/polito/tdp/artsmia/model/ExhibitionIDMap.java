package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

public class ExhibitionIDMap {
	
	private Map<Integer, Exhibition> map;
	
	public ExhibitionIDMap() {
		this.map = new HashMap<>();
	}
	
	public Exhibition get(int id) {
		return map.get(id);
	}
	
	public Exhibition get(Exhibition e) {
		Exhibition old = map.get(e.getId());
		if (old == null) {
			map.put(e.getId(), e);
			return e;
		}
		return old;
	}
	
	public void put(int id, Exhibition e) {
		map.put(id, e);
	}

}

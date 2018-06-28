package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;

public class ObjectIDMap {

	private Map<Integer, ArtObject> map;
	
	public ObjectIDMap() {
		this.map = new HashMap<>();
	}
	
	public ArtObject get(int id) {
		return map.get(id);
	}
	
	public ArtObject get(ArtObject ao) {
		ArtObject old = map.get(ao.getObjectId());
		if (old == null) {
			map.put(ao.getObjectId(), ao);
			return ao;
		}
		return old;
	}
	
	public void put(int id, ArtObject ao) {
		map.put(id, ao);
	}
	
}

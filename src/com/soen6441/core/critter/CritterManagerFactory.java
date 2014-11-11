package com.soen6441.core.critter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soen6441.core.tower.TowerManager;

public class CritterManagerFactory {

	private List<String> typeNames;
	private Map<String, CritterManager> managers = new HashMap<String, CritterManager>();
	
	public static CritterManagerFactory defaultFactory(){
		return null;
	}

	public List<String> getTypeNames() {
		return typeNames;
	}

	public void getManager(String typeName){
		
	}
}

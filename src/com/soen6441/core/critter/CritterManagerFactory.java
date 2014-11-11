package com.soen6441.core.critter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soen6441.core.tower.TowerManager;
import com.soen6441.core.tower.TowerManagerFactory;

public class CritterManagerFactory {

	private List<String> typeNames;
	private Map<String, String> fileNames;
	
	private Map<String, CritterManager> managers = new HashMap<String, CritterManager>();
	
	private static CritterManagerFactory defaultFactory;
	

	
	public static CritterManagerFactory defaultFactory(){
		if (defaultFactory == null){
			defaultFactory = new CritterManagerFactory();
		}
		return defaultFactory;
	}

	private CritterManagerFactory() {
		
		typeNames = new ArrayList<String>(Arrays.asList("Critter 1","Critter 2"));
		fileNames = new HashMap<String, String>();
		fileNames.put("Critter 1", "critter_1");
		fileNames.put("Critter 2", "critter_2");
		
		
		for(int i=0; i<typeNames.size(); i++) {
			CritterManager critterManager = new CritterManager(typeNames.get(i), System.getProperty("user.dir")+ "/data/"+ fileNames.get(typeNames.get(i)) +".xml");
	
			managers.put(typeNames.get(i), critterManager);
		}
		
	}
	public List<String> getTypeNames() {
		return typeNames;
	}

	public void getManager(String typeName){
		
	}
}

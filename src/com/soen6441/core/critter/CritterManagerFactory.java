package com.soen6441.core.critter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class CritterManagerFactory {

	/*
	 * Mark - Singleton - Properties
	 */
	
	private static CritterManagerFactory defaultFactory;
	
	/*
	 * Mark - Singleton - Methods
	 */
	
	/**
	 * Method defaultFactory.
	 * @return CritterManagerFactory
	 */
	public static CritterManagerFactory defaultFactory(){
		if (defaultFactory == null){
			defaultFactory = new CritterManagerFactory();
		}
		return defaultFactory;
	}
	
	/*
	 * Mark - Constructors
	 */
	
	private CritterManagerFactory() {
		
		typeNames = new ArrayList<String>(Arrays.asList("Critter 1","Critter 2", "Critter 3"));
		fileNames = new HashMap<String, String>();
		fileNames.put("Critter 1", "critter_1");
		fileNames.put("Critter 2", "critter_2");
		fileNames.put("Critter 3", "critter_3");
		
		
		for(int i = 0; i < typeNames.size(); i++) {
			CritterManager critterManager = new CritterManager(typeNames.get(i), System.getProperty("user.dir") + "/data/" + fileNames.get(typeNames.get(i)) + ".xml");
			managers.put(typeNames.get(i), critterManager);
		}
		
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	 
	private List<String> typeNames;
	private Map<String, String> fileNames;
	
	private Map<String, CritterManager> managers = new HashMap<String, CritterManager>();
	
	/*
	 * Mark - Basic - Methods
	 */
	 
	/**
	 * Method getManager.
	 * @param typeName String
	 * @return CritterManager
	 */
	public CritterManager getManager(String typeName){
		return managers.get(typeName);
	}
}

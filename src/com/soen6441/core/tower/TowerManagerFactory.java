package com.soen6441.core.tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to define TowerManagers.
 * This class uses singleton pattern.
 * While the first time using this class, it will generate all kinds of TowerManagers and store them in a map.
 * When there is a need to create or upgrade a tower, this class should be called first, 
 * in order to get a particular TowerManager. 
 * 
 * @see TowerManager
 * @see Tower
 * 
 * @author Haiyang Sun
 *
 * @version $Revision: 1.0 $
 */

public class TowerManagerFactory {
	
	/*
	 * Mark - Singleton
	 */
	
	private static TowerManagerFactory defaultFactory;
	
	/**
	 * Method currentManagerFactory.
	 * 
	 * @return TowerManagerFactory  
     */ 
	public static TowerManagerFactory defaultFactory() {
		
		if (defaultFactory == null){
			defaultFactory = new TowerManagerFactory();
		}
		return defaultFactory;
		
	}
	
	/**
	 * Constructor.
	 * Declare all kinds of tower type names, find the XML data file path by type names, 
	 * then create TowerManagers by both of them. 
	 */
	private TowerManagerFactory() {
		
		typeNames = new ArrayList<String>(Arrays.asList("BottleTower","IceTower","SunTower","MoonTower","FireTower","EnergyTower"));
		
		for(int i=0; i<typeNames.size(); i++) {
			TowerManager towerManager = new TowerManager(typeNames.get(i), System.getProperty("user.dir")+ "/data/tower_"+typeNames.get(i).toLowerCase()+".xml");
			managers.put(typeNames.get(i), towerManager);
		}
		
	}

	/*
	 * Mark - Basic - Properties
	 */
	
	/**
	 * The array is used to store type names.
	 */
	private List<String> typeNames;
	
	
	/**
	 * The map is used to store TowerManager objects and its names.
	 */
	private Map<String, TowerManager> managers = new HashMap<String, TowerManager>();
	
	/* 
	 * Mark - Basic - Methods
	 */
	
	/**
	 * This method is used to get a certain type of TowerManager by its type name.
	 * 
	 * @param typeName
	 * @return Return a TowerManager object  
     */
	
	public TowerManager getManager(String typeName) {
		return managers.get(typeName);
	}

	/**
	 * Method getTypeNames.
	 * @return List<String> 
     */
	public List<String> getTypeNames() {
		return typeNames;
	}

}

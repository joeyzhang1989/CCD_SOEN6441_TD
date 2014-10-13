package com.soen6441.core.tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class is used to define TowerManager.
 * 
 * @author Haiyang Sun
 *
 */

public class TowerManagerFactory {
	
	private static TowerManagerFactory currentManagerFactory;
	
	public static TowerManagerFactory currentManagerFactory() {
		
		if (currentManagerFactory == null){
			currentManagerFactory = new TowerManagerFactory();
		}
		return currentManagerFactory;
		
	}
	
	/**
	 * Use this method to set the shared instance to null
	 */
	public static void destroy(){
		currentManagerFactory = null;
	}
	
	/**
	 * Close the access to the general constructor
	 */
	
	private TowerManagerFactory()
	{
		
		typeNames = new ArrayList<String>(Arrays.asList("BottleTower","MudTower"));
		
		for(int i=0; i<typeNames.size(); i++) {
			TowerManager towerManager = new TowerManager(typeNames.get(i), System.getProperty("user.dir")+ "/data/tower_"+typeNames.get(i)+".xml");
			managers.put(typeNames.get(i), towerManager);
		}
		
	}
	/**
	 * The array is used to store type names.
	 */
	public static List<String> typeNames;
	
	
	/**
	 * The map is used to store TowerManager objects and its names.
	 */
	private Map<String, TowerManager> managers;
	
	
	/**
	 * 
	 * @return TowerManagerFactory
	 */

	public static TowerManagerFactory defaultFactory() {
		return null;
	}
	
	/**
	 * This method is used to get a certain type of TowerManager by its type name.
	 * 
	 * @param typeName
	 * @return Return a TowerManager object
	 */
	
	public TowerManager getManager(String typeName) {
		return managers.get(typeName);
	}

}

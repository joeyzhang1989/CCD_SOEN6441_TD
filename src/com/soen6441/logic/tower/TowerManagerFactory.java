package com.soen6441.logic.tower;

import java.util.List;
import java.util.Map;

/**
 * This class is used to define TowerManager.
 * 
 * @author Haiyang Sun
 *
 */

public class TowerManagerFactory {
	
	/**
	 * The array is used to store type names.
	 */
	public List<String> typeNames;
	
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
		return null;
	}

}

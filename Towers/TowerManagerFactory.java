package com.soen6441.Towers;

import java.util.Map;

public class TowerManagerFactory {

	public static TowerManagerFactory defaultFactory() {
		return null;
	}
	
	public String[] typeNames;
	
	private Map<String, TowerManager> managers;
	
	public TowerManager getManager(String typeName) {
		return null;
	}

}

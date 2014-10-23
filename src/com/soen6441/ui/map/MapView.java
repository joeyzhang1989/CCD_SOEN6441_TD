package com.soen6441.ui.map;

import com.soen6441.core.map.GridMap;
import com.soen6441.ui.common.GridView;

/**
 * This class defines the view of the map
 * 
 * @author Zhe Zhao
 * @author jean raymond daher
 * @author Chenglong Zhang
 * @since 0.1
 * 
 */
public class MapView extends GridView {

	private GridMap map;
	private static final int _UNIT_LENGTH = 40;
	
	
	/**
	 * Method getMap.
	 * @return GridMap
	
	/*
	 * Getters & Setters
	 */
	public GridMap getMap() {
		return map;
	}

	/**
	 * Method setMap.
	 * @param map GridMap
	 */
	public void setMap(GridMap map) {
		this.map = map;
	}


	/**
	 * Method suggestedSize.
	 * @return int
	 */
	public int suggestedSize() {
		return 0;
	}

}

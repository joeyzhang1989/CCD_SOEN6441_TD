package com.soen6441.ui.map;

import com.soen6441.core.map.GridMap;
import com.soen6441.ui.common.GridView;

/**
<<<<<<< Updated upstream
 * @author Jean Raymond Daher
=======
 * This class defines the view of the map
 * 
 * @author jean raymond daher
>>>>>>> Stashed changes
 * @author Chenglong Zhang
 * @since 0.1
 * 
 */
public class MapView extends GridView {

	private GridMap map;
	private MapViewListener eventListener;
<<<<<<< Updated upstream

	/**
	 * Method getMap.
	 * @return GridMap
=======
	
	/*
	 * Getters & Setters
>>>>>>> Stashed changes
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
	 * Method getEventListener.
	 * @return MapViewListener
	 */
	public MapViewListener getEventListener() {
		return eventListener;
	}

	/**
	 * Method setEventListener.
	 * @param eventListener MapViewListener
	 */
	public void setEventListener(MapViewListener eventListener) {
		this.eventListener = eventListener;
	}

	/**
	 * Method suggestedSize.
	 * @return int
	 */
	public int suggestedSize() {

		return 0;

	}

}

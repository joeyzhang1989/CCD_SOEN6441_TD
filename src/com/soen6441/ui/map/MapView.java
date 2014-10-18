package com.soen6441.ui.map;

import com.soen6441.core.map.GridMap;
import com.soen6441.ui.common.GridView;

//not finish
/**
 * @author Jean Raymond Daher
 * @author Chenglong Zhang
 */
public class MapView extends GridView {

	// here is the main map of the game
	// this class should draw the map view of the game
	// a place for the grid , for the inspector also

	private GridMap map;

	private MapViewListener eventListener;

	/**
	 * Method getMap.
	 * @return GridMap
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

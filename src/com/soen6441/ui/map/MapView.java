package com.soen6441.ui.map;

import com.soen6441.core.map.GridMap;
import com.soen6441.ui.common.GridView;

//not finish
/**
 * @author chenglong zhang chenglongzhang931@gmail.com , jean raymond daher
<<<<<<< HEAD
 * @since  0.1
=======
>>>>>>> FETCH_HEAD
 */
public class MapView extends GridView{

	//here is the main map of the game
	//this class should draw the map view of the game 
	//a place for the grid , for the inspector also
	
	
	private GridMap map;
	private MapViewListener eventListener;
	
	public GridMap getMap() {
		return map;
	}
	
	public void setMap(GridMap map) {
		this.map = map;
	}
	
	public MapViewListener getEventListener() {
		return eventListener;
	}
	
	public void setEventListener(MapViewListener eventListener) {
		this.eventListener = eventListener;
	}
	
}

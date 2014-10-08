package com.soen6441.ui.map;

import com.soen6441.logic.map.GridMap;
import com.soen6441.ui.common.GridView;

//not finish
/**
 * @author chenglong zhang chenglongzhang931@gmail.com , jean raymond daher
 * @since version 1.0
 */
public class MapView extends GridView{

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

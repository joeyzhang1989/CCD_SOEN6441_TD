package com.soen6441.ui.map;

import java.awt.Dimension;

import com.soen6441.core.map.GridMap;
import com.soen6441.core.map.MapItem;
import com.soen6441.core.map.MapPoint;
import com.soen6441.core.play.Play;
import com.soen6441.ui.common.GridPoint;
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
	private MapViewListener eventListener;
	private static final int _UNIT_LENGTH  = 40;
	
	public MapView() {
		Play play = Play.currentPlay();
		play.buildDemo();
		this.setMap(play.getMap());
	}

	/*
	 * Getters & Setters
	 */
	
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
		initialSetup();
	}
	
	private void initialSetup(){
		this.setUnitWidth(_UNIT_LENGTH );
		this.setUnitHeight(_UNIT_LENGTH );
		
		for (int i = 0; i < map.getWidth(); i ++) {
			for (int j = 0; j < map.getHeight(); j++) {
				MapItem item = map.getItem(new MapPoint(i, j));
				MapItemCell cell = MapItemCellFactory.cellFromItem(item);
				cell.setSize(new Dimension(_UNIT_LENGTH , _UNIT_LENGTH ));
				this.addCell(cell, new GridPoint(j, i));
			}
		}
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
	public Dimension suggestedSize() {
		return new Dimension(map.getWidth() * _UNIT_LENGTH , map.getHeight() * _UNIT_LENGTH );
	}

}
